import java.sql.*;
public class Scrollable {

   public static void main(String[] args) {
       String url = "jdbc:mysql://localhost:3306/revature";
       String username = "root";
         String password = "root123";

       try (Connection conn = DriverManager.getConnection(url, username, password)) {

           String sql = "SELECT * FROM emp";

           Statement statement = conn.createStatement(
                   ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

           ResultSet result = statement.executeQuery(sql);

           result.first();

           readEmpInfo("first", result);

           result.relative(3);

           readEmpInfo("relative(3)", result);

           result.previous();

           readEmpInfo("previous", result);

           result.absolute(4);

           readEmpInfo("absolute(4)", result);

           result.last();

           readEmpInfo("last", result);

           result.relative(-2);

           readEmpInfo("relative(-2)", result);


       } catch (SQLException ex) {
           ex.printStackTrace();
       }

   }

   private static void readEmpInfo(String position, ResultSet result)
           throws SQLException {
       String empid = result.getString("empid");
       String ename = result.getString("empname");
       String salary = result.getString("salary");

       String empInfo = "%s: %s - %s - %s\n";
       System.out.format(empInfo,position,empid, ename,salary);
   }
}