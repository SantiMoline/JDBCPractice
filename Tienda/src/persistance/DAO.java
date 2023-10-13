package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DAO {
    Connection connection = null;
    Statement statement = null;
    ResultSet result = null;

    private final String USER = "root";
    private final String PASSWORD = "root1234";
    private final String DATABASE = "tienda";
    
    protected void connectDatabase() throws SQLException {
        try {
            String urlDatabase = "jdbc:mysql://localhost:3306/" + DATABASE + "?useSSL=false";
            connection = DriverManager.getConnection(urlDatabase, USER, PASSWORD);
        } catch (SQLException e) {
            throw e;
        }
    }

    protected void disconnectDatabase() throws SQLException {
        try {
            if (result != null)
                result.close();
            if (statement != null) 
                statement.close();
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    protected void insertUpdateDelete(String query) throws SQLException {
        try {
            connectDatabase();
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw e;
        } finally {
            disconnectDatabase();
        }
    }

    protected void queryDatabase(String query) throws SQLException {
        try {
            connectDatabase();
            statement = connection.createStatement();
            result = statement.executeQuery(query);
        } catch (SQLException e) {
            throw e;
        }
    }
}
