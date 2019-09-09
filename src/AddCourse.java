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

public class AddCourse extends JFrame {

	private JPanel contentPane;
	private JTextField courseCode;
	private JTextField courseName;
	private JTextField creditHour;
	private JTextField stdEnrolled;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCourse frame = new AddCourse();
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
	
	public AddCourse() {
		super("Add Course");
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
				Course obj = new Course();
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
		
		JLabel lblAddNewCourse = new JLabel("Add New Course");
		lblAddNewCourse.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAddNewCourse.setBounds(150, 11, 134, 20);
		contentPane.add(lblAddNewCourse);
		
		JLabel lblCourseName = new JLabel("Course Code");
		lblCourseName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCourseName.setBounds(37, 51, 101, 20);
		contentPane.add(lblCourseName);
		
		JLabel label = new JLabel("Course Name");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(37, 91, 101, 20);
		contentPane.add(label);
		
		JLabel lblCreditHour = new JLabel("Credit Hour");
		lblCreditHour.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCreditHour.setBounds(37, 133, 101, 20);
		contentPane.add(lblCreditHour);
		
		JLabel lblStudEnrolled = new JLabel("Student Enrolled");
		lblStudEnrolled.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStudEnrolled.setBounds(35, 167, 115, 33);
		contentPane.add(lblStudEnrolled);
		
		courseCode = new JTextField();
		courseCode.setColumns(10);
		courseCode.setBounds(174, 53, 213, 20);
		contentPane.add(courseCode);
		
		courseName = new JTextField();
		courseName.setColumns(10);
		courseName.setBounds(174, 91, 213, 20);
		contentPane.add(courseName);
		
		creditHour = new JTextField();
		creditHour.setColumns(10);
		creditHour.setBounds(174, 133, 213, 20);
		contentPane.add(creditHour);
		
		stdEnrolled = new JTextField();
		stdEnrolled.setColumns(10);
		stdEnrolled.setBounds(174, 175, 213, 20);
		contentPane.add(stdEnrolled);
		
		JButton button_3 = new JButton("CONFIRM");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					stmt =  (Statement) conn.createStatement();
					//int textId = Integer.parseInt(id.getText()); 
					String cCode = courseCode.getText();
					String cName = courseName.getText();
					String cCredit = creditHour.getText();
					String stdEnr = stdEnrolled.getText();
					String sql = "INSERT INTO COURSE VALUES('"+cCode+"','"+cName+"','"+cCredit+"','"+stdEnr+"')";
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Updated!");
					dispose();
					Course obj = new Course();
					obj.setVisible(true);
					
				}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		button_3.setBounds(298, 202, 89, 23);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("Cancel");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Course obj = new Course();
				obj.setVisible(true);
				dispose();
			}
		});
		button_4.setBounds(194, 202, 89, 23);
		contentPane.add(button_4);
	}

}
