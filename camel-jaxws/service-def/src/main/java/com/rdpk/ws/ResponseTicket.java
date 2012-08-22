package com.rdpk.ws;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "responseTicket")
public class ResponseTicket {

	private String desc;
	private List<String> errors;

	public ResponseTicket() {
		
	}
	
//	public ResponseTicket(List<String> errors) {
//		super();
//		this.errors = errors;
//	}

	@Override
	public String toString() {
//		return "ResponseTicket [desc=" + desc + "]";
		StringBuffer s = new StringBuffer("ResponseTicket [\n");
		for (String e: errors) {
			s.append(e + "\n");
		}
		return s + "\n]";
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
