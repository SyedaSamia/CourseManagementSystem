import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Course extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Course frame = new Course();
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
	private JTable table;
	private JTextField textCourseCode;
	String courseCode;
	
	public Course() {
		super("Course");
		conn = DatabseConnection.connection();
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 494, 384);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton button = new JButton("Home");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCourse = new JLabel("Course");
		lblCourse.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCourse.setBounds(200, 0, 64, 20);
		contentPane.add(lblCourse);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 29, 468, 256);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton button_2 = new JButton("ADD");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCourse obj = new AddCourse();
				obj.setVisible(true);
				dispose();
			}
		});
		button_2.setBounds(10, 296, 89, 23);
		contentPane.add(button_2);
		
		JLabel lblCourseCode = new JLabel("Course Code");
		lblCourseCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCourseCode.setBounds(109, 296, 98, 19);
		contentPane.add(lblCourseCode);
		
		textCourseCode = new JTextField();
		textCourseCode.setColumns(10);
		textCourseCode.setBounds(200, 297, 98, 20);
		contentPane.add(textCourseCode);
		
		JButton button_4 = new JButton("Delete");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					stmt =  (Statement) conn.createStatement();	
					courseCode = textCourseCode.getText();
					String sql = "DELETE FROM COURSE WHERE  course_code = '"+courseCode+"'";
					stmt.executeUpdate(sql);
					
					JOptionPane.showMessageDialog(null, "Deleted Successfully!");
					dispose();
					Course obj = new Course();
					obj.setVisible(true);
					
				}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null, e);
				}
			
			}
		});
		button_4.setBounds(389, 296, 89, 23);
		contentPane.add(button_4);
		try{
			stmt =  (Statement) conn.createStatement();			
			String sql = "SELECT * FROM COURSE";
			PreparedStatement pst = conn.prepareStatement(sql);
			rs =  pst.executeQuery(sql);
			table.setModel(DbUtils.resultSetToTableModel(rs));			
			
			JButton courseDetails = new JButton("Search");
			courseDetails.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try{
						setVisible(false);
						stmt =  (Statement) conn.createStatement();	
						courseCode = textCourseCode.getText();
						
						String sql = "SELECT COURSE_NAME,COURSE_INSTRUCTOR,STUDENT_ENROLLED FROM COURSE WHERE course_code = '"+courseCode+"'";
						rs =  ((java.sql.Statement) stmt).executeQuery(sql);
						if(rs.next()){
							CourseDetails obj = new CourseDetails();
							obj.showCourseCode.setText(courseCode);
							obj.showCourseName.setText(rs.getString("COURSE_NAME"));
							obj.showInstructor.setText(rs.getString("COURSE_INSTRUCTOR"));
							obj.showStd.setText(rs.getString("STUDENT_ENROLLED"));
							
							obj.setVisible(true);
							//dispose();
						}
						else{
							JOptionPane.showMessageDialog(null, "Record not found.");
							Course obj = new Course();
							obj.setVisible(true);
							dispose();
						}
						
					} catch(Exception e1){
						JOptionPane.showMessageDialog(null, e1);
					}
					
					dispose();
				}
				
			});
			courseDetails.setBounds(303, 296, 89, 23);
			contentPane.add(courseDetails);
		
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
