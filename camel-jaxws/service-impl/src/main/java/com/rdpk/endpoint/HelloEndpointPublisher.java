package com.rdpk.endpoint;

import java.util.concurrent.Executors;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.xml.ws.Endpoint;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultProducerTemplate;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.camel.main.Main;

import com.rdpk.ws.Hello;

public class HelloEndpointPublisher {

	final Main main = new Main();
	
	final DefaultCamelContext c;

	final Hello service;

	public HelloEndpointPublisher() throws Exception {

		c = new DefaultCamelContext(createRegistry());

		c.addRoutes(new TestRoute());

		DefaultProducerTemplate p = new DefaultProducerTemplate(c, c.getEndpoint("direct:fromJaxWs"));
		
		p.start();

   	    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

		service = new HelloImpl(p, validator);

	}
	
	public void doSomeWOrk() throws Exception {
		
//		System.out.println(System.getProperty("java.endorsed.dirs"));
//		System.setProperty("com.sun.net.httpserver.HttpServerProvider","org.eclipse.jetty.jaxws2spi.JettyHttpServerProvider");
//		HttpsServer httpsServer = HttpsServer.create(new InetSocketAddress(9999),0);
//		HttpContext httpContext = httpsServer.createContext("/ws/hello");
//		Endpoint endpoint = Endpoint.create(service);
//		endpoint.publish(httpContext);
		
		Endpoint ws = Endpoint.create(service);
		ws.setExecutor(Executors.newFixedThreadPool(10));
		ws.publish("http://localhost:9999/ws/hello");
		
		main.enableHangupSupport();

		main.getCamelContexts().clear();
		main.getCamelContexts().add(c);
		
		main.run();
		
		ws.stop();
		//endpoint.stop();
		
		System.out.println("finished !!!");
		
	}

	
	public static void main(String[] args) throws Exception {

		HelloEndpointPublisher p = new HelloEndpointPublisher();
		
		p.doSomeWOrk();
		
    }
	
	private static SimpleRegistry createRegistry() {
		SimpleRegistry r = new SimpleRegistry();
		//r.put("ws", service);
		return r;
	}
}

class TestRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		from("direct:fromJaxWs")
		  .process(new Processor() {
			@Override
			public void process(Exchange e) throws Exception {
				System.out.println("--------------------------------------------------------------->>from jax ws !!");
				System.out.println(e.getIn().getBody());
			}
		});

	}
}
