# AI, Machine Learning & Deep Learning
## AI
1. makes computers intelligent in order to mimic the cognitive functions of humans
2. is a general field with a broad scope including: Computer Vision, Language Processing, Creativity, and Summarization
## Machine Learning
1. the branch of AI that covers the statistical part of artificial intelligence
2. teaches the computer to solve problems by looking at hundreds or thousands of examples, learning from them, and then using that experience to solve the same problem in new situations
### Major Machine Learning Techniques
* Regression/Estimation: predicting continuous values
* Classification: predicting the item class/category of a case
* Clustering: finding the structure of data; summarization; group
* Association: associating frequent co-occurring items/events
* Anomaly Detection: discovering abnormal and unusual cases
* Sequence Mining: predicting next events; click-stream (Markov Model, HMM)
* Dimension Reduction: reducing the size of data (PCA)
* Recommendation Systems: recommending items
## Deep Learning
1. computers can actually learn and make intelligent decisions on their own
2. involves a deeper level of automation in comparison with most machine learning algorithms
# Supervised & Unsupervised Learning
## Supervised
* Classification: classifies ***labeled*** data
* Regression: predicates trends using previous ***labeled*** data
* Features:
  * Has more evaluation methods than unsupervised learning
  * Controlled environment
## Unsupervised
* Clustering: finds patterns and groupings from ***unlabeled*** data
* Features:
  * Has fewer evaluation methods than supervised learning
  * Less controlled environment
# Regression
## Types
* Simple Regression
* Multiple Regression
* Linear Regression
* Non-linear Regression
## Regression Algorithms
* Ordinal regression
* Poisson regression
* Fast forest quantile regression
* Linear, Polynomial, Lasso, Stepwise, Ridge regression
* Bayesian linear regression
* Neutral network regression
* Decision forest regression
* Boosted decision tree regression
* KNN (K-nearest neighbors)
## Simple Linear Regression

## Multiple Linear Regression
## Non-linear Regression
# Recommender Systems
## Types
* Content-based: "Show me more of the same of what I've liked before"  
  * input user rating (a column vector) × "liked" item-genre matrix = weighted "liked-genre" matrix
  * sum up weighted genre matrix by genre (and count each percentage) to get user profile (a row vector)
  * user profile × "candidate" item-genre matrix = weighted "candidate-genre" matrix
  * aggregate the ratings by iten to get recommendation (a column vector)
* Collaborative Filtering: "Tell me what's popular among my neighbors, I also might like it"
  * user-based: based on users' neighborhood (sharing similar rating patterns)
    * rating matrix (subset) × similarity matrix = weighted rating matrix
    * sum up the weighted rating and normalize it (by similarity matrix) to get recommendation matrix
  * item-based: based on items' similarity
  * Challenges: data sparsity (users only rate a limited number of items), cold start (hard to recommend to new users), scalability (increase in number of users or items)
## Implementation
* Memory-based
  * Uses the entire user-item dataset to generate a recommendation
  * Uses statistical techniques to approximate users or items
  * eg. Pearson Correlation, Cosine Similarity, Euclidean Distance, etc.
* Model-based
  * Develops a model of users in an attempt to learn their preferences
  * Models can be created using Machine Learning techniques like regression, clusteing, classification, etc. 
