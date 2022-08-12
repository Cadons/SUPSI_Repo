package ch.supsi.gavdat.mongo.blog.server.handler;

import ch.supsi.gavdat.mongo.blog.dao.BlogPostDAO;
import ch.supsi.gavdat.mongo.blog.dao.UserDAO;
import ch.supsi.gavdat.mongo.blog.dao.exception.PostNotFoundException;
import ch.supsi.web.Model;
import ch.supsi.web.Nimble;
import ch.supsi.web.Request;
import ch.supsi.web.Response;
import ch.supsi.web.annotation.*;
import ch.supsi.web.annotation.httpMethod.GET;
import ch.supsi.web.annotation.httpMethod.POST;
import ch.supsi.web.annotation.redirectTo.ThenRedirectTo;
import freemarker.template.SimpleHash;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/")
public class Blog {

	@GET
	@FreeMarkerTemplate("blog_template.ftl")
	public static Model home(@RequestAttribute("blogPostDAO") BlogPostDAO postDAO) {
		return Model.add("myposts", postDAO.findByDateDescending(10));
	}

	@Path("/post/{permalink}")
	@GET
	@FreeMarkerTemplate("entry_template.ftl")
	public static Model post(@RequestAttribute("blogPostDAO") BlogPostDAO postDAO, @PathParam("permalink") String permalink) {
		Document post = postDAO.findByPermalink(permalink);
		if (post == null) {
			throw new PostNotFoundException(permalink);
		} else {
			return Model.add("post", post);
		}
	}

	@Path("/signup")
	@GET
	public static Model signup() {
		return Model.empty();
	}

	@Path("/signup")
	@POST
	public static Map<String, Object> signup(@RequestAttribute("userDAO") UserDAO userDAO, @RequestParam("email") String email,
			@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("verify") String verify, Response response,
			Request request) throws IOException {

		HashMap<String, Object> model = new HashMap<String, Object>();
		model.put("username", username);
		model.put("email", email);

		if (validateSignup(username, password, verify, email, model)) {
			// good user
			if (!userDAO.addUser(username, password, email)) {
				// duplicate user
				model.put("username_error", "Nome utente già in uso.");
				return model;
			} else {
				// good user, let's start a session
				request.setSessionAttribute(Nimble.SESSION_USER, username);
				response.redirect("/newpost");
				return null;
			}
		} else {
			// bad signup
			return model;
		}
	}

	@Path("/newpost")
	@GET
	@FreeMarkerTemplate("newpost_template.ftl")
	@Authorize
	public static Model newpost() {
		return Model.empty();
	}

	@Path("/newpost")
	@POST
	@FreeMarkerTemplate("newpost_template.ftl")
	@Authorize
	public static Model newpost(@RequestAttribute("blogPostDAO") BlogPostDAO postDAO, @RequestParam("subject") String title, @RequestParam("body") String post,
			@RequestParam("tags") String tags, Request request, Response response) throws IOException {

		if (title.equals("") || post.equals("")) {
			// redisplay page with errors
			return Model.add("errors", "Il post deve avere un titolo e del testo.").plus("subject", title).plus("tags", tags).plus("body", post);
		} else {
			// extract tags
			ArrayList<String> tagsArray = extractTags(tags);

			// substitute some <p> for the paragraph breaks
			post = post.replaceAll("\\r?\\n", "<p>");

			String permalink = postDAO.addPost(title, post, tagsArray, request.getSessionAttribute(Nimble.SESSION_USER));

			// now redirect to the blog permalink
			response.redirect("/post/" + permalink);
			return null;
		}
	}
	
	@Path("/newcomment")
	@POST
	@FreeMarkerTemplate("entry_template.ftl")
	public static Model newcomment(@RequestAttribute("blogPostDAO") BlogPostDAO postDAO, @RequestParam("commentName") String name,
			@RequestParam("commentEmail") String email, @RequestParam("commentBody") String body, @RequestParam("permalink") String permalink, Response response)
			throws IOException {

		Document post = postDAO.findByPermalink(permalink);
		if (post == null) {
			throw new PostNotFoundException(permalink);
		}
		// check that comment is good
		else if (name.equals("") || body.equals("")) {
			// bounce this back to the user for correction
			SimpleHash comment = new SimpleHash();
			comment.put("name", name);
			comment.put("email", email);
			comment.put("body", body);

			return Model.add("errors", "Deve contenere un nome e un commento").plus("post", post).plus("comment", comment);
		} else {
			postDAO.addPostComment(name, email, body, permalink);
			response.redirect("/post/" + permalink);
			return null;
		}
	}

	// present the login page
	@Path("/login")
	@GET
	public static Model login() {
		return Model.empty();
	}

	@Path("/login")
	@POST
	public static Model login(@RequestAttribute("userDAO") UserDAO userDAO, @RequestParam("username") String username,
			@RequestParam("password") String password, Request request, Response response) throws IOException {

		Document user = userDAO.validateLogin(username, password);

		if (user != null) {
			request.setSessionAttribute(Nimble.SESSION_USER, username);
			response.redirect("/newpost");
			return null;
		} else {
			return Model.add("username", username).plus("password", "").plus("login_error", "Login non valido");
		}
	}

	// Show the posts filed under a certain tag
	@GET
	@Path("/tag/{thetag}")
	@FreeMarkerTemplate("blog_template.ftl")
	public static Model tag(@RequestAttribute("blogPostDAO") BlogPostDAO blogPostDAO, @PathParam("thetag") String tag) {
		List<Document> posts = blogPostDAO.findByTagDateDescending(tag);
		return Model.add("myposts", posts);
	}

	// allows the user to logout of the blog
	@GET
	@Path("/logout")
	@ThenRedirectTo("/")
	public static void logout(Request request) {
		request.invalidateSession();
	}

	private static ArrayList<String> extractTags(String tags) {

		tags = tags.replaceAll("\\s", "");
		String tagArray[] = tags.split(",");

		ArrayList<String> cleaned = new ArrayList<String>();
		for (String tag : tagArray) {
			if (!tag.equals("") && !cleaned.contains(tag)) {
				cleaned.add(tag);
			}
		}

		return cleaned;
	}

	private static boolean validateSignup(String username, String password, String verify, String email, HashMap<String, Object> errors) {
		String USER_RE = "^[a-zA-Z0-9_-]{3,20}$";
		String PASS_RE = "^.{3,20}$";
		String EMAIL_RE = "^[\\S]+@[\\S]+\\.[\\S]+$";

		errors.put("username_error", "");
		errors.put("password_error", "");
		errors.put("verify_error", "");
		errors.put("email_error", "");

		if (!username.matches(USER_RE)) {
			errors.put("username_error", "Nome utente non valido. Prova solo lettere e numeri.");
			return false;
		}

		if (!password.matches(PASS_RE)) {
			errors.put("password_error", "Password non valida.");
			return false;
		}

		if (!password.equals(verify)) {
			errors.put("verify_error", "Le password devono coincidere.");
			return false;
		}

		if (!email.equals("")) {
			if (!email.matches(EMAIL_RE)) {
				errors.put("email_error", "Email non valido.");
				return false;
			}
		}

		return true;
	}

}