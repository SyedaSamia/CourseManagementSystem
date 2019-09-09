import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

public class Admin extends JFrame {

	private JPanel contentPane;
	private JTable tableAdmin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin();
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
	
	public Admin() {
		super("Admin");
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 373, 300);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(UIManager.getColor("Button.highlight"));
		setJMenuBar(menuBar);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(73, 37, 223, 156);
		contentPane.add(scrollPane);
		
		tableAdmin = new JTable();
		scrollPane.setViewportView(tableAdmin);
		conn = DatabseConnection.connection();
		try{
			stmt =  (Statement) conn.createStatement();			
			String sql = "SELECT * FROM ADMIN";
			PreparedStatement pst = conn.prepareStatement(sql);
			rs =  pst.executeQuery(sql);
			tableAdmin.setModel(DbUtils.resultSetToTableModel(rs));			
		
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
