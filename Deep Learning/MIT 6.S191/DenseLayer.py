import tensorflow as tf


class MyDenseLayer(tf.keras.layers.Layer):
    def __init__(self, input_dim, output_dim):
        super(MyDenseLayer, self).__init__()

        # initialize weights and bias
        self.W = self.add_weight([input_dim, output_dim])
        self.b = self.add_weight([1, output_dim])

    def call(self, inputs):
        # forward propagate the inputs
        # z = bais + sum(xj * Wj)
        z = tf.matmul(inputs, self.W) + self.b

        # feed through a non-linear activation
        # sigmoid: S-shape; for x < 0, y < 0.5; x = 0, y = 0.5; x > 0; y > 0.5
        output = tf.math.sigmoid(z)

        return output


n1 = 2
n2 = 3
layer = tf.keras.layers.Dense(units=2)  # units = number of output perceptrons
model = tf.keras.Sequential([
    tf.keras.layers.Dense(n1),
    tf.keras.layers.Dense(n2)
])


# loss: the cost incurred from incorrect predictions
# L(f(x,W),y)
#   pred   act
# empirical loss / empirical risk / objective function / cost function: total loss over entire dataset
# J(W) = 1/n * sum(L(f(x,W),y))
y = 5  # actual
predicted = 4
# cross entropy loss: used with models that output a probabiliy between 0 and 1
loss = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(y, predicted))
# mean squared error loss: used with regression models that output continuous real numbers
error = tf.reduce_mean(tf.square(tf.subtract(y, predicted)))


# loss optimization: find W that achieve the lowest loss
# Adaptive: Adam, Adadelta, Adagrad, RMSProp
optimizer = tf.keras.optimizer.SGD()
while True:  # loop forever
    # forward pass through the network
    prediction = model(x)
    with tf.GradientTape() as tape:
        # compute the loss
        loss = compute_loss(y, prediction)
    # update the weights using the gradient
    weights = tape.gradient(loss, model.trainable_variables)
    optimizer.apply_gradients(zip(weights, model.trainable_variables))


# mini-batches lead to fast training: can parallelize computation and achieve significant speed increase on GPU
# overfitting regularization
# dropout
tf.keras.layers.Dropout(p=0.5)  # drop 50% of activations in layer
# early-stopping: make the loss as small as possible before the value for training data and testing data diverge
