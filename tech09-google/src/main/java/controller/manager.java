package controller;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import service.Video;

import javax.enterprise.context.Dependent;

@SessionScoped
@ManagedBean
public class manager implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    @Inject
    private TrimConverter convert;
	public TrimConverter getConvert() {
		return convert;
	}




	@Inject
	private SearchEJB sejb;
	 
	  private List<Video> vids;
	  public List<Video> getVids() {
		return vids;
	}
	


	
	private Query query=new Query();
    
	  
	public Query getQuery() {
		return query;
	}


	public void setQuery(Query query) {
		this.query = query;
	}




	public void search(){
	    String q=query.getQuery().replaceAll(" ","%20");
		vids=sejb.search(q);
		System.out.println("looking for.." +query.getQuery()+ "...please wait");
		for(Video video : vids){System.out.println(video.getId()+ "\\"+video.getTitle());
		
		}
	   
	
	}}
	



