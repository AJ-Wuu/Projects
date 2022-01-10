const BigchainDB = require('bigchaindb-driver')
const bip39 = require('bip39')
const seed = bip39.mnemonicToSeedSync(bip39.generateMnemonic()).slice(0,32)
const API_PATH = 'https://test.ipdb.io/api/v1/'
const conn = new BigchainDB.Connection(API_PATH)

const nTokens = 10000
let tokensLeft
const tokenCreator = new BigchainDB.Ed25519Keypair(seed)
let createTxId
const amountToSend = 200

function tokenLaunch() {
    // Construct a transaction payload
    const tx = BigchainDB.Transaction.makeCreateTransaction({
            token: 'TT (Tutorial Tokens)',
            number_tokens: nTokens
        },
        // Metadata field, contains information about the transaction itself
        // (can be `null` if not needed)
        {
            datetime: new Date().toString()
        },
        // Output: Divisible asset, include nTokens as parameter
        [BigchainDB.Transaction.makeOutput(BigchainDB.Transaction
          .makeEd25519Condition(tokenCreator.publicKey), nTokens.toString())],
        tokenCreator.publicKey
    )

    // Sign the transaction with the private key of the token creator
    const txSigned = BigchainDB.Transaction
      .signTransaction(tx, tokenCreator.privateKey)

    // Send the transaction off to BigchainDB
    conn.postTransactionCommit(txSigned)
        .then(res => {
            createTxId = res.id
            tokensLeft = nTokens
            document.body.innerHTML ='<h3>Transaction created</h3>';
            // txSigned.id corresponds to the asset id of the tokens
            document.body.innerHTML +=txSigned.id
        })
}

const newUser = new BigchainDB.Ed25519Keypair(seed)
function transferTokens() {
    // User who will receive the 200 tokens
    const newUser = new BigchainDB.Ed25519Keypair()

    // Search outputs of the transactions belonging the token creator
    // False argument to retrieve unspent outputs
    conn.getTransaction(createTxId)
        .then((txOutputs) => {
            // Create transfer transaction
            const createTranfer = BigchainDB.Transaction
                .makeTransferTransaction(
                    [{
                        tx: txOutputs,
                        output_index: 0
                    }],
                    // Transaction output: Two outputs, because the whole input
                    // must be spent
                    [BigchainDB.Transaction.makeOutput(
                            BigchainDB.Transaction
                            .makeEd25519Condition(tokenCreator.publicKey),
                            (tokensLeft - amountToSend).toString()),
                        BigchainDB.Transaction.makeOutput(
                            BigchainDB.Transaction
                            .makeEd25519Condition(newUser.publicKey),
                            amountToSend)
                    ],
                    // Metadata (optional)
                    {
                        transfer_to: 'john',
                        tokens_left: tokensLeft
                    }
                )

            // Sign the transaction with the tokenCreator key
            const signedTransfer = BigchainDB.Transaction
                .signTransaction(createTranfer, tokenCreator.privateKey)

            return conn.postTransactionCommit(signedTransfer)
        })
        .then(res => {
            // Update tokensLeft
            tokensLeft -= amountToSend
            document.body.innerHTML += '<h3>Transfer transaction created</h3>'
            document.body.innerHTML += res.id
        })

}

const bestFriend = new BigchainDB.Ed25519Keypair()
function combineTokens(transaction1, outputIndex1, transaction2, outputIndex2,
    totalTokens) {
    const combineTranfer = BigchainDB.Transaction.makeTransferTransaction(
        [{
            tx: transaction1,
            output_index: outputIndex1
        }, {
            tx: transaction2,
            output_index: outputIndex2
        }],
        // Output
        [BigchainDB.Transaction.makeOutput(
            BigchainDB.Transaction.makeEd25519Condition(
                bestFriend.publicKey),
            (totalTokens).toString())], {
            transfer_to: 'my best friend'
        }
    )

    // Sign the transaction with the newUser key
    const signedTransfer = BigchainDB.Transaction
        .signTransaction(combineTranfer, newUser.privateKey)

    return conn.postTransactionCommit(signedTransfer)

}
