REST is cool but sometimes you will have to expose some SOAP services. 

You can do it with Camel in many ways (CFX, Spring WebServices and even Axis) but I really wanted to explore an alternative without having Spring deps in my poms 

How to run it:
--------------

cd camel-jaxws
mvn clean install
mvn exec:exec     

once the server is running then let's make some calls:

cd app-client
mvn clean install
mvn exec:exec

you will notice something like this on the console:

s.enqueueToProcess(com.rdpk.ws.Command1@4e2016b0)
returned erro?
hello = Hello, Rodolfo
ha = com.rdpk.ws.Command1@58c16b18

TODO

* Jetty as a default HttpServer impl
* Figure out how to make it work within Eclipse with the current Hello import (pls see line 9 of com.rdpk.client.Client class) 
