package controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import service.Video;
import javax.enterprise.context.Dependent;

@Dependent
@Named
public class manager {
	@Inject
	private SearchEJB sejb;
	 
	  private List<Video> vids;
	  public List<Video> getVids() {
		return vids;
	}



	private String query;

	  public String getQuery() {
	  	return query;
	  }

	  public void setQuery(String query) {
	  	this.query = query;
	  }
	

	  
	public void search(){
	
		vids=sejb.search(query);
		}
	
	
	}
	



