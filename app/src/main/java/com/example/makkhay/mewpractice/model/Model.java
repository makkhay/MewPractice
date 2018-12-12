package com.example.makkhay.mewpractice.model;


public class Model {

  private String title;
  private String description;
  private String image_url;

  public Model(String name, String description, String imageUrl) {
    this.title = name;
    this.description = description;
    this.image_url = imageUrl;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImage_url() {
    return image_url;
  }

  public void setImage_url(String image_url) {
    this.image_url = image_url;
  }

}
