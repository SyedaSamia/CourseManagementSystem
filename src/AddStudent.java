import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AddStudent extends JFrame {

	private JPanel contentPane;
	private JTextField id;
	private JTextField name;
	private JTextField totCourse;
	private JTextField totCredit;
	private JTextField phoneNo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudent frame = new AddStudent();
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
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	public AddStudent() {
		super("Add Student");
		conn = DatabseConnection.connection();
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton button = new JButton("Home");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home obj = new home();
				obj.setVisible(true);
				dispose();
			}
		});
		menuBar.add(button);
		
		JButton button_1 = new JButton("Logout");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login obj = new login();
				obj.setVisible(true);
				dispose();
			}
		});
		menuBar.add(button_1);
		
		JButton button_2 = new JButton("Back");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student obj = new Student();
				obj.setVisible(true);
				dispose();
			}
		});
		menuBar.add(button_2);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddNewStudent = new JLabel("Add New Student");
		lblAddNewStudent.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAddNewStudent.setBounds(142, 11, 151, 20);
		contentPane.add(lblAddNewStudent);
		
		JLabel label = new JLabel("ID");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(52, 45, 46, 20);
		contentPane.add(label);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(52, 76, 46, 20);
		contentPane.add(lblName);
		
		JLabel lblCourseEnrolled = new JLabel("Course Enrolled");
		lblCourseEnrolled.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCourseEnrolled.setBounds(52, 107, 101, 20);
		contentPane.add(lblCourseEnrolled);
		
		JLabel lblTotalCredits = new JLabel("Total Credits");
		lblTotalCredits.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalCredits.setBounds(52, 139, 101, 20);
		contentPane.add(lblTotalCredits);
		
		JLabel lblPhoneNo = new JLabel("Phone No");
		lblPhoneNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPhoneNo.setBounds(52, 173, 101, 20);
		contentPane.add(lblPhoneNo);
		
		id = new JTextField();
		id.setColumns(10);
		id.setBounds(164, 47, 238, 20);
		contentPane.add(id);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(164, 78, 238, 20);
		contentPane.add(name);
		
		totCourse = new JTextField();
		totCourse.setColumns(10);
		totCourse.setBounds(164, 109, 238, 20);
		contentPane.add(totCourse);
		
		totCredit = new JTextField();
		totCredit.setColumns(10);
		totCredit.setBounds(164, 141, 238, 20);
		contentPane.add(totCredit);
		
		phoneNo = new JTextField();
		phoneNo.setColumns(10);
		phoneNo.setBounds(164, 175, 238, 20);
		contentPane.add(phoneNo);
		
		JButton button_3 = new JButton("Cancel");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id.setText("");
				name.setText("");
				totCourse.setText("");
				totCredit.setText("");
				phoneNo.setText("");
			}
			
		});
		button_3.setBounds(210, 206, 89, 23);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("CONFIRM");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					stmt =  (Statement) conn.createStatement();
					int textId = Integer.parseInt(id.getText());
					String textName = name.getText();
					String textTotCourse = totCourse.getText();
					String textTotCredit = totCredit.getText();
					String textPhone = phoneNo.getText();
					String sql = "INSERT INTO STUDENT VALUES('"+textId+"','"+textName+"','"+textTotCourse+"','"+textTotCredit+"','"+textPhone+"')";
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Updated!");
					dispose();
					Student obj = new Student();
					obj.setVisible(true);
					
					
				}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		button_4.setBounds(312, 206, 89, 23);
		contentPane.add(button_4);
	}

}
