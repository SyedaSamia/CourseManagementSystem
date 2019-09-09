import java.sql.DriverManager;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class DatabseConnection {
	
	final static String USER = "root";
	final static String PASS = "";
	public static Connection connection(){
	
		try{  
			   //step1 load the driver class  
			   Class.forName("oracle.jdbc.driver.OracleDriver");  
			  
			   //step2 create  the connection object  
			   Connection conn=DriverManager.getConnection(  
			   "jdbc:oracle:thin:@localhost:1521:SIMCHANG","simchang","123");  
			 
			   return conn; 
			  
			  }
		catch(Exception e){ 
			JOptionPane.showMessageDialog(null, e);
			   return null;
			  }  

	}
	
}
