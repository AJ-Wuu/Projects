# Image Classification
* Definition: the process of taking an image or picture and getting a computer to automatically classify it, or try providing the probability of the class of the image
  * class = a label
  * use the intensity values to classify the image
* Challenges: change in viewpoint, change of illumination, deformation, occlusion, background clutter
# KNN
* Hyperparameter = the best K
* To select the Hyperparameter we split our data set into three parts, the training set, validation set, and test set
  * Training data: use to get different hyperparameters
  * Validation data: find the accuracy of K and select the hyperparameter K that maximizes the accuracy
  * Test data: check how the model will perform on the real-world data
# Linear Classifiers
* Learnable Parameters (z = wx + b)
  * w = weight
  * b = bias 
* Decision Plane = represent z=wx+b as a dot product of row vector w and image x
# Logistic Regression Training: Gradient Descent
* Loss Function = how good the prediction is
* Cross Entropy deals with how likely the image belongs to a specific class
  * If the likelihood of belonging to an incorrect class is large, the cross entropy loss in turn will be large.
  * If the likelihood of belonging to the correct class is correct, the cross entropy is small, but not zero.
* Gradient Descent = a method to find the minimum of the cost function
  * add a number proportional to the gradient depending on the context (could be negative)
# Mini-Batch Gradient Descent
* Allow to train models with more data
* Epoch = use all the samples in the dataset 
* Batch Gradient Descent = use all the samples, where one iteration equals one epoch
* Total Loss = use a few samples to calculate the cost
* Process
  * Noisy Version of the Cost: calculate the total loss for each iteration
  * At the end of each epoch we calculate the accuracy on the validation data We repeat the process for the next iteration
    * If the accuracy decreases we have trained too much (overfitting)
# SoftMax and Multi-Class Classification
* Softmax Function = a function that turns a vector of K real values into a vector of K real values that sum to 1
* Training for SoftMax is almost identical to logistic regression
* Other methods to convert a two-class classifier to multi-class classifier:
  * one-vs-rest
  * one-vs-one
# Support Vector Machines (SVM)
* Kernel types:
  * Linear
  * Polynomial
  * Radial basis function (RBF)
    * most widely used
    * finds the difference between two inputs X and X’ (called a support vector)
    * has the parameter gamma (higher gamma == overfit)
* Maximize Margin
  * the hyperplane is learned from training data using an optimization procedure that maximizes the margin
  * this optimization problem can also be solved by gradient descent
# Image Features
* In practice, using the image intensities for classification does not function well
* Color is not always the best tool for classification
* Histogram of Oriented Gradients (H.O.G.) = a combination of all pixel-level histograms and used with SVM to classify the image
  * counts occurrences of gradient orientation in localized portions of an image
  * HOG would generate a histogram for each of these regions separately
  * the histograms are created using the gradients and orientations of the pixel values
  * to improve imbalance to highlights and shadows in the image, cells are block normalized
