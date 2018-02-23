package controller;

import java.util.List;

import service.Video;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;

public class TestClient {

 public static void main(String [] args){
	   
	        String query="warcraft";
		 
			List<Video> vid = Client.create().resource("http://localhost:8080/tech09-google-0.0.1-SNAPSHOT/services/search/"+query)
	                .get(new GenericType<List<Video>>(){});
		   
		   for(Video video : vid){System.out.println(video.getId()+ "\\"+video.getTitle());}
		  }
 }

