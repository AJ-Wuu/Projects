from sklearn import metrics
from sklearn.model_selection import train_test_split
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
import numpy as np
from sklearn import preprocessing
from sklearn.neighbors import KNeighborsClassifier

# data source: !wget -O teleCust1000t.csv https://cf-courses-data.s3.us.cloud-object-storage.appdomain.cloud/IBMDeveloperSkillsNetwork-ML0101EN-SkillsNetwork/labs/Module%203/data/teleCust1000t.csv
df = pd.read_csv('teleCust1000t.csv')
# df.head()
# calculate the number of customers of each class
# df['custcat'].value_counts()
df.hist(column='income', bins=50)

# feature set
X = df[['region', 'tenure', 'age', 'marital', 'address', 'income', 'ed',
        'employ', 'retire', 'gender', 'reside']].values  # .astype(float)
y = df['custcat'].values

# normalize data
X = preprocessing.StandardScaler().fit(X).transform(X.astype(float))

# train dataset split
X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.2, random_state=4)
print('Train set:', X_train.shape,  y_train.shape)
print('Test set:', X_test.shape,  y_test.shape)

# set k
k = 4
# train
neigh = KNeighborsClassifier(n_neighbors=k).fit(X_train, y_train)
# predicting
yhat = neigh.predict(X_test)
# evaluation
print("Train set Accuracy: ", metrics.accuracy_score(
    y_train, neigh.predict(X_train)))
print("Test set Accuracy: ", metrics.accuracy_score(y_test, yhat))

# accuracy of KNN for different k
Ks = 10
mean_acc = np.zeros((Ks-1))
std_acc = np.zeros((Ks-1))
for n in range(1, Ks):
    # train model and predict
    neigh = KNeighborsClassifier(n_neighbors=n).fit(X_train, y_train)
    yhat = neigh.predict(X_test)
    mean_acc[n-1] = metrics.accuracy_score(y_test, yhat)
    std_acc[n-1] = np.std(yhat == y_test)/np.sqrt(yhat.shape[0])
plt.plot(range(1, Ks), mean_acc, 'g')
plt.fill_between(range(1, Ks), mean_acc - 1 * std_acc,
                 mean_acc + 1 * std_acc, alpha=0.10)
plt.fill_between(range(1, Ks), mean_acc - 3 * std_acc,
                 mean_acc + 3 * std_acc, alpha=0.10, color="green")
plt.legend(('Accuracy ', '+/- 1xstd', '+/- 3xstd'))
plt.ylabel('Accuracy ')
plt.xlabel('Number of Neighbors (K)')
plt.tight_layout()
plt.show()
