
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AdminLogin {
    private JFrame frame;
    private JPanel loginPanel, registerPanel;
    private JTextField usernameFieldLogin, emailFieldLogin;
    private JPasswordField passwordFieldLogin;
    private JButton loginButton, switchToRegisterButton, registerButton;
    private JTextField fullNameField, surnameField, emailFieldRegister;
    private JPasswordField passwordFieldRegister;

    // List to store registered admins
    private List<Admin> registeredAdmins;

    public AdminLogin() {
        // Initialize JFrame and panels
        frame = new JFrame("Admin Login");
        loginPanel = new JPanel(new CardLayout());
        registerPanel = new JPanel(new GridLayout(5, 2));

        // Initialize components for login panel
        usernameFieldLogin = new JTextField(20);
        emailFieldLogin = new JTextField(20);
        passwordFieldLogin = new JPasswordField(20);
        loginButton = new JButton("Login");
        switchToRegisterButton = new JButton("Switch to Register");

        // Initialize components for register panel
        fullNameField = new JTextField(20);
        surnameField = new JTextField(20);
        emailFieldRegister = new JTextField(20);
        passwordFieldRegister = new JPasswordField(20);
        registerButton = new JButton("Register");

        // Add action listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameFieldLogin.getText();
                String email = emailFieldLogin.getText();
                String password = String.valueOf(passwordFieldLogin.getPassword());

                // Simple authentication logic (replace with actual authentication)
                if (authenticate(username, email, password)) {
                    JOptionPane.showMessageDialog(frame, "Login Successful!");
                    // Reset fields after successful login
                    resetLoginFields();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username, email, or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        switchToRegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch to register panel
                CardLayout cardLayout = (CardLayout) loginPanel.getLayout();
                cardLayout.next(loginPanel);
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fullName = fullNameField.getText();
                String surname = surnameField.getText();
                String email = emailFieldRegister.getText();
                String password = String.valueOf(passwordFieldRegister.getPassword());

                // Create a new Admin object and add to the registeredAdmins list
                Admin newAdmin = new Admin(fullName, surname, email, password);
                registeredAdmins.add(newAdmin);

                JOptionPane.showMessageDialog(frame, "Registration Successful!");
                // Reset fields after successful registration
                resetRegisterFields();

                // Switch back to login panel after registration
                CardLayout cardLayout = (CardLayout) loginPanel.getLayout();
                cardLayout.previous(loginPanel);
            }
        });

        // Add components to login panel
        loginPanel.add(createLoginPanel(), "login");
        loginPanel.add(createRegisterPanel(), "register");

        // Set default close operation and display frame
        frame.add(loginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setVisible(true);

        // Initialize list to store registered admins
        registeredAdmins = new ArrayList<>();
    }

    // Method to create login panel
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Username:"));
        panel.add(usernameFieldLogin);
        panel.add(new JLabel("Email:"));
        panel.add(emailFieldLogin);
        panel.add(new JLabel("Password:"));
        panel.add(passwordFieldLogin);
        panel.add(new JLabel()); // Placeholder
        panel.add(loginButton);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(panel, BorderLayout.CENTER);
        wrapperPanel.add(switchToRegisterButton, BorderLayout.SOUTH);

        return wrapperPanel;
    }

    // Method to create register panel
    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Full Name:"));
        panel.add(fullNameField);
        panel.add(new JLabel("Surname:"));
        panel.add(surnameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailFieldRegister);
        panel.add(new JLabel("Password:"));
        panel.add(passwordFieldRegister);
        panel.add(new JLabel()); // Placeholder
        panel.add(registerButton);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        return panel;
    }

    // Method to authenticate user (replace with actual authentication logic)
    private boolean authenticate(String username, String email, String password) {
        for (Admin admin : registeredAdmins) {
            if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    // Method to reset login fields
    private void resetLoginFields() {
        usernameFieldLogin.setText("");
        emailFieldLogin.setText("");
        passwordFieldLogin.setText("");
    }

    // Method to reset register fields
    private void resetRegisterFields() {
        fullNameField.setText("");
        surnameField.setText("");
        emailFieldRegister.setText("");
        passwordFieldRegister.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AdminLogin();
            }
        });
    }

    // Inner class representing an Admin
    private static class Admin {
        private String fullName;
        private String surname;
        private String email;
        private String password;

        public Admin(String fullName, String surname, String email, String password) {
            this.fullName = fullName;
            this.surname = surname;
            this.email = email;
            this.password = password;
        }

        public String getFullName() {
            return fullName;
        }

        public String getSurname() {
            return surname;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }
}
