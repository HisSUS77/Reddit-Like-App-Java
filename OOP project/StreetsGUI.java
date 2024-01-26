import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;

public class StreetsGUI extends JFrame {

    JButton b1, b2, b3;
    JTextField t1, t2;
    JLabel l1, l2 , l3;
    

    public StreetsGUI() {
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 5, 5));
        l3 = new JLabel("LOGIN PAGE");
        l3.setFont(new Font("Arial", Font.BOLD, 20));
        l3.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel loginPanel = new JPanel(new FlowLayout());
        loginPanel.add(l3);

        t1 = new JTextField(20);
        t2 = new JTextField(20);

        MyActionListener a = new MyActionListener();
        b1 = new JButton("Submit");
        b2 = new JButton("Exit");

        // Set preferred size for the registration button
        b3 = new JButton("Registration");
        b3.setPreferredSize(new Dimension(100, 30));

        b1.setBackground(Color.GREEN);
        b2.setBackground(Color.RED);
        b3.setBackground(Color.PINK);
        b1.addActionListener(a);
        b2.addActionListener(a);
        b3.addActionListener(a);
        
        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        l1 = new JLabel("Email");
        emailPanel.add(l1);
        emailPanel.add(t1);

        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        l2 = new JLabel("Password");
        passwordPanel.add(l2);
        passwordPanel.add(t2);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        buttonPanel.add(b1);
        buttonPanel.add(b2);

        add(loginPanel);
        add(emailPanel);
        add(passwordPanel);
        add(buttonPanel);
        add(b3);

        setVisible(true);
    }


    public class MyActionListener implements ActionListener {
        public static CurrentUser CU; 


        
        public void actionPerformed(ActionEvent ae) {
            if (ae.getActionCommand().equals("Submit")) {
                try {
                    if (User.checkUserCreds(t1.getText(), t2.getText())) {

                        // if not reported only then accept 

                             JOptionPane.showMessageDialog(new JFrame(), "Welcome");
                             StreetsAppGUI streets = new StreetsAppGUI();
                            
                             String  current  = User.getCurrentUser(t1.getText()).name;
                              CurrentUser CU = new CurrentUser(current);  
                              CurrentUser.WriteCurrentUser(CU);

                        }  
                         
                        else {
                            JOptionPane.showMessageDialog(new JFrame(), "Sorry, email or password Incorrect !");
                        }
                        
                        
                    }               

                    catch (NoSuchAlgorithmException e) {
                        JOptionPane.showMessageDialog(new JFrame(), "Oops can't connect to our Servers at the moment");
                    }
    
                    
                }
            
                
               

            
            
            else if (ae.getActionCommand().equals("Exit")) {
                System.exit(0);
            } 

            else if (ae.getActionCommand().equals("Registration")) {
                RegistrationGUI r1 = new RegistrationGUI();
                r1.setVisible(true);
            }


        }
    }
}


    
