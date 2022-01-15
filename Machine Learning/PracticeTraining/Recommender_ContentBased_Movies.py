import pandas as pd
from math import sqrt
import numpy as np
import matplotlib.pyplot as plt

movies_df = pd.read_csv('movies.csv')
ratings_df = pd.read_csv('ratings.csv')
movies_df['year'] = movies_df.title.str.extract('(\(\d\d\d\d\))', expand=False)
movies_df['year'] = movies_df.year.str.extract('(\d\d\d\d)', expand=False)
movies_df['title'] = movies_df.title.str.replace('(\(\d\d\d\d\))', '')
movies_df['title'] = movies_df['title'].apply(lambda x: x.strip())
# every genre is separated by a | so we simply have to call the split function on |
movies_df['genres'] = movies_df.genres.str.split('|')
# copy the movie dataframe into a new one since we won't need to use the genre information in our first case
moviesWithGenres_df = movies_df.copy()
# for every row in the dataframe, iterate through the list of genres and place a 1 into the corresponding column
for index, row in movies_df.iterrows():
    for genre in row['genres']:
        moviesWithGenres_df.at[index, genre] = 1
# fill in the NaN values with 0 to show that a movie doesn't have that column's genre
moviesWithGenres_df = moviesWithGenres_df.fillna(0)
ratings_df = ratings_df.drop('timestamp', 1)

userInput = [
    {'title': 'Breakfast Club, The', 'rating': 5},
    {'title': 'Toy Story', 'rating': 3.5},
    {'title': 'Jumanji', 'rating': 2},
    {'title': "Pulp Fiction", 'rating': 5},
    {'title': 'Akira', 'rating': 4.5}
]
inputMovies = pd.DataFrame(userInput)
inputId = movies_df[movies_df['title'].isin(inputMovies['title'].tolist())]
inputMovies = pd.merge(inputId, inputMovies)
inputMovies = inputMovies.drop('genres', 1).drop('year', 1)
userMovies = moviesWithGenres_df[moviesWithGenres_df['movieId'].isin(
    inputMovies['movieId'].tolist())]
userMovies = userMovies.reset_index(drop=True)
userGenreTable = userMovies.drop('movieId', 1).drop(
    'title', 1).drop('genres', 1).drop('year', 1)
# dot produt to get weights
userProfile = userGenreTable.transpose().dot(inputMovies['rating'])
genreTable = moviesWithGenres_df.set_index(moviesWithGenres_df['movieId'])
genreTable = genreTable.drop('movieId', 1).drop(
    'title', 1).drop('genres', 1).drop('year', 1)
# multiply the genres by the weights and then take the weighted average
recommendationTable_df = (
    (genreTable*userProfile).sum(axis=1))/(userProfile.sum())
# sort the recommendations in descending order
recommendationTable_df = recommendationTable_df.sort_values(ascending=False)
# final recommendation table
movies_df.loc[movies_df['movieId'].isin(
    recommendationTable_df.head(20).keys())]
