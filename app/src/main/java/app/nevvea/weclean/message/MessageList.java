package app.nevvea.weclean.message;


/**
 * Created by Anna on 4/20/15.
 * This class is for firebase mapping
 */
public class MessageList {
    private String date;
    private String message;
    private String name;
    private String profile_url;
    private String status;
    private String uid;

    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    private MessageList() {}

    MessageList(String date, String message, String name, String profile_url, String status, String uid) {
        this.date = date;
        this.message = message;
        this.name = name;
        this.profile_url = profile_url;
        this.status = status;
        this.uid = uid;
    }

    public String getDate() { return date; }
    public String getMessage() { return message; }
    public String getName() { return name; }
    public String getProfile_url() { return profile_url; }
    public String getStatus() { return status; }
    public String getUid() { return uid; }

}
