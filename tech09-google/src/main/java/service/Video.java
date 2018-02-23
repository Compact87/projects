package service;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Video {
 private String id;
 private String title;
 private String thumbnail;
 /* 
  * Getters/Stetters
  */
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getThumbnail() {
	return thumbnail;
}
public void setThumbnail(String thumbnail) {
	this.thumbnail = thumbnail;
}
public Video() {
	
}
 
}
