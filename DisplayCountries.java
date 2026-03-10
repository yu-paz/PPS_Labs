import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.sql.PreparedStatement;

public class DisplayCountries {
    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3306/nation";
        String user = "javauser";
        String password = "mypassword";

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a language: ");
        String inputLanguage = scanner.nextLine();
    try {
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();
        System.out.println("Connection to MariaDB established successfully!");
        System.out.println("Countries speaking" + inputLanguage);
        System.out.println("-----------------");
// Example: Execute a query
        String sql = 
        "SELECT c.name" + 
        " FROM countries c" + 
        " JOIN country_languages cl ON c.country_id = cl.country_id" +
        " JOIN languages l ON cl.language_id = l.language_id" +
        " WHERE l.language =?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, inputLanguage);
        ResultSet rs =  pstmt.executeQuery();
        
        
        while (rs.next()) {
            System.out.println(rs.getString("name"));
        }

        conn.close();
        scanner.close();
    } catch (SQLException e) {
        System.err.println("Database connection failed:");
        e.printStackTrace();
    }
    }   
}