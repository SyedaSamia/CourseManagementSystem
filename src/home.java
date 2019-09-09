import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class home extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					home frame = new home();
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
	public home() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 389);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(70, 130, 180));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 445, 21);
		contentPane.add(menuBar);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHome.setBackground(new Color(192, 192, 192));
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
		
		JLabel lblHomeView = new JLabel("Home View");
		lblHomeView.setBackground(new Color(70, 130, 180));
		lblHomeView.setForeground(Color.WHITE);
		lblHomeView.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHomeView.setBounds(165, 66, 121, 39);
		contentPane.add(lblHomeView);
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//setVisible(false); //this frame will be invisible will go to home frame
				Admin obj = new Admin();
				obj.setVisible(true);
				dispose();
			}
		});
		btnAdmin.setBounds(106, 157, 89, 23);
		contentPane.add(btnAdmin);
		
		JButton btnFaculty = new JButton("Faculty");
		btnFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false); //this frame will be invisible will go to home frame
				Faculty obj = new Faculty();
				obj.setVisible(true);
			}
		});
		btnFaculty.setBounds(252, 157, 89, 23);
		contentPane.add(btnFaculty);
		
		JButton btnStudent = new JButton("Student");
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false); //this frame will be invisible will go to home frame
				Student obj = new Student();
				obj.setVisible(true);
			}
		});
		btnStudent.setBounds(106, 222, 89, 23);
		contentPane.add(btnStudent);
		
		JButton btnCourse = new JButton("Course");
		btnCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false); //this frame will be invisible will go to home frame
				Course obj = new Course();
				obj.setVisible(true);
			}
		});
		btnCourse.setBounds(252, 222, 89, 23);
		contentPane.add(btnCourse);
	}
}
