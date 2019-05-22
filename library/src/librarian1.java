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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class librarian1 extends books {

    int librarianId;
    String librarianName;
    int librarianPassword;
    String librarianContactNo;

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

    void add(books bo) {

        Label leb11 = new Label();
        Label leb12 = new Label();
        Label leb13 = new Label();
        Label leb14 = new Label();
        Label leb15 = new Label();
        Label leb16 = new Label();
        leb11.setText("Book Id: ");
        leb12.setText("Name: ");
        leb13.setText("Written by: ");
        leb14.setText("Publications: ");
        leb15.setText("Edition: ");
        leb16.setText("Number of books: ");
        TextField textfield11 = new TextField();
        TextField textfield12 = new TextField();
        TextField textfield13 = new TextField();
        TextField textfield14 = new TextField();
        TextField textfield15 = new TextField();
        TextField textfield16 = new TextField();

        Button bt = new Button();
        bt.setText("Done");

        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.add(leb11, 0, 0);
        root.add(textfield11, 1, 0);
        root.add(leb12, 0, 1);
        root.add(textfield12, 1, 1);
        root.add(leb13, 0, 2);
        root.add(textfield13, 1, 2);
        root.add(leb14, 0, 3);
        root.add(textfield14, 1, 3);
        root.add(leb15, 0, 4);
        root.add(textfield15, 1, 4);
        root.add(leb16, 0, 5);
        root.add(textfield16, 1, 5);
        root.add(bt, 1, 6);

        Scene sce = new Scene(root, 400, 300);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("New Book Entry");
        primaryStage.setScene(sce);
        primaryStage.show();
        bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                bo.bookId = Integer.parseInt(textfield11.getText());
                bo.bookName = textfield12.getText();
                bo.author = textfield13.getText();
                bo.publisher = textfield14.getText();
                bo.edition = Integer.parseInt(textfield15.getText());
                bo.number = Integer.parseInt(textfield15.getText());
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "12345");
                    System.out.print("Database connection success\n");
                    Statement stmt = con.createStatement();
                    //System.out.print(bo.bookId);
                    stmt.executeUpdate("insert into book values (" + bo.bookId + ",'" + bo.bookName + "','" + bo.author + "','" + bo.publisher + "'," + bo.edition + "," + bo.number + ")");
                    con.close();
                    primaryStage.close();

                } catch (ClassNotFoundException ex) {
                    System.out.print("Database connection success");
                    Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

    }

    int issuedBooks(books bo) {
        if (bo.number >= 1) {
            bo.number = bo.number - 1;
            return 1;
        } else {
            return 0;
        }
    }

    int returnBooks(int i) {
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
            int i2 = i1 + 1;
            if (i1 > 0) {
                stmt.executeUpdate("update book set number = " + i2 + "  where bookId = " + i + ";");
                con.close();
                return 1;
            }

        } catch (ClassNotFoundException ex) {
            System.out.print("Database connection success");
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
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

    void viewLibrarian() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "12345");
            System.out.print("Database connection success\n");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from libririan;");
            FileWriter fw;
            try {
                fw = new FileWriter("D:\\Java\\library\\Write.txt");
                fw.write("Librarian Details \r\n\r\n");
                while (rs.next()) {
                    librarianId = Integer.parseInt(rs.getString("librarianId"));
                    librarianName = rs.getString("librarianName");
                    librarianPassword = Integer.parseInt(rs.getString("librarianPassword"));
                    librarianContactNo = rs.getString("librarianContactNo");

                    fw.write("Librarian Id: " + librarianId + "\r\n");
                    fw.write("Librarian Name: '" + librarianName + "'\r\n");
                    fw.write("Password: " + librarianPassword + "\r\n");
                    fw.write("Phone: '" + librarianContactNo + "'\r\n\r\n");

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

}
