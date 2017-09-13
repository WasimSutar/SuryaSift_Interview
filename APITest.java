package com.suryasoft.webservice;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.json.JSONException;
import org.json.JSONObject;
public class APITest implements Callable<String> {

	private static final String URL = "http://surya-interview.appspot.com/message";

	 static String post_param = "";
	 static ArrayList<Long> timearray = new ArrayList<Long>();
	 long sum=0;
	int k=0;
	public static void main(String[] args)  {
		//Get ExecutorService from Executors utility class, thread pool size is 10
        ExecutorService executor = Executors.newFixedThreadPool(10);
        //create a list to hold the Future object associated with Callable
        List<Future<String>> list = new ArrayList<Future<String>>();
        //Create MyCallable instance
        Callable<String> callable = new APITest();
        for(int i=0; i< 100; i++){
            //submit Callable tasks to be executed by thread pool
            Future<String> future = executor.submit(callable);
            //add Future to the list, we can get return value using Future
            list.add(future);
        }        
        executor.shutdown();
        
    }
		

	private static void executeService() {
		// TODO Auto-generated method stub
		try {
			//System.out.println("***********GET Response Start**************\n");			
			post_param = sendGET();
			JSONObject myObject = new JSONObject(post_param);
			//System.out.println(myObject);
			//System.out.println("\n***********GET Response End**************\n\n");
			//System.out.println("***********POST Response Start**************\n");
			sendPOST(myObject);
			//System.out.println("\n***********POST Response End**************");
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
		
	

	private static String sendGET() throws IOException {
		StringBuffer response = new StringBuffer();
		URL obj = new URL(URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("X-Surya-Email-Id", "waseemsutar@gmail.com");
		int responseCode = con.getResponseCode();
		String responseMessage = con.getResponseMessage();
		//System.out.println(responseCode + "\t" +responseMessage );
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			String ctype = con.getContentType();
			
			
			
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			
			String inputLine;
			

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		} else {
			System.out.println("GET request not worked");
		}
		return response.toString();
	}

	private static void sendPOST(JSONObject param) throws IOException {
		URL obj = new URL(URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");

		// For POST only - START
		con.setDoOutput(true);
		String jsonText = param.toString();
		OutputStream os = con.getOutputStream();
		os.write(jsonText.getBytes());
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		//System.out.println( responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			//System.out.println(response.toString());
		} else {
			System.out.println("POST request not worked");
		}
	}


	public String call() throws Exception {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();
		executeService();
		long endTime = System.currentTimeMillis();
		long elapsedTime = System.currentTimeMillis() - startTime;
		System.out.println("Request " + ++k + " took " + elapsedTime + " milliseconds to return response " );
		timearray.add(elapsedTime);
		if (k == 100){
			for(int i = 1; i < timearray.size(); i++)
				sum += timearray.get(i);
			long mean = sum /100;
			
			System.out.println("Mean = "+mean);
			
			// standard daviation
			long sum_var=0;
			for(int i = 1; i < timearray.size(); i++){
				
				long dif = (timearray.get(i) - mean) * (timearray.get(i) - mean);
				
				sum_var+=dif;
				
			}
			
			float variance = (float) ((0.01) * sum_var);
			
			double standard_daviation = Math.sqrt(variance);
			
			System.out.println("Standard daviation : "+standard_daviation);
				
		}
		return "";
	}

}