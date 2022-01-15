import pandas as pd
from math import sqrt
import numpy as np
import matplotlib.pyplot as plt

# data source: !wget - O moviedataset.zip https: // cf-courses-data.s3.us.cloud-object-storage.appdomain.cloud/IBMDeveloperSkillsNetwork-ML0101EN-SkillsNetwork/labs/Module % 205/data/moviedataset.zip
movies_df = pd.read_csv('movies.csv')
ratings_df = pd.read_csv('ratings.csv')

# use regular expressions to find a year stored between parentheses
movies_df['year'] = movies_df.title.str.extract('(\(\d\d\d\d\))', expand=False)
# remove the parentheses
movies_df['year'] = movies_df.year.str.extract('(\d\d\d\d)', expand=False)
# remove the years from the 'title' column
movies_df['title'] = movies_df.title.str.replace('(\(\d\d\d\d\))', '')
# apply the strip function to get rid of any ending whitespace characters that may have appeared
movies_df['title'] = movies_df['title'].apply(lambda x: x.strip())
# drop the genres column
movies_df = movies_df.drop('genres', 1)
# drop a specified row or column from a dataframe
ratings_df = ratings_df.drop('timestamp', 1)

userInput = [
    {'title': 'Breakfast Club, The', 'rating': 5},
    {'title': 'Toy Story', 'rating': 3.5},
    {'title': 'Jumanji', 'rating': 2},
    {'title': "Pulp Fiction", 'rating': 5},
    {'title': 'Akira', 'rating': 4.5}
]
inputMovies = pd.DataFrame(userInput)
# filter out the movies by title
inputId = movies_df[movies_df['title'].isin(inputMovies['title'].tolist())]
# merge to get the movieId
inputMovies = pd.merge(inputId, inputMovies)
# drop unnecessary information
inputMovies = inputMovies.drop('year', 1)
# filter out users that have watched movies that the input has watched and storing it
userSubset = ratings_df[ratings_df['movieId'].isin(
    inputMovies['movieId'].tolist())]
# groupby creates several sub dataframes where they all have the same value in the column specified as the parameter
userSubsetGroup = userSubset.groupby(['userId'])
# take one of the users
userSubsetGroup.get_group(1130)
# sort it so users with movie most in common with the input will have priority
userSubsetGroup = sorted(
    userSubsetGroup,  key=lambda x: len(x[1]), reverse=True)

userSubsetGroup = userSubsetGroup[0:100]
# store the Pearson Correlation in a dictionary, where the key is the user Id and the value is the coefficient
pearsonCorrelationDict = {}
# for every user group in the subset
for name, group in userSubsetGroup:
    # sort the input and current user group so the values aren't mixed up later on
    group = group.sort_values(by='movieId')
    inputMovies = inputMovies.sort_values(by='movieId')
    # get the N for the formula
    nRatings = len(group)
    # get the review scores for the movies that they both have in common
    temp_df = inputMovies[inputMovies['movieId'].isin(
        group['movieId'].tolist())]
    # store them in a temporary buffer variable in a list format to facilitate future calculations
    tempRatingList = temp_df['rating'].tolist()
    # put the current user group reviews in a list format
    tempGroupList = group['rating'].tolist()
    # calculate the pearson correlation between two users, so called, x and y
    Sxx = sum([i**2 for i in tempRatingList]) - \
        pow(sum(tempRatingList), 2)/float(nRatings)
    Syy = sum([i**2 for i in tempGroupList]) - \
        pow(sum(tempGroupList), 2)/float(nRatings)
    Sxy = sum(i*j for i, j in zip(tempRatingList, tempGroupList)) - \
        sum(tempRatingList)*sum(tempGroupList)/float(nRatings)

    # if the denominator is different than zero, then divide; else, 0 correlation
    if Sxx != 0 and Syy != 0:
        pearsonCorrelationDict[name] = Sxy/sqrt(Sxx*Syy)
    else:
        pearsonCorrelationDict[name] = 0
pearsonDF = pd.DataFrame.from_dict(pearsonCorrelationDict, orient='index')
pearsonDF.columns = ['similarityIndex']
pearsonDF['userId'] = pearsonDF.index
pearsonDF.index = range(len(pearsonDF))

# top 50 similar users to input user
topUsers = pearsonDF.sort_values(by='similarityIndex', ascending=False)[0:50]

# rating of selected users to all movies
topUsersRating = topUsers.merge(
    ratings_df, left_on='userId', right_on='userId', how='inner')
# multiplies the similarity by the user's ratings
topUsersRating['weightedRating'] = topUsersRating['similarityIndex'] * \
    topUsersRating['rating']
# applies a sum to the topUsers after grouping it up by userId
tempTopUsersRating = topUsersRating.groupby(
    'movieId').sum()[['similarityIndex', 'weightedRating']]
tempTopUsersRating.columns = ['sum_similarityIndex', 'sum_weightedRating']
# creates an empty dataframe
recommendation_df = pd.DataFrame()
# take the weighted average
recommendation_df['weighted average recommendation score'] = tempTopUsersRating['sum_weightedRating'] / \
    tempTopUsersRating['sum_similarityIndex']
recommendation_df['movieId'] = tempTopUsersRating.index
recommendation_df = recommendation_df.sort_values(
    by='weighted average recommendation score', ascending=False)
movies_df.loc[movies_df['movieId'].isin(
    recommendation_df.head(10)['movieId'].tolist())]
