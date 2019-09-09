import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField userNameField;
	private JPasswordField passwordField;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public login() {

		super("Login");
		conn = DatabseConnection.connection();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 483, 456);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(47, 79, 79));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUserName = new JLabel("Username");
		lblUserName.setForeground(SystemColor.text);
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUserName.setBounds(110, 144, 83, 20);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(SystemColor.text);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(110, 183, 75, 20);
		contentPane.add(lblPassword);
		
		userNameField = new JTextField();
		userNameField.setBounds(203, 137, 171, 29);
		contentPane.add(userNameField);
		userNameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(202, 181, 172, 29);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					stmt =  (Statement) conn.createStatement();
					String userName = userNameField.getText();
					String userPass = passwordField.getText();
					
					String sql = "SELECT * FROM ADMIN WHERE USERNAME = '"+userName+"' and PASSWORD = '"+userPass+"'";
					rs =  ((java.sql.Statement) stmt).executeQuery(sql);
					if(rs.next()){
						setVisible(false); //this frame will be invisible will go to home frame
						home obj = new home();
						obj.setVisible(true);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Username or Password is invalid.");
					}
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
				
			}
		});
		btnLogin.setBounds(125, 260, 89, 23);
		contentPane.add(btnLogin);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancel.setBounds(274, 260, 89, 23);
		contentPane.add(btnCancel);
		
		JLabel lblCourseManagement = new JLabel("Student Management System");
		lblCourseManagement.setForeground(SystemColor.text);
		lblCourseManagement.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblCourseManagement.setBounds(102, 43, 290, 75);
		contentPane.add(lblCourseManagement);
	}
}
