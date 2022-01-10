const BigchainDB = require('bigchaindb-driver')
const API_PATH = 'https://test.ipdb.io/api/v1/'
const bip39 = require('bip39')
const seed = bip39.mnemonicToSeedSync(bip39.generateMnemonic()).slice(0,32)
const Orm = require('bigchaindb-orm')
//npm install --save @babel/runtime babel-runtime

class DID extends Orm {
    // create DID class
    // Decentralized identifiers (DID) are identifiers intended for verifiable digital identity that is “self-sovereign”.
    // They do not dependent on a centralized registry, identity provider, or certificate authority.
    constructor(entity) {
        super(API_PATH)
        this.entity = entity //public key of the object itself
    }
}

const alice = new BigchainDB.Ed25519Keypair(seed)
const car = new BigchainDB.Ed25519Keypair(seed)
const sensorGPS = new BigchainDB.Ed25519Keypair(seed)

const userDID = new DID(alice.publicKey)
const carDID = new DID(car.publicKey)
const gpsDID = new DID(sensorGPS.publicKey)

userDID.define("myModel", "https://schema.org/v1/myModel")
carDID.define("myModel", "https://schema.org/v1/myModel")
gpsDID.define("myModel", "https://schema.org/v1/myModel")

// Digital Registration of Assets
userDID.myModel.create({
    // create new asset
    keypair: alice,
    data: {
        name: 'Alice',
        bithday: '08/16/1999'
    }
}).then(asset => {
    userDID.id = 'did:' + asset.id
    document.body.innerHTML += '<h3>Transaction created</h3>'
    document.body.innerHTML += asset.id
})

const vehicle = { // define the asset field that represents a car
    value: '6sd8f68sd67',
    power: {
        engine: '2.5',
        hp: '220 hp',
    },
    consumption: '10.8 l'
}

carDID.myModel.create({
    keypair: alice, // Alice's key pair is needed as she is the owner
    data: {
        vehicle
    }
}).then(asset => {
    carDID.id = 'did:' + asset.id
    document.body.innerHTML += '<h3>Transaction created</h3>'
    document.body.innerHTML += txTelemetrySigned.id
})

gpsDID.myModel.create({
    keypair: car, // car is the owner of GPS
    data: {
        gps_identifier: 'a32bc2440da012'
    }
}).then(asset => {
    gpsDID.id = 'did:' + asset.id
    document.body.innerHTML += '<h3>Transaction created</h3>'
    document.body.innerHTML += txTelemetrySigned.id
})

// Update an Asset
function updateMileage(did, newMileage) {
    did.myModel
        .retrieve(did.id)
        .then(assets => {
            // assets is an array of myModel
            // the retrieve asset contains the last (unspent) state of the asset
            return assets[0].append({
                toPublicKey: car.publicKey,
                keypair: car,
                data: { newMileage }
            })
        })
        .then(updatedAsset => {
            did.mileage = updatedAsset.data.newMileage
            document.body.innerHTML += '<h3>Append transaction created</h3>'
            document.body.innerHTML += txTelemetrySigned.id
            return updatedAsset
        })
}
