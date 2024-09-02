import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AirlineReservationSystem {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginScreen();  // Display the login screen
        });
    }
}

class LoginScreen extends JFrame {
    private JTextField userIdField;
    private JPasswordField passwordField;

    public LoginScreen() {
        setTitle("Airline Reservation System - Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("User ID:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        userIdField = new JTextField(20);
        userIdField.setBounds(100, 20, 165, 25);
        panel.add(userIdField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 165, 25);
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(100, 80, 100, 25);
        panel.add(registerButton);

        // Action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userId = userIdField.getText();
                String password = new String(passwordField.getPassword());

                // Check credentials against text files
                if (validateAdminLogin(userId, password)) {
                    JOptionPane.showMessageDialog(null, "Admin Login Successful");
                    new AdminDashboard();
                    dispose(); // Close the login screen
                } else if (validateCustomerLogin(userId, password)) {
                    JOptionPane.showMessageDialog(null, "Customer Login Successful");
                    new CustomerDashboard();
                    dispose(); // Close the login screen
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Credentials");
                }
            }
        });

        // Action listener for the register button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterScreen();  // Open the registration screen
                dispose(); // Close the login screen
            }
        });
    }

    private boolean validateAdminLogin(String userId, String password) {
        // Implement validation logic using AdminLogin.txt
        return FileUtil.validateLogin("AdminLogin.txt", userId, password);
    }

    private boolean validateCustomerLogin(String userId, String password) {
        // Implement validation logic using CustomerLogin.txt
        return FileUtil.validateLogin("CustomerLogin.txt", userId, password);
    }
}

class AdminDashboard extends JFrame {
    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JButton addFlightButton = new JButton("Add Flight");
        addFlightButton.setBounds(10, 20, 150, 25);
        panel.add(addFlightButton);

        JButton editFlightButton = new JButton("Edit Flight");
        editFlightButton.setBounds(10, 60, 150, 25);
        panel.add(editFlightButton);

        JButton deleteFlightButton = new JButton("Delete Flight");
        deleteFlightButton.setBounds(10, 100, 150, 25);
        panel.add(deleteFlightButton);

        JButton viewFlightsButton = new JButton("View Flights");
        viewFlightsButton.setBounds(10, 140, 150, 25);
        panel.add(viewFlightsButton);

        // Action listeners for buttons to perform flight management operations
        addFlightButton.addActionListener(e -> new AddFlightScreen());
        editFlightButton.addActionListener(e -> new EditFlightScreen());
        deleteFlightButton.addActionListener(e -> new DeleteFlightScreen());
        viewFlightsButton.addActionListener(e -> new ViewFlightsScreen());
    }
}

class CustomerDashboard extends JFrame {
    public CustomerDashboard() {
        setTitle("Customer Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JButton viewFlightsButton = new JButton("View Available Flights");
        viewFlightsButton.setBounds(10, 20, 200, 25);
        panel.add(viewFlightsButton);

        JButton manageBookingButton = new JButton("Manage Booking");
        manageBookingButton.setBounds(10, 60, 200, 25);
        panel.add(manageBookingButton);

        // Action listeners for buttons to handle booking operations
        viewFlightsButton.addActionListener(e -> new ViewFlightsScreen());
        manageBookingButton.addActionListener(e -> new ManageBookingScreen());
    }
}

class RegisterScreen extends JFrame {
    private JTextField userIdField;
    private JPasswordField passwordField;

    public RegisterScreen() {
        setTitle("Airline Reservation System - Register");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("User ID:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        userIdField = new JTextField(20);
        userIdField.setBounds(100, 20, 165, 25);
        panel.add(userIdField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 165, 25);
        panel.add(passwordField);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(10, 80, 100, 25);
        panel.add(registerButton);

        // Action listener for the register button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userId = userIdField.getText();
                String password = new String(passwordField.getPassword());

                if (FileUtil.saveUser("CustomerLogin.txt", userId, password)) {
                    JOptionPane.showMessageDialog(null, "Registration Successful");
                    new LoginScreen();  // Go back to the login screen
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Registration Failed");
                }
            }
        });
    }
}

class FileUtil {
    public static boolean validateLogin(String fileName, String userId, String password) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] credentials = line.split(",");
                if (credentials[0].equals(userId) && credentials[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean saveUser(String fileName, String userId, String password) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(userId + "," + password + "\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Other utility methods for reading, updating, and deleting flight details can be added here.
}

// Placeholder classes for flight management screens
class AddFlightScreen extends JFrame {
    public AddFlightScreen() {
        setTitle("Add Flight");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

class EditFlightScreen extends JFrame {
    public EditFlightScreen() {
        setTitle("Edit Flight");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

class DeleteFlightScreen extends JFrame {
    public DeleteFlightScreen() {
        setTitle("Delete Flight");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

class ViewFlightsScreen extends JFrame {
    public ViewFlightsScreen() {
        setTitle("View Flights");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

class ManageBookingScreen extends JFrame {
    public ManageBookingScreen() {
        setTitle("Manage Booking");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
