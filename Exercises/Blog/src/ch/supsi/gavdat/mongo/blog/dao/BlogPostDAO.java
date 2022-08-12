package ch.supsi.gavdat.mongo.blog.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BlogPostDAO {
    MongoCollection<Document> postsCollection;

    public static String COMMENTS="comments";

    public BlogPostDAO(final MongoDatabase blogDatabase) {
        postsCollection = blogDatabase.getCollection("posts");
    }

    public Document findByPermalink(String permalink) {
    	//return  postsCollection.find(new Document("permalink", permalink)).first();
        return postsCollection.find(Filters.eq("permalink", permalink)).first();
    }

    public List<Document> findByDateDescending(int limit) {
    	//return  postsCollection.find().sort(new Document("date", -1)).limit(limit).into(new ArrayList<>());
        return  postsCollection.find().sort(Sorts.descending("date")).limit(limit).into(new ArrayList<>());
    }

    public List<Document> findByTagDateDescending(final String tag) {
        //return postsCollection.find(new Document("tags", tag)).sort(new Document().append("date", -1)).limit(10).into(new ArrayList<>());
        return postsCollection.find(Filters.eq("tags", tag)).sort(Sorts.descending("date", "-1")).limit(10).into(new ArrayList<>());
    }

    public String addPost(String title, String body, List<String> tags, String username) {
        String permalink = title.replaceAll("\\s", "_"); // whitespace becomes _
        permalink = permalink.replaceAll("\\W", ""); // get rid of non alphanumeric
        permalink = permalink.toLowerCase()+"_"+ UUID.randomUUID();

        Document post = new Document("title", title);
        post.append("author", username);
        post.append("body", body);
        post.append("permalink", permalink);
        post.append("tags", tags);
        post.append(COMMENTS, new java.util.ArrayList<String>());
        post.append("date", new java.util.Date());

        postsCollection.insertOne(post);

        return permalink;
    }

    public void addPostComment(final String name, final String email, final String body, final String permalink) {
    	Document comment = new Document("author", name).append("body", body);
        if (email != null && !email.equals("")) {
            comment.append("email", email);
        }
        postsCollection.updateOne(new Document("permalink", permalink), new Document("$push", new Document(COMMENTS, comment)));
        //postsCollection.updateOne(Filters.eq("permalink", permalink), Updates.push("comments", comment));
    }

}
