package kr.co.bizframe.mas.camel;

import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;

import kr.co.bizframe.mas.Application;
import kr.co.bizframe.mas.Serviceable;
import kr.co.bizframe.mas.application.ApplicationContext;

public class CamelApplication implements Application, Serviceable {
	
	private static Logger log = LoggerFactory.getLogger(CamelApplication.class);
	
	private Main main;
	
	public void init(ApplicationContext context) {
		
		log.info("init camel servcie app11");
		log.info("props = " + context.getProperties());
		String routeConfigFile = context.getProperty("route_xml_file");
		
		String routeXml = "camel-route.xml";
		if(routeConfigFile != null){
			routeXml = routeConfigFile;
		}
    	main = new Main();
		main.setApplicationContextUri(routeXml);
	}

	
	public void destroy(ApplicationContext context) {
		log.info("destory camel service app");
		try{
			main.shutdown();
		}catch(Throwable t){
			log.error(t.getMessage(), t);
		}
	}
	
	
	public void start() throws Exception{
		log.info("start camel service app");
		try{
			AbstractApplicationContext context = main.getApplicationContext();
			if(context != null){
				context.refresh();
			}
			main.start();
		}catch(Throwable t){
			log.error(t.getMessage(), t);
		}
	}

	
	public void stop() throws Exception{
		log.info("stop camel service app");		
		try{
			main.stop();
		}catch(Throwable t){
			log.error(t.getMessage(), t);
		}
	}
	
	public List<CamelContext> getCamelContexts(){
		List<CamelContext> camelContexts = main.getCamelContexts();
		//for(CamelContext cc : camelContexts){
		//	System.out.println("cc = " + cc);
		//}
		return camelContexts;
	}
}
