package android.example.com.myyogasongs.models;


public class Category {
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageId() {
        return imageId;
    }

    String name;
    String description;
    int imageId;

    public Category(String name, String description, int imageId) {
        this.name = name;
        this.description = description;
        this.imageId = imageId;
    }

}
