# Clustering
## Definition
The process of finding clusters in a dataset, unsupervised.
A cluster is a group of data points or objects in a dataset that are similar to other objects in the group, and dissimilar to datapoints in other clusters.
## Implementation
* Exploratory data analysis
* Summary generation
* Outlier detection
* Finding duplicates
* Pre-processing step
## Algorithm Types
* Partitioned-Based
  * Relatively effcient
  * Eg. k-Means, k-Median, Fuzzy c-Means
* Hierarchical
  * Produces trees of clusters
  * Eg. Agglomerative, Divisive
* Density-Based
  * Produces arbitrary shaped clusters
  * Eg. DBSCAN
## Clustering VS Classification
classification is a supervised learning where each training data instance belongs to a particular class.
Clustering is an unsupervised learning where the data is unlabeled.
## K-Means Clustering
* Definition: k-means divide the data into k non-overlapping subsets or clusters without any cluster internal structure or labels
  * Objects within a cluster are very similar, and objects across different clusters are very different or dissimilar
* Goal: minimize the intra-cluster distances, maximize the inter-cluster distances, divide the data into non-overlapping clusters without any cluster-internal structure
  * Conventionally, the distance of samples from each other is used to shape the clusters
* 
## Hierarchical Clustering
## Density-Based Clustering
