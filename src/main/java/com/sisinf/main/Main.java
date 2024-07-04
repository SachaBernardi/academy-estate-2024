package com.sisinf.main;

import java.sql.*;

public class Main {

    private static final String dbDriver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/openjob";
    private static final String user = "root";
    private static final String password = "";


    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String sqlStudentInsert = "insert into student(first_name,age) values(?,?)";


        String sqlStudentUpdate = "update student" +
                "                  set first_name = ?," +
                "                       last_name = ?," +
                "                       age = ?" +
                "                  where id = ?";

        //Step 1: Indicazione del driver di connessione
        Class.forName(dbDriver);

        //Step 2: Apertura connessione
        Connection con = DriverManager.getConnection(url,user,password);

        //Step 3: Operazioni di crud
        //Step 3.1: Inserimento nella tabella student del database openjob
        //Il metodo prepared state riceve in input uno script anonimo, ovvero con parametri di placeholder
        PreparedStatement psInsert = con.prepareStatement(sqlStudentUpdate);
        psInsert.setString(1,"Kid");
        psInsert.setString(2,"Yugi");
        psInsert.setInt(3,23);
        psInsert.setInt(4, 4);

        psInsert.executeUpdate();

        //step 3.2 Solo lettura

        String sqlStudentRead = "select * from student";

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sqlStudentRead);

        while (rs.next()) {
            System.out.println(rs.getString("first_name") + " " + rs.getString("last_name") + " " + rs.getInt("age"));
        }

        con.close();
    }
}
