package ch.supsi.gavdat.mongo.blog.dao.exception;

public class PostNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2236168758313859515L;
	
	public PostNotFoundException(String permalink) {
		super("Post with permalink "+permalink+" not found");
	}
	
}
