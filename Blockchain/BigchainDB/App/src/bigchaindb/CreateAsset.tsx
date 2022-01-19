import * as BigchainDB from 'bigchaindb-driver';
import BigchainDBConnection from './Connection';

const newAsset = {
    TxID: "",
    tokensLeft: 0
}

const createAsset = async (alice: any, asset: object, tokenNumbers: number, metadata: object):Promise<any> => {
    const txCreateAsset = BigchainDB.Transaction.makeCreateTransaction(
        { 
            token: asset,
            tokenNumbers: tokenNumbers
        },
        { metadata },
        [BigchainDB.Transaction.makeOutput(
            BigchainDB.Transaction.makeEd25519Condition(alice.publicKey), tokenNumbers.toString())],
        alice.publicKey
    );
    const txSigned = BigchainDB.Transaction.signTransaction(txCreateAsset, alice.privateKey);
    try {
        await BigchainDBConnection.postTransaction(txSigned)
            .then(res => {
                newAsset.TxID = res.id;
                newAsset.tokensLeft = tokenNumbers;
                console.log(newAsset);
            })
    } catch (err) {
        console.log(err);
    }
}

export default createAsset;
