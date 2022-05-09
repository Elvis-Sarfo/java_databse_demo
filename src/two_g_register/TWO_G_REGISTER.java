/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package two_g_register;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author kanta
 */
public class TWO_G_REGISTER extends Application {
    Connection con;
    @Override
    public void start(Stage primaryStage){
        // Database connection       
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/F_Resgister", "root", "root");
        } catch (SQLException ex) {
            Logger.getLogger(TWO_G_REGISTER.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Controls
        Label lblName = new Label("Name");
        Label lblIndex = new Label("Index No.");
        Label lblPhone = new Label("Phone No.");

        
        TextField txtName = new TextField();
        TextField txtIndex = new TextField();        
        TextField txtPhone = new TextField();

        
        Button btnSave = new Button("Save");
        Button btnClear =new Button ("Clear");
       
        btnSave.setOnAction(event -> {
            String name = txtName.getText();
            String index = txtIndex.getText();
            String phone = txtPhone.getText();
            try {
                Statement st =con.createStatement();
                st.execute("INSERT INTO student VALUES ('"+name+"','"+index+"','"+phone+"')");
            } catch (SQLException ex) {
                Logger.getLogger(TWO_G_REGISTER.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btnClear.setOnAction(event -> {
            txtName.clear();
            txtIndex.clear();
            txtPhone.clear();
        });
        
        //Pane
        GridPane root = new GridPane();
        //add controls to pane
        root.add(lblName, 0, 0);
        root.add(txtName, 1, 0);
        root.add(lblIndex, 0, 1);
        root.add(txtIndex, 1, 1);
        root.add(lblPhone, 0, 2);
        root.add(txtPhone, 1, 2);
        root.add(btnSave, 0, 3);
        root.add(btnClear, 1, 3);
        
        root.setVgap(20);
        root.setPadding(new Insets(10, 10, 10, 10));
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
