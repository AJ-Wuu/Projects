# Object Detection
* Object detection locates multiple objects with a bounding box and their classes
* Algorithms
  * Sliding Windows
    * find all the possible objects in the image
    * When the object occupies most of the window, it will be classified
  * Bounding Box: a rectangular box that can be determined, with the lower-right corner of the rectangle with coordinates y'=0 and x'=0 and the width and height
  * Bounding Box Pipeline: class x, y, and bounding box
  * Score = how confident the model prediction is
# Haar - Cascade Classifier
* Haar wavelets extract information about: Edges, Lines, Diagonal edges
* The Integral image concept is each pixel represents the cumulative sum of the corresponding input pixels above and to the left of that pixel.
* An AdaBoost classifier is used to reduce the number of features:
  * A weak classifier is made on top of the training data based on the weighted samples
  * It selects only those features that help to improve the classifier accuracy
  * AdaBoost cuts down the number of features significantly
