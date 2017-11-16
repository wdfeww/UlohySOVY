package com.database;

import com.models.Note;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionProvider {
    private final String URL = "jdbc:mysql://sql15.dnsserver.eu/";
    private final String DBNAME = "db93174xSOVY";
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String USERNAME = "db93174xSOVY";
    private final String PASSWORD = "MysqlSOVY352";

    private Connection getConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Connection conn = null;
        try {
            Class.forName(DRIVER).newInstance();
            conn = (com.mysql.jdbc.Connection) DriverManager.getConnection(URL + DBNAME, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
        }
        return conn;
    }

    public List<Note> getAllNotes() {
        try {
            Connection conn = getConnection();
            List<Note> notes = new ArrayList<>();
            String query = "SELECT * FROM notes";

            try {
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(query);
                if (conn != null) {
                    while (rs.next()) {
                        int id = rs.getInt("notes.id");
                        String text = rs.getString("text");
                        Note note = new Note(id, text);
                        notes.add(note);
                    }
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.toString());
            }
            return notes;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
        return null;
    }

    public boolean setNote(String text) {
        try {
            String query = "INSERT INTO notes (text) VALUES(?)";
            Connection conn = getConnection();
            try {
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, text);
                ps.execute();
                return true;
            } catch (SQLException ex) {
                System.out.println("Error: 'insertClientDetailsToDatabase' :" + ex.toString());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return false;
        }
    }

    public boolean deleteNote(String text) {
        try {
            String query = "DELETE FROM notes WHERE text = ?";
            Connection conn = getConnection();
            try {
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, text);
                ps.execute();
                return true;
            } catch (SQLException ex) {
                System.out.println("Error: 'insertClientDetailsToDatabase' :" + ex.toString());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return false;
        }
    }
}
