import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationGUI extends JFrame {
    JButton b1, b2, b3;
    JTextField t1, t2, t3, t4, t5, t6, t7, t8;
    JLabel l1, l2, l3, l4, l5, l6, l7, l8;
    JPanel p1, p2, p3, p4;

    

    public RegistrationGUI() {

        setSize(600, 400);  // Adjusted the height for better visibility
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        setLayout(new GridLayout(5, 1, 5, 5));

        // Panel 1: Registration Title
        l1 = new JLabel("REGISTRATION PAGE");
        l1.setFont(new Font("Arial", Font.BOLD, 20));
        l1.setHorizontalAlignment(SwingConstants.CENTER);
        p1 = new JPanel(new FlowLayout());
        p1.add(l1);

        // Panel 2: Name
        l2 = new JLabel("Name");
        t1 = new JTextField(20);
        p2 = new JPanel(new FlowLayout());
        p2.add(l2);
        p2.add(t1);

        // Panel 3: Date of Birth
        l3 = new JLabel("Date of Birth");
        l4 = new JLabel("Day");
        t2 = new JTextField(5);
        l5 = new JLabel("Month");
        t3 = new JTextField(5);
        l6 = new JLabel("Year");
        t4 = new JTextField(5);
        p3 = new JPanel(new FlowLayout());
        p3.add(l3);
        p3.add(l4);
        p3.add(t2);
        p3.add(l5);
        p3.add(t3);
        p3.add(l6);
        p3.add(t4);

        // Panel 4: Email and Password
        l7 = new JLabel("Email");
        t5 = new JTextField(20);
        l8 = new JLabel("Password");
        t6 = new JTextField(20);
        p4 = new JPanel(new FlowLayout());
        p4.add(l7);
        p4.add(t5);
        p4.add(l8);
        p4.add(t6);

        // Panel 5: Buttons
        b1 = new JButton("Submit");
        b2 = new JButton("Exit");
        b3 = new JButton("Login page");


        b1.setBackground(Color.GREEN);
        b2.setBackground(Color.RED);
        b3.setBackground(Color.PINK);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(b1);
        buttonPanel.add(b2);
        buttonPanel.add(b3);

        MyActionListener a = new MyActionListener();
        b1.addActionListener(a);
        b2.addActionListener(a);
        b3.addActionListener(a);

        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(buttonPanel);

        setVisible(true);
    }


    public class MyActionListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            if (ae.getActionCommand().equals("Submit")) {
                Date d = new Date(Integer.parseInt(t2.getText()) , Integer.parseInt(t3.getText()) , Integer.parseInt(t4.getText()));
                if(!User.checkForDuplicateEmail(t5.getText())){
                User u = new User(t1.getText() , d , t5.getText() , t6.getText() );
                
                try{

                     User.WriteUserTOFIle(u);
                        JOptionPane.showMessageDialog(new JFrame(), "Welcome To the Streets... the Hood Awaits U !! ___<(•‿•)>___ ");


                }
            
                catch(Exception e) {
                    JOptionPane.showMessageDialog(new JFrame(), "Servers Down Right Now try Again Later ! or call 911 ");
                }
            }
            else{
                JOptionPane.showMessageDialog(new JFrame(), "Email Already Exist Sussy baka (^-^;) .");
            }

            } 
            
            
            else if (ae.getActionCommand().equals("Exit")) {
                System.exit(0);
            } else if (ae.getActionCommand().equals("Login page")) {
                // Add your logic for handling the login page button
                
                dispose();
                new StreetsGUI();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RegistrationGUI();
            }
        });
    }
}