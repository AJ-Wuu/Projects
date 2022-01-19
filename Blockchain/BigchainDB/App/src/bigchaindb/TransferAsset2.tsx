import * as BigchainDB from 'bigchaindb-driver';
import BigchainDBConnection from './Connection';

const TransferAsset2 = async (alice: any, bobPublicKey: string, chosenTransaction: any, amountToSend: number, metadata: object) => {
    console.log(alice);
    console.log(bobPublicKey);
    console.log(chosenTransaction);
    console.log(amountToSend);
    console.log(metadata);
    var currTransaction = await BigchainDBConnection.getTransaction(chosenTransaction.id);
    console.log(currTransaction);
    const createTranfer = BigchainDB.Transaction.makeTransferTransaction(
        [{
            tx: currTransaction,
            output_index: 0
        }],
        [BigchainDB.Transaction.makeOutput(BigchainDB.Transaction.makeEd25519Condition(bobPublicKey))],
        { metadata }
    );
    console.log(createTranfer);
    const signedTransfer = BigchainDB.Transaction.signTransaction(createTranfer, alice.privateKey);
    console.log(signedTransfer);
    return BigchainDBConnection.postTransactionCommit(signedTransfer);
        /*
        .then(res => {
            chosenTransaction.data.tokenNumbers -= amountToSend;
        })*/
}

export default TransferAsset2;