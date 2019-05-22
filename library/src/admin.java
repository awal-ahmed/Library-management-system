/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

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
public class admin extends librarian1 {
    int adminId;
    int adminPassword;
    
    void addLibrarian(librarian1 li) {
        Label leb11= new Label();
        Label leb12= new Label();
        Label leb13= new Label();
        Label leb14= new Label();
        leb11.setText("Librarian Id: ");
        leb12.setText("Name: ");
        leb13.setText("Set password: ");
        leb14.setText("Contact Number: ");
        TextField textfield11= new TextField();
        TextField textfield12= new TextField();
        TextField textfield13= new TextField();
        TextField textfield14= new TextField();
        
        Button bt = new Button();
        bt.setText("Done");
        
        GridPane root =new GridPane();
        root.setAlignment(Pos.CENTER);
        root.add(leb11,0,0);
        root.add(textfield11,2,0);
        root.add(leb12,0,1);
        root.add(textfield12,2,1);
        root.add(leb13,0,2);
        root.add(textfield13,2,2);
        root.add(leb14,0,3);
        root.add(textfield14,2,3);
        root.add(bt,2,4);
        
        Scene sce = new Scene(root,400,300);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("New  Librarian Recrude");
        primaryStage.setScene(sce);
        primaryStage.show();
        bt.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                li.librarianId= Integer.parseInt(textfield11.getText());
                li.librarianName=textfield12.getText();
                li.librarianPassword=Integer.parseInt(textfield13.getText());
                li.librarianContactNo=textfield14.getText();
                 try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "12345");
                    System.out.print("Database connection success\n");
                    Statement stmt = con.createStatement();
                    //System.out.print(bo.bookId);
                    stmt.executeUpdate("insert into libririan values (" + li.librarianId + ",'" + li.librarianName + "'," + li.librarianPassword + ",'" +li.librarianContactNo + "')");
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
    void viewLibrarian() {
    
    }
    void deletLibrarian(int i)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","12345");
            System.out.print("Database connection success\n");
            Statement stmt = con.createStatement();
            
            stmt.executeUpdate("delete from libririan where librarianId = "+i+";");
            
            con.close();
        } catch (ClassNotFoundException ex) {
            System.out.print("Database connection success");
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
