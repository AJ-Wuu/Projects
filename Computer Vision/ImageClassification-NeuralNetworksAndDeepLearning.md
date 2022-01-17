# Neural Networks
* classification problem = decision function
* Activation Function = apply the logistic function in the context of neural networks
* Artificial Neuron = each linear function and activation
* The more dimensions, the more neurons we require.
# Fully Connected Neural Network Architecture
* More neurons or more layers may lead to overfitting.
* The output or activation of each layer is the same dimension as the number of neurons.
* Different Methods
  * Dropout prevents overfitting
  * Batch Normalization helps with training
  * Skip Connections allows to train deeper networks by connecting deeper layers during training
# Convolutional Networks (CNNs)
* Convolution and pooling layers are the first layers used to extract features from an input these can be thought of as the feature learning layers, the fully connected layers are simply a neural network Both are learned simultaneously by minimizing the cross-entropy loss
  * neurons
  * kernels -> learnable parameters
  * activation functions (RELU) are applied to each pixel
  * each kernel of a CNN will detect a different property of the image
* Adding neurons = stack convolutional layers each output channel is analogous to the neurons
* Receptive Field = the size of the region in the input that produces a pixel value in the activation map
* Pooling = reduces the number of parameters, increases the receptive field while preserving the important features
  * Max pooling is the most popular type of pooling
* Flattening and the Fully Connected layers = flatten or reshape the output of the feature learning layers and use them as an input to the fully connected layers 
* CNN Architectures
  * LeNet-5: most successful case - MNIST Dataset of handwritten digits
  * AlexNet: benchmark dataset (everyone uses this to see who has the best image classification method)
  * VGGNet: very deep convolutional network that was developed out of the need to reduce the number of parameters in the convolution layers and improve on training time
  * ResNet: helps solve the problem by introducing residual learning
* Transfer Learning: use a pre-trained CNN to classify an image
