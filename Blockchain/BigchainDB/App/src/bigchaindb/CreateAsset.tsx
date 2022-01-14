import * as BigchainDB from 'bigchaindb-driver';
import BigchainDBConnection from './Connection';

const createAsset = async (alice: any, asset: object, metadata: object):Promise<any> => {
    const txCreateAsset = BigchainDB.Transaction.makeCreateTransaction(
        { asset },
        { metadata },
        [BigchainDB.Transaction.makeOutput(
            BigchainDB.Transaction.makeEd25519Condition(alice.publicKey))],
        alice.publicKey
    );
    const txSigned = BigchainDB.Transaction.signTransaction(txCreateAsset, alice.privateKey);
    try {
        await BigchainDBConnection.postTransaction(txSigned);
    } catch (err) {
        console.log(err);
    }
}

export default createAsset;
