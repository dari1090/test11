package kr.co.bizframe.camel.simple;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import com.sun.xml.internal.bind.v2.model.core.ID;

import kr.co.bizframe.camel.file.FileProcessor;
import kr.co.bizframe.camel.rnd.RndComponent;


public class SimpleServerCamelTest {

	public void test() {
		
		try{
			CamelContext context = new DefaultCamelContext();
			context.addComponent("simple", new SimpleComponent());
			context.addRoutes(new RouteBuilder() {
				public void configure() {
					//from("simple:foo?a=11&b=2323&c=2323")
					
					from("simple:foo")
					//.routeId("test-id")
					.process(new SimpleProcessor("1"))
					//.process(new SimpleProcessor("2"))
					//.to("stream:out");
					//.to("mock:bar")
					//.process(new SimpleProcessor("2"));
					.to("file:d:/camel/dest");
					//.to("log:?level=ERROR");
				}
			});
			context.start();
			Thread.sleep(100000);
			context.stop();		
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] argv){
		
		SimpleServerCamelTest sct = new SimpleServerCamelTest();
		sct.test();
		
	}
	
}
