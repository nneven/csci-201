
import java.util.Properties;    
import javax.mail.*;    
import javax.mail.internet.*;    

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mongodb.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Properties;
import java.util.Arrays;
import java.util.ArrayList;


/**
 * Servlet implementation class Register
 */
@WebServlet("Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String major = request.getParameter("major");
		String gradyear = request.getParameter("gradyear");
		send("mytest.email.201@gmail.com","mytestemail",email,"Message from Viterbi Schedule Builder","Welcome to Viterbi Schedule Builder " + name + "! You have successfully registered an account and can now start saving schedules.");  	
		ConnectionString connString = new ConnectionString(
				"mongodb+srv://password:username@cluster0-v2kcb.gcp.mongodb.net/test?retryWrites=true&w=majority"
			);
		MongoClientSettings settings = MongoClientSettings.builder()
		    .applyConnectionString(connString)
		    .retryWrites(true)
		    .build();
		MongoClient mongoClient = MongoClients.create(settings);
		MongoDatabase database = mongoClient.getDatabase("ViterbiSchedule");
		MongoCollection<Document> majorsCollection = database.getCollection("Majors");
		MongoCollection<Document> usersCollection = database.getCollection("Users");
		
		Document template = majorsCollection.find(eq("_id", major)).first();
		
		Document newUser = new Document("_id", email)
			.append("name", name)
			.append("email", email)
			.append("password",password)
			.append("major", major)
			.append("gradyear", gradyear)
			.append("schedule", template.get("schedule"));
		usersCollection.insertOne(newUser);
		session.setAttribute("user", newUser.toJson());
		session.setAttribute("guest", null);
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/schedule.jsp");
		dispatch.forward(request,response);
	}
	public static void send(final String from,final String password,String to,String sub,String msg){  
        //Get properties object    
        Properties props = new Properties();    
        props.put("mail.smtp.host", "smtp.gmail.com");    
        props.put("mail.smtp.socketFactory.port", "465");    
        props.put("mail.smtp.socketFactory.class",    
                  "javax.net.ssl.SSLSocketFactory");    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.port", "465");    
        //get Session   
        Session session = Session.getDefaultInstance(props,    
         new javax.mail.Authenticator() {    
         protected PasswordAuthentication getPasswordAuthentication() {    
         return new PasswordAuthentication(from,password);  
         }    
        });    
        //compose message    
        try {    
         MimeMessage message = new MimeMessage(session);    
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
         message.setSubject(sub);    
         message.setText(msg);    
         //send message  
         Transport.send(message);    
         System.out.println("message sent successfully");    
        } catch (MessagingException e) {throw new RuntimeException(e);}    
           
  } 
}
