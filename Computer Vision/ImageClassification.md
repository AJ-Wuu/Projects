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
