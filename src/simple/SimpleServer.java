package kr.co.bizframe.camel.simple;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class SimpleServer extends Thread  {

	private ServerSocket listener = null;
	
	private SimpleConsumer consumer;
	
	public SimpleServer(SimpleConsumer consumer){
		
		this.consumer = consumer;

		try {
			listener = new ServerSocket(9090);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}


	public void run(){
		
		try {

			while (true) {
				Socket socket = listener.accept();
				SimpleWorker worker = new SimpleWorker(socket, consumer);
				worker.start();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		} 
	}

	
	
	public void close(){
		
		try{
			listener.close();
		}catch(Exception e){
			e.printStackTrace();
		} 
	}
	
	public static void main(String[] argv){
		//SimpleServer ss = new SimpleServer();
		//ss.start();
	}
}
