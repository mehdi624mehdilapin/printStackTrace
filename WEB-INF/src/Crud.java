import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.json.*;
import org.json.JSONObject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.StringReader;
import java.sql.Connection;
public class Crud extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override 
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) {
		    
			String DATE1 = null;
			String APPLICATION = null;
			String TEST_CASE_NAME = null;
			String TEST_STATUS = null;
			int NUMBER_OF_ERRORS;
			try {
			BufferedReader receivedPayLoad = request.getReader();
			String str, wholeStr = "";
		        while ((str = receivedPayLoad.readLine()) != null) {
		        	wholeStr += str;
		        }
			JsonReader json = Json.createReader(new StringReader(wholeStr.toUpperCase()));
			//System.out.println(wholeStr.toUpperCase());
			JsonArray jsonArray =  json.readArray();
			ListIterator<JsonValue> i = jsonArray.listIterator();
			while(i.hasNext())
			{
				JsonObject o = i.next().asJsonObject();
				
				DATE1 = o.getString("DATE1");
				APPLICATION = o.getString("APPLICATION");
				TEST_CASE_NAME = o.getString("TEST_CASE_NAME");
				TEST_STATUS = o.getString("TEST_STATUS");
				NUMBER_OF_ERRORS = o.getInt("NUMBER_OF_ERRORS");
				Connection con = Connecction.connect();
				PreparedStatement ps = con.prepareStatement("INSERT INTO external(DATE1,APPLICATION,TEST_CASE_NAME,TEST_STATUS,NUMBER_OF_ERRORS) VALUES(?,?,?,?,?)");
				ps.setString(1,DATE1);
				ps.setString(2,APPLICATION);
				ps.setString(3,TEST_CASE_NAME);
				ps.setString(4,TEST_STATUS);
				ps.setInt(5,NUMBER_OF_ERRORS);
				ps.executeUpdate();
				
			}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			}

	@Override 
	public void doGet(HttpServletRequest request,
			HttpServletResponse response){
		
		ArrayList<DataObject> objects = new ArrayList<DataObject>();
		
		try {
			Connection con = Connecction.connect();
		PreparedStatement ps = con.prepareStatement("SELECT * FROM external");
		ResultSet data = ps.executeQuery();
		
		while(data.next()) {
			DataObject dataobject = new DataObject();
			dataobject.setDATE1(data.getString("DATE1"));
			dataobject.setAPPLICATION(data.getString("APPLICATION"));
			dataobject.setTESTCASE_NAME(data.getString("TEST_CASE_NAME"));
			dataobject.setTEST_STATUS(data.getString("TEST_STATUS"));
			dataobject.setNUMBER_OF_ERRORS(data.getInt("NUMBER_OF_ERRORS"));
			objects.add(dataobject);
			
			
		}
		ListIterator<DataObject> iterator = objects.listIterator();

		
		String[] Object= new String[2000];
		int i=0;
		while(iterator.hasNext()) {
			i++;
			DataObject temp = iterator.next();
			Object[i] = "{\n \"DATE1\" : "+JSONObject.quote(temp.DATE1)+",\n";
			Object[i]+= " \"APPLICATION\" : "+JSONObject.quote(temp.APPLICATION)+",\n";
			Object[i]+= " \"TEST_CASE_NAME\" : "+JSONObject.quote(temp.TEST_CASE_NAME)+",\n";
			Object[i]+= " \"TEST_STATUS\" : "+JSONObject.quote(temp.TEST_STATUS)+",\n";
			Object[i]+=" \"NUMBER_OF_ERRORS\" : "+temp.NUMBER_OF_ERRORS +"\n}";
			
		}
		
		String Object1="";
		int j;
		for(j=1;j<=i;j++)
		{
		Object1+=Object[j];
		if(i!=j)
		{
		Object1+=",\n";
		}
		}
		
		Object1="[\n"+Object1+"\n]";
		
		System.out.println((j-1)+" Rows");
		
		
		response.getOutputStream().println(Object1.toString());
		}catch(Exception e)
		
		{
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
}