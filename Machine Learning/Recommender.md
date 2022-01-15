# Recommender Systems
## Types
* Content-based: "Show me more of the same of what I've liked before"  
  * input user rating (a column vector) × "liked" item-genre matrix = weighted "liked-genre" matrix
  * sum up weighted genre matrix by genre (and count each percentage) to get user profile (a row vector)
  * user profile × "candidate" item-genre matrix = weighted "candidate-genre" matrix
  * aggregate the ratings by iten to get recommendation (a column vector)
  * Advantages
    * Learns user's preferences
    * Highly personalized for the user
  * Disadvantages
    * Doesn't take into account what others think of the item, so low quality item recommendations might happen
    * Extracting data is not always intuitive
    * Determining what characteristics of the item the user dislikes or likes is not always obvious
* Collaborative Filtering: "Tell me what's popular among my neighbors, I also might like it"
  * user-based: based on users' neighborhood (sharing similar rating patterns)
    * rating matrix (subset) × similarity matrix = weighted rating matrix
    * sum up the weighted rating and normalize it (by similarity matrix) to get recommendation matrix
  * item-based: based on items' similarity
  * Challenges: data sparsity (users only rate a limited number of items), cold start (hard to recommend to new users), scalability (increase in number of users or items)
  * Advantages
    * Takes other user's ratings into consideration
    * Doesn't need to study or extract information from the recommended item
    * Adapts to the user's interests which might change over time
  * Disadvantages
    * Approximation function can be slow
    * There might be a low amount of users to approximate
    * Privacy issues when trying to learn the user's preferences

## Implementation
* Memory-based
  * Uses the entire user-item dataset to generate a recommendation
  * Uses statistical techniques to approximate users or items
  * eg. Pearson Correlation, Cosine Similarity, Euclidean Distance, etc.
* Model-based
  * Develops a model of users in an attempt to learn their preferences
  * Models can be created using Machine Learning techniques like regression, clusteing, classification, etc. 
