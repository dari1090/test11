package kr.co.bizframe.camel.simple;

import java.util.UUID;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultConsumer;
import org.apache.camel.impl.ScheduledPollConsumer;

public class SimpleConsumer4 extends DefaultConsumer {


	public SimpleConsumer4(SimpleEndpoint endpoint, Processor processor) {
		super(endpoint, processor);
		
		//SimpleWorker2 worker = new SimpleWorker2(this);
		//worker.start();
		
		//SimpleServer2 server = new SimpleServer2(this);
		//server.start();
	}
	
	
	@Override
	protected void doStart() throws Exception {
		super.doStart();
	}
	

	
}
