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

import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;

public class Faculty extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Faculty frame = new Faculty();
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
	ResultSet rs = null,rs1 = null, rs2 = null;
	private JTable table;
	private JTextField id;
	int textId;
	
	public Faculty() {
		super("Faculty");
		setResizable(false);
		conn = DatabseConnection.connection();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 398);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 25, 475, 304);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 512, 21);
		contentPane.add(menuBar);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false); //this frame will be invisible will go to home frame
				home obj = new home();
				obj.setVisible(true);
				dispose();
			}
		});
		menuBar.add(btnHome);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login obj = new login();
				obj.setVisible(true);
				dispose();
			}
		});
		menuBar.add(btnLogout);
		try{
			stmt =  (Statement) conn.createStatement();			
			String sql = "SELECT * FROM FACULTY_MEMBERS";
			PreparedStatement pst = conn.prepareStatement(sql);
			rs =  pst.executeQuery(sql);
			table.setModel(DbUtils.resultSetToTableModel(rs));			
		
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddFaculty obj = new AddFaculty();
				obj.setVisible(true);
				dispose();
			}
		});
		btnAdd.setBounds(10, 335, 89, 23);
		contentPane.add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					stmt =  (Statement) conn.createStatement();	
					textId = Integer.parseInt(id.getText());
					String myString = Integer.toString(textId);
					String sql = "DELETE FROM FACULTY_MEMBERS WHERE  id = '"+textId+"'";
					stmt.executeUpdate(sql);
					
					JOptionPane.showMessageDialog(null, "Deleted Successfully!");
					dispose();
					Faculty obj = new Faculty();
					obj.setVisible(true);
					
				}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnDelete.setBounds(396, 335, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnDetails = new JButton("Search");
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					setVisible(false);
					stmt =  (Statement) conn.createStatement();	
					textId = Integer.parseInt(id.getText());
					String myString = Integer.toString(textId);
					
					String sql = "SELECT NAME,MAIL FROM FACULTY_MEMBERS WHERE id = '"+textId+"'";
					rs =  ((java.sql.Statement) stmt).executeQuery(sql);
					if(rs.next()){
						FacultyDetails obj = new FacultyDetails();
						obj.showId.setText(myString);
						obj.showName.setText(rs.getString("NAME"));
						obj.showMail.setText(rs.getString("MAIL"));
						
						obj.setVisible(true);
						//dispose();
					}
					else{
						JOptionPane.showMessageDialog(null, "Record not found.");
						Faculty obj = new Faculty();
						obj.setVisible(true);
						dispose();
					}
					
				} catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
				
				
				dispose();
			}
		});
		btnDetails.setBounds(308, 335, 89, 23);
		contentPane.add(btnDetails);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblId.setBounds(162, 335, 24, 19);
		contentPane.add(lblId);
		
		id = new JTextField();
		id.setBounds(186, 336, 118, 20);
		contentPane.add(id);
		id.setColumns(10);
	}
	
	
}
