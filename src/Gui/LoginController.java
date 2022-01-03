/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.mysql.cj.conf.ConnectionUrlParser;
import entities.Dotation;
import entities.Responsable;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import services.DotationCRUD;
import services.ResponsableCRUD;

/**
 * FXML Controller class
 *
 * @author mohamed ben samir
 */
public class LoginController implements Initializable {

    @FXML
    private TextField login;
    @FXML
    private TextField mdp;
    Boolean showDialog;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) throws SQLException, IOException {
        ResponsableCRUD RC = new ResponsableCRUD();
        Responsable r = new Responsable();
        
        r = RC.Login(login.getText(), mdp.getText());
        if(r.getNom()==null){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Merci de verifier votre login et mot de passe");
        alert.setHeaderText("Login ou mot de passe incorrecte! ");
        alert.setContentText("Veuillez contacter l'admin si vous avez oublier votre login ou mot de passe.");

        ButtonType buttonTypeOne = new ButtonType("ok");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
          
           
        } else {
        }
        }else{
            if (mdp.getText().equalsIgnoreCase("password")){
            showDialog = mdp.getText().equalsIgnoreCase("password");
                                         Label message = new Label("");

            int i=0;
                   while (showDialog ) {
                     Dialog<ConnectionUrlParser.Pair<String, String>> dialog = new Dialog<>();
                    dialog.setTitle("Veuillez choisir un nouveau mot de passe");

                    // Set the button types.
                    ButtonType loginButtonType = new ButtonType("Confirmer", ButtonBar.ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().addAll(loginButtonType);

                            GridPane gridPane = new GridPane();
                            gridPane.setHgap(10);
                            gridPane.setVgap(10);
                            gridPane.setPadding(new Insets(20, 150, 10, 10));

                            TextField nMdp = new TextField();
                            nMdp.setPromptText("Nouveau mot de passe");
                            TextField nCMdp = new TextField();
                            nCMdp.setPromptText("Confirmer votre mot de passe");
                                
                            message.setTextFill(Color.color(1, 0, 0));
                            gridPane.add(new Label("Nouveau mot de passe:"), 0, 0);
                            gridPane.add(nMdp, 1, 0);
                            gridPane.add(new Label("Confirmer:"), 0, 1);
                            gridPane.add(nCMdp, 1, 1);
                            gridPane.add(message, 0, 2);

                    dialog.getDialogPane().setContent(gridPane);
                        
                    // Request focus on the username field by default.
                    Platform.runLater(() -> nMdp.requestFocus());

                    // Convert the result to a username-password-pair when the login button is clicked.
                    dialog.setResultConverter(dialogButton -> {
                        if (dialogButton == loginButtonType) {
                            return new ConnectionUrlParser.Pair<>(nMdp.getText(), nCMdp.getText());
                        }
                        return null;
                    });

                    Optional<ConnectionUrlParser.Pair<String, String>> result = dialog.showAndWait();

                    result.ifPresent(pair -> {
                        if ( nMdp.getText().equals(nCMdp.getText())) {
                            if(nMdp.getText().length() >= 8){
                                showDialog = false;
                                Responsable resp = new Responsable();
                                resp.setLogin(login.getText());
                                resp.setPassword(nMdp.getText());
                                RC.modifierMdp(resp);
                            }else{
                                showDialog = true;
                                message.setText("Le mot de passe doit contenir au moins 8 caractères");
                            }
                        }else{
                        showDialog = true;
                         message.setText("Les 2 mots de passe sont différents !");
                        }
                        
                       
                    });
                    i++;
                }
                   
            }
            Preferences userRole = Preferences.userRoot();
            Preferences userNom = Preferences.userRoot();
            userRole.put("role", r.getRole());
            userNom.put("nom", r.getNom());

            System.out.println(r.getId());
            System.out.println(r.getNom());
            System.out.println(r.getRole());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            Parent root = loader.load();
            int width = (int) Screen.getPrimary().getBounds().getWidth();
            int height = (int) Screen.getPrimary().getBounds().getHeight();
            
            Scene scene = new Scene(root,width,height);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setMaximized(true);
            stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            stage.setScene(scene);
            stage.show();
        }
    }
    
}
