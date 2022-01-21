import * as BigchainDB from 'bigchaindb-driver';
import BigchainDBConnection from './Connection';

var preMetadata;

const Transfer = async (alice: any, bobPublicKey: string, chosenTxID: string, amountToSend: number, metadata: any) => {
    var currTransaction = await BigchainDBConnection.getTransaction(chosenTxID);
    preMetadata = currTransaction.metadata;
    preMetadata.preOwner.push(metadata.preOwner[0]);
    preMetadata.newOwner.push(metadata.newOwner[0]);
    preMetadata.time = metadata.time;
    preMetadata.tokenNumber.push(metadata.tokenNumber[0]);
    preMetadata.notes = metadata.notes;
    const createTranfer = BigchainDB.Transaction.makeTransferTransaction(
        [{
            tx: currTransaction,
            output_index: 0
        }],
        [BigchainDB.Transaction.makeOutput(
            BigchainDB.Transaction.makeEd25519Condition(alice.publicKey), (currTransaction.asset.data.tokenNumbers - amountToSend).toString()),
         BigchainDB.Transaction.makeOutput(
            BigchainDB.Transaction.makeEd25519Condition(bobPublicKey), amountToSend.toString())
        ],
        preMetadata
    );
    console.log(createTranfer);
    const signedTransfer = BigchainDB.Transaction.signTransaction(createTranfer, alice.privateKey);
    console.log(signedTransfer);
    return BigchainDBConnection.postTransactionCommit(signedTransfer);
}

export default Transfer;