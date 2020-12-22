package com;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
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

    public List<User> getUsers() {
        String SQL = "SELECT user_id, username, password, age FROM users_javafx";
        int count = 0;
        List<User> users = new ArrayList<>();
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getString("user_id"));
                user.setName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAge(rs.getString("age"));
                users.add(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }
    public User createUser(User user){
        String SQL = "INSERT INTO users_javafx (user_id, username, password,age) values ('"+user.getId()+"', '"+user.getName()+"', '"+user.getPassword()+"', '"+user.getAge()+"')";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
                stmt.execute(SQL);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return user;
    }
}
