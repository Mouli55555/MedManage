import javax.swing.*;
import java.sql.*;

public class PatientManagementRemove {

    // Image sources
    ImageIcon enterIcon = new ImageIcon(PatientManagementRemove.class.getResource("Enter.png"));
    ImageIcon checkIcon = new ImageIcon(PatientManagementRemove.class.getResource("check.png"));
    ImageIcon errorIcon = new ImageIcon(PatientManagementRemove.class.getResource("Error.png"));

    // Constructor
    public PatientManagementRemove() {
        String id = null;
        Connection connection = null;
        while (true) {
            id = (String) JOptionPane.showInputDialog(
                null,
                "Enter PatientID",
                "Enter",
                JOptionPane.PLAIN_MESSAGE,
                enterIcon,
                null,
                ""
            );
            
            if (id == null) {
                return; 
            }

            try {
                connection = DriverManager.getConnection(Global.url, Global.userName, Global.password);
                if (connection != null) {
                    String sql = "DELETE FROM Patients WHERE PatientID=?";
                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.setString(1, id);
                    
                    int rowsAffected = pst.executeUpdate();
                    
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Patient information Removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE, checkIcon);
                    } else {
                        JOptionPane.showMessageDialog(null, "Patient Not found", "Error", JOptionPane.ERROR_MESSAGE,errorIcon);
                    }
                    break;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Connection error","error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new PatientManagementRemove();
    }
}
