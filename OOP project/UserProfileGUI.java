
    import java.awt.Color;
    import java.awt.GridLayout;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.io.Serializable;
    import javax.swing.*;

    import javax.swing.JButton;
    import javax.swing.JFrame;
    import javax.swing.JLabel;
    import javax.swing.JOptionPane;
    import javax.swing.JPanel;
    import javax.swing.JTextField;
    import javax.swing.SwingConstants;
    
    public class UserProfileGUI extends JFrame {
        
     JButton b1, b2, b3;
     JLabel l1 , l2 , l3 ;
        
        public UserProfileGUI() {

            setTitle("My Profile");
            setSize(400, 400);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLayout(new GridLayout(3 ,1));
            
     
            b1= new JButton("Upload");
            b2 = new JButton("MY POST");
            b3 = new JButton("Exit");
           
            
            l1 = new JLabel("      " + User.ReturnForActiveUser().name);
            l2 = new JLabel("      " + User.ReturnForActiveUser().uniqueid);
            l3 = new JLabel("      " + User.ReturnForActiveUser().age);

            b1.setBackground(Color.GREEN);
            b2.setBackground(Color.RED);
    
            MyActionListener a = new MyActionListener();
    
            b1.addActionListener(a);
            b2.addActionListener(a);
            b3.addActionListener(a);
    
            add(b1);
            add(b2);
            add(b3);
    
            setVisible(true);
    
        }
    
        
         public class MyActionListener implements ActionListener {
            
            public  void actionPerformed(ActionEvent ae) {
                if (ae.getActionCommand().equals("Upload")) {
                         dispose();
                        SubredditGUI s = new SubredditGUI();
                } 
                else if(ae.getActionCommand().equals("MY POST")){
                    dispose();
                    UserPostGUI ug = new UserPostGUI();
                }
                
                
                else if (ae.getActionCommand().equals("Upload")) {
                        dispose();
                        UploadGUI uploadGUI = new UploadGUI();
                        uploadGUI.showUploadGUI();
        
                } else if (ae.getActionCommand().equals("Exit")) {
                    dispose();
                }
            }
    
    
        }
        public static void main(String[] args) {
            UserProfileGUI u = new UserProfileGUI();
            u.setVisible(true);
        }
    }
    