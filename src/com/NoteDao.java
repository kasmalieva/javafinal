package com;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteDao {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "12345";

    public Connection connect(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public List<Note> getNotes() {
        String SQL = "SELECT text,userid FROM notes_javafx";
        int count = 0;
        List<Note> notes = new ArrayList<>();
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                Note note = new Note();
                note.setText(rs.getString("text"));
                note.setUserId(rs.getString("userid"));
                notes.add(note);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return notes;
    }
    public Note createNote(Note note){
        String SQL = "INSERT INTO notes_javafx (text, userid) values ('"+note.getText()+"', '"+note.getUserId()+"')";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(SQL);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return note;
    }
}
