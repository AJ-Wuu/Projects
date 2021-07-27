/**
 * @author AJWuu
 *
 * Adapted from #355 - Design Twitter
 */

package twitter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Twitter {

	public HashMap<Integer, User> map;
	public int orderNum = 0; //Record the lateness (larger == newer)
	public int feed = 10; //The latest 10 tweets will be shown

	public class Tweet {
		int content;
		int number;

		public Tweet(int content) {
			this.content = content;
			this.number = orderNum++;
		}
	}

	public class User {
		public int ID;
		public HashSet<Integer> Following; //Use HashSet to prevent following the same followee twice
		public LinkedList<Tweet> Tweets;

		public User(int ID) {
			this.ID = ID;
			Following = new HashSet<Integer>();
			Following.add(ID);
			Tweets = new LinkedList<Tweet>();
		}

		public void follow(int followeeID) {
			Following.add(followeeID);
		}

		public void unfollow(int followeeID) {
			Following.remove((Object)followeeID); //It's important to specify that the parameter is an Object, not an index
		}

		public void postTweet(int content) {
			Tweets.addFirst(new Tweet(content));
		}
	}

	//Initialize the data structure
	public Twitter() {
		map = new HashMap<Integer, User>();
	}

	//Compose a new tweet
	public void postTweet(int userID, int tweet) {
		if (!map.containsKey(userID)) {
			map.put(userID, new User(userID));
		}
		map.get(userID).postTweet(tweet);
	}

	//Retrieve the 10 most recent tweet IDs in the user's news feed
	//Each item in the news feed must be posted by users who the user followed or by the user herself
	//Tweets must be ordered from most recent to least recent
	public LinkedList<Integer> getNewsFeed(int userID) {
		if (!map.containsKey(userID)) {
			return (new LinkedList<Integer>());
		}

		//Add all relevant tweets into queue
		Integer[] userNums = map.get(userID).Following.toArray(new Integer[map.get(userID).Following.size()]);
		PriorityQueue<Tweet> queue = new PriorityQueue<Tweet>((a, b) -> (b.number - a.number));
		for (int userNum : userNums) {
			User currUser = map.get(userNum);
			for (int i=0; i<currUser.Tweets.size(); i++) {
				Tweet currTweet = currUser.Tweets.get(i);
				if (currTweet != null) { //It's necessary to make sure the element is not null, otherwise terrible things will happen
					queue.add(currTweet);
				}
			}
		}
		
		//Output the latest 10 tweets
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (int i=0; i<feed; i++) {
			if (queue.isEmpty()) {
				break;
			}
			list.add(queue.poll().content);
		}
		return list;
	}

	//Follower follows a followee
	public void follow(int followerID, int followeeID) {
		if (!map.containsKey(followeeID)) {
			map.put(followeeID, new User(followeeID));
		}
		if (!map.containsKey(followerID)) {
			map.put(followerID, new User(followerID));
		}
		map.get(followerID).follow(followeeID);
	}

	//Follower unfollows a followee
	//If the operation is invalid, it should be a no-operation
	public void unfollow(int followerID, int followeeID) {
		if (!map.containsKey(followerID) || !map.containsKey(followeeID) || followerID == followeeID) {
			return ;
		}
		map.get(followerID).unfollow(followeeID);
	}

	public static void main(String[] args) {
		Twitter myTwitter = new Twitter();
		myTwitter.postTweet(1, 5);
		System.out.println(myTwitter.getNewsFeed(1));
		myTwitter.follow(1, 2);
		myTwitter.postTweet(2, 6);
		System.out.println(myTwitter.getNewsFeed(1));
		myTwitter.unfollow(1, 2);
		System.out.println(myTwitter.getNewsFeed(1));
	}

}
