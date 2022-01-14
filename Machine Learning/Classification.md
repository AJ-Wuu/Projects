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
* Definition: Decision trees are built by splitting the training set into distinct nodes, where one node contains all of or most of one category of the data.
* Algorithm
  * Choose an attribute from the dataset
  * Calculate the significance of attribute in splitting of data
  * Split data based on the value of the best attribute
  * Go back to the beginning
* Predictiveness is based on decrease in impurity of nodes
* Entropy is the measure of randomness or uncertainty
* More predictiveness, purer (less impurity), lower entropy, less uniform of the distribution
* Information gain
  * is the information that can increase the level of certainty after splitting
  * information gain = entropy before split - weighted entropy after split
  * can be seen as the "opposite" of the entropy (entropy = information disorder)
  * after a split, information gain should increase
## Logistic Regression
* Definition: Logistic regression is a classification algorithm for categorical / discrete variables.
* Usage: when the data is binary (True/False, Yes/No, 0/1); need probabilistic results belong to a specific class; need a linear decision boundary; need to understand the impact an independent variable has on the dependent variable while controlling other independent variables
* Algorithm
  * Initialize θ (one of the most popular ways is gradient descent)
  * Calculate y_hat for one customer
  * Compare y_hat with y (actual output), and record it as error
  * Calculate the error for all customers
  * Change θ to reduce the cost
  * Go back to calculate y_hat
* Training Steps
  * Step 1: Initialize the parameters randomly
  * Step 2: Feed the cost function with training set, and calculate the error
  * Step 3: Calculate the gradient of cost function (expensive)
    * Gradient Descent is a technique to use derivative of a cost function to change the parameter values to minimize the cost
  * Step 4: Update weights with new values
  * Step 5: Go to Step 2 until cost is small enough
  * Step 6: Predict the new customer X
### Logistic VS Linear Regression
Logistic is analogous to Linear Regression.
Linear predicts a continuous value of variables (eg. the price of a house), in a numeric target field, cannot properly measure the probability of a case belonging to a class.
Logistic predicts a variable which is binary (eg. yes/no, true/false), in a categorical or discrete target field.
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
