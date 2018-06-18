package kr.co.bizframe.camel.simple;

import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;

public class SimpleComponent extends DefaultComponent  {

	protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
		
		System.out.println("createEndpont uri=" +uri);
		System.out.println("createEndpont remaining=" +remaining);
		System.out.println("createEndpont parameters=" +parameters);
		
		SimpleEndpoint endpoint = new SimpleEndpoint(uri, this);
		return endpoint;
	}
}
