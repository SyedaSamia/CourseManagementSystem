import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AddFaculty extends JFrame {

	private JPanel contentPane;
	private JTextField id;
	private JTextField name;
	private JTextField mail;
	private JTextField totCourse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddFaculty frame = new AddFaculty();
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
	public AddFaculty() {
		super("Add faculty");
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
				Faculty obj = new Faculty();
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
		
		JLabel lblAddNewFaculty = new JLabel("Add New Faculty");
		lblAddNewFaculty.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAddNewFaculty.setBounds(149, 11, 151, 20);
		contentPane.add(lblAddNewFaculty);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblId.setBounds(55, 48, 46, 20);
		contentPane.add(lblId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(55, 84, 46, 20);
		contentPane.add(lblName);
		
		JLabel lblMail = new JLabel("Mail");
		lblMail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMail.setBounds(55, 119, 46, 26);
		contentPane.add(lblMail);
		
		JLabel lblHowManyCourses = new JLabel("Total Courses");
		lblHowManyCourses.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHowManyCourses.setBounds(55, 156, 93, 32);
		contentPane.add(lblHowManyCourses);
		
		id = new JTextField();
		id.setBounds(159, 50, 238, 20);
		contentPane.add(id);
		id.setColumns(10);
		
		name = new JTextField();
		name.setBounds(159, 86, 238, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		mail = new JTextField();
		mail.setColumns(10);
		mail.setBounds(159, 124, 238, 20);
		contentPane.add(mail);
		
		totCourse = new JTextField();
		totCourse.setColumns(10);
		totCourse.setBounds(159, 164, 238, 20);
		contentPane.add(totCourse);
		
		JButton btnConfirm = new JButton("CONFIRM");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					stmt =  (Statement) conn.createStatement();
					int textId = Integer.parseInt(id.getText()); 
					String textName = name.getText();
					String textMail = mail.getText();
					int textTotCourse = Integer.parseInt(totCourse.getText());
					String sql = "INSERT INTO FACULTY_MEMBERS VALUES('"+textId+"','"+textName+"','"+textMail+"','"+textTotCourse+"')";
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Updated!");
					dispose();
					Faculty obj = new Faculty();
					obj.setVisible(true);
					
				/*	id.setText("");
					name.setText("");
					mail.setText("");
					totCourse.setText("");	*/
					
				}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnConfirm.setBounds(307, 195, 89, 23);
		contentPane.add(btnConfirm);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id.setText("");
				name.setText("");
				mail.setText("");
				totCourse.setText("");		
			}
		});
		btnCancel.setBounds(211, 195, 89, 23);
		contentPane.add(btnCancel);
	}

}
