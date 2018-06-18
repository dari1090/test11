package kr.co.bizframe.camel.simple;

import java.util.UUID;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.ScheduledPollConsumer;

public class SimpleConsumer2 extends ScheduledPollConsumer {

	private final SimpleEndpoint endpoint;

	public SimpleConsumer2(SimpleEndpoint endpoint, Processor processor) {
		super(endpoint, processor);
		this.endpoint = endpoint;
	}
	
	@Override
	protected int poll() throws Exception {
		System.out.println("===========================================================================");
		Exchange exchange = endpoint.createExchange();

		// create a message body
		exchange.getIn().setBody(UUID.randomUUID().toString());
			
		System.out.println("exchange id=" + exchange.getExchangeId());
		System.out.println("exchange from route id=" + exchange.getFromRouteId());
		System.out.println("exchange exchange pattern=" + exchange.getPattern());
	
		
		try {
			// send message to next processor in the route
			getProcessor().process(exchange);
			return 1; // number of messages polled
		} finally {
			// log exception if an exception occurred and was not handled
			if (exchange.getException() != null) {
				getExceptionHandler().handleException("Error processing exchange", exchange, exchange.getException());
			}
		}
	}
}
