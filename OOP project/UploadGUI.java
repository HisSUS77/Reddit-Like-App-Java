import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UploadGUI extends JFrame {

    private JTextField postTitleField;
    private JTextArea postContentArea;

    public UploadGUI() {
        setTitle("Upload Post");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel titleLabel = new JLabel("Post Title:");
        titleLabel.setBounds(20, 20, 80, 20);
        add(titleLabel);

        postTitleField = new JTextField();
        postTitleField.setBounds(110, 20, 250, 20);
        add(postTitleField);

        JLabel contentLabel = new JLabel("Post Content:");
        contentLabel.setBounds(20, 60, 100, 20);
        add(contentLabel);

        postContentArea = new JTextArea();
        postContentArea.setBounds(110, 60, 250, 100);
        add(postContentArea);

        JButton uploadButton = new JButton("Upload Post");
        uploadButton.setBounds(150, 180, 120, 30);
        add(uploadButton);

        // ActionListener for the Upload Button
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadPostButtonClicked();
            }
        });
    }

    private void uploadPostButtonClicked() {
        String postTitle = postTitleField.getText();
        String postContent = postContentArea.getText();

        // Assuming you have a CurrentLoggedinUser instance available, create a Post object
        User u = User.ReturnForActiveUser();
        Post newPost = new Post(postTitle, postContent, u);

        // Call the Uploadpost method from the Post class
        Post.Uploadpost(newPost);

        JOptionPane.showMessageDialog(this, "Post uploaded successfully!");
        clearFields();
    }

    private void clearFields() {
        postTitleField.setText("");
        postContentArea.setText("");
    }

    // A method to show this JFrame
    public void showUploadGUI() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                setVisible(true);
            }
        });
    }



    // public static void main(String[] args) {
    //     UploadGUI u = new UploadGUI();
    //     u.setVisible(true);
    // }
}
