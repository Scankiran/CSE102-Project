package SignUpManager;

import Project_Classes.Load_Pages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import Db_Connection.Db_Connection;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.*;

public class Controller {
    @FXML
    private TextField email;

    @FXML
    private TextField nameSurname;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label signUpStatus;

    @FXML
    private Label codeForMember;


    public void initialize() throws Exception {
    }


    public void createUsers(ActionEvent event) throws Exception {
        try{
            String mail = email.getText();
            String name = nameSurname.getText();
            String pass = passwordField.getText();

            double random = (Math.random() * 10000);
            int rand = (int) random;
            String pin = Integer.toString(rand);
            codeForMember.setText(pin);

            //String serial = codeForMember.getText();


            //preparedStatement.setString(4,serial);

            String s = ("INSERT INTO users (Username,Password,Name,Surname,PhoneNumber,TCNumber,SerialNumber,ApartmentNumber,IsAdmin) Values"+
                    " (?,?,?,'Admin','5555555555','11111111111','abc123','14',1)");

            Db_Connection.connectiondb();
            PreparedStatement pr = Db_Connection.getConnection().prepareStatement(s);
            pr.setString(1, mail);
            pr.setString(2, name);
            pr.setString(3, pass);
            Db_Connection.ExecuteSql(s);
            Db_Connection.CloseConnection();


            signUpStatus.setTextFill(Color.GREEN);
            signUpStatus.setText("Sign Up Succesful");


            Thread.sleep(2000);
            Load_Pages load = new Load_Pages();
            load.loadStart();

            //Yeni sayfa açıldığında eski sayfanın kalmaması için
            ((Node)(event.getSource())).getScene().getWindow().hide();
            //Todo: execute problemi halledilecek..
        }
        catch (Exception e){
            signUpStatus.setTextFill(Color.RED);
            signUpStatus.setText("Sign Up Unsuccesful");
        }






    }

}
