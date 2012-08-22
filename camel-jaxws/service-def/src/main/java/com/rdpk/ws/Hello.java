package com.rdpk.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;


@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL)
public interface Hello {

	@WebMethod public ResponseTicket enqueueToProcess(@WebParam Command1 c);
	
	@WebMethod public String hello(final String name);

	@WebMethod public Command1 ha();
	
	@WebMethod public String enumPar(@WebParam ReturnCode r);

}
