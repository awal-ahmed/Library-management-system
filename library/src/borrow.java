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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class borrow extends user {

    int borrowNumber;
    int bookId;
    int studentId;

    public void update() {
        Label leb11 = new Label();
        Label leb12 = new Label();
        Label leb13 = new Label();
        Label leb14 = new Label();
        leb11.setText("Book Id:");
        leb12.setText("User Id: ");
        leb13.setText("Borrow Nember: ");
        leb14.setText("User password: ");
        TextField textfield11 = new TextField();
        TextField textfield12 = new TextField();
        TextField textfield13 = new TextField();
        TextField textfield14 = new TextField();

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
        root.add(bt, 1, 4);

        Scene sce = new Scene(root, 400, 300);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("New Book Entry");
        primaryStage.setScene(sce);
        primaryStage.show();
        bt.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                int username = Integer.parseInt(textfield12.getText());
                int password = Integer.parseInt(textfield14.getText());
                int i = 0;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "12345");
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM user");
                    while (rs.next()) {
                        int in = rs.getInt("userId");
                        int in1 = rs.getInt("userPassword");
                        if (username == in && password == in1) {
                            i = 1;
                            break;
                        }
                        con.close();
                    }
                } catch (ClassNotFoundException ex) {
                    System.out.print("Database connection success");
                    Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (i == 1) {
                    int i1 = Integer.parseInt(textfield11.getText());
                    int i2 = Integer.parseInt(textfield13.getText());
                    int k = update(i2, i1, username);
                    if (k != 0) {
                        primaryStage.close();
                    }
                } else {
                    System.out.print("Enter Correct Password");
                }

            }
        });

    }

    public int update(int i, int i1, int i2) {
        borrowNumber = i;
        bookId = i1;
        studentId = i2;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "12345");
            System.out.print("Database connection success\n");
            Statement stmt = con.createStatement();
            //System.out.print(bo.bookId);
            stmt.executeUpdate("insert into borrow values (" + borrowNumber + "," + bookId + "," + studentId + ");");
            ResultSet rs = stmt.executeQuery("select * from book where bookId = " + i1 + ";");
            int k = 0;
            while (rs.next()) {

                k = Integer.parseInt(rs.getString("number"));
            }
            int k1 = k - 1;
            if (k1 > 0) {
                System.out.print("You are going right");
                stmt.executeUpdate("update book set number = " + k1 + "  where bookId = " + i1 + ";");
            }
            con.close();
            return 1;

        } catch (ClassNotFoundException ex) {
            System.out.print("Database connection success");
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    int retunBook(int i) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "12345");
            System.out.print("Database connection success\n");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from borrow where borrowId = " + i + ";");
            int i1 = 0;
            while (rs.next()) {

                i1 = Integer.parseInt(rs.getString("bookId"));
            }
            if (i1 != 0) {
                stmt.executeUpdate("delete from borrow where borrowId = " + i + ";");
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

    @Override
    void displayBookDetails() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "12345");
            System.out.print("Database connection success\n");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from borrow;");
            FileWriter fw;
            try {
                fw = new FileWriter("D:\\Java\\library\\Write.txt");
                fw.write("Details of Borrow \r\n\r\n");
                while (rs.next()) {
                    borrowNumber = Integer.parseInt(rs.getString("borrowId"));
                    bookId = Integer.parseInt(rs.getString("bookId"));
                    studentId = Integer.parseInt(rs.getString("studentId"));
                    
                    fw.write("Borrow Number : " + borrowNumber + "\r\n");
                    fw.write("Book Id       : " + bookId + "\r\n");
                    fw.write("Student Id    : " + studentId + "\r\n\r\n");

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
