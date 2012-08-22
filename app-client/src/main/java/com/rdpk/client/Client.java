package com.rdpk.client;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.rdpk.ws.Command1;
import com.rdpk.ws.Hello;
import com.rdpk.ws.ResponseTicket;

public class Client {

	public static void main(String[] args) throws Exception {

		URL url = new URL("http://localhost:9999/ws/hello?wsdl");

		QName qname = new QName("http://endpoint.rdpk.com/", "HelloImplService");
		
		// QName qname = new QName("http://ws.rdpk.com/", "HelloImplService");

		Service service = Service.create(url, qname);

		Hello s = service.getPort(Hello.class);

		Command1 c = new Command1();
		
		c.setDescription("description-from client");
		c.setFoo("foo-from client");

		System.out.println("s.enqueueToProcess(" + c + ")");
	
		ResponseTicket r = s.enqueueToProcess(c);
		
		System.out.println("returned " + r.getDesc());
		System.out.println("hello = " + s.hello("Rodolfo"));
		System.out.println("ha = " + s.ha());
		
	}

}
