import * as BigchainDB from 'bigchaindb-driver';
import BigchainDBConnection from './Connection';

const TransferAsset = async (alice: any, bobPublicKey: string, chosenTxID: string, amountToSend: number, metadata: object) => {
    BigchainDBConnection.getTransaction(chosenTxID)
        .then((chosenTransaction) => {
            console.log(chosenTransaction);
            const createTranfer = BigchainDB.Transaction.makeTransferTransaction(
                    [{
                        tx: chosenTransaction,
                        output_index: 0
                    }],
                    [BigchainDB.Transaction.makeOutput(BigchainDB.Transaction.makeEd25519Condition(bobPublicKey))],
                    { metadata }
                );
            console.log(createTranfer);
            const signedTransfer = BigchainDB.Transaction.signTransaction(createTranfer, alice.privateKey);
            console.log(signedTransfer);
            BigchainDBConnection.postTransaction(signedTransfer);
        })
        /*
        .then(res => {
            chosenTransaction.data.tokenNumbers -= amountToSend;
        })*/
}

export default TransferAsset;