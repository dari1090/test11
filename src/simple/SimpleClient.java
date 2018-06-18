package kr.co.bizframe.camel.simple;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class SimpleClient {

	private String address = "localhost";
	
	private int port = 9090;
	
	public SimpleClient(){
		
	}
	
	
	public String send(String msg){
		
		try{
		     Socket s = new Socket(address, port);
		     BufferedReader input =
			            new BufferedReader(new InputStreamReader(s.getInputStream()));
		     PrintWriter out = new PrintWriter(s.getOutputStream(), true);

		     out.println(msg);
		     String response = input.readLine();
			 System.out.println("response = " + response);       
		     return response;
		}catch(Exception e){
			e.printStackTrace();
		}		
		return null;
	
	}
	
	
	public static void main(String[] argv){
		
		SimpleClient sc = new SimpleClient();
		sc.send("test");
	}
}
