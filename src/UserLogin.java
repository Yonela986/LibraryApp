import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserLogin {
    private JFrame frame;
    private JPanel panel;  // Declare panel variable here

    private JLabel usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public UserLogin() {
        frame = new JFrame("User Login");
        panel = new JPanel();  // Initialize panel here

        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                // Add your authentication logic here
                if (username.equals("user") && password.equals("user123")) {
                    JOptionPane.showMessageDialog(frame, "Login Successful!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.setLayout(new GridLayout(3, 2));
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Placeholder
        panel.add(loginButton);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UserLogin();
            }
        });
    }

}
