const BigchainDB = require('bigchaindb-driver')
const bip39 = require('bip39')
const seed = bip39.mnemonicToSeedSync(bip39.generateMnemonic()).slice(0,32)
const alice = new BigchainDB.Ed25519Keypair(seed)

// Define the Asset Field
const painting = {
    name: 'Meninas',
    author: 'Diego Rodríguez de Silva y Velázquez',
    place: 'Madrid',
    year: '1656'
}

// Generate a CREATE transaction to link the defined asset to the user Alice
// Three Steps: create, sign, and then send
function createPaint() {
    // Create a transaction payload
    const txCreatePaint = BigchainDB.Transaction.makeCreateTransaction(
        // Asset field
        {
            painting,
        },
        // Metadata field, contains information about the transaction itself (can be null if not needed)
        {
            datetime: new Date().toString(),
            location: 'Madrid',
            value: {
                value_eur: '25000000€',
                value_btc: '2200',
            }
        },
        // Output, for this case we create a simple Ed25519 condition
        [BigchainDB.Transaction.makeOutput(
            BigchainDB.Transaction.makeEd25519Condition(alice.publicKey))],
        // Issuers
        alice.publicKey
    )

    // The owner of the painting signs the transaction
    const txSigned = BigchainDB.Transaction.signTransaction(txCreatePaint,
        alice.privateKey)

    // Send the transaction off to BigchainDB
    conn.postTransactionCommit(txSigned)
        .then(res => {
            document.body.innerHTML += '<h3>Transaction created</h3>';
            document.body.innerHTML += txSigned.id
            // txSigned.id corresponds to the asset id of the painting
        })
}

//TRANSFER Transaction, need to create a new key pair for a new owner first
const newOwner = new BigchainDB.Ed25519Keypair(seed)
function transferOwnership(txCreatedID, newOwner) {
    // Get transaction payload by ID
    conn.getTransaction(txCreatedID)
        .then((txCreated) => {
            const createTranfer = BigchainDB.Transaction.
                makeTransferTransaction(
                    // The output index 0 is the one that is being spent
                    [{
                        tx: txCreated,
                        output_index: 0
                    }],
                    [BigchainDB.Transaction.makeOutput(
                        BigchainDB.Transaction.makeEd25519Condition(
                            newOwner.publicKey))],
                    {
                        datetime: new Date().toString(),
                        value: {
                            value_eur: '30000000€',
                            value_btc: '2100',
                        }
                    }
                )
            // Sign with the key of the owner of the painting (Alice)
            const signedTransfer = BigchainDB.Transaction
                .signTransaction(createTranfer, alice.privateKey)
            return conn.postTransactionCommit(signedTransfer)
        })
        .then(res => {
            document.body.innerHTML += '<h3>Transfer Transaction created</h3>'
            document.body.innerHTML += res.id
        })
}
