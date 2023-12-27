package IO;
import java.io.*;
import java.io.IOException;
import java.sql.*;
import java.io.InputStream;

public class StoreImage {
	public static void main(String [] args)
	{
		String url = "jdbc:mysql://localhost:3307/test1";
		String user="root";
		String password="12345";

		String filePath="C:\\Users\\HP\\Pictures\\Saved Pictures\\MSD.jpg";
		try
		{

			Connection con=DriverManager.getConnection(url,user,password);
			String sql= "INSERT INTO person (name,image) values(?,?)";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1,"MSD");
			InputStream inputStream= new FileInputStream(new File(filePath));

			statement.setBlob(2,inputStream);

			

			int row= statement.executeUpdate();
			if(row > 0)

			{
				System.out.println("A contact was inserted with photo image");
			}

			con.close();
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		catch(IOException ex)

		{
			ex.printStackTrace();
		}
	}
}