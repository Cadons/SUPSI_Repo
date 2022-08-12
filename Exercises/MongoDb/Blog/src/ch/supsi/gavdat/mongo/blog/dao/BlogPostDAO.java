package ch.supsi.gavdat.mongo.blog.dao;

import ch.supsi.gavdat.mongo.blog.dao.exception.NotImplementedException;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlogPostDAO {
    MongoCollection<Document> postsCollection;

    public BlogPostDAO(final MongoDatabase blogDatabase) {
        postsCollection = blogDatabase.getCollection("posts");
    }

    public Document findByPermalink(String permalink) {
        // TODO DA IMPLEMENTARE
        BasicDBObject where=new BasicDBObject();
        where.put("permalink",permalink);
        return postsCollection.find(where).first();
    }

    public List<Document> findByDateDescending(int limit) {

        return postsCollection.find().sort(Sorts.descending("date")).limit(limit).into(new ArrayList<>());
    }

    public List<Document> findByTagDateDescending(final String tag) {
        // TODO DA IMPLEMENTARE
        return postsCollection.find(Filters.eq("tags",tag)).sort(Sorts.descending("date")).limit(10).into(new ArrayList<>());

    }

    public String addPost(String title, String body, List<String> tags, String username) {

        //System.out.println("inserting blog entry " + title + " " + body);

        String permalink = title.replaceAll("\\s", "_"); // whitespace becomes _
        permalink = permalink.replaceAll("\\W", ""); // get rid of non alphanumeric
        permalink = permalink.toLowerCase();

        // TODO DA IMPLEMENTARE
        postsCollection.insertOne(new Document()
                .append("title",title)
                .append("body",body)
                .append("tags",tags)
                .append("author",username)
                .append("permalink",permalink)
                .append("comments",new ArrayList<>())
                .append("date",new Date()));
        // deve ritornare il permalink
        return permalink;
    }

    public void addPostComment(final String name, final String email, final String body, final String permalink) {
        // TODO DA IMPLEMENTARE
        // usare update e push, in modo da aggiungere alla lista di commenti il nuovo commento

        postsCollection.updateOne(Filters.eq("permalink",permalink), Updates.push("comments",new Document().append("author",name).append("email",email).append("body",body)));

       // throw new NotImplementedException();
    }

}
