import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from scipy.optimize import curve_fit
from sklearn.metrics import r2_score


def sigmoid(x, Beta_1, Beta_2):
    # build the model
    y = 1 / (1 + np.exp(-Beta_1*(x-Beta_2)))
    return y


def train(df, xdata, ydata):
    # split data into train/test
    msk = np.random.rand(len(df)) < 0.8
    train_x = xdata[msk]
    test_x = xdata[~msk]
    train_y = ydata[msk]
    test_y = ydata[~msk]

    # build the model using train set
    popt, pcov = curve_fit(sigmoid, train_x, train_y)

    # predict using test set
    y_hat = sigmoid(test_x, *popt)

    # evaluation
    print("Mean absolute error: %.2f" % np.mean(np.absolute(y_hat - test_y)))
    print("Residual sum of squares (MSE): %.2f" %
          np.mean((y_hat - test_y) ** 2))
    print("R2-score: %.2f" % r2_score(test_y, y_hat))


def main():
    # data source: !wget - nv - O china_gdp.csv https: // cf-courses-data.s3.us.cloud-object-storage.appdomain.cloud/IBMDeveloperSkillsNetwork-ML0101EN-SkillsNetwork/labs/Module % 202/data/china_gdp.csv
    df = pd.read_csv("china_gdp.csv")

    # plot the data
    plt.figure(figsize=(8, 5))
    x_data, y_data = (df["Year"].values, df["Value"].values)
    plt.plot(x_data, y_data, 'ro')
    plt.ylabel('GDP')
    plt.xlabel('Year')
    plt.show()

    # choose a model -- logistic
    X = np.arange(-5.0, 5.0, 0.1)
    Y = 1.0 / (1.0 + np.exp(-X))
    plt.plot(X, Y)
    plt.ylabel('Dependent Variable')
    plt.xlabel('Independent Variable')
    plt.show()

    # initialize parameters
    beta_1 = 0.10
    beta_2 = 1990.0
    # logistic function
    Y_pred = sigmoid(x_data, beta_1, beta_2)
    # plot initial prediction against datapoints
    plt.plot(x_data, Y_pred*15000000000000.)
    plt.plot(x_data, y_data, 'ro')

    # normalize the data
    xdata = x_data/max(x_data)
    ydata = y_data/max(y_data)
    # curve_fit uses non-linear least squares to fit our sigmoid function to data
    # goal: the sum of the squared residuals of sigmoid(xdata, \*popt) - ydata is minimized
    # popt is the optimized parameter
    popt, pcov = curve_fit(sigmoid, xdata, ydata)
    print(" beta_1 = %f, beta_2 = %f" % (popt[0], popt[1]))

    # plot the resulting regression model
    x = np.linspace(1960, 2015, 55)
    x = x/max(x)
    plt.figure(figsize=(8, 5))
    y = sigmoid(x, *popt)
    plt.plot(xdata, ydata, 'ro', label='data')
    plt.plot(x, y, linewidth=3.0, label='fit')
    plt.legend(loc='best')
    plt.ylabel('GDP')
    plt.xlabel('Year')
    plt.show()

    train(df, xdata, ydata)


if __name__ == "__main__":
    main()
