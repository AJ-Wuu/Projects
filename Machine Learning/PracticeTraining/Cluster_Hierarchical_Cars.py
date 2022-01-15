import matplotlib.cm as cm
from sklearn.metrics.pairwise import euclidean_distances
from scipy.cluster.hierarchy import fcluster
import scipy.cluster.hierarchy
import pylab
from sklearn.preprocessing import MinMaxScaler
import numpy as np
import pandas as pd
from scipy import ndimage
from scipy.cluster import hierarchy
from scipy.spatial import distance_matrix
from matplotlib import pyplot as plt
from sklearn.cluster import AgglomerativeClustering
from sklearn.datasets import make_blobs
import scipy


def llf(id):
    return '[%s %s %s]' % (pdf['manufact'][id], pdf['model'][id], int(float(pdf['type'][id])))


# data source: !wget -O cars_clus.csv https://cf-courses-data.s3.us.cloud-object-storage.appdomain.cloud/IBMDeveloperSkillsNetwork-ML0101EN-SkillsNetwork/labs/Module%204/data/cars_clus.csv
pdf = pd.read_csv('cars_clus.csv')

# data cleaning
print("Shape of dataset before cleaning: ", pdf.size)
pdf[['sales', 'resale', 'type', 'price', 'engine_s',
     'horsepow', 'wheelbas', 'width', 'length', 'curb_wgt', 'fuel_cap',
     'mpg', 'lnsales']] = pdf[['sales', 'resale', 'type', 'price', 'engine_s',
                               'horsepow', 'wheelbas', 'width', 'length', 'curb_wgt', 'fuel_cap',
                               'mpg', 'lnsales']].apply(pd.to_numeric, errors='coerce')
pdf = pdf.dropna()
pdf = pdf.reset_index(drop=True)
print("Shape of dataset after cleaning: ", pdf.size)

# feature selection
featureset = pdf[['engine_s',  'horsepow', 'wheelbas',
                  'width', 'length', 'curb_wgt', 'fuel_cap', 'mpg']]

# normalize
x = featureset.values  # returns a numpy array
min_max_scaler = MinMaxScaler()
feature_mtx = min_max_scaler.fit_transform(x)

# clustering using scipy
leng = feature_mtx.shape[0]
D = scipy.zeros([leng, leng])
for i in range(leng):
    for j in range(leng):
        D[i, j] = scipy.spatial.distance.euclidean(
            feature_mtx[i], feature_mtx[j])
Z = hierarchy.linkage(D, 'complete')
# use a cutting line
max_d = 3
clusters = fcluster(Z, max_d, criterion='distance')
# directly determine the number of clusters
k = 5
clusters = fcluster(Z, k, criterion='maxclust')
# plotting
fig = pylab.figure(figsize=(18, 50))
dendro = hierarchy.dendrogram(
    Z,  leaf_label_func=llf, leaf_rotation=0, leaf_font_size=12, orientation='right')

# clustering using scikit-learn
dist_matrix = euclidean_distances(feature_mtx, feature_mtx)
print(dist_matrix)
Z_using_dist_matrix = hierarchy.linkage(dist_matrix, 'complete')
fig = pylab.figure(figsize=(18, 50))
dendro = hierarchy.dendrogram(Z_using_dist_matrix,  leaf_label_func=llf,
                              leaf_rotation=0, leaf_font_size=12, orientation='right')
agglom = AgglomerativeClustering(n_clusters=6, linkage='complete')
agglom.fit(dist_matrix)
pdf['cluster_'] = agglom.labels_
n_clusters = max(agglom.labels_)+1
colors = cm.rainbow(np.linspace(0, 1, n_clusters))
cluster_labels = list(range(0, n_clusters))

plt.figure(figsize=(16, 14))
for color, label in zip(colors, cluster_labels):
    subset = pdf[pdf.cluster_ == label]
    for i in subset.index:
        plt.text(subset.horsepow[i], subset.mpg[i],
                 str(subset['model'][i]), rotation=25)
    plt.scatter(subset.horsepow, subset.mpg, s=subset.price*10,
                c=color, label='cluster'+str(label), alpha=0.5)
#    plt.scatter(subset.horsepow, subset.mpg)
plt.legend()
plt.title('Clusters')
plt.xlabel('horsepow')
plt.ylabel('mpg')

pdf.groupby(['cluster_', 'type'])['cluster_'].count()
agg_cars = pdf.groupby(['cluster_', 'type'])[
    'horsepow', 'engine_s', 'mpg', 'price'].mean()
plt.figure(figsize=(16, 10))
for color, label in zip(colors, cluster_labels):
    subset = agg_cars.loc[(label,), ]
    for i in subset.index:
        plt.text(subset.loc[i][0]+5, subset.loc[i][2], 'type=' +
                 str(int(i)) + ', price='+str(int(subset.loc[i][3]))+'k')
    plt.scatter(subset.horsepow, subset.mpg, s=subset.price *
                20, c=color, label='cluster'+str(label))
plt.legend()
plt.title('Clusters')
plt.xlabel('horsepow')
plt.ylabel('mpg')
