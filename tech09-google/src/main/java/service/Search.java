/*
 * Copyright (c) 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package service;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.samples.youtube.cmdline.Auth;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * Print a list of videos matching a search term.
 *
 * @author Jeremy Walker
 */
public class Search
{
  public List<Video> videos = new ArrayList();
  private static final String PROPERTIES_FILENAME = "youtube.properties";
  private static final long NUMBER_OF_VIDEOS_RETURNED = 5L;
  private static YouTube youtube;
  
  public List<Video> searchres(String text)
  {
    Properties properties = new Properties();
    try
    {
      InputStream in = Search.class.getResourceAsStream("/youtube.properties");
      properties.load(in);
    }
    catch (IOException e)
    {
      System.err.println("There was an error reading youtube.properties: " + e.getCause() + " : " + e
        .getMessage());
      System.exit(1);
    }
    try
    {
      youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer()
      {
        public void initialize(HttpRequest request)
          throws IOException
        {}
      }
      
        ).setApplicationName("youtube-cmdline-search-sample").build();
      
      String queryTerm = text;
      
      YouTube.Search.List search = youtube.search().list("id,snippet");
      
      String apiKey = properties.getProperty("youtube.apikey");
      search.setKey(apiKey);
      search.setQ(queryTerm);
      
      search.setType("video");
      
      search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
      search.setMaxResults(Long.valueOf(5L));
      
      SearchListResponse searchResponse = (SearchListResponse)search.execute();
      List<SearchResult> searchResultList = searchResponse.getItems();
      Iterator<SearchResult> iteratorSearchResults = searchResultList.iterator();
      while (iteratorSearchResults.hasNext())
      {
        SearchResult singleVideo = (SearchResult)iteratorSearchResults.next();
        ResourceId rId = singleVideo.getId();
        if (rId.getKind().equals("youtube#video"))
        {
          Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();
          Video vid = new Video();
          vid.setId(rId.getVideoId());
          vid.setTitle(singleVideo.getSnippet().getTitle());
          vid.setThumbnail(thumbnail.getUrl());
          this.videos.add(vid);
        }
      }
    }
    catch (GoogleJsonResponseException e)
    {
      System.err.println("There was a service error: " + e.getDetails().getCode() + " : " + e
        .getDetails().getMessage());
    }
    catch (IOException e)
    {
      System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
    }
    catch (Throwable t)
    {
      t.printStackTrace();
    }
    return this.videos;
  }
}
