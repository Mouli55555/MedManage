//importing packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuFrame{
    public MenuFrame(){

        //image sources
        ImageIcon logo2 = new ImageIcon(MenuFrame.class.getResource("MedManageLogo.png"));
        ImageIcon patientIcon = new ImageIcon(MenuFrame.class.getResource("MenuPatientManagementIcon.png"));
        ImageIcon doctorIcon = new ImageIcon(MenuFrame.class.getResource("MenuDoctorManagementIcon.png"));
        ImageIcon staffIcon = new ImageIcon(MenuFrame.class.getResource("MenuStaffManagementIcon.png"));
        ImageIcon logosmall = new ImageIcon(MenuFrame.class.getResource("logoSmall.png"));

      
        //frame creation
        JFrame menuFrame = new JFrame();
        menuFrame.setSize(1440, 900);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setTitle("Menu");
        menuFrame.setResizable(false);
        menuFrame.setLayout(null);
        menuFrame.getContentPane().setBackground(new Color(230, 247, 255));
        menuFrame.setIconImage(logo2.getImage());

        //labels
        JButton patientInfo = new JButton();
        patientInfo.setIcon(patientIcon);
        patientInfo.setBounds(30, 250, 400, 400);
        patientInfo.setBackground(new Color(230, 255, 242));
        patientInfo.setFocusPainted(false);

        JButton doctorInfo = new JButton();
        doctorInfo.setIcon(doctorIcon);
        doctorInfo.setBounds(520, 250, 400, 400);
        doctorInfo.setBackground(new Color(230, 255, 242));
        doctorInfo.setFocusPainted(false);


        JButton staffInfo = new JButton();
        staffInfo.setIcon(staffIcon);
        staffInfo.setBounds(1010, 250, 400, 400);
        staffInfo.setBackground(new Color(230, 255, 242));
        staffInfo.setFocusPainted(false);

        JLabel textLabel = new JLabel("MED MANAGE");
        textLabel.setBounds(400, 10, 1000, 150); 
        textLabel.setFont(new Font("Times new roman", Font.BOLD, 100)); 
        textLabel.setForeground(Color.BLACK);

        JLabel logo = new JLabel();
        logo.setIcon(logosmall);
        logo.setBounds(300, 30, 100, 100);


        //mouse events
        patientInfo.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e){
                patientInfo.setBounds(20, 260, 420, 420);
            }
            public void mouseExited(MouseEvent e){
                patientInfo.setBounds(30, 250, 400, 400);
            }
            public void mousePressed(MouseEvent e){
                patientInfo.setBounds(30, 250, 400, 400);
            }
        });
        doctorInfo.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e){
                doctorInfo.setBounds(510, 260, 420, 420);
            }
            public void mouseExited(MouseEvent e){
                doctorInfo.setBounds(520, 250, 400, 400);
            }
            public void mousePressed(MouseEvent e){
                doctorInfo.setBounds(520, 250, 400, 400);
            }
        });
        staffInfo.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e){
                staffInfo.setBounds(1000, 260, 420, 420);
            }
            public void mouseExited(MouseEvent e){
                staffInfo.setBounds(1010, 250, 400, 400);
            }
            public void mousePressed(MouseEvent e){
                staffInfo.setBounds(1010, 250, 400, 400);
            }
        });

        //action listener
        doctorInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new DoctorManagement();
            }
        });
        patientInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new PatientManagement();
            }
        });
        staffInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new StaffManagement();
            }
        });

        //frame add
        menuFrame.add(patientInfo);
        menuFrame.add(doctorInfo);
        menuFrame.add(staffInfo);
        menuFrame.add(textLabel);
        menuFrame.add(logo);

        //visability
        menuFrame.setVisible(true);
        menuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public static void main(String[] args) {
        new MenuFrame();
    }
}