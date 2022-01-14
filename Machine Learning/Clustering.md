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
* Process
  * Randomly placing k centroids, one for each cluster (the farther apart the clusters are placed, the better)
  * Calculate the distance of each point from each centroid (Euclidean distance is the most popular)
  * Assign each point / object to the closest centroid, creating a cluster
  * Recalculate the position of the k centroids
  * Repeat until the centroids no longer move
* Accuracy
  * External approach: compare the clusters with the ground truth (not always available in real-world case, as clustering is unsupervised)
  * Internal approach: average the distance between data points within a cluster
* Find k: at the elbow point (where the rate of decrease sharply shifts)
* Features
  * Relatively efficient on medium and large sized databases
  * Produces sphere-like clusters
  * Needs number of clusters (k) -> NOT EASY
## Hierarchical Clustering
* Definition: build a hierarchy of clusters where each node is a cluster consist of the clusters of its daughter nodes
* Types
  * Divisive: top down; start with all observations in a large cluster and break it down into smaller pieces
  * Agglomerative: bottom up; each observation starts in its own cluster and pairs of clusters are merged together as they move up the hierarchy (more popular)
* Agglomerative Algorithm
  * Create n clusters, one for each data point
  * Compute the Proximity Matrix (n × n)
  * Repeat
    * Merge the two closest clusters
    * Update the proximity matrix
  * Until only a singly cluster remains
* Distance between clusters
  * Single-Linkage: minimum distance
  * Complete-Linkage: maximum distance
  * Average Linkage: average distance
  * Centroid Linkage: distance between cluster centroids
* Advantages & Disadvantages

| Advantages | Disadvantages |
|-----------:|:-------------:|
| Doesn't require number of clusters to be specified | Can never undo any previous steps throughout the algorithm |
| Easy to implement | Generally has long runtimes |
| Produces a dendrogram, which helps with understanding the data | Sometimes difficult to identify the number of clusters by the dendrogram |
* Hierarchical VS k-Means

| Hierarchical | k-Means |
|-------------:|:-------:|
| Slow for large datasets | Much more efficient |
| Not require the number of clusters to run | Require the number of clusters to be specified |
| Gives more than one partitioning depending on the resolution | Gives only one partitioning of the data based on the predefined number of clusters |
| Always generates the same clusters | Potentially returns different clusters each time it is run due to random initialization of centroids |
## Density-Based Clustering
* Definition: proper for arbitrary shape clusters
* Density-Based VS k-Means
  * Density-Based: locates regions of high density, and separates outliers
  * k-Means: assigns all points to a cluster even if they do not belong to any
* DBSCAN (Density-Based Spatial Clustering of Applications with Noise)
  * Features
    * is one of the most common clustering algorithms
    * works based on density of objects
    * can find arbitrarily shaped clusters
    * can even find a cluster completely surrounded by a different cluster
    * has a notion of noise
    * is robust to outliers
    * does not require to specify the number of clusters (so it is very practical for use in many real-world problems)
  * R (Radius of neighborhood)
    * dense area if includes enough number of points within
  * M (Min number of neighbors)
    * the minimum number of data points wanted in a neighborhood to define a cluster
  * Core Point: within its neighborhood, there are at least M points
  * Border Point (A or B)
    * A. its neighbourhood contains less than M data points
    * B. it is reachable from some core point (reachability means it is within our distance from a core point)
  * Outlier: not core nor border
 
