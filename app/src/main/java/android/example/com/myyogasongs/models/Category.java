package android.example.com.myyogasongs.models;


public class Category {

    String name;
    String description;
    int imageId;
    String type;

    public Category(String name, String description, int imageId, String type) {
        this.name = name;
        this.description = description;
        this.imageId = imageId;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageId() {
        return imageId;
    }

    public String getType() {
        return type;
    }
}
