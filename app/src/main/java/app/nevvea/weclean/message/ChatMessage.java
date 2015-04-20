package app.nevvea.weclean.message;

/**
 * Created by Anna on 4/19/15.
 */
public class ChatMessage {
    private String message;
    private String author;

    private  ChatMessage() {}

    ChatMessage(String message, String author) {
        this.message = message;
        this.author = author;
    }

    public String getMessage() { return message; }
    public String getAuthor() { return author; }
}
