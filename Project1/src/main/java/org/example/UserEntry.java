package org.example;
import java.sql.*;
import java.util.Scanner;

public class UserEntry {

    public static void register(String name, String username, String password) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter your name:");
        name = sc.nextLine();

        System.out.println("Enter your Username:");
        username = sc.nextLine();

        System.out.println("Enter your Password:");
        password = sc.nextLine();
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_authentication", "root", "mysql419");

            Statement st= conn.createStatement();
            ResultSet rs=st.executeQuery("select username from login where username='" + username + "'"); // we are checking that if it already exist of not the username
            if (rs.next()){
                System.out.println("username already exist");
            }else {
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO login (name, username, password) VALUES (?, ?, ?)");

                pstmt.setString(1, name);
                pstmt.setString(2, username);
                pstmt.setString(3, password);
                pstmt.executeUpdate();
                System.out.println("Registration done");

            }
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void login(String username, String password) {

        Scanner sc=new Scanner(System.in);
        System.out.println("Enter your Username:");
        username = sc.nextLine();

        System.out.println("Enter your Password:");
        password = sc.nextLine();
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_authentication", "root", "mysql419");
            PreparedStatement pstmt = conn.prepareStatement( "SELECT * FROM login WHERE username = ? AND password = ?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                String name=rs.getString("name");
                System.out.println(name+" "+"Welcome");

            } else {
                System.out.println("Login failed");
            }

            rs.close();
            pstmt.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    }




