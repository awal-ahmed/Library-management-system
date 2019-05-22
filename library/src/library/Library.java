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
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class Library extends Application {

    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException, SQLException {

        Button btn1 = new Button();
        btn1.setText("Admin login");
        btn1.setTextFill(Color.BLUE);

        btn1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                TextField textfield1 = new TextField();
                TextField textfield2 = new TextField();
                Label lbl1 = new Label();
                lbl1.setText("Admin ID:");
                lbl1.setTextFill(Color.BLUE);
                Label lbl2 = new Label();
                lbl2.setText("Password:");
                lbl2.setTextFill(Color.BLUE);
                Button btn4 = new Button();
                GridPane root2 = new GridPane();
                root2.add(lbl1, 0, 0);
                root2.add(textfield1, 2, 0);
                root2.add(lbl2, 0, 1);
                root2.add(textfield2, 2, 1);
                root2.add(btn4, 2, 2);

                Scene scene = new Scene(root2, 300, 250);

                Stage primaryStage = new Stage();
                primaryStage.setTitle("Admin login Window");
                primaryStage.setScene(scene);
                primaryStage.show();
                btn4.setText("login");
                btn4.setTextFill(Color.BLUE);
                btn4.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        int username = Integer.parseInt(textfield1.getText());
                        int password = Integer.parseInt(textfield2.getText());
                        int i = 0;
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "12345");
                            Statement stmt = con.createStatement();
                            ResultSet rs = stmt.executeQuery("SELECT * FROM admin");
                            while (rs.next()) {
                                int in = rs.getInt("adminId");
                                int in1 = rs.getInt("adminPassword");
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

                            Button butt1 = new Button();
                            Button butt2 = new Button();
                            Button butt3 = new Button();
                            Button butt4 = new Button();

                            butt1.setText("Add Librarian");
                            butt2.setText("View Librarian Details");
                            butt3.setText("Remove Librarian");
                            butt4.setText("LogOut");

                            VBox root3 = new VBox();
                            root3.setSpacing(20);
                            root3.setAlignment(Pos.CENTER);
                            root3.getChildren().add(butt1);
                            root3.getChildren().add(butt2);
                            root3.getChildren().add(butt3);
                            root3.getChildren().add(butt4);

                            Scene scene = new Scene(root3, 400, 350);

                            primaryStage.setTitle("Option for Admin");
                            primaryStage.setScene(scene);
                            primaryStage.show();

                            librarian1 li = new librarian1();
                            butt1.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent event) {
                                    admin ad = new admin();
                                    ad.addLibrarian(li);
                                }
                            });
                            butt2.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent event) {
                                        librarian1 lb= new librarian1();
                                        lb.viewLibrarian();
                                }
                            });
                            butt3.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent event) {

                                    Button btn1 = new Button();
                                    btn1.setText("Remove");
                                    btn1.setTextFill(Color.BLUE);
                                    Label lbl1 = new Label();
                                    lbl1.setText("Librarian Id:");
                                    lbl1.setTextFill(Color.BLUE);
                                    TextField textfield1 = new TextField();

                                    GridPane root2 = new GridPane();
                                    root2.add(lbl1, 0, 0);
                                    root2.add(textfield1, 1, 0);
                                    root2.add(btn1, 1, 1);
                                    root2.setAlignment(Pos.CENTER);
                                    Scene sce = new Scene(root2, 400, 300);
                                    Stage primaryStage = new Stage();
                                    primaryStage.setTitle("Removing LIbrarian");
                                    primaryStage.setScene(sce);
                                    primaryStage.show();
                                    btn1.setOnAction(new EventHandler<ActionEvent>() {

                                        @Override
                                        public void handle(ActionEvent event) {
                                            int i = Integer.parseInt(textfield1.getText());
                                            admin ad = new admin();
                                            ad.deletLibrarian(i);
                                            primaryStage.close();
                                        }
                                    });

                                }

                            });
                            butt4.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent event) {

                                    primaryStage.close();
                                }
                            });

                        } else {
                            System.out.print("Wrong userId or Password\n");
                        }

                    }
                });

            }
        });
        Button btn2 = new Button();
        btn2.setText("Librarian login");
        btn2.setTextFill(Color.BLUE);
        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                TextField textfield1 = new TextField();
                TextField textfield2 = new TextField();
                Label lbl1 = new Label();
                lbl1.setText("Librarian ID:");
                lbl1.setTextFill(Color.BLUE);
                Label lbl2 = new Label();
                lbl2.setText("Librarian Password:");
                lbl2.setTextFill(Color.BLUE);
                Button btn4 = new Button();
                GridPane root2 = new GridPane();
                root2.add(lbl1, 0, 0);
                root2.add(textfield1, 2, 0);
                root2.add(lbl2, 0, 1);
                root2.add(textfield2, 2, 1);
                root2.add(btn4, 2, 2);

                Scene scene = new Scene(root2, 300, 250);

                Stage primaryStage = new Stage();
                primaryStage.setTitle("Libraian login Window");
                primaryStage.setScene(scene);
                primaryStage.show();
                btn4.setText("login");
                btn4.setTextFill(Color.BLUE);
                btn4.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        int username = Integer.parseInt(textfield1.getText());
                        int password = Integer.parseInt(textfield2.getText());
                        int i = 0;
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "12345");
                            Statement stmt = con.createStatement();
                            ResultSet rs = stmt.executeQuery("SELECT * FROM libririan");
                            while (rs.next()) {
                                int in = rs.getInt("librarianId");
                                int in1 = rs.getInt("librarianPassword");
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

                            Button butt1 = new Button();
                            Button butt2 = new Button();
                            Button butt3 = new Button();
                            Button butt4 = new Button();
                            Button butt5 = new Button();
                            Button butt6 = new Button();

                            butt1.setText("Add Book");
                            butt2.setText("View Books Details");
                            butt3.setText("Issue Book");
                            butt4.setText("View Issued Books");
                            butt5.setText("Return Books");
                            butt6.setText("LogOut");

                            VBox root3 = new VBox();
                            root3.setSpacing(20);
                            root3.setAlignment(Pos.CENTER);
                            root3.getChildren().add(butt1);
                            root3.getChildren().add(butt2);
                            root3.getChildren().add(butt3);
                            root3.getChildren().add(butt4);
                            root3.getChildren().add(butt5);
                            root3.getChildren().add(butt6);

                            Scene scene = new Scene(root3, 400, 350);

                            primaryStage.setTitle("Options of Librarian");
                            primaryStage.setScene(scene);
                            primaryStage.show();

                            books bo = new librarian1();
                            butt1.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent event) {
                                    librarian1 li = new librarian1();
                                    li.add(bo);
                                }
                            });
                            butt2.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent event) {
                                    bo.displayBookDetails();
                                }
                            });
                            butt3.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent event) {
                                    Label leb0 = new Label();
                                    leb0.setText("Borrow Id: ");
                                    TextField textField0 = new TextField();

                                    Label leb = new Label();
                                    leb.setText("Enter book id: ");
                                    TextField textField = new TextField();

                                    Label leb1 = new Label();
                                    leb1.setText("Enter Student id: ");
                                    TextField textField1 = new TextField();

                                    Button butt31 = new Button();
                                    butt31.setText("Done");
                                    GridPane root = new GridPane();
                                    root.setAlignment(Pos.CENTER);

                                    root.add(leb0, 0, 0);
                                    root.add(textField0, 1, 0);
                                    root.add(leb, 0, 1);
                                    root.add(textField, 1, 1);
                                    root.add(leb1, 0, 2);
                                    root.add(textField1, 1, 2);
                                    root.add(butt31, 0, 3);

                                    Scene sce = new Scene(root, 400, 300);
                                    Stage primaryStage = new Stage();
                                    primaryStage.setTitle("New Borrow Entry");
                                    primaryStage.setScene(sce);
                                    primaryStage.show();
                                    butt31.setOnAction(new EventHandler<ActionEvent>() {

                                        @Override
                                        public void handle(ActionEvent event) {
                                            int i = Integer.parseInt(textField0.getText());
                                            int i1 = Integer.parseInt(textField.getText());

                                            int i2 = Integer.parseInt(textField1.getText());
                                            int fl = bo.searchById(i1);
                                            if (i > 0) {
                                                borrow bro = new borrow();
                                                int k= bro.update(i, i1, i2);
                                                if(k==1)
                                                {
                                                    primaryStage.close();
                                                }
                                                
                                            } else {
                                                Label leb = new Label();
                                                leb.setText("This book is not available\n");
                                                GridPane root = new GridPane();
                                                root.setAlignment(Pos.CENTER);
                                                root.add(leb, 0, 0);
                                                Scene sce = new Scene(root, 400, 300);
                                                Stage primaryStage = new Stage();
                                                primaryStage.setTitle("Notice");
                                                primaryStage.setScene(sce);
                                                primaryStage.show();
                                            }
                                        }
                                    });

                                }

                            });
                            butt4.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent event) {
                                       borrow br= new borrow();
                                       br.displayBookDetails();
                                }
                            });
                            butt5.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent event) {

                                    Button btn1 = new Button();
                                    btn1.setText("Ok");
                                    btn1.setTextFill(Color.BLUE);
                                    Label lbl1 = new Label();
                                    lbl1.setText("Borrow Id:");
                                    lbl1.setTextFill(Color.BLUE);
                                    TextField textfield1 = new TextField();

                                    GridPane root2 = new GridPane();
                                    root2.add(lbl1, 0, 0);
                                    root2.add(textfield1, 1, 0);
                                    root2.add(btn1, 1, 1);
                                    root2.setAlignment(Pos.CENTER);
                                    Scene sce = new Scene(root2, 400, 300);
                                    Stage primaryStage = new Stage();
                                    primaryStage.setTitle("Notice");
                                    primaryStage.setScene(sce);
                                    primaryStage.show();
                                    btn1.setOnAction(new EventHandler<ActionEvent>() {

                                        @Override
                                        public void handle(ActionEvent event) {
                                            int i = Integer.parseInt(textfield1.getText());
                                            borrow bro = new borrow();
                                            int k =bro.retunBook(i);
                                            librarian1 bo = new librarian1();
                                            k = bo.returnBooks(k);
                                            if(k==1)
                                            {
                                                primaryStage.close();
                                            }
                                            
                                        }
                                    });

                                }
                            });
                            butt6.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent event) {
                                    primaryStage.close();
                                }
                            });

                        } else {
                            System.out.print("Wrong userId or Password\n");
                        }

                    }
                });

            }
        });
        Button btn3 = new Button();
        btn3.setText("User login");
        btn3.setTextFill(Color.BLUE);
        btn3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                TextField textfield1 = new TextField();
                TextField textfield2 = new TextField();
                Label lbl1 = new Label();
                lbl1.setText("User ID:");
                lbl1.setTextFill(Color.BLUE);
                Label lbl2 = new Label();
                lbl2.setText("Password:");
                lbl2.setTextFill(Color.BLUE);
                Button btn4 = new Button();
                GridPane root2 = new GridPane();
                root2.add(lbl1, 0, 0);
                root2.add(textfield1, 2, 0);
                root2.add(lbl2, 0, 1);
                root2.add(textfield2, 2, 1);
                root2.add(btn4, 2, 2);

                Scene scene = new Scene(root2, 300, 250);

                Stage primaryStage = new Stage();
                primaryStage.setTitle("User login Window");
                primaryStage.setScene(scene);
                primaryStage.show();
                btn4.setText("login");
                btn4.setTextFill(Color.BLUE);
                btn4.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        int username = Integer.parseInt(textfield1.getText());
                        int password = Integer.parseInt(textfield2.getText());
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

                            Button butt1 = new Button();
                            Button butt2 = new Button();
                            Button butt3 = new Button();
                            Button butt4 = new Button();

                            butt1.setText("View Books");
                            butt2.setText("Search Book");
                            butt3.setText("Borrow Book");
                            butt4.setText("LogOut");

                            VBox root3 = new VBox();
                            root3.setSpacing(20);
                            root3.setAlignment(Pos.CENTER);
                            root3.getChildren().add(butt1);
                            root3.getChildren().add(butt2);
                            root3.getChildren().add(butt3);
                            root3.getChildren().add(butt4);

                            Scene scene = new Scene(root3, 400, 350);

                            primaryStage.setTitle("Options for users");
                            primaryStage.setScene(scene);
                            primaryStage.show();

                            librarian1 li = new librarian1();
                            butt1.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent event) {
                                    user ur = new user();
                                    ur.displayBookDetails();
                                }
                            });
                            butt2.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent event) {
                                    Button butt1 = new Button();
                                    Button butt2 = new Button();

                                    butt1.setText("Search With Id");
                                    butt2.setText("Search With Book Name");

                                    VBox root3 = new VBox();
                                    root3.setSpacing(20);
                                    root3.setAlignment(Pos.CENTER);
                                    root3.getChildren().add(butt1);
                                    root3.getChildren().add(butt2);

                                    Scene scene = new Scene(root3, 400, 350);

                                    primaryStage.setTitle("Searching way");
                                    primaryStage.setScene(scene);
                                    primaryStage.show();
                                    butt1.setOnAction(new EventHandler<ActionEvent>() {

                                        @Override
                                        public void handle(ActionEvent event) {
                                            Label lbl1 = new Label();
                                            Button butt = new Button();
                                            butt.setText("Search");
                                            lbl1.setText("Book Id:");
                                            lbl1.setTextFill(Color.BLUE);
                                            TextField textfield1 = new TextField();

                                            GridPane root2 = new GridPane();
                                            root2.add(lbl1, 0, 0);
                                            root2.add(textfield1, 2, 0);
                                            root2.add(butt, 2, 2);

                                            Scene scene = new Scene(root2, 300, 250);

                                            Stage primaryStage = new Stage();
                                            primaryStage.setTitle("Search With Id");
                                            primaryStage.setScene(scene);
                                            primaryStage.show();
                                            butt.setOnAction(new EventHandler<ActionEvent>() {

                                                @Override
                                                public void handle(ActionEvent event) {
                                                    int i2 = Integer.parseInt(textfield1.getText());
                                                    user ur = new user();
                                                    int i = ur.searchById(i2);
                                                    if (i >= 1) {
                                                        Label lbl2 = new Label();
                                                        lbl2.setText("This book is Available");
                                                        GridPane root2 = new GridPane();
                                                        root2.add(lbl2, 0, 0);

                                                        Scene scene = new Scene(root2, 300, 250);
                                                        root2.setAlignment(Pos.CENTER);

                                                        btn4.setTextFill(Color.BLUE);
                                                        Stage primaryStage = new Stage();
                                                        primaryStage.setTitle("Searching Result");
                                                        primaryStage.setScene(scene);
                                                        primaryStage.show();
                                                    } else {
                                                        Label lbl2 = new Label();
                                                        lbl2.setText("This book is not Available");
                                                        GridPane root2 = new GridPane();
                                                        root2.add(lbl2, 0, 0);
                                                        root2.setAlignment(Pos.CENTER);

                                                        btn4.setTextFill(Color.RED);
                                                        Scene scene = new Scene(root2, 300, 250);

                                                        Stage primaryStage = new Stage();
                                                        primaryStage.setTitle("Searching Result");
                                                        primaryStage.setScene(scene);
                                                        primaryStage.show();
                                                    }
                                                }
                                            });
                                        }
                                    });
                                    butt2.setOnAction(new EventHandler<ActionEvent>() {

                                        @Override
                                        public void handle(ActionEvent event) {
                                            Label lbl1 = new Label();
                                            Button butt = new Button();
                                            
                                            butt.setText("Search");
                                            lbl1.setText("Book Name:");
                                            lbl1.setTextFill(Color.BLUE);
                                            TextField textfield1 = new TextField();

                                            GridPane root2 = new GridPane();
                                            root2.add(lbl1, 0, 0);
                                            root2.add(textfield1, 2, 0);
                                            root2.add(butt, 0, 2);

                                            Scene scene = new Scene(root2, 300, 250);

                                            Stage primaryStage = new Stage();
                                            primaryStage.setTitle("Search With Book name");
                                            primaryStage.setScene(scene);
                                            primaryStage.show();
                                            butt.setOnAction(new EventHandler<ActionEvent>() {

                                                @Override
                                                public void handle(ActionEvent event) {
                                                    String st = textfield1.getText();
                                                    user ur = new user();
                                                    ur.searchBook(st);
                                                }
                                            });
                                        }
                                    });

                                }
                            });
                            butt3.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent event) {

                                    borrow br = new borrow();
                                    br.update();
                                }

                            });
                            butt4.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent event) {

                                    primaryStage.close();
                                }
                            });

                        } else {
                            System.out.print("Wrong userId or Password\n");
                        }

                    }
                });

            }
        });

        VBox root = new VBox();
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(btn1);
        root.getChildren().add(btn2);
        root.getChildren().add(btn3);

        Scene scene = new Scene(root, 400, 350);

        primaryStage.setTitle("Account login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Library pro = new Library();
        pro.createConnection();
        launch(args);

    }

    void createConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "12345");
            System.out.print("Database connection success\n");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM libririan");
            while (rs.next()) {
                String st = rs.getString("librarianName");
                System.out.print("\n" + st);
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
