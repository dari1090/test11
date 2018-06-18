package kr.co.bizframe.camel.simple;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.UUID;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Processor;

public class SimpleWorker2 extends Thread {

	private SimpleConsumer4 consumer;
	
	
	public SimpleWorker2(SimpleConsumer4 consumer){
		this.consumer = consumer;
	}
	
	
	public void run(){
		
		try{
			
			while(true){
				Thread.sleep(5000);
				poll();
				Thread.sleep(5000);
			}
			
			//poll();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	private void poll() throws Exception {
		System.out.println("===========================================================================");
		Exchange exchange = consumer.getEndpoint().createExchange();

		// create a message body
		exchange.getIn().setBody(UUID.randomUUID().toString());
			
		System.out.println("exchange id=" + exchange.getExchangeId());
		System.out.println("exchange from route id=" + exchange.getFromRouteId());
		System.out.println("exchange exchange pattern=" + exchange.getPattern());
	
		
		try {
			// send message to next processor in the route
			consumer.getProcessor().process(exchange);
			System.out.println("called processor");
		} finally {
			// log exception if an exception occurred and was not handled
			if (exchange.getException() != null) {
				consumer.getExceptionHandler().handleException("Error processing exchange", exchange, exchange.getException());
			}
		}
	}
	
	
	
}
