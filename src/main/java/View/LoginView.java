package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;

import Model.Person;
import Utils.LoginUtil;

public class LoginView {

  private HashMap<String, String> loginInfo;
  public String userName;
  public String password;

  public JFrame registerFrame;

  public LoginView(String userName, String password) {

  }

  public void register() {
    // Create a JFrame for the registration form
    registerFrame = new JFrame("HuskSheets");
    registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only the registration window

    // Create components for the registration form
    JPanel panel = new JPanel();
    JLabel usernameLabel = new JLabel("Username:");
    JLabel passwordLabel = new JLabel("Password:");
    JTextField usernameField = new JTextField(20);
    JPasswordField passwordField = new JPasswordField(20);
    JButton createAccountButton = new JButton("Create Account");
    JButton submitButton = new JButton("Submit");

    // Set layout for the panel
    panel.setLayout(new GridLayout(4, 2)); // Adjusted to fit the additional button

    // Add components to the panel
    panel.add(usernameLabel);
    panel.add(usernameField);
    panel.add(passwordLabel);
    panel.add(passwordField);
    panel.add(createAccountButton);
    panel.add(submitButton); // Add the Submit button to the panel

    // Add panel to the registerFrame
    registerFrame.getContentPane().add(panel);

    // Add action listener to the createAccountButton
    createAccountButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Retrieve username and password
        String username = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);

        // Store the username and password
        LoginView.this.userName = username;
        LoginView.this.password = password;



        // Close the registration frame
        registerFrame.dispose();

      }
    });

    // Add action listener to the submitButton
    submitButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String userName = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);
        LoginView.this.userName = userName;
        LoginView.this.password = password;
        login(userName, password);
      }
    });

    // Set the size of the registerFrame to be larger
    registerFrame.setSize(400, 300); // Set the size to 400x300 pixels
    registerFrame.setLocationRelativeTo(null);
    registerFrame.setVisible(true);
  }


  public void login(String userName, String password) {
    String result = LoginUtil.login(userName, password);
    if (result.getPersonId() != null) {
      JOptionPane.showMessageDialog(null, "Login successful! Person ID: " + result.getPersonId(), "Login Success", JOptionPane.INFORMATION_MESSAGE);
      registerFrame.dispose();
      showMainWindow();
    } else {
      JOptionPane.showMessageDialog(null, "Login failed: Invalid username or password.", "Login Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  public void showMainWindow() {
    JFrame mainFrame = new JFrame("Main Application");
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create a button for the main window
    JButton mainButton = new JButton("Main Button");
    mainButton.setFont(new Font("Arial", Font.BOLD, 14));
    mainButton.setForeground(new Color(70, 130, 180));

    // Add the button to the frame
    mainFrame.getContentPane().add(mainButton, BorderLayout.CENTER);

    // Set the size of the mainFrame and make it visible
    mainFrame.setSize(500, 400);
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setVisible(true);
  }

  public static void main(String[] args) {
    // Create an instance of view.LoginView and call the register method
    LoginView loginView = new LoginView(null, null);
    loginView.register();
  }
}

