/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class user extends books {

    int userId;
    String userName;
    int userPassword;
    String userContactNo;

    @Override
    void displayBookDetails() {
    try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "12345");
            System.out.print("Database connection success\n");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from book;");
            FileWriter fw;
            try {
                fw = new FileWriter("D:\\Java\\library\\Write.txt");
                fw.write("Book Details \r\n\r\n");
                while (rs.next()) {
                    bookId = Integer.parseInt(rs.getString("bookId"));
                    bookName = rs.getString("bookName");;
                    author = rs.getString("author");;
                    publisher = rs.getString("publisher");;
                    edition = Integer.parseInt(rs.getString("edition"));
                    number = Integer.parseInt(rs.getString("number"));
                       
        
                    fw.write("Book Id     :" + bookId + "\r\n");
                    fw.write("Book Name   :'" + bookName + "'\r\n");
                    fw.write("Author      :'" + author + "'\r\n");
                    fw.write("Publication :'" + publisher + "'\r\n");
                    fw.write("Edition     :" + edition + "\r\n");
                    fw.write("NO of books :" + number + "\r\n\r\n");

                }
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(librarian1.class.getName()).log(Level.SEVERE, null, ex);
            }

            con.close();
        } catch (ClassNotFoundException ex) {
            System.out.print("Database connection success");
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void searchBook(String st) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "12345");
            System.out.print("Database connection success\n");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from book where bookName ='" + st + "';");
            while (rs.next()) {

                bookId = Integer.parseInt(rs.getString("bookId"));
                bookName = rs.getString("bookName");
                author = rs.getString("author");
                publisher = rs.getString("publisher");
                edition = Integer.parseInt(rs.getString("edition"));
                number = Integer.parseInt(rs.getString("number"));
                Label leb11 = new Label();
                Label leb12 = new Label();
                Label leb13 = new Label();
                Label leb14 = new Label();
                Label leb15 = new Label();
                Label leb16 = new Label();
                leb11.setText("Book Id: " + bookId);
                leb12.setText("Name: " + bookName);
                leb13.setText("Written by: " + author);
                leb14.setText("Publications: " + publisher);
                leb15.setText("Edition: " + edition);
                leb15.setText("Number of books: " + number);

                GridPane rood = new GridPane();
                rood.setAlignment(Pos.CENTER);
                rood.add(leb11, 0, 0);
                rood.add(leb12, 0, 1);
                rood.add(leb13, 0, 2);
                rood.add(leb14, 0, 3);
                rood.add(leb15, 0, 4);
                Scene sce = new Scene(rood,300,200);

                Stage primaryStage = new Stage();
                primaryStage.setTitle("Book Details");
                primaryStage.setScene(sce);
                primaryStage.show();
            }

            con.close();
        } catch (ClassNotFoundException ex) {
            System.out.print("Database connection success");
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void borrowBook() {

    }

    void add() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    int searchById(int i) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "12345");
            System.out.print("Database connection success\n");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from book where bookId = " + i + ";");
            int i1 = 0;
            while (rs.next()) {

                i1 = Integer.parseInt(rs.getString("number"));
            }

            con.close();
            return i1;
        } catch (ClassNotFoundException ex) {
            System.out.print("Database connection success");
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
