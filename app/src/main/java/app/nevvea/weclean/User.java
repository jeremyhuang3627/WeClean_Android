package app.nevvea.weclean;

/**
 * Created by anna on 4/10/15.
 *
 * This class stores fake data of users for debugging
 *
 */
public class User {
    private String name;
    private int gender;
    private int picid;
    private String description;

    // constructer
    public User(String name, int gender, int picid, String description) {
        this.name = name;
        this.gender = gender;
        this.picid = picid;
        this.description = description;
    }

    // getters
    public String getName() { return name; }

    public int getGender() { return gender; }

    public int getPicid() { return picid; }

    public String getDescription() { return description; }

}
