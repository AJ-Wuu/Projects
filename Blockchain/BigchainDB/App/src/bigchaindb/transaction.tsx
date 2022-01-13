import BigchainDB from 'bigchaindb-driver';
const API_PATH:string = process.env.BIGCHAINDB_API_PATH as string;

function createAsset(alice: any, asset: object, metadata: object) {
    console.log(API_PATH);
    const conn:BigchainDB.Connection = new BigchainDB.Connection('https://test.ipdb.io/api/v1/');
    const txCreateAsset = BigchainDB.Transaction.makeCreateTransaction(
        { asset }, 
        { metadata }, 
        [BigchainDB.Transaction.makeOutput(
            BigchainDB.Transaction.makeEd25519Condition(alice.publicKey))],
        alice.publicKey
    )
    const txSigned = BigchainDB.Transaction.signTransaction(txCreateAsset, alice.privateKey)
    conn.postTransactionCommit(txSigned)
        .then(res => {
            alert("Asset Created Successfully")
        })
}

export default createAsset;