package kr.co.bizframe.camel.simple;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Processor;

public class SimpleWorker extends Thread {

	private SimpleConsumer consumer;
	
	private Socket socket;
	
	public SimpleWorker(Socket socket, SimpleConsumer consumer){
		this.consumer = consumer;
		this.socket = socket;
	}
	
	
	public void run(){
		
		try {
			
			BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
			String input = in.readLine();
			System.out.println("read line= " + input);  
			
			String response = doHandle(input);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.println(response);
			
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			try{
				socket.close();
			}catch(Exception e){
	
			}
		}
	}
	
	
	private String doHandle(String request){
		System.out.println("doHandle");
		Exchange exchange = consumer.getEndpoint().createExchange();
		exchange.getIn().setBody(request);
		Processor processor = consumer.getProcessor();
		
		/*
		System.out.println("processor = " + processor.getClass().getName());
		org.apache.camel.processor.CamelInternalProcessor p = (org.apache.camel.processor.CamelInternalProcessor)processor;
		
		List<Processor> lp1 = p.next();
		for(Processor ps1 : lp1){
			System.out.println("processor = " + ps1.getClass().getName());
			org.apache.camel.processor.Pipeline ps2 = (org.apache.camel.processor.Pipeline)ps1;
			for(Processor ps3 : ps2.getProcessors()){
				System.out.println("processor = " + ps3.getClass().getName());
			}
		}
		*/
		exchange.setOut(exchange.getIn());
		//exchange.setPattern(ExchangePattern.InOut);
	
		System.out.println("exchange id=" + exchange.getExchangeId());
		System.out.println("exchange from route id=" + exchange.getFromRouteId());
		System.out.println("exchange exchange pattern=" + exchange.getPattern());
	
		try {
			processor.process(exchange);
			//System.out.println("do processed..1!!");
		}catch(Throwable e){
			consumer.getExceptionHandler().handleException("Error processing exchange", exchange, exchange.getException());
		}
		//System.out.println("do processed..2");
	
		return request;
		//return exchange.getOut().getBody().toString();
	}
}
