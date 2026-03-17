import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnection {

    public static void main(String[] args) {

        // Command line arguments: username, password, database
        if (args.length < 3) {
            System.out.println("Usage: java DBConnection <username> <password> <database>");
            System.exit(1);
        }

        String username = args[0];
        String password = args[1];
        String database = args[2];

        String url = "jdbc:mariadb://localhost:3306/" + database;

        // Query: join orders, customer, and salesman 
        String query =
            "SELECT o.order_no, " +
            "c.customer_name, " +
            "c.city AS customer_city, " +
            "s.name AS salesman_name, " +
            "o.purchase_amt, " +
            "s.commission " +
            "FROM orders o " +
            "JOIN customer c ON o.customer_id = c.customer_id " +
            "JOIN salesman s ON o.salesman_id = s.salesman_id " +
            "ORDER BY o.order_no";

        ArrayList<Sales> salesList = new ArrayList<>();

        //Connect and execute 
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database: " + database);

            Statement stmt = conn.createStatement();
            ResultSet rs   = stmt.executeQuery(query);

            while (rs.next()) {
                int orderNumber = rs.getInt("order_no");
                String customerName = rs.getString("customer_name");
                String customerCity = rs.getString("customer_city");
                String salesmanName = rs.getString("salesman_name");
                double amount = rs.getDouble("purchase_amt");
                double commissionRate = rs.getDouble("commission");
                double commissionAmount = amount * commissionRate;

                Sales sale = new Sales(orderNumber, customerName, customerCity,
                                       salesmanName, amount, commissionAmount);
                salesList.add(sale);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        // Print results 
        System.out.println("\n--- Sales Records (" + salesList.size() + " orders) ---\n");
        for (Sales s : salesList) {
            System.out.println(s);
        }
    }
}