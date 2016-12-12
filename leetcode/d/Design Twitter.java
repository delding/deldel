/**
 Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:

 postTweet(userId, tweetId): Compose a new tweet.
 getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
 follow(followerId, followeeId): Follower follows a followee.
 unfollow(followerId, followeeId): Follower unfollows a followee.
 Example:

 Twitter twitter = new Twitter();

 // User 1 posts a new tweet (id = 5).
 twitter.postTweet(1, 5);

 // User 1's news feed should return a list with 1 tweet id -> [5].
 twitter.getNewsFeed(1);

 // User 1 follows user 2.
 twitter.follow(1, 2);

 // User 2 posts a new tweet (id = 6).
 twitter.postTweet(2, 6);

 // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
 // Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
 twitter.getNewsFeed(1);

 // User 1 unfollows user 2.
 twitter.unfollow(1, 2);

 // User 1's news feed should return a list with 1 tweet id -> [5],
 // since user 1 is no longer following user 2.
 twitter.getNewsFeed(1);
 **/



/**
 *  Pull Mode optimize for write tweet, slow for read timeline
 */
public class Twitter {
	static int timestamp = 0;
	static class Tweet {
		int id;
		int ts;
		int userId;
		Tweet(int id, int ts, int userId) {
			this.id = id;
			this.ts = ts;
			this.userId = userId;
		}
	}
	static class User {
		int id;
		Deque<Tweet> tweets = new LinkedList<>();
		Set<Integer> followees = new HashSet<>();
		User(int id) {
			this.id = id;
		}
		void postTweet(int tweetId, int ts) {
			tweets.addFirst(new Tweet(tweetId, ts, id));
		}
		Iterator<Tweet> tweetIt() {
			return tweets.iterator();
		}
	}

	Map<Integer, User> userTable = new HashMap<>();

	/** Initialize your data structure here. */
	public Twitter() {

	}

	/** Compose a new tweet. */
	public void postTweet(int userId, int tweetId) {
		if (!userTable.containsKey(userId)) userTable.put(userId, new User(userId));
		userTable.get(userId).postTweet(tweetId, timestamp++);
	}

	/** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
	public List<Integer> getNewsFeed(int userId) {
		if (!userTable.containsKey(userId)) return new ArrayList<>();
		Map<Integer, Iterator<Tweet>> userToTweets = new HashMap<>();
		userToTweets.put(userId, userTable.get(userId).tweetIt());
		for (int id : userTable.get(userId).followees) {
			if (userTable.containsKey(id)) {
				userToTweets.put(id, userTable.get(id).tweetIt());
			}
		}
		PriorityQueue<Tweet> pq = new PriorityQueue<Tweet>((t1, t2) -> t2.ts - t1.ts);
		for (Iterator<Tweet> it : userToTweets.values()) {
			if (it.hasNext()) pq.add(it.next());
		}
		List<Integer> ret = new ArrayList<>();
		while (ret.size() < 10 && !pq.isEmpty()) {
			Tweet newest = pq.poll();
			ret.add(newest.id);
			Iterator<Tweet> it = userToTweets.get(newest.userId);
			if (it.hasNext()) pq.add(it.next());
		}
		return ret;
	}

	/** Follower follows a followee. If the operation is invalid, it should be a no-op. */
	public void follow(int followerId, int followeeId) {
		User follower = userTable.get(followerId);
		if (follower == null) {
			follower = new User(followerId);
			userTable.put(followerId, follower);
		}
		follower.followees.add(followeeId);
	}

	/** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
	public void unfollow(int followerId, int followeeId) {
		User follower = userTable.get(followerId);
		if (follower == null) return;
		follower.followees.remove(followeeId);
	}
}

/**
 * Push mode design, optimize for read timeline
 */
class TwitterPushMode {
	static int timestamp = 0;
	static class User {
		int uid;
		int ts;
		User(int uid) {
			this.uid = uid;
			this.ts = timestamp++;
		}
	}

	static class Tweet {
		int tid;
		int uid;
		int ts;
		Tweet(int tid, int uid) {
			this.tid = tid;
			this.uid = uid;
			this.ts = timestamp++;
		}
	}

	Map<Integer, User> users = new HashMap<>(); // user table, indexed by uid
	Map<Integer, Tweet> tweets = new HashMap<>(); // tweet table, indexed by tid
	Map<Integer, List<Integer>> timelines = new HashMap<>(); // timeline cache, indexed by uid

	// TreeMap<Edge> sorted by source end would be better
	Map<Integer, Set<Integer>> followers = new HashMap<>(); // social graph(edge table), indexed by followee's id
	Map<Integer, Set<Integer>> follows = new HashMap<>(); // social graph(edge table), indexed by follower's id

	/** Initialize your data structure here. */
	public TwitterPushMode() {

	}

	/** Compose a new tweet. */
	public void postTweet(int userId, int tweetId) { // Push mode, push tweet to all followers' timeline
		Tweet tt = new Tweet(tweetId, userId);
		tweets.put(tweetId, tt); // add to tweet table
		if (!users.containsKey(userId)) users.put(userId, new User(userId)); // update user table
		if (!timelines.containsKey(userId)) timelines.put(userId, new ArrayList<Integer>());
		timelines.get(userId).add(tweetId); // user's own timeline
		for (int id : followers.get(userId)) { // push to follwers' timeline
			timelines.get(id).add(tweetId);
		}
	}

	/** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
	public List<Integer> getNewsFeed(int userId) {
		List<Integer> timeline = timelines.get(userId);
		List<Integer> ret = new ArrayList<>();
		for (int i = 0; i < Math.min(10, timeline.size()); i++) {
			ret.add(timeline.get(timeline.size() - 1 - i));
		}
		return ret;
	}


	// problems with Push mode is follow and unfollow would also need to change timeline for correctness
	/** Follower follows a followee. If the operation is invalid, it should be a no-op. */
	public void follow(int followerId, int followeeId) {
		if (users.containsKey(followerId) && users.containsKey(followeeId)) {
			if (!follows.containsKey(followerId)) {
				follows.put(followerId, new HashSet<Integer>());
			}
			follows.get(followerId).add(followeeId);
			if (!followers.containsKey(followeeId)) {
				follows.put(followeeId, new HashSet<Integer>());
			}
			followers.get(followeeId).add(followerId);
		}
	}

	/** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
	public void unfollow(int followerId, int followeeId) {
		if (users.containsKey(followerId) && users.containsKey(followeeId)) {
			if (follows.containsKey(followerId)) {
				follows.get(followerId).remove(followeeId);
			}
			if (followers.containsKey(followeeId)) {
				followers.get(followeeId).remove(followerId);
			}
		}
	}
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */