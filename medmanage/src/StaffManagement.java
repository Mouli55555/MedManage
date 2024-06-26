//importing
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class StaffManagement {
    public StaffManagement(){
        //image sources
        ImageIcon logo2 = new ImageIcon(StaffManagement.class.getResource("MedManageLogo.png"));
        ImageIcon logoSmall = new ImageIcon(StaffManagement.class.getResource("logoSmall.png"));
        ImageIcon staffAddIcon = new ImageIcon(StaffManagement.class.getResource("staffManagementAdd.png"));
        ImageIcon staffRemoveIcon = new ImageIcon(StaffManagement.class.getResource("staffManagementRemove.png"));
        ImageIcon staffSearchIcon = new ImageIcon(StaffManagement.class.getResource("staffManagementSearch.png"));
        ImageIcon staffListIcon = new ImageIcon(StaffManagement.class.getResource("staffManagementList.png"));

        //frame creation
        JFrame staffManagement = new JFrame();
        staffManagement.setSize(1440, 900);
        staffManagement.setLocationRelativeTo(null);
        staffManagement.setResizable(false);
        staffManagement.setLayout(null);
        staffManagement.setTitle("Staff Management");
        staffManagement.getContentPane().setBackground(new Color(230, 247, 255));
        staffManagement.setIconImage(logo2.getImage());

        //Label
        JLabel medLabel = new JLabel();
        medLabel.setText("STAFF MANAGEMENT");
        medLabel.setBounds(200, 50, 1440, 150);
        medLabel.setFont(new Font("Times new roman", Font.BOLD, 100)); 
        medLabel.setForeground(Color.BLACK);

        JLabel logoLabel = new JLabel();
        logoLabel.setIcon(logoSmall);
        logoLabel.setBounds(70, 50, 200, 150);
        

        //Option buttons
        JButton staffAdd = new JButton();
        staffAdd.setBounds(50, 350, 250, 250);
        staffAdd.setIcon(staffAddIcon);
        staffAdd.setBackground(new Color(230, 255, 242));
        staffAdd.setFocusPainted(false);

        JButton staffRemove = new JButton();
        staffRemove.setBounds(400, 350, 250, 250);
        staffRemove.setIcon(staffRemoveIcon);
        staffRemove.setBackground(new Color(230, 255, 242));
        staffRemove.setFocusPainted(false);

        JButton staffSearch = new JButton();
        staffSearch.setBounds(770, 350, 250, 250);
        staffSearch.setIcon(staffSearchIcon);
        staffSearch.setBackground(new Color(230, 255, 242));
        staffSearch.setFocusPainted(false);

        JButton staffList = new JButton();
        staffList.setBounds(1140, 350, 250, 250);
        staffList.setIcon(staffListIcon);
        staffList.setBackground(new Color(230, 255, 242));
        staffList.setFocusPainted(false);

        //mouse events
        staffAdd.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e){
                staffAdd.setBounds(35, 340, 280, 280);
            }
            public void mouseExited(MouseEvent e){
                staffAdd.setBounds(50, 350, 250, 250);
            }
            public void mousePressed(MouseEvent e){
                staffAdd.setBounds(50, 350, 250, 250);
            }
        });

        staffRemove.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e){
                staffRemove.setBounds(385, 340, 280, 280);
            }
            public void mouseExited(MouseEvent e){
                staffRemove.setBounds(400, 350, 250, 250);
            }
            public void mousePressed(MouseEvent e){
                staffRemove.setBounds(400, 350, 250, 250);
            }
        });

        staffSearch.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e){
                staffSearch.setBounds(755, 340, 280, 280);
            }
            public void mouseExited(MouseEvent e){
                staffSearch.setBounds(770, 350, 250, 250);
            }
            public void mousePressed(MouseEvent e){
                staffSearch.setBounds(770, 350, 250, 250);
            }
        });

        staffList.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e){
                staffList.setBounds(1125, 340, 280, 280);
            }
            public void mouseExited(MouseEvent e){
                staffList.setBounds(1140, 350, 250, 250);
            }
            public void mousePressed(MouseEvent e){
                staffList.setBounds(1140, 350, 250, 250);
            }
        });

        //action listener
        staffAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new StaffManagementAdd();
            }
        });
        staffRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new StaffManagementRemove();
            }
        });
        staffSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new StaffManagementSearch();
            }
        });
        staffList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new StaffManagementList();
            }
        });
        //Frame add
        staffManagement.add(staffAdd);
        staffManagement.add(medLabel);
        staffManagement.add(logoLabel);
        staffManagement.add(staffRemove);
        staffManagement.add(staffSearch);
        staffManagement.add(staffList);

        //visability
        staffManagement.setVisible(true);
        staffManagement.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public static void main(String[] args) {
        new StaffManagement();
    }
}