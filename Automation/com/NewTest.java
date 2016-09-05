package com;

import org.testng.annotations.Test;

 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.DataProvider;

public class NewTest {
	
	 private final String USER_AGENT = "Mozilla/5.0";

  @Test
  public void f() throws Exception {
	  
 
	 login();
	  
  }
  
  @Test
  public void f1() throws Exception {
	  
 
	  register();
	  
  }
  
  
  
  
  
  private static void login() throws Exception {
		 
		 String requestUrl = "http://www.orbitpage.com/Auth/Login";
		 

		 String payload = "{\"Username\":\"abhinav\",\"Password\":\"Abhi999@\",\"Type\":\"web\",\"KeepMeSignedInCheckBox\":true}";
	 
		 postWorker(requestUrl, payload);
		 
	 }
	 
  private static void register() throws Exception {
		 
		 String requestUrl = "http://www.orbitpage.com/Auth/CreateAccount";
		 

		 String payload = "{\"FirstName\":\"user F name\",\"LastName\":\"user L Name\",\"Gender\":\"male\",\"EmailId\":\"username@aa.com\",\"Username\":\"username\",\"Password\":\"Abhinav\",\"Source\":\"web\",\"Referral\":\"NA\"}";
	 
		 postWorker(requestUrl, payload);
		 
	 }
  
  private static void postWorker(String requestUrl, String payload) throws Exception {
		

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
	 	 
	  }

  
}
