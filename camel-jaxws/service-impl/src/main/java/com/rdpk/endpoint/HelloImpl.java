package com.rdpk.endpoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.jws.WebService;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultProducerTemplate;

import com.rdpk.ws.Command1;
import com.rdpk.ws.Hello;
import com.rdpk.ws.ResponseTicket;
import com.rdpk.ws.ReturnCode;


@WebService(endpointInterface = "com.rdpk.ws.Hello")
public class HelloImpl implements Hello {
	
	private final ProducerTemplate producer;
	private final Validator validator;

	public HelloImpl() { // just to not make jax-ws angry.. 
		this.producer = null;
		this.validator = null;
	}
	
	public HelloImpl(final DefaultProducerTemplate p, final Validator v) {
		this.producer = p;
		this.validator = v;
	}
	
	@Override
	public String hello(final String name) {
		return "Hello, " + name;
	}

	@Override
	public ResponseTicket enqueueToProcess(final Command1 c) {
	    List<String> errors = new ArrayList<String>();
		System.out.println("recei " + c);
		producer.sendBody(c);
	    Set<ConstraintViolation<Command1>> constraintViolations = validator.validate(c);
	    for (ConstraintViolation<Command1> e: constraintViolations) {
		    errors.add(e.toString());
	    }
	    ResponseTicket r = new ResponseTicket();
	    r.setDesc("erro?");
	    r.setErrors(errors);
	    System.out.println("retornando " + r);
	    return r;
		// return new ResponseTicket(errors);
	}

	@Override
	public Command1 ha() {
		Command1 c = new Command1();
		c.setDescription("description!");
		c.setFoo("foo!");
		return c;
	}

	@Override
	public String enumPar(ReturnCode r) {
		System.out.println("received " + r);
		return r.toString();
	}

}
