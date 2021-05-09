/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.canraytech.java.mysql;

/**
 * import per class
 */
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * import keseluruhan class
 */
//import java.sql.*;

/**
 *
 * @author User-1
 */
public class JavaMysql {
    // prepare parameter JDBC(Java Data Base Connectivity) for connection to database
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/perpustakaan";
    static final String USER = "root";
    static final String PASS = "";
    // prepare object that needed for manage database
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    
    public static void main(String[] args){
        // connection to database
        // must wrapper by try/catch block
        try {
            // register driver that want to use
            Class.forName(JDBC_DRIVER);
            
            // create connection to database
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            // create statement object
            stmt = conn.createStatement();
            
            // query to database
            String sql = "SELECT * FROM buku";
            
            // execute query and save the result in ResultSet object
            rs = stmt.executeQuery(sql);
            
            // showing query result
            while(rs.next()){
                System.out.println("ID Buku: " + rs.getInt("id_buku"));
                System.out.println("Judul: " + rs.getString("judul"));
                System.out.println("Pengarang: " + rs.getString("pengarang"));
            }
            
            // close statement
            stmt.close();
            // close connection
            conn.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
