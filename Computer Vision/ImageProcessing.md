# Digital Image
* Digital Image = a rectangular array of numbers
* Gray-Scale Image = an image that is made up of different shades of gray
* Pixel = blocks in a rectangular grid that comprise the image
* Intensity Values = numbers to represent pixels (0~255 -> 0 is black, 255 is white; overlay over each pixel; index: top->down, left->right)
* RGB channels: each one of these channels has its own intensity values
# Pixel Transformation
* Histogram = counts the number of occurrences of a pixel
* Intensity Transformation = changes a image one pixel at a time (depends on only one single point (i,j), in the image array)
* Linear Transform = applies Brightness and Contrast Adjustments
* Threshold Function = applies a threshold to every pixel
* Segmentation = threshold function extracts objects from an image
# Geometrix Operations
* Geometric Transformation = changes the coordinates of the image x and y
* Affine Transformations = a subset of Geometric Transformations
* Scaling = reshapes the image (shrink or expand the image in a horizontal and / or vertical direction)
  * the scaling factor does not have to be an integer, and it can be less than one
* Translation = shifts the image (eg. x->tx), requires the Affine Transformation matrix M
* Rotation: using getRotationMatrix2D, which will rotate the image by angle θ
# Spatial Operations
* Convolution = Linear Filtering = a standard way to filter an image
  * kernel = the filter (different kernels perform different tasks)
  * Median Filter: better at removing some types of noise but may distort the image
* Edge Detection = uses methods to approximate derivatives and gradients for identifying edge areas
  * edge = where the image brightness changes sharply
