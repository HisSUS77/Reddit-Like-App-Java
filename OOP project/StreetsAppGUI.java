import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class StreetsAppGUI extends JFrame {
    
    
 JButton b1, b2, b3 , b4 ;
    
    public StreetsAppGUI() {

        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3 ,1));
        
 
        b1 = new JButton("Feed");
        b2 = new JButton("Upload");
        b3 = new JButton("Exit");
        b4 = new JButton("My Profile");


        
        b1.setBackground(Color.GREEN);
        b2.setBackground(Color.RED);
        b3.setBackground(Color.PINK);

        MyActionListener a = new MyActionListener();

        b1.addActionListener(a);
        b2.addActionListener(a);
        b3.addActionListener(a);
        b4.addActionListener(a);

        add(b1);
        add(b2);
        add(b3);
        add(b4);

        setVisible(true);

    }

    
     public class MyActionListener implements ActionListener {
        
        public  void actionPerformed(ActionEvent ae) {
            if (ae.getActionCommand().equals("Feed")) {
                     dispose();
                    SubredditGUI s = new SubredditGUI();
            } 
            
            
            else if (ae.getActionCommand().equals("Upload")) {
                    dispose();
                    UploadGUI uploadGUI = new UploadGUI();
                    uploadGUI.showUploadGUI();
    
            } else if (ae.getActionCommand().equals("Exit")) {
                dispose();
            }

            else if(ae.getActionCommand().equals("My Profile")){
                UserProfileGUI up = new UserProfileGUI();
            }
        }
    }
}
