package controller;

import java.util.List;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import service.Video;


@ManagedBean
public class SearchEJB {
	
	 
	
	public List<Video> search(String query){
	 
		List<Video> vid = Client.create().resource("http://localhost:8080/tech09-google-0.0.1-SNAPSHOT/services/search/"+query)
                .get(new GenericType<List<Video>>(){});
	   
	   return vid; 
	  }
}
