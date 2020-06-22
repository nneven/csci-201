import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mongodb.*;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import com.mongodb.client.model.Sorts;
import java.util.Arrays;
import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Servlet implementation class Profile
 */
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		ConnectionString connString = new ConnectionString(
			"mongodb+srv://password:username@cluster0-v2kcb.gcp.mongodb.net/test?retryWrites=true&w=majority"
		);
		MongoClientSettings settings = MongoClientSettings.builder()
		    .applyConnectionString(connString)
		    .retryWrites(true)
		    .build();
		MongoClient mongoClient = MongoClients.create(settings);
		MongoDatabase database = mongoClient.getDatabase("ViterbiSchedule");
		MongoCollection<Document> users = database.getCollection("Users");
		MongoCollection<Document> majors = database.getCollection("Majors");
		String username = "";
		try {
			JSONObject jo = (JSONObject) new JSONParser().parse((String) session.getAttribute("user"));
			username = (String) jo.get("_id");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document profile = users.find(eq("_id", username)).first();
		Document classes = majors.find(eq("_id", profile.get("major"))).first();
		session.setAttribute("classes", classes.toJson());
		session.setAttribute("user", profile.toJson());
		session.setAttribute("name", profile.get("name"));
		session.setAttribute("major", profile.get("major"));
		session.setAttribute("gradyear", profile.get("gradyear"));
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/Profile.jsp");
        dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	Block<Document> printBlock = new Block<Document>() {
	       public void apply(final Document document) {
	           System.out.println(document.toJson());
	       }
	};

}
