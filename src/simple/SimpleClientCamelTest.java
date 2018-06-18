package kr.co.bizframe.camel.simple;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class SimpleClientCamelTest {

	public void test() {
		
		try{
			CamelContext context = new DefaultCamelContext();
			context.addComponent("simple", new SimpleComponent());
			context.addRoutes(new RouteBuilder() {
				public void configure() {
					//from("simple:foo?a=11&b=2323&c=2323")
		
					from("timer://timerExample?period=2000")
					.setBody()
					.simple("This is timer example ${header.firedTime}")
					.process(new SimpleProcessor("1"))
					.to("simple:foo");
					//.to("file:d:/camel/dest");
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
		SimpleClientCamelTest sct = new SimpleClientCamelTest();
		sct.test();
		
	}
}
