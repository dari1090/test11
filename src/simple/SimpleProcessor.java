package kr.co.bizframe.camel.simple;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

public class SimpleProcessor implements Processor {
	
	private String name;
	
	public SimpleProcessor(String name){
		this.name = name;
	}
	
	
	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println("simpleProcessor process.! name=" + name);
		//Message in = exchange.getIn();
		//System.out.println("message in ="+in);
	}	
	
}
