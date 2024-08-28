package com.example.Databases;

import java.sql.*;

public class DbConnections {
    private static final String URL = "jdbc:mysql://localhost:3306/criminalrecords";
    private static final String USER = "root";
    private static final String PASSWORD = "Root@123";

    private Connection connection;

    public DbConnections() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection to MySQL database established successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to the database.");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public boolean addCriminal(String name, String crimeType, String crimeDetails, String location, String bloodGroup) {
        String sql = "INSERT INTO criminals (name, crimeType, crimeDetails, location, bloodgroup) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, crimeType);
            ps.setString(3, crimeDetails);
            ps.setString(4, location);
            ps.setString(5, bloodGroup);

            int rowsAffected = ps.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted.");
            return rowsAffected>0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while inserting criminal record.");
        }
        return false;
    }

    public boolean updateCriminal(int id, String name, String crimeType, String crimeDetails, String location, String bloodGroup) {
        String sql = "UPDATE criminals SET name=?, crimeType=?, crimeDetails=?, location=?, bloodgroup=? WHERE criminalID=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, crimeType);
            ps.setString(3, crimeDetails);
            ps.setString(4, location);
            ps.setString(5, bloodGroup);
            ps.setInt(6, id);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCriminal(int id) {
        String sql = "DELETE FROM criminals WHERE criminalID=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet getCriminal(int id) {
        String sql = "SELECT * FROM criminals WHERE criminalID=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't fetch the requested record at the moment.");
            return null;
        }
    }

    public ResultSet searchCriminal(String name, String bloodGroup, String crimeType,String crimeLocation, String cellNo) {
        StringBuilder sql = new StringBuilder("SELECT * FROM criminals WHERE 1=1");
        if (name != null) sql.append(" AND name = ?");
        if (bloodGroup != null) sql.append(" AND bloodgroup = ?");
        if (crimeType != null) sql.append(" AND crimeType = ?");
        if (crimeLocation != null) sql.append(" AND Location = ?");
        if (cellNo != null) sql.append(" AND cellNo = ?");

        try {
            PreparedStatement ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (name != null) ps.setString(index++, name);
            if (bloodGroup != null) ps.setString(index++, bloodGroup);
            if (crimeType != null) ps.setString(index++, crimeType);
            if (crimeLocation != null) ps.setString(index++, crimeLocation);
            if (cellNo != null) ps.setString(index++, cellNo);
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DbConnections dbc = new DbConnections();
        ResultSet rs = null;
        try {
            // Example operations
            dbc.addCriminal("James Bond", "Robbery", "Robbed a bank", "New York", "O+");

            // Fetch a criminal
            rs = dbc.getCriminal(1);
            if (rs != null && rs.next()) {
                int id = rs.getInt("criminalID");
                String name = rs.getString("name");
                String crime = rs.getString("crimeType");

                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Crime: " + crime);
            } else {
                System.out.println("No record found for the given ID.");
            }

            // Update a criminal
            boolean updated = dbc.updateCriminal(1, "John Doe", "Robbery", "Robbed a bank", "New York", "A+");
            System.out.println("Update successful: " + updated);

            // Delete a criminal
            boolean deleted = dbc.deleteCriminal(1);
            System.out.println("Delete successful: " + deleted);

            // Search for a criminal
            rs = dbc.searchCriminal("John Doe", null, null, null,null);
            if (rs != null && rs.next()) {
                System.out.println("Criminal Found: " + rs.getString("name"));
            } else {
                System.out.println("No criminal found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            dbc.close(); // Close database connection
        }
    }
}
