from sklearn.metrics import log_loss
from sklearn.metrics import confusion_matrix
from sklearn.linear_model import LogisticRegression
from sklearn.model_selection import train_test_split
import matplotlib.pyplot as plt
import pandas as pd
import pylab as pl
import numpy as np
import scipy.optimize as opt
from sklearn import preprocessing
from sklearn.metrics import jaccard_score
from sklearn.metrics import classification_report, confusion_matrix
import itertools


def plot_confusion_matrix(cm, classes,
                          normalize=False,
                          title='Confusion matrix',
                          cmap=plt.cm.Blues):
    """
    This function prints and plots the confusion matrix.
    Normalization can be applied by setting `normalize=True`.
    """
    if normalize:
        cm = cm.astype('float') / cm.sum(axis=1)[:, np.newaxis]
        print("Normalized confusion matrix")
    else:
        print('Confusion matrix, without normalization')

    print(cm)

    plt.imshow(cm, interpolation='nearest', cmap=cmap)
    plt.title(title)
    plt.colorbar()
    tick_marks = np.arange(len(classes))
    plt.xticks(tick_marks, classes, rotation=45)
    plt.yticks(tick_marks, classes)

    fmt = '.2f' if normalize else 'd'
    thresh = cm.max() / 2.
    for i, j in itertools.product(range(cm.shape[0]), range(cm.shape[1])):
        plt.text(j, i, format(cm[i, j], fmt),
                 horizontalalignment="center",
                 color="white" if cm[i, j] > thresh else "black")

    plt.tight_layout()
    plt.ylabel('True label')
    plt.xlabel('Predicted label')

    print(confusion_matrix(y_test, yhat, labels=[1, 0]))


# data source: !wget -O ChurnData.csv https://cf-courses-data.s3.us.cloud-object-storage.appdomain.cloud/IBMDeveloperSkillsNetwork-ML0101EN-SkillsNetwork/labs/Module%203/data/ChurnData.csv
churn_df = pd.read_csv("ChurnData.csv")

# pre-processing and selection
churn_df = churn_df[['tenure', 'age', 'address', 'income',
                     'ed', 'employ', 'equip',   'callcard', 'wireless', 'churn']]
churn_df['churn'] = churn_df['churn'].astype('int')
# define
X = np.asarray(churn_df[['tenure', 'age', 'address',
               'income', 'ed', 'employ', 'equip']])
y = np.asarray(churn_df['churn'])
# normalize
X = preprocessing.StandardScaler().fit(X).transform(X)

# train-test split
X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.2, random_state=4)
print('Train set:', X_train.shape,  y_train.shape)
print('Test set:', X_test.shape,  y_test.shape)

# modeling
LR = LogisticRegression(C=0.01, solver='liblinear').fit(X_train, y_train)
# LR2 = LogisticRegression(C=0.01, solver='sag').fit(X_train,y_train)
yhat = LR.predict(X_test)
# predict_proba returns estimates for all classes, ordered by the label of classes
yhat_prob = LR.predict_proba(X_test)

# evaluation
# jaccard index
jaccard_score(y_test, yhat, pos_label=0)
# confusion matrix
cnf_matrix = confusion_matrix(y_test, yhat, labels=[1, 0])
np.set_printoptions(precision=2)
plt.figure()
plot_confusion_matrix(cnf_matrix, classes=[
                      'churn=1', 'churn=0'], normalize=False,  title='Confusion matrix')
print(classification_report(y_test, yhat))
'''
For each class, we can calculate:
Precision is a measure of the accuracy provided that a class label has been predicted. It is defined by: precision = TP/(TP+FP)
Recall is the true positive rate. It is defined as: Recall = TP/(TP+FN)
F1-score = 0.72
'''
# log loss
log_loss(y_test, yhat_prob)
