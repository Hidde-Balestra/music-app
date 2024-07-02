package teamx.musiccollabbackend.databases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


//@Service
public class OegeDatabase {

    // Hardcoded credentials for now :)
    String url = "jdbc:mysql://oege.ie.hva.nl/zbijlsmy001?serverTimezone=UTC";
    String user = "bijlsmy001";
    String password = "I.Gv5i1B0UQsbr";

    private Connection connection;

    @Autowired
    public OegeDatabase() throws SQLException {
        connect();
    }

    private void connect() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database!");
            this.connection = connection;
        } finally {

        }
    }

    public String executeQuery(String query) throws SQLException {

        // Any code that wants to execute queries goes through me!
        try (Statement statement = connection.createStatement()) {
            // For now, we extract the sample data from the resultset and return it as string.
            // This will change once we settle on the datatypes
            try (ResultSet resultSet = statement.executeQuery(query)) {
                return getSamplesFrom(resultSet);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public String getSamplesFrom(ResultSet set) throws SQLException {

        // Much of this will have to be re-written once we decide on the datatypes
        // That's fine, this is just a placeholder to get a proof of concept

        ArrayList<String> samples = new ArrayList<>();

        while (set.next()) {
            String sampleName = set.getString("sampleName");
            String authorName = set.getString("authorName");
            String genre = set.getString("genre");
            String sampleData = set.getString("sampleData");

            String sampleSummary = "Sample: " + sampleName +
                    " by: " + authorName +
                    " genre: " + genre +
                    " with byte data: " + sampleData;

            samples.add(sampleSummary);

        }
        return samples.toString();
    }

    public String getAll() {
        try{
            return executeQuery("SELECT * FROM MusicSamples");
        }
        catch (SQLException e){
            return "nope";
        }
    }
}
