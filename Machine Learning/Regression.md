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
