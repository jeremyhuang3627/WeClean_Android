package app.nevvea.weclean.message;

/**
 * Created by Anna on 4/21/15.
 */
public class ChatImageMessage {
    private String date;
    private String from;
    private String image;
    private String status;

    private ChatImageMessage() {
    }

    ChatImageMessage(String date, String from, String image, String status) {
        this.date = date;
        this.from = from;
        this.image = image;
    }


    public String getDate() {
        return date;
    }
    public String getFrom() {
        return from;
    }
    public String getImage() {
        return image;
    }
    public String getStatus() {
        return status;
    }

}
