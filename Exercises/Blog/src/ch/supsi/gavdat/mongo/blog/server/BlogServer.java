package ch.supsi.gavdat.mongo.blog.server;

import ch.supsi.gavdat.mongo.blog.dao.BlogPostDAO;
import ch.supsi.gavdat.mongo.blog.dao.UserDAO;
import ch.supsi.gavdat.mongo.blog.redis.MostVisitedHandler;
import ch.supsi.gavdat.mongo.blog.redis.SessionHandler;
import ch.supsi.gavdat.mongo.blog.server.handler.Blog;
import ch.supsi.web.HandlerSetup;
import ch.supsi.web.Nimble;
import ch.supsi.web.Request;
import ch.supsi.web.Server;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import java.util.Map;

public class BlogServer implements HandlerSetup {

	private static final String DB = "blog";
	private static final String MONGO_URI = "mongodb://localhost";

	private BlogPostDAO blogPostDAO;
	private UserDAO userDAO;
	private SessionHandler sessionHandler;
	private MostVisitedHandler mostVisitedHandler;

	public static void main(String[] args) throws Exception {
		Server server = new Server();
		BlogServer elevatorServer = new BlogServer();
		server.Start(8082, elevatorServer, new Blog(), elevatorServer);
	}

	@Override
	public void init() {
		MongoClient mongoClient = MongoClients.create(MONGO_URI);
		final MongoDatabase blogDatabase = mongoClient.getDatabase(DB);
		blogPostDAO = new BlogPostDAO(blogDatabase);
		userDAO = new UserDAO(blogDatabase);
		sessionHandler = new SessionHandler();
		mostVisitedHandler = new MostVisitedHandler();
	}

	@Override
	public void preHandle(Request request) {
		request.setAttribute("blogPostDAO", blogPostDAO);
		request.setAttribute("userDAO", userDAO);
		request.setAttribute("sessionHandler", sessionHandler);
		request.setAttribute("mostVisitedHandler", mostVisitedHandler);
		String sessionID = request.getCookie("JSESSIONID");
		if(	request.getSessionAttribute(Nimble.SESSION_USER) == null){
			String username = sessionHandler.getUser(sessionID);
			request.setSessionAttribute(Nimble.SESSION_USER, username);
		}
		sessionHandler.updateExpiration(sessionID);
	}

	@Override
	public void postHandle(Request request) {

	}	

	@Override
	public void manageMap(Map<String, Object> map, Request request) {
		String username = request.getSessionAttribute(Nimble.SESSION_USER);
		if(username != null){
			map.put("sessionUser", request.getSessionAttribute(Nimble.SESSION_USER));
		}
		map.put("request", request);
	}

}
