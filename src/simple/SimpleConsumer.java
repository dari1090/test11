package kr.co.bizframe.camel.simple;

import org.apache.camel.Endpoint;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultConsumer;

public class SimpleConsumer extends DefaultConsumer {


	public SimpleConsumer(SimpleEndpoint endpoint, Processor processor){
		super(endpoint, processor);
		
		System.out.println("SimpleConsumer created.!");
		SimpleServer server = new SimpleServer(this);
		server.start();
	}
	
	@Override
	protected void doStart() throws Exception {
		super.doStart();
	}
}
