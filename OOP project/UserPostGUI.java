import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class UserPostGUI  extends JFrame{
     private JTextField titleFilterField;
    private JButton filterButton;
    private JTextArea displayArea;
    private List<Post> allPosts;




 public UserPostGUI() {

        setTitle("My Posts ");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createComponents();

        // Display all posts initially
        allPosts=  Post.ReturnCurrentUserPost();

        displayFilteredPosts(allPosts);

        setVisible(true);
    }



    

    private void createComponents() {
        titleFilterField = new JTextField(20);
        filterButton = new JButton("Filter by Title");
        displayArea = new JTextArea(20, 60);
        displayArea.setEditable(false);

        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterPostsByUser();
            }
        });

        JPanel filterPanel = new JPanel();
        filterPanel.add(new JLabel("Filter by Title:"));
        filterPanel.add(titleFilterField);
        filterPanel.add(filterButton);

        add(filterPanel, BorderLayout.NORTH);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);
    }

    private void filterPostsByUser() {
        String titleFilter = titleFilterField.getText();
        List<Post> filteredPosts = Post.getPostsByTitle(titleFilter);
        displayFilteredPosts(filteredPosts);
    }

    private void displayFilteredPosts(List<Post> filteredPosts) {
        displayArea.setText("");
        for (Post post : filteredPosts) {
            displayArea.append("Post ID: " + post.getPostId() + "\n");
            displayArea.append("Title: " + post.getPostTitle() + "\n");
            displayArea.append("Content: " + post.getPostContent() + "\n");
            displayArea.append("User ID: " + post.getUserIdWhoPosted() + "\n");
            displayArea.append("Likes: " + post.getLikeCount() + "\n");
            displayArea.append("Comments: " + post.getComments() + "\n");
            displayArea.append("\n=====================\n\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserPostGUI userPostGUI = new UserPostGUI();
        });
    }
    

}
