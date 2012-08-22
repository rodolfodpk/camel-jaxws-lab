package com.rdpk.ws;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "command1")
public class Command1 {

   @NotNull
    private String description;

    @NotNull
    @Size(min = 2, max = 14)
    private String foo;

    public Command1() {
    }
    
//    public Command1(String description, String foo) {
//    	super();
//        this.description = description;
//        this.foo = foo;
//    }

	public String getDescription() {
		return description;
	}

	public String getFoo() {
		return foo;
	}

	@Override
	public String toString() {
		return "Command1 [description=" + description + ", foo=" + foo + "]";
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFoo(String foo) {
		this.foo = foo;
	}

}
