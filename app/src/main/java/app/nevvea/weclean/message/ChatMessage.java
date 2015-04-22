package app.nevvea.weclean.message;

/**
 * Created by Anna on 4/19/15.
 */
public class ChatMessage {
    private String date;
    private String from;
    private String message;
    private String status;

    private ChatMessage() {
    }

    ChatMessage(String date, String from, String message, String status) {
        this.date = date;
        this.from = from;
        this.message = message;
    }


    public String getDate() {
        return date;
    }
    public String getFrom() {
        return from;
    }
    public String getMessage() {
        return message;
    }
    public String getStatus() {
        return status;
    }
}
