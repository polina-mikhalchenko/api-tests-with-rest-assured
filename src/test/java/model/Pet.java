package model;

import jsonBody.Category;

import java.util.ArrayList;

public class Pet {
    private int id;
    private int categoryId;
    private String categoryName;
    private String name;
    private String photoUrls;
    public static Pet.Builder newEntity() {return new Pet().new Builder();}
    public int getId() {return id;}
    public int getCategoryId() {return categoryId;}
    public String getCategoryName() {return categoryName;}
    public String getName() {return name;}
    public String getPhotoUrls() {return photoUrls;}
    public class Builder {
        private Builder(){}
        public Pet.Builder withId(int id) {Pet.this.id = id; return this;}
        public Pet.Builder withCategoryId(int categoryId) {Pet.this.categoryId = categoryId; return this;}
        public Pet.Builder withCategoryName(String categoryName) {Pet.this.categoryName = categoryName; return this;}
        public Pet.Builder withName(String name) {Pet.this.name = name; return this;}
        public Pet.Builder withPhotoUrls(String photoUrls) {Pet.this.photoUrls = photoUrls; return this;}
        public Pet build() {return Pet.this;}
    }
}
