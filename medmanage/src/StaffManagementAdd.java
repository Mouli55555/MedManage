import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StaffManagementAdd {

    // Fields
    private JFrame staffAdd;
    private JTextField firstNameField, lastNameField, roleField, contactNumberField, emailField, addressField, cityField, stateField;
    private ImageIcon errorIcon, checkIcon;

    // Constructor
    public StaffManagementAdd() {
        // Image sources
        ImageIcon logo = new ImageIcon(StaffManagementAdd.class.getResource("MedManageLogo.png"));
        errorIcon = new ImageIcon(StaffManagementAdd.class.getResource("Error.png"));
        checkIcon = new ImageIcon(StaffManagementAdd.class.getResource("check.png"));

        // Frame creation
        staffAdd = new JFrame();
        staffAdd.setTitle("Staff Form");
        staffAdd.setSize(500, 600);
        staffAdd.setLocationRelativeTo(null);
        staffAdd.setLayout(null);
        staffAdd.setIconImage(logo.getImage());
        staffAdd.setResizable(false);
        staffAdd.getContentPane().setBackground(new Color(230, 247, 255));

        // Labels and text fields
        Font f1 = new Font("Times New Roman", Font.BOLD, 15);

        JLabel firstNameLabel = new JLabel("First Name: ");
        firstNameLabel.setFont(f1);
        firstNameLabel.setBounds(50, 50, 150, 30);
        staffAdd.add(firstNameLabel);
        firstNameField = new JTextField();
        firstNameField.setBounds(250, 50, 200, 30);
        staffAdd.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name: ");
        lastNameLabel.setFont(f1);
        lastNameLabel.setBounds(50, 100, 150, 30);
        staffAdd.add(lastNameLabel);
        lastNameField = new JTextField();
        lastNameField.setBounds(250, 100, 200, 30);
        staffAdd.add(lastNameField);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setFont(f1);
        roleLabel.setBounds(50, 150, 150, 30);
        staffAdd.add(roleLabel);
        roleField = new JTextField();
        roleField.setBounds(250, 150, 200, 30);
        staffAdd.add(roleField);

        JLabel contactNumberLabel = new JLabel("Contact Number: ");
        contactNumberLabel.setFont(f1);
        contactNumberLabel.setBounds(50, 200, 150, 30);
        staffAdd.add(contactNumberLabel);
        contactNumberField = new JTextField();
        contactNumberField.setBounds(250, 200, 200, 30);
        staffAdd.add(contactNumberField);

        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setFont(f1);
        emailLabel.setBounds(50, 250, 150, 30);
        staffAdd.add(emailLabel);
        emailField = new JTextField();
        emailField.setBounds(250, 250, 200, 30);
        staffAdd.add(emailField);

        JLabel addressLabel = new JLabel("Address: ");
        addressLabel.setFont(f1);
        addressLabel.setBounds(50, 300, 150, 30);
        staffAdd.add(addressLabel);
        addressField = new JTextField();
        addressField.setBounds(250, 300, 200, 30);
        staffAdd.add(addressField);

        JLabel cityLabel = new JLabel("City: ");
        cityLabel.setFont(f1);
        cityLabel.setBounds(50, 350, 150, 30);
        staffAdd.add(cityLabel);
        cityField = new JTextField();
        cityField.setBounds(250, 350, 200, 30);
        staffAdd.add(cityField);

        JLabel stateLabel = new JLabel("State: ");
        stateLabel.setFont(f1);
        stateLabel.setBounds(50, 400, 150, 30);
        staffAdd.add(stateLabel);
        stateField = new JTextField();
        stateField.setBounds(250, 400, 200, 30);
        staffAdd.add(stateField);

        // Adding KeyListener to text fields
        addEnterKeyListener(firstNameField, lastNameField);
        addEnterKeyListener(lastNameField, roleField);
        addEnterKeyListener(roleField, contactNumberField);
        addEnterKeyListener(contactNumberField, emailField);
        addEnterKeyListener(emailField, addressField);
        addEnterKeyListener(addressField, cityField);
        addEnterKeyListener(cityField, stateField);
        addEnterKeyListener(stateField, null); // Last field

        // Button
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(f1);
        submitButton.setBackground(Color.BLACK);
        submitButton.setForeground(Color.white);
        submitButton.setBounds(150, 480, 200, 50);
        submitButton.setFocusPainted(false);
        staffAdd.add(submitButton);

        // Action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateInput()) {
                    // Insert into database
                    insertIntoDatabase(firstNameField.getText(), lastNameField.getText(), roleField.getText(),
                            contactNumberField.getText(), emailField.getText(),
                            addressField.getText(), cityField.getText(), stateField.getText());
                }
            }
        });

        // Set initial focus
        firstNameField.requestFocusInWindow();

        // Visibility and exiting frame
        staffAdd.setVisible(true);
        staffAdd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // Method to add KeyListener to text fields
    private void addEnterKeyListener(JTextField currentField, JTextField nextField) {
        currentField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (nextField != null) {
                        nextField.requestFocusInWindow();
                    }
                }
            }
        });
    }

    // Validate input fields
    private boolean validateInput() {
        if (firstNameField.getText().trim().isEmpty() ||
                lastNameField.getText().trim().isEmpty() ||
                roleField.getText().trim().isEmpty() ||
                contactNumberField.getText().trim().isEmpty() ||
                emailField.getText().trim().isEmpty() ||
                addressField.getText().trim().isEmpty() ||
                cityField.getText().trim().isEmpty() ||
                stateField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all required fields.", "Input Error", JOptionPane.PLAIN_MESSAGE, errorIcon);
            return false;
        }
        return true;
    }

    // Insert data into the database
    private void insertIntoDatabase(String firstName, String lastName, String role, String contactNumber,
                                    String email, String address, String city, String state) {
        String insertSQL = "INSERT INTO Staff (FirstName, LastName, Role, ContactNumber, Email, Address, City, State) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(Global.url, Global.userName, Global.password);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, role);
            preparedStatement.setString(4, contactNumber);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, address);
            preparedStatement.setString(7, city);
            preparedStatement.setString(8, state);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int staffID = generatedKeys.getInt(1);
                        JOptionPane.showMessageDialog(null, "Staff information submitted successfully! Staff ID: " + staffID, "Success", JOptionPane.INFORMATION_MESSAGE, checkIcon);
                        resetForm();
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error inserting data into the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Reset the form fields
    private void resetForm() {
        firstNameField.setText("");
        lastNameField.setText("");
        roleField.setText("");
        contactNumberField.setText("");
        emailField.setText("");
        addressField.setText("");
        cityField.setText("");
        stateField.setText("");
        firstNameField.requestFocusInWindow();
        staffAdd.revalidate();
        staffAdd.repaint();
    }

    public static void main(String[] args) {
        new StaffManagementAdd();
    }
}
