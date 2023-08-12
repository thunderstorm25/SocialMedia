import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private List<User> friends;
    private List<Post> posts;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.friends = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void addFriend(User friend) {
        friends.add(friend);
    }

    public void createPost(String message) {
        Post post = new Post(this, message);
        posts.add(post);
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<User> getFriends() {
        return friends;
    }
}
