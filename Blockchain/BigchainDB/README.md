# Asset
An asset can have one or multiple owners, but it can also be its own owner.  
Multiple assets can be issued and attributed to one overarching asset.  

# Ways to Register an Asset
1. By users in CREATE transactions.  
2. Transferred (or updated) to other users in TRANSFER transactions.

# Create Transaction
## Data of characteristics & Metadata
Asset's data of characteristic cannot be changed after registration.  
Metadata could be used to add additional information to an asset, and Metadata can be changed during transaction.
## Transaction ID
Hash of all information of the current transaction.  
Used as a reference in the future transactions.
## Input
1. User (represented by their public key)
2. User Request (Asset Object)
3. User's Signature
## Output
1. User (represented by their public key)
2. Granted Ownership
3. Condition to transfer (User needs to provide a Signature with their private key)  
Note: The output can also contain complex conditions (e.g. multiple signatures of multiple people) to acquire ownership.

# Transfer Transaction
## Input
1. Reference of the Output of the Create Transaction
2. Signature of the previous User to satisfy the condition to transfer
## Output
Same as Create Transaction, just replace the previous User with the current User

# Website
http://docs.bigchaindb.com/projects/js-driver/en/latest/usage.html
