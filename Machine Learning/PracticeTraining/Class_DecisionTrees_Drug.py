import matplotlib.pyplot as plt
from sklearn import metrics
import numpy as np
import pandas as pd
from sklearn.tree import DecisionTreeClassifier
from sklearn import preprocessing
from sklearn.model_selection import train_test_split
from io import StringIO
import pydotplus
import matplotlib.image as mpimg
from sklearn import tree

# data source: !wget -O drug200.csv https://cf-courses-data.s3.us.cloud-object-storage.appdomain.cloud/IBMDeveloperSkillsNetwork-ML0101EN-SkillsNetwork/labs/Module%203/data/drug200.csv
my_data = pd.read_csv("drug200.csv", delimiter=",")
# size of data
# my_data.shape

# pre-processing
X = my_data[['Age', 'Sex', 'BP', 'Cholesterol', 'Na_to_K']].values

# categorize
le_sex = preprocessing.LabelEncoder()
le_sex.fit(['F', 'M'])
X[:, 1] = le_sex.transform(X[:, 1])
le_BP = preprocessing.LabelEncoder()
le_BP.fit(['LOW', 'NORMAL', 'HIGH'])
X[:, 2] = le_BP.transform(X[:, 2])
le_Chol = preprocessing.LabelEncoder()
le_Chol.fit(['NORMAL', 'HIGH'])
X[:, 3] = le_Chol.transform(X[:, 3])

# target variable
y = my_data["Drug"]

# train & test split
X_trainset, X_testset, y_trainset, y_testset = train_test_split(
    X, y, test_size=0.3, random_state=3)
print('Shape of X training set {}'.format(X_trainset.shape),
      '&', ' Size of Y training set {}'.format(y_trainset.shape))
print('Shape of X training set {}'.format(X_testset.shape),
      '&', ' Size of Y training set {}'.format(y_testset.shape))

# modelling
drugTree = DecisionTreeClassifier(criterion="entropy", max_depth=4)
drugTree  # it shows the default parameters
drugTree.fit(X_trainset, y_trainset)

# prediction
predTree = drugTree.predict(X_testset)
print(predTree[0:5])
print(y_testset[0:5])

# evaluation
print("DecisionTrees's Accuracy: ", metrics.accuracy_score(y_testset, predTree))

# visualization
dot_data = StringIO()
filename = "drugtree.png"
featureNames = my_data.columns[0:5]
out = tree.export_graphviz(drugTree, feature_names=featureNames, out_file=dot_data, class_names=np.unique(
    y_trainset), filled=True,  special_characters=True, rotate=False)
graph = pydotplus.graph_from_dot_data(dot_data.getvalue())
graph.write_png(filename)
img = mpimg.imread(filename)
plt.figure(figsize=(100, 200))
plt.imshow(img, interpolation='nearest')
