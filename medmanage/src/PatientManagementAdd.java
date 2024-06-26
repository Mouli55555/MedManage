import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PatientManagementAdd {

    // Fields
    private JFrame patientAdd;
    private JTextField firstNameField, lastNameField, dateOfBirthField, contactNumberField, emailField, addressField, cityField, stateField;
    private JComboBox<String> genderField;
    private ImageIcon errorIcon, checkIcon;

    // Constructor
    public PatientManagementAdd() {
        // Image sources
        ImageIcon logo = new ImageIcon(PatientManagementAdd.class.getResource("MedManageLogo.png"));
        errorIcon = new ImageIcon(PatientManagementAdd.class.getResource("Error.png"));
        checkIcon = new ImageIcon(PatientManagementAdd.class.getResource("check.png"));

        // Frame creation
        patientAdd = new JFrame();
        patientAdd.setTitle("Patient Form");
        patientAdd.setSize(500, 650);
        patientAdd.setLocationRelativeTo(null);
        patientAdd.setLayout(null); 
        patientAdd.setIconImage(logo.getImage());
        patientAdd.setResizable(false);
        patientAdd.getContentPane().setBackground(new Color(230, 247, 255));

        // Labels and text fields
        Font f1 = new Font("Times New Roman", Font.BOLD, 15);

        JLabel firstNameLabel = new JLabel("First Name: ");
        firstNameLabel.setFont(f1);
        firstNameLabel.setBounds(50, 50, 150, 30); 
        patientAdd.add(firstNameLabel);
        firstNameField = new JTextField();
        firstNameField.setBounds(250, 50, 200, 30); 
        patientAdd.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name: ");
        lastNameLabel.setFont(f1);
        lastNameLabel.setBounds(50, 100, 150, 30); 
        patientAdd.add(lastNameLabel);
        lastNameField = new JTextField();
        lastNameField.setBounds(250, 100, 200, 30); 
        patientAdd.add(lastNameField);

        JLabel dateOfBirthLabel = new JLabel("Date of Birth (yyyy-mm-dd):");
        dateOfBirthLabel.setFont(f1);
        dateOfBirthLabel.setBounds(50, 150, 200, 30); 
        patientAdd.add(dateOfBirthLabel);
        dateOfBirthField = new JTextField();
        dateOfBirthField.setBounds(250, 150, 200, 30);
        patientAdd.add(dateOfBirthField);

        JLabel genderLabel = new JLabel("Gender: ");
        genderLabel.setFont(f1);
        genderLabel.setBounds(50, 200, 150, 30); 
        patientAdd.add(genderLabel);
        genderField = new JComboBox<>(new String[]{"Select Gender", "Male", "Female", "Other"});
        genderField.setBounds(250, 200, 200, 30); 
        patientAdd.add(genderField);
        genderField.setSelectedIndex(0);

        JLabel contactNumberLabel = new JLabel("Contact Number: ");
        contactNumberLabel.setFont(f1);
        contactNumberLabel.setBounds(50, 250, 150, 30); 
        patientAdd.add(contactNumberLabel);
        contactNumberField = new JTextField();
        contactNumberField.setBounds(250, 250, 200, 30); 
        patientAdd.add(contactNumberField);

        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setFont(f1);
        emailLabel.setBounds(50, 300, 150, 30); 
        patientAdd.add(emailLabel);
        emailField = new JTextField();
        emailField.setBounds(250, 300, 200, 30); 
        patientAdd.add(emailField);

        JLabel addressLabel = new JLabel("Address: ");
        addressLabel.setFont(f1);
        addressLabel.setBounds(50, 350, 150, 30); 
        patientAdd.add(addressLabel);
        addressField = new JTextField();
        addressField.setBounds(250, 350, 200, 30); 
        patientAdd.add(addressField);

        JLabel cityLabel = new JLabel("City: ");
        cityLabel.setFont(f1);
        cityLabel.setBounds(50, 400, 150, 30); 
        patientAdd.add(cityLabel);
        cityField = new JTextField();
        cityField.setBounds(250, 400, 200, 30); 
        patientAdd.add(cityField);

        JLabel stateLabel = new JLabel("State: ");
        stateLabel.setFont(f1);
        stateLabel.setBounds(50, 450, 150, 30);
        patientAdd.add(stateLabel);
        stateField = new JTextField();
        stateField.setBounds(250, 450, 200, 30); 
        patientAdd.add(stateField);

        // Button
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(f1);
        submitButton.setBackground(Color.BLACK);
        submitButton.setForeground(Color.white);
        submitButton.setBounds(150, 530, 200, 50);
        submitButton.setFocusPainted(false);
        patientAdd.add(submitButton);

        // Action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateInput()) {
                    // Insert into database
                    insertIntoDatabase(firstNameField.getText(), lastNameField.getText(), genderField.getSelectedItem().toString(),
                            dateOfBirthField.getText(), contactNumberField.getText(), emailField.getText(),
                            addressField.getText(), cityField.getText(), stateField.getText());
                }
            }
        });

        // Add KeyListener to navigate with Enter key
        addEnterKeyListener(firstNameField, lastNameField);
        addEnterKeyListener(lastNameField, dateOfBirthField);
        addEnterKeyListener(dateOfBirthField, genderField);
        addEnterKeyListener(contactNumberField, emailField);
        addEnterKeyListener(emailField, addressField);
        addEnterKeyListener(addressField, cityField);
        addEnterKeyListener(cityField, stateField);
        addEnterKeyListener(stateField, submitButton);

        // Visibility and exiting frame
        patientAdd.setVisible(true);
        patientAdd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // Validate input fields
    private boolean validateInput() {
        if (firstNameField.getText().trim().isEmpty() ||
            lastNameField.getText().trim().isEmpty() ||
            genderField.getSelectedIndex() == 0 || 
            dateOfBirthField.getText().trim().isEmpty() ||
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
    private void insertIntoDatabase(String firstName, String lastName, String gender, String dateOfBirth, String contactNumber,
                                String email, String address, String city, String state) {
        String insertSQL = "INSERT INTO Patients (FirstName, LastName, Gender, DateOfBirth, ContactNumber, Email, Address, City, State) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(Global.url, Global.userName, Global.password);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, gender);
            preparedStatement.setString(4, dateOfBirth);
            preparedStatement.setString(5, contactNumber);
            preparedStatement.setString(6, email);
            preparedStatement.setString(7, address);
            preparedStatement.setString(8, city);
            preparedStatement.setString(9, state);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int patientID = generatedKeys.getInt(1);
                        JOptionPane.showMessageDialog(null, "Patient information submitted successfully! Patient ID: " + patientID, "Success", JOptionPane.INFORMATION_MESSAGE, checkIcon);
                        resetForm();
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inserting data into the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Reset the form fields
    private void resetForm() {
        firstNameField.setText("");
        lastNameField.setText("");
        dateOfBirthField.setText("");
        genderField.setSelectedIndex(0);
        contactNumberField.setText("");
        emailField.setText("");
        addressField.setText("");
        cityField.setText("");
        stateField.setText("");
        patientAdd.revalidate();
        patientAdd.repaint();
    }

    // Method to add Enter Key Listener
    // Add key listener for Enter key navigation
    private void addEnterKeyListener(JTextField currentField, JComponent nextComponent) {
    currentField.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                nextComponent.requestFocusInWindow();
            }
        }
    });
}

public static void main(String[] args) {
    new PatientManagementAdd();
}
}