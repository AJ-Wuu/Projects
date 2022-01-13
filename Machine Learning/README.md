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
## Regression Algorithms
Ordinal regression; Poisson regression; Fast forest quantile regression; Linear, Polynomial, Lasso, Stepwise, Ridge regression; Bayesian linear regression; Neutral network regression; Decision forest regression; Boosted decision tree regression; KNN (K-nearest neighbors)
## Simple Linear Regression
* Definition: One x & One y
* Goal: minimize MSE
* Pros: fast; no parameter tuning; easy to understand; highly interpretable
## Multiple Linear Regression
* Definition: Multiple x & One y, predicting a continuous variable
* Implementation:
  * identify the strength of the effect that the independent variables have on the dependent variable
  * predict the impact of changes (how the dependent variable changes when we change the independent variables)
* Estimate θ
  * Ordinary Least Squares: linear algebra operations (takes a long time, only works for datasets less than 10k rows)
  * Optimization algorithm: gradient descent (proper for a very large dataset)
## Non-linear Regression
* Definition: y_hat must be a non-linear function of the parameter θ, not necessarily the features x
* Modeled by a polynomial regression
  * can be transformed into linear regression model
  * can fit the curve using the method of least squares
## Linear VS Non-linear
1. Visualization
2. Calculate the correlation coefficient between independent and dependent variables (greater than or equal to 0.7 -> linear tendency)
3. Choose non-linear if we cannot accurately model the relationship with linear parameters
## Model Evaluation Approaches
### Train&Test on the same dataset
* Definition: train the model on the entire dataset, then test it using a portion of the same dataset
* High training accuracy
  * Training accuracy is the percentage of correct predictions that the model makes when using the test dataset. 
  * Having a high training accuracy may result in an ‘over-fit’ of the data. 
  * If a model is overly trained to the dataset, it may capture noise and produce a non-generalized model.
* Low out-of-sample accuracy
  * Out-of-sample accuracy is the percentage of correct predictions that the model makes on data that the model has not been trained on.
### Train/Test split
* Definition: splitting the dataset into training and testing sets respectively, which are mutually exclusive
* More accurate evaluation on out-of-sample accuracy
* More realistic for real-world problems
* Highly dependent on the datasets on which the data was trained and tested
### K-fold cross-validation
* Use the same dataset where each split is different
* Process
  * 1st fold: trained with \[0, 1/k) data, and tested with \[1/k, 1) data
  * 2nd fold: trained with \[1/k, 2/k) data, and tested with \[0, 1/k) ∪ \[2/k, 1) data
  * keep going until the kth fold
  * average the accuracy of each fold
## Evaluation Metrics
* MAE: Mean Absolute Error
* MSE: Mean Square Error
* RMSE: Root Mean Square Error -> square root of MSE
* RAE: Relative Absolute Error
* RSE: Relative Squared Error
* R^2 -> NOT an error
# Classification
## Definition
1. Categorize unlabeled items into classes
2. The target attribute is a categorical variable
3. Multi-Class Classification: a classifier that can predict a field with multiple discrete values
## Classification Algorithms
Decision Trees (ID3, C4.5, C5.0); Naive Bayes; Linear Discriminant Analysis; k-Nearest Neighbor; Logistic Regression; Neural Networks; Support Vector Machines (SVM)
## K-Nearest Neighbors (KNN)
* A method for classifying cases based on their similarity to other cases.
* Cases that are near each other are said to be "neighbors".
* Based on similar cases with same class labels are near each other.
* Algorithm
  * Pick a value for K
  * Calculate the distance of unknown case from all cases
  * Select the K-observations in the training data that are "nearest" to the unknown data point
  * Predict the response of the unknown data point using the most popular response value from the K-nearest neighbors
## Decision Trees
## Logistic Regression
## Support Vector Machine (SVM)
* Definition: SVM is a supervised algorithm that classifies cases by finding a separator
* Process
  * Mapping data to a high-dimensional feature space
  * Finding a separator
* Data Transformation: Kernelling
  * Mapping data into a higher dimensional space that can change a linearly inseparable dataset into a linearly separable dataset
  * Linear; Polynomial; RBF; Sigmoid
* Hyperplane is learned from training data using an optimization procedure that maximizes the margin
* Pros: accurate in high-dimensional spaces; memory efficient
* Cons: prone to over-fitting; no probability estimation; only works for small datasets
* Applications: Image recognition; Text category assignment; Detecting spam; Sentiment analysis; Gene Expression Classification; Regression, outlier detection and clustering
## Evaluation Metrics
* Jaccard index
  * J(y, y_hat) = |y ∩ y_hat| / |y ∪ y_hat|
  * J(y, y_hat) = 1 -> highest accuracy; J(y, y_hat) = 0 -> lowest accuracy
* F1-score
  * Confusion matrix
  * Precision is a measure of the accuracy, provided that a class label has been predicted -> Precision: TP / (TP + FP)
  * Recall is the true positive rate -> Recall = TP / (TP + FN)
  * F1-score = 2 × (prc × rec) / (prc + rec)
  * F1-score = 1 -> highest accuracy; F1-score = 0 -> lowest accuracy
* Log Loss
  * LogLoss = -(1/n) * sum(y × log(y_hat) + (1-y) × log(1-y_hat))
  * LogLoss = 0 -> highest accuracy; LogLoss = 1 -> lowest accuracy
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
