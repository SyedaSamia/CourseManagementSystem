import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;

public class CourseDetails extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseDetails frame = new CourseDetails();
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
	public JTextField showCourseCode;
	public JTextField showCourseName;
	public JTextField showInstructor;
	public JTextField showStd;
	public CourseDetails() {
		super("Course Details");
		setBackground(new Color(0, 0, 0));
		setResizable(false);
		conn = DatabseConnection.connection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 353);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton button = new JButton("Home");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//setVisible(false); //this frame will be invisible will go to home frame
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
		contentPane.setBackground(new Color(238, 232, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCourseCode = new JLabel("Course Code");
		lblCourseCode.setBounds(43, 60, 62, 14);
		contentPane.add(lblCourseCode);
		
		showCourseCode = new JTextField();
		showCourseCode.setColumns(10);
		showCourseCode.setBounds(115, 57, 202, 20);
		contentPane.add(showCourseCode);
		
		JLabel lblCoursename = new JLabel("Course Name");
		lblCoursename.setBounds(43, 123, 70, 14);
		contentPane.add(lblCoursename);
		
		showCourseName = new JTextField();
		showCourseName.setColumns(10);
		showCourseName.setBounds(117, 120, 200, 20);
		contentPane.add(showCourseName);
		
		JLabel lblPhoneNo = new JLabel("Instructor");
		lblPhoneNo.setBounds(43, 184, 64, 14);
		contentPane.add(lblPhoneNo);
		
		showInstructor = new JTextField();
		showInstructor.setColumns(10);
		showInstructor.setBounds(117, 181, 200, 20);
		contentPane.add(showInstructor);
		
		JLabel lblCourseDetails = new JLabel("Course Details");
		lblCourseDetails.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCourseDetails.setBounds(150, 11, 143, 28);
		contentPane.add(lblCourseDetails);
		
		JLabel lblCredits = new JLabel("Std Enrolled");
		lblCredits.setBounds(43, 242, 70, 14);
		contentPane.add(lblCredits);
		
		showStd = new JTextField();
		showStd.setColumns(10);
		showStd.setBounds(115, 239, 202, 20);
		contentPane.add(showStd);
		
		JButton updateStd = new JButton("Update");
		updateStd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					stmt =  (Statement) conn.createStatement();
					int std = Integer.parseInt(showStd.getText());
					String courseCode = showCourseCode.getText();
					String sql = "UPDATE COURSE SET STUDENT_ENROLLED = '"+std+"' WHERE COURSE_CODE = '"+courseCode+"'";
					PreparedStatement pst = conn.prepareStatement(sql);
					pst.execute(sql);
					JOptionPane.showMessageDialog(null, "Total Credits Updated!");
				}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		updateStd.setBounds(327, 238, 89, 23);
		contentPane.add(updateStd);
		
		JButton button_3 = new JButton("Update");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					stmt =  (Statement) conn.createStatement();
					String name = showInstructor.getText();
					String courseCode = showCourseCode.getText();
					String sql = "UPDATE COURSE SET COURSE_INSTRUCTOR = '"+name+"' WHERE COURSE_CODE = '"+courseCode+"'";
					PreparedStatement pst = conn.prepareStatement(sql);
					pst.execute(sql);
					JOptionPane.showMessageDialog(null, "Instructor Updated!");
				}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		button_3.setBounds(327, 180, 89, 23);
		contentPane.add(button_3);
		
	/*	JLabel lblCourseTaken = new JLabel("Course Taken");
		lblCourseTaken.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCourseTaken.setBounds(151, 11, 129, 20);
		contentPane.add(lblCourseTaken);
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 32, 390, 171);
		contentPane.add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		
		try{
			stmt =  (Statement) conn.createStatement();			
			String sql = "SELECT * FROM COURSE_OFFERED_S";
			PreparedStatement pst = conn.prepareStatement(sql);
			rs =  pst.executeQuery(sql);
			table.setModel(DbUtils.resultSetToTableModel(rs));			
		
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}*/
		
	}
}
