# What is blockchain?

At its most basic, a blockchain is a list of transactions that anyone can view and verify. The Bitcoin blockchain, for example, contains a record of every time someone sent or received bitcoin.

### An analogy, curtesy of coinbase.com
> Picture a chain you might use for a ship’s anchor. But in this case, every link on the chain is a chunk of information that contains transaction data. At the top of the chain you see what happened today, and as you move down the chain you see older and older transactions. And if you follow it all the way down to the anchor sitting at the bottom of the harbor? You’ll have seen every single transaction in the history of that cryptocurrency. Which gives the blockchain powerful security advantages: it’s an open, transparent record of a cryptocurrency’s entire history. If anyone tries to manipulate a transaction it will cause the link to break, and the entire network will see what happened. That, in a nutshell, is blockchain explained.

### A walk down the eternal, immutable life of a block on a blockchain.
*We will look at Bitcoin in this example, however the process is similar, if not the same, for any blockchain.*

Let's say you buy a bitcoin from someone. This transfer of a bitcoin is entered and transmitted to a large network of powerful computers called **Nodes**.

Each node maintains a copy of the blockchain. In the case of bitcoin, each node would have a copy of the list of every bitcoin transaction dating back to the dawn of time. Nodes are responsible for approving new transactions, an action that is called **mining**.

The act of mining is not complex. It is merely a guessing game. However, in the case of bitcoin, the odds that you guess correctly is one in the tens of trillions.

Once a node confirms a transaction, it is permanently added to a block on the blockchain. Once the majority of the network confirms this new block addition, it is permanently added to the front of the blockchain.

![](img1.png)

### If blockchains are simply a record of transactions, what are people actually transacting?
*Again, we will use bitcoin to answer this.*

Miners receive bitcoins for mining blocks on the blockchain. This cycle essentially mints new currency as existing currency is transacted. Bitcoins are essentially complex computer files that can be linked to one's address.

# Pros and Cons

### Pros
* **They are distributed.** This means that one could perform transactions relatively quickly and cheaply with any member of a blockchain network, regardless of geographic location. It also means that, for financial applications like Bitcoin, they are decetralized. This is amazing because no one organization/agency can determine the value of a cryptocurrency. Additionally, third party transactions are not needed. You exchange blockchain with an individual, with no entity looking over your shoulder.
* **Bigtime Privacy.** One does not have to provide any personal information to exchange cryptocurrencies. In the ledger, one would only see a hash that is derived from your public key, which is not a security risk and cannot be tied to you in any way.
* **Transparency.** Blockchains are published to every node in the network. This means every transaction is subject to scrutiny, making it hard to forge or manipulate transactions. Essentially, a Bitcoin user's wallet's transactions can be traced back to their first purchase, but the owner of the wallet cannot be determined. It's like how your gamertag does not reveal to other online players who your true identity is.
* **Automation.** Transactions can be automated with *smart contracts*. Once a condition specified within the contract is met, the next step in the transaction is automatically triggered. An example, if a customer has provided all necessry documentation to file an insurance claim, the claim can automatically be settled and paid.

### Cons
* **Energy consumption.** I read recently that the bitcoin network consumes as much energy as all of Switzerland. This is scary, especially as more and more attention gets paid to crypto. Perhaps an overlooked fault in the design of blockchains, a miner is essentially trading energy in for value by having their computers run, continuously trying to mine blocks.
* **51% attacks.** The reason bitcoin is considered decentralized is because there is no one body with a majority of the coins, or that owns a majority of the network. Thus, no one entity has any incentive to behave unethically. However, as soon as one entity owns 51% of a blockchain network, they are economically incentivised rewrite or alter blockchain records, since that entity could be a majority in approving these changes. Remember, a key step in the block generation process is gaining approval from the other nodes in the network.
* **Scalability.** One block is created every 10 minutes on the bitcoin network. This means that a potential transaction could take 10 minutes, minimum, to be recorded in the ledger. This provides major scalability issues, especially when you compare this to something like Visa, which processes thousands of transactions per second.
* **Memory issues.** As mentioned earlier, nodes in most blockchain networks are required to store the entire transaction history of the network before they can transact. In bitcoins case, this exceeds 100GB. What's worse is this 100GB is redundantly replicated across all nodes. This is why users typically do not try to use a locally stored wallet for crypto, opting for sites like coinbase.com. However, as much as they might like to disclaim it, using these sites essentially creates a third-party in any transaction performed. For a currency that claims to be totally peer-to-peer, this is somewhat of an issue.

# How are blockchains private?
A key aspect of privacy in blockchains is the use of private and public keys. These keys are random strings of numbers and are cryptographically related. However, it is mathematically impossible for a user to guess another user's private key from their public key. This provides an increase in security and protects users from hackers. Public keys can be shared with other users in the network because they give away no personal data. Each user has an address that is derived from the public key using a hash function. These addresses are used to send and receive assets on the blockchain, such as cryptocurrency.

A somewhat decent privacy analogy is reddit. Let's say you create a reddit account with no relation to your name or anything, say AnonymousUser. Anyone on reddit could see what you have posted, and could send you messages. However, no one could see who you are. Also, no one would be able to forge messages on your behalf without your password, which is impossible to guess by just knowing your username. The only difference is that, on a blockchain, your reddit posts would be encrypted.

# Uses beyond crypto

Blockchain supply chain: Companies like IBM Blockchain are already providing private network solutions using blockchain technology to more accurately track product supply chains. For example, companies can use the technology quickly find out where recalled food products have been shipped and sold.

Health care records: Deloitte Consulting has suggested that a nationwide blockchain network for electronic medical records “may improve efficiencies and support better health outcomes for patients.”

Smart contracts: With blockchain technology, contract terms can automatically be changed or updated based on hitting a predetermined set of conditions.

Digital elections: Some developers are working on blockchain technology to be applied to elections.

Property transactions: Proponents say blockchain technology can be applied to a wide range of asset sales, be it real estate, autos or investment portfolios.

### Copyright: @evanwire
