import numpy as np
import matplotlib.pyplot as plt

x = np.arange(-5.0, 5.0, 0.1)

# linear
y = 2*(x) + 3
y_noise = 2 * np.random.normal(size=x.size)
ydata = y + y_noise
# plt.figure(figsize=(8,6))
plt.plot(x, ydata,  'bo')
plt.plot(x, y, 'r')
plt.ylabel('Dependent Variable')
plt.xlabel('Independent Variable')
plt.show()

# cubic
y = 1*(x**3) + 1*(x**2) + 1*x + 3
y_noise = 20 * np.random.normal(size=x.size)
ydata = y + y_noise
plt.plot(x, ydata,  'bo')
plt.plot(x, y, 'r')
plt.ylabel('Dependent Variable')
plt.xlabel('Independent Variable')
plt.show()

# quadric
y = np.power(x, 2)
y_noise = 2 * np.random.normal(size=x.size)
ydata = y + y_noise
plt.plot(x, ydata,  'bo')
plt.plot(x, y, 'r')
plt.ylabel('Dependent Variable')
plt.xlabel('Independent Variable')
plt.show()

# exponential
y = np.exp(x)
plt.plot(x, y)
plt.ylabel('Dependent Variable')
plt.xlabel('Independent Variable')
plt.show()

# logarithmic
y = np.log(x)
plt.plot(x, y)
plt.ylabel('Dependent Variable')
plt.xlabel('Independent Variable')
plt.show()

# sigmoidal / logistic
y = 1-4/(1+np.power(3, x-2))
plt.plot(x, y)
plt.ylabel('Dependent Variable')
plt.xlabel('Independent Variable')
plt.show()
