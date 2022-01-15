import random
import numpy as np
import matplotlib.pyplot as plt
from sklearn.cluster import KMeans
from sklearn.datasets import make_blobs

# random seed
np.random.seed(0)
# random clusters
X, y = make_blobs(n_samples=5000, centers=[
                  [4, 4], [-2, -1], [2, -3], [1, 1]], cluster_std=0.9)
plt.scatter(X[:, 0], X[:, 1], marker='.')

# initialize the plot with the specified dimensions
k_means = KMeans(init="k-means++", n_clusters=4, n_init=12)  # 4 clusters
k_means.fit(X)
k_means_labels = k_means.labels_
k_means_cluster_centers = k_means.cluster_centers_
fig = plt.figure(figsize=(6, 4))
# colors uses a color map, which will produce an array of colors based on the number of labels there are
# use set(k_means_labels) to get the unique labels
colors = plt.cm.Spectral(np.linspace(0, 1, len(set(k_means_labels))))
# create a plot
ax = fig.add_subplot(1, 1, 1)
# for loop that plots the data points and centroids
# k will range from 0-3, which will match the possible clusters that each data point is in
for k, col in zip(range(len([[4, 4], [-2, -1], [2, -3], [1, 1]])), colors):
    # create a list of all data points, where the data points that are
    # in the cluster (ex. cluster 0) are labeled as true, else they are
    # labeled as false
    my_members = (k_means_labels == k)
    # define the centroid, or cluster center.
    cluster_center = k_means_cluster_centers[k]
    # plots the datapoints with color col.
    ax.plot(X[my_members, 0], X[my_members, 1],
            'w', markerfacecolor=col, marker='.')
    # plots the centroids with specified color, but with a darker outline
    ax.plot(cluster_center[0], cluster_center[1], 'o',
            markerfacecolor=col,  markeredgecolor='k', markersize=6)
# title of the plot
ax.set_title('KMeans')
# remove x-axis ticks
ax.set_xticks(())
# remove y-axis ticks
ax.set_yticks(())
# show the plot
plt.show()
