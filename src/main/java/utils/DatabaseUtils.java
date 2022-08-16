package utils;

import domain.ExpenseClaim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DatabaseUtils {

    public void saveData (List<ExpenseClaim> claims) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/expenseclaims","root", "c0nygre");

        claims.stream().forEach( claim -> {
            try {
                Statement statement = connection.createStatement();
                String command = "INSERT INTO expenseclaims (id, employeeId, dateOfClaim) VALUES (" +
                        claim.getId() + "," +
                        claim.getEmployeeId() + "," +
                        "\"" + claim.getDateOfClaim().toString() + "\" )";
                statement.execute(command);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
