import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SocialMediaApp {
    private List<User> users;
    private List<FriendRequest> friendRequests;

    public SocialMediaApp() {
        this.users = new ArrayList<>();
        this.friendRequests = new ArrayList<>();
    }

    public void registerUser(String username, String password) {
        users.add(new User(username, password));
        System.out.println("User registered successfully!");
    }

    public User loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.checkPassword(password)) {
                System.out.println("Login successful!");
                return user;
            }
        }
        System.out.println("Invalid username or password.");
        return null;
    }

    public void sendFriendRequest(User sender, User receiver) {
        FriendRequest request = new FriendRequest(sender, receiver);
        friendRequests.add(request);
        System.out.println("Friend request sent!");
    }

    public void showFriendRequests(User user) {
        System.out.println("Pending friend requests:");
        System.out.println();
        System.out.println();
        for (FriendRequest request : friendRequests) {
            if (request.getReceiver() == user) {
                System.out.println(request.getSender().getUsername());
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SocialMediaApp app = new SocialMediaApp();
        User loggedInUser = null;

        while (true) {
            System.out.println("1. Register  2. Login  3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String regUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String regPassword = scanner.nextLine();
                    app.registerUser(regUsername, regPassword);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    loggedInUser = app.loginUser(loginUsername, loginPassword);
                    if (loggedInUser != null) {
                        System.out.println("Logged in as: " + loggedInUser.getUsername());
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }

            if (loggedInUser != null) {
                boolean loggedin = true;
                while (loggedin) {
                    System.out.println(
                            "1. Add Friend  2. Create Post  3. Show Friend Requests  4. Logout  5. Exit  6.Show post");
                    int userChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    switch (userChoice) {
                        case 1:
                            System.out.print("Enter friend's username: ");
                            String friendUsername = scanner.nextLine();
                            User friend = null;
                            for (User u : app.users) {
                                if (u.getUsername().equals(friendUsername)) {
                                    friend = u;
                                    break;
                                }
                            }
                            if (friend != null) {
                                app.sendFriendRequest(loggedInUser, friend);
                            } else {
                                System.out.println("User not found.");
                            }
                            break;
                        case 2:
                            System.out.print("Enter your post: ");
                            String postMessage = scanner.nextLine();
                            loggedInUser.createPost(postMessage);
                            break;
                        case 3:
                            app.showFriendRequests(loggedInUser);
                            break;
                        case 4:
                            System.out.println("Logging out...");
                            loggedInUser = null;
                            loggedin = false;
                            break;
                        case 5:
                            System.out.println("Exiting...");
                            scanner.close();
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                        case 6:
                            if (loggedInUser != null) {
                                System.out.println("Your posts:");
                                for (Post post : loggedInUser.getPosts()) {
                                    System.out.println(post.getAuthor().getUsername() + " - " + post.getMessage());
                                    System.out.println();
                                    System.out.println();
                                }
                            }

                    }

                }
            }
        }
    }
}
