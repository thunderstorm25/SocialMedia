import java.time.LocalDateTime;

public class Post {
    private User author;
    private String message;
    private LocalDateTime timestamp;

    public Post(User author, String message) {
        this.author = author;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public User getAuthor() {
        return author;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
