
package service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationPath("services")
public class ApplicationConfig extends Application{
	 private final Set<Class<?>> classes;
	 
	 public ApplicationConfig() {
		    HashSet<Class<?>> c = new HashSet<>();
		    c.add(SearchRS.class);
		    
		   
		    

		   
		    classes = Collections.unmodifiableSet(c);
		  }
}