package com;

import org.testng.annotations.Test;

 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

public class NewTest {
	
	 private final String USER_AGENT = "Mozilla/5.0";

  @Test
  public void LoginMyUser() throws Exception {
	  
 
	 login();
	  
  }
  
  @Test
  public void RegisterNewUser() throws Exception {
	  
 
 register();
	  
  }
  
  
  
  
  
  private static void login() throws Exception {
		 
		 String requestUrl = "http://www.orbitpage.com/Auth/Login";
		 

		 String payload = "{\"Username\":\"abhinav\",\"Password\":\"abhinav\",\"Type\":\"web\",\"KeepMeSignedInCheckBox\":true}";
	 
		 String responce=	 postWorker(requestUrl, payload);
		 
		 JSONParser parser = new JSONParser();

		  JSONObject newobj= (JSONObject) parser.parse(responce);  
		 String user=null;
		  
		  JSONObject oneob =(JSONObject) newobj.get("Payload");
		     user=oneob.get("Username").toString();

		  
		  
		 if(user.equals("abhinav"))
		 {
			 Assert.assertTrue(true);
		 }
		 else
		 {
			 Assert.assertTrue(false);
 
		 }
		 
	 }

  
  
  
   
  
  
  
  private static void register() throws Exception {
		 
		 String requestUrl = "http://www.orbitpage.com/Auth/CreateAccount";
		 

		 String payload = "{\"FirstName1\":\"user F name\",\"LastName\":\"user L Name\",\"Gender\":\"male\",\"EmailId\":\""
		 		+ "DeleteThisUser+"+System.currentTimeMillis()/1000+"@gmail.com"
		 		+ "\",\"Username\":\"user"
		 		+ System.currentTimeMillis()/1000+"\",\"Password\":\"Abhinav\",\"Source\":\"web\",\"Referral\":\"NA\"}";
		 
	 System.out.println(payload);
	 
	 System.out.println();
	 
	String responce=	 postWorker(requestUrl, payload);
	
	
	 JSONParser parser = new JSONParser();

	  JSONObject newobj= (JSONObject) parser.parse(responce);  
	 
	String assetValue=  newobj.get("Message").toString();
	  
		 if(assetValue.equals("Registered Successfully."))
		 {
			 Assert.assertTrue(true);
		 }
		 else
		 {
			 Assert.assertTrue(false);
 
		 }
	 }
  
  private static String postWorker(String requestUrl, String payload) throws Exception {
		

	  URL url = new URL(requestUrl);
	  HttpURLConnection connection = (HttpURLConnection) url.openConnection();

	  connection.setDoInput(true);
	  connection.setDoOutput(true);
	  connection.setRequestMethod("POST");
	  connection.setRequestProperty("Accept", "application/json");
	  connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	  OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
	  
	  writer.write(payload);
	  writer.close();
	  BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	  StringBuffer jsonString = new StringBuffer();
	  String line;
	  while ((line = br.readLine()) != null) {
	          jsonString.append(line);
	   }
	  br.close();
	 int responseCode = connection.getResponseCode();
	 System.out.println("Responce Code: "+responseCode );
	  connection.disconnect();
	  
	  System.out.println( jsonString.toString()); 
	  return  jsonString.toString();
	 	 
	  }

  
}
