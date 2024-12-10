package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
public class Pet {
    @JsonProperty("id")
    private Integer id;
    
    @JsonProperty("category")
    private Category category;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("photoUrls")
    private List<String> photoUrls;
    
    @JsonProperty("tags")
    private List<Tag> tags;
    
    @JsonProperty("status")
    private String status;
	
	public Pet() {
	    // デフォルトコンストラクタ
	}
	
	public Pet(Integer id, Category category, String name, List<String> photoUrls, List<Tag> tags, String status) {
		super();
		this.id = id;
		this.category = category;
		this.name = name;
		this.photoUrls = photoUrls;
		this.tags = tags;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
	    return "Pet{" +
	            "id=" + id +
	            ", category=" + category +
	            ", name='" + name + '\'' +
	            ", photoUrls=" + photoUrls +
	            ", tags=" + tags +
	            ", status='" + status + '\'' +
	            '}';
	}

	
}
