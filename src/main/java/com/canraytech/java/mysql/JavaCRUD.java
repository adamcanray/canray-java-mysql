/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.canraytech.java.mysql;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
        
public class JavaCRUD {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/perpustakaan";
    static final String USER = "root";
    static final String PASS = "";
    
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStreamReader);
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        try {
            // register driver
            Class.forName(JDBC_DRIVER);
            
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            
            // looping while connected to database
            while(!conn.isClosed()){
                showMenu();
            }
            
            stmt.close();
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static void showMenu(){
        System.out.println("\n========== MAIN COURSE ==========");
        System.out.println("1. Insert Data");
        System.out.println("2. Show Data");
        System.out.println("3. Edit Data");
        System.out.println("4. Delete Data");
        System.out.println("0. Keluar");
        System.out.println("");
        System.out.println("SELECTION> ");
        
        try {
            int selection = Integer.parseInt(input.readLine());
            
            switch(selection){
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    insertBuku();
                    break;
                case 2:
                    showData();
                    break;
                case 3:
                    updateBuku();
                    break;
                case 4:
                    deleteBuku();
                    break;
                default:
                    System.out.println("Pilihan Salah!");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    static void showData(){
        String sql = "SELECT * FROM buku";
        try {
            rs = stmt.executeQuery(sql);

            System.out.println("+------------------------------+");
            System.out.println("|   BOOK DATA IN THE LIBRARY   |");
            System.out.println("+------------------------------+");

            while(rs.next()){
                int idBuku = rs.getInt("id_buku");
                String judul = rs.getString("judul");
                String pengarang = rs.getString("pengarang");

                System.out.println(String.format("%d. %s -- (%s)",idBuku, judul, pengarang));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static void insertBuku(){
        try {
            // input from user
            System.out.println("Judul: ");
            String judul = input.readLine().trim();
            System.out.println("Pengarang: ");
            String pengarang = input.readLine().trim();
        
            // query save
            String sql = "INSERT INTO buku (judul, pengarang) VALUES ('%s', '%s')";
            sql = String.format(sql, judul, pengarang);
            
            // save book
            stmt.execute(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static void updateBuku(){
        try {
            // input from user
            System.out.println("ID yang mau diedit: ");
            int idBuku = Integer.parseInt(input.readLine());
            System.out.println("Judul: ");
            String judul = input.readLine().trim();
            System.out.println("Pengarang: ");
            String pengarang = input.readLine().trim();
        
            // query save
            String sql = "UPDATE buku SET judul='%s', pengarang='%s' WHERE id_buku='%d'";
            sql = String.format(sql, judul, pengarang, idBuku);
            
            // save book
            stmt.execute(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static void deleteBuku() {
        try {

            // ambil input dari user
            System.out.println("ID yang mau dihapus: ");
            int idBuku = Integer.parseInt(input.readLine());

            // buat query hapus
            String sql = String.format("DELETE FROM buku WHERE id_buku=%d", idBuku);
            // hapus data
            stmt.execute(sql);

            System.out.println("Data telah terhapus...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
