package main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@SuppressWarnings("serial")
public class Output extends HttpServlet {
	private final String clientId = "local.56c178d9cd0d04.25001330";

	private final String clientSecret = "317c7ea6bfdf927169195ecd5c5876c6";
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, Sandip");
		 String code = req.getParameter("code");
			
			resp.getWriter().println("Code:"+code);
	
				
		StringBuilder oauthUrl = new StringBuilder().append("https://bhajiworld.bitrix24.com/oauth/token/")
				   .append("?client_id=").append(clientId) // the client id from the api console registration
				   .append("&grant_type=authorization_code")
				   .append("&client_secret=").append(clientSecret)
				   .append("&redirect_uri=http://demofirst-1245.appspot.com/output")
				   .append("&code=").append(code)
				   .append("&scope=user,crm");
		 try {
			
				URL url = new URL(oauthUrl.toString());
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
				StringBuilder builder = new StringBuilder();
				String output;
				
				
				while ((output = br.readLine()) != null) {
					
					/*resp.getWriter().println(output);*/
					builder.append(output).append("\n");
					
				}
				
				/*resp.getWriter().println(builder.toString());*/
				JSONParser parser = new JSONParser();
				 
				try {
					Object	obj = parser.parse(builder.toString());
					JSONObject jsonObject = (JSONObject) obj;
					String at = (String) jsonObject.get("access_token");
					resp.getWriter().println("access_token:"+at);
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		         
			
				
				
				conn.disconnect();
		
			  }catch (MalformedURLException e) {

				e.printStackTrace();

			  } catch (IOException e) {

				e.printStackTrace();

			  }

/*		 RequestDispatcher rd=req.getRequestDispatcher("final.html");  
	        try {
				rd.forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} */
		 
		 

			
			
	}
}
