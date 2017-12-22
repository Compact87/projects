package service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@Path("/search")
public class SearchRS
{
  List<Video> vids = new ArrayList();
  private Search search = new Search();
  
  @GET
  @Path("{query}")
  @Produces({"application/json"})
  public Response getBySearch(@PathParam("query") String q)
  {
    this.vids = this.search.searchres(q);
    return Response.ok(this.vids).build();
  }
}
