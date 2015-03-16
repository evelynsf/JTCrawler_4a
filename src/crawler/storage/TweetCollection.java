package crawler.storage;

import java.net.UnknownHostException;
import org.bson.Document;

import twitter4j.Status;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
/**
 * Implements a data collection of tweets - 
 * @author Evelyn
 *
 */
public class TweetCollection{
	
	private String collectionName;
	private String dbname;
	private MongoClient client;
	private MongoDatabase tweetDB;
	private MongoCollection<Document> collection;

	public TweetCollection(String collection, String db) throws UnknownHostException{
		
		this.collectionName = collection;
		this.dbname = db;
		
		this.client = new MongoClient();
		
		this.tweetDB = client.getDatabase(dbname);
		this.collection = this.tweetDB.getCollection(collectionName);	
	}
	
	/*
	 * inserts full status object
	 */
	public void insertFullStatus(Status status){
		Document doc = new Document().append("status", status);
		this.collection.insertOne(doc);
	}
	
	public void insert(Status status){
		
		Document document = new Document().append("id",status.getId())
				.append("username", status.getUser().getName())
				.append("userScreenName", status.getUser().getScreenName())
				.append("timestamp", status.getCreatedAt().toString())
				.append("text", status.getText())
				.append("language", status.getLang())
				.append("location", status.getUser().getLocation())
				/*
				.append("geoLocation", status.getGeoLocation())
				.append("place", status.getPlace()) //Place has many methods (including getCountry() )
				.append("user", status.getUser()) //Object user
				.append("accessLevel", status.getAccessLevel())
				.append("contributors", status.getContributors())
				.append("favoritedCount", status.getCurrentUserRetweetId())
				.append("inReplyToScreenName", status.getInReplyToScreenName())
				.append("inReplyToStatusID", status.getInReplyToStatusId())
				.append("inReplyToUserID", status.getInReplyToUserId())
				.append("retweetCount", status.getRetweetCount())
				.append("hashCode", status.hashCode())
				.append("source", status.getSource())
				.append("isFavorited", status.isFavorited())
				.append("isPossiblySensitive", status.isPossiblySensitive())
				.append("isRetweet", status.isRetweet())
				.append("isRetweeted", status.isRetweeted())
				.append("isRetweetedByMe", status.isRetweetedByMe())
				.append("isTruncated", status.isTruncated())
				.append("hashtagEntities", status.getHashtagEntities())
				.append("scopes", status.getScopes())
				.append("URLEntities", status.getURLEntities())
				.append("symbolEntities", status.getSymbolEntities())
				.append("userMentionEntities", status.getUserMentionEntities())
				.append("mediaEntities", status.getMediaEntities())
				.append("extendedMediaEntities", status.getExtendedMediaEntities())
				*/
		;
		this.collection.insertOne(document);
	}
	

}
