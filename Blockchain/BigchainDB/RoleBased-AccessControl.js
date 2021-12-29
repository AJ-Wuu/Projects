const BigchainDB = require('bigchaindb-driver');
const API_PATH = 'https://test.ipdb.io/api/v1/';
const connect = new BigchainDB.Connection(API_PATH);
const nameSpace = 'rbac-bdb-tutorial';

async function createNewAsset(keypair, asset, metadata) {
    let condition = BigchainDB.Transaction.makeEd25519Condition(keypair.publicKey, true);
    let output = BigchainDB.Transaction.makeOutput(condition);
    output.public_keys = [keypair.publicKey];
    const transaction = BigchainDB.Transaction.makeCreateTransaction(
        asset,
        metadata,
        [output],
        keypair.publicKey
    );

    const txSigned = BigchainDB.Transaction.signTransaction(transaction, keypair.privateKey);
    let tx
    await connect.postTransactionCommit(txSigned)
        .then(retrievedTx => {
            tx = retrievedTx
        });
    return tx;
}

async function createApp() {
    // Generate keypair for admin instance
    const admin1 = new BigchainDB.Ed25519Keypair();

    // Create admin user type. This is the asset representing the group of admins
    const adminGroupAsset = {
        ns: `${nameSpace}.admin`,
        name: 'admin'
    }
    const adminGroupMetadata = {
        canLink: [admin1.publicKey]
    }

    const adminGroupId = (await createNewAsset(admin1, adminGroupAsset,
        adminGroupMetadata)).id
    document.body.innerHTML = '<h3>Admin Group asset created</h3>'
    document.body.innerHTML += adminGroupId.id


    // Create admin user instance. This is a single user with admin role
    // represented by an asset. Is the asset representing admin1 user
    // Create app asset with admin1, the umbrella asset for representing the app
    const appAsset = {
        ns: nameSpace,
        name: nameSpace
    }
    const appMetadata = {
        canLink: adminGroupId
    }

    const appId = (await createNewAsset(admin1, appAsset, appMetadata)).id
    console.log('App: ' + appId)
}

function createUsers() {

    const user1 = new BigchainDB.Ed25519Keypair()
    const user2 = new BigchainDB.Ed25519Keypair()
    const user3 = new BigchainDB.Ed25519Keypair()

    const adminuser1Metadata = {
        event: 'User Assigned',
        date: new Date(),
        timestamp: Date.now(),
        publicKey: admin1.publicKey,
        eventData: {
            userType: 'admin'
        }
    }

    // Admin user instance belongs to the AdminGroup
    const adminUserId = (await createUser(admin1, adminGroupId, 'admin',
        admin1.publicKey, adminuser1Metadata)).id
    document.body.innerHTML = '<h3>Admin user asset created</h3>'
    document.body.innerHTML += adminUserId

    // Tribes are user groups
    const tribe1Id = (await createType('tribe1', appId, adminGroupId)).id
    document.body.innerHTML = '<h3>Tribe 1 asset created</h3>'
    document.body.innerHTML += tribe1Id

    const tribe2Id = (await createType('tribe2', appId, adminGroupId)).id
    document.body.innerHTML = '<h3>Tribe 2 asset created</h3>'
    document.body.innerHTML += tribe2Id

    // create user instances
    const user1Metadata = {
        event: 'User Assigned',
        date: new Date(),
        timestamp: Date.now(),
        publicKey: admin1.publicKey,
        eventData: {
            userType: 'tribe1'
        }
    }
    // Create the asset representing user1 with the admin1 keys.
    // Add it to tribe 1
    const user1AssetId = (await createUser(admin1, tribe1Id, 'tribe1',
        user1.publicKey, user1Metadata)).id
    document.body.innerHTML = '<h3>User 1 asset created</h3>'
    document.body.innerHTML += user1AssetId

    // create user instances
    const user2Metadata = {
        event: 'User Assigned',
        date: new Date(),
        timestamp: Date.now(),
        publicKey: admin1.publicKey,
        eventData: {
            userType: 'tribe2'
        }
    }

    // user 2 added to tribe 2
    // Is the asset representing user1
    const user2AssetId = (await createUser(admin1, tribe2Id, 'tribe2',
        user2.publicKey, user2Metadata)).id
    document.body.innerHTML = '<h3>User 2 asset created</h3>'
    document.body.innerHTML += user2AssetId

    const user3Metadata = {
        event: 'User Assigned',
        date: new Date(),
        timestamp: Date.now(),
        publicKey: admin1.publicKey,
        eventData: {
            userType: 'tribe1'
        }
    }

    // user 3 added to tribe 1
    const user3AssetId = (await createUser(admin1, tribe1Id, 'tribe1',
        user3.publicKey, user3Metadata)).id
    document.body.innerHTML = '<h3>User 3 asset created</h3>'
    document.body.innerHTML += user3AssetId
}

async function usersToTribes() {
    // Non users
    // Proposal: only tribe 1 users can create proposal
    const proposalGroupId = (await createType('proposal', appId, tribe1Id)).id
    console.log('ProposalGroup: ' + proposalGroupId)

    // Vote: only tribe 2 users can create vote
    const voteGroupId = (await createType('vote', appId, tribe2Id)).id
    document.body.innerHTML = '<h3>Vote group asset created</h3>'
    document.body.innerHTML += voteGroupId
    // create proposal by user 1 - should pass
    const proposal1 = await createTypeInstance(user1, 'proposal',
        proposalGroupId, {
        name: 'new proposal by user 1',
        timestamp: Date.now()
    })
    document.body.innerHTML = '<h3>Proposal group asset created</h3>'
    document.body.innerHTML += proposal1.id

    // create vote by user 2 - should pass
    const vote1 = await createTypeInstance(user2, 'vote', voteGroupId,
        { name: 'new vote by user 2', timestamp: Date.now() })
    document.body.innerHTML = '<h3>Vote instance created</h3>'
    document.body.innerHTML += vote1.id

    // create proposal by user 3 - should pass
    const proposal2 = await createTypeInstance(user3, 'proposal',
        proposalGroupId, {
        name: 'new proposal by user 3',
        timestamp: Date.now()
    })
    document.body.innerHTML = '<h3>Vote instance created</h3>'
    document.body.innerHTML += proposal2.id

    // create vote by user 1 - should fail
    const vote2 = await createTypeInstance(user1, 'vote',
        voteGroupId, { name: 'new vote by user 1', timestamp: Date.now() })
    document.body.innerHTML = '<h3>Vote instance could not be created</h3>'
    document.body.innerHTML += vote2.id
}
