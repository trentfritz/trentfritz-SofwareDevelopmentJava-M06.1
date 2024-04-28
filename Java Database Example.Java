import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseExample {
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:sqlite:test.db";

    public static void main(String[] args) {
        try {
            // Connect to the database
            Connection conn = DriverManager.getConnection(JDBC_URL);
            System.out.println("Connected to the database");

            // Create a table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age INTEGER)";
            Statement statement = conn.createStatement();
            statement.execute(createTableSQL);
            System.out.println("Table created");

            // Insert some data
            String insertDataSQL = "INSERT INTO students (name, age) VALUES (?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(insertDataSQL);
            preparedStatement.setString(1, "John");
            preparedStatement.setInt(2, 25);
            preparedStatement.executeUpdate();

            preparedStatement.setString(1, "Jane");
            preparedStatement.setInt(2, 23);
            preparedStatement.executeUpdate();
            System.out.println("Data inserted");

            // Retrieve data
            String selectDataSQL = "SELECT * FROM students";
            ResultSet resultSet = statement.executeQuery(selectDataSQL);
            System.out.println("Retrieving data from database:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
            }

            // Close connections
            resultSet.close();
            preparedStatement.close();
            statement.close();
            conn.close();
            System.out.println("Disconnected from the database");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
