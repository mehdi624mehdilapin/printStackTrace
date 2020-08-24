import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class Connecction {

	private static String adress= "jdbc:mysql://mysql:3306/";
	private static String user= "root";
	private static String password = "uvmxu624GJK6DXMS@";
	private static String dataBase = "external_metrics_push";
	private static String table = "external";
	private static Connection sigma;
	private static String tabelStructure= "CREATE TABLE " +table+" ("
		+"ID INT(32) AUTO_INCREMENT PRIMARY KEY, "
		+"DATE1 VARCHAR(20), "
		+"APPLICATION VARCHAR(20), "
		+"TEST_CASE_NAME VARCHAR(20), "
		+"TEST_STATUS VARCHAR(20), "
		+"NUMBER_OF_ERRORS INT(32) "
        +");";
 

		public static Connection connect() 
		{
		try
		{
		String connectionURL = adress + dataBase + "?serverTimezone=UTC";
		Connection connection = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection(connectionURL , user, password);
		
		return connection;
		}
		catch (ClassNotFoundException|SQLException e )
		{
		  createDataBase();
		  createTable();
		  return sigma;
		}
		
		}


	private static void createDataBase(){
		try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection c = DriverManager.getConnection(adress +"?serverTimezone=UTC", user , password);
		Statement s = c.createStatement();
		s.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dataBase);
		System.out.println("database created");
		
	}
		catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();

		}
	}
	private static void createTable(){
	
try{
   Class.forName("com.mysql.cj.jdbc.Driver");
   sigma = DriverManager.getConnection( adress + dataBase, user, password);
   Statement s = sigma.createStatement();
   s.executeUpdate(tabelStructure);
}
catch(ClassNotFoundException | SQLException e){

	e.printStackTrace();
	
}
	}


}
