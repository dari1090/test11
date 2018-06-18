package kr.co.bizframe.camel.simple;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;

public class SimpleProducer extends DefaultProducer {
	
	
	public SimpleProducer(Endpoint endpoint) {
		super(endpoint);
	}
	
	public void process(Exchange exchange) throws Exception {
		
		String msg = exchange.getIn().getBody().toString();
		SimpleClient sc = new SimpleClient();
		sc.send(msg);
	}

	  
}
