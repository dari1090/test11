package kr.co.bizframe.camel.simple;

import org.apache.camel.Consumer;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.RuntimeCamelException;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.UriEndpoint;



@UriEndpoint(scheme = "simple", syntax = "", title = "")
public class SimpleEndpoint extends DefaultEndpoint {
	  
	
	public SimpleEndpoint(String uri, SimpleComponent component) {
		super(uri, component);
		System.out.println("SimpleEndpoint created.!");
	}
	   
	@Override
	public Producer createProducer() throws Exception {
		SimpleProducer producer = new SimpleProducer(this);
		return producer;
	}

	@Override
	public Consumer createConsumer(Processor processor) throws Exception {
		SimpleConsumer consumer = new SimpleConsumer(this, processor);
        configureConsumer(consumer);
		return consumer;
	}
	
	@Override
	public Exchange createExchange() {
		System.out.println("create exchange");
		return super.createExchange();
	}
	
	public boolean isSingleton() {
		return true;
	}
	
}
