package jsonBody;

import java.util.ArrayList;

public class PetBody {
    private int id;
    private Category category;
    private String name;
    private ArrayList<String> photoUrls;
    private String status;

    public PetBody(int id, Category category, String name, ArrayList<String> photoUrls, String status) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.status = status;
    }
    public PetBody() {
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", photoUrls=" + photoUrls +
                ", status=" + status +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public ArrayList<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(ArrayList<String> photoUrls) {
        this.photoUrls = photoUrls;
    }
    public String getStatus(String status) {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
