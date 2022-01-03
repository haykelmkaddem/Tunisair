/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;
import entities.Responsable;
import entities.Stock;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import services.ResponsableCRUD;
import services.StockCRUD;

/**
 * FXML Controller class
 *
 * @author mohamed ben samir
 */
public class ResponsableController implements Initializable {

    @FXML
    private TableColumn<Responsable, Integer> personnelMatr;
    @FXML
    private TableColumn<Responsable, String> personnelNom;
    @FXML
    private TableColumn<Responsable, String> personnelStatut;
    @FXML
    private TextField nom;
    @FXML
    private TextField login;
    private RadioButton role;
    @FXML
    private Button btnSavePerso;
    @FXML
    private Button goToResponsableBtn;
    @FXML
    private TableView<Responsable> TableResponsable;
    @FXML
    private TableView<Responsable> TableAdmins;
    @FXML
    private TableColumn<Responsable, String> personnelMatr1;
    @FXML
    private TableColumn<Responsable, String> personnelNom1;
    @FXML
    private TableColumn<Responsable, String> personnelStatut1;
    @FXML
    private Button btnEditResp;
    ObservableList<Responsable> respListV = FXCollections.observableArrayList();
    ObservableList<Responsable> adminsListV = FXCollections.observableArrayList();
    @FXML
    private RadioButton role1;
    @FXML
    private RadioButton role2;
    @FXML
    private TextField id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup radioGroup = new ToggleGroup();

        role1.setToggleGroup(radioGroup);
        role2.setToggleGroup(radioGroup);
        
        Preferences userPreferences = Preferences.userRoot();
        String role = userPreferences.get("role", "role undefined");
        String nom = userPreferences.get("nom", "nom undefined");
        System.out.println("role"+role+"nom"+nom);
        if(role.equalsIgnoreCase("admin")){
        goToResponsableBtn.setVisible(true);
            System.out.println("role****-***>"+role);
        }else{
                    goToResponsableBtn.setVisible(false);
                    System.out.println("xrole****-***>"+role);

        }
        
        
         try {
            ResponsableCRUD pc = new ResponsableCRUD ();
            List<Responsable> listResponsable = pc.Affichertout_responsables();
            List<Responsable> listAdmins = pc.Affichertout_admins();

            if (!listResponsable.isEmpty()) {
                for (int i = 0; i < listResponsable.size(); i++) {
                    respListV.add(listResponsable.get(i));
                    System.out.println(listResponsable.get(i));
                }
            }
            personnelMatr.setCellValueFactory(new PropertyValueFactory<>("id"));
            personnelNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            personnelStatut.setCellValueFactory(new PropertyValueFactory<>("login"));
            
            TableResponsable.setItems(respListV);
            
            
            if (!listAdmins.isEmpty()) {
                for (int i = 0; i < listAdmins.size(); i++) {
                    adminsListV.add(listAdmins.get(i));
                    System.out.println(listAdmins.get(i));
                }
            }
            personnelMatr1.setCellValueFactory(new PropertyValueFactory<>("id"));
            personnelNom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
            personnelStatut1.setCellValueFactory(new PropertyValueFactory<>("login"));
            
            TableAdmins.setItems(adminsListV);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }    
 @FXML
    private void goToStock(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("stock.fxml"));
            Parent root = loader.load();
            int width = (int) Screen.getPrimary().getBounds().getWidth();
            int height = (int) Screen.getPrimary().getBounds().getHeight();
            
            Scene scene = new Scene(root,width,height);
           // Scene scene = new Scene(root);
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setMaximized(true);
            stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void goToEqui(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("equipement.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setMaximized(true);
            stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void goToDota(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dotation.fxml"));
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


    @FXML
    private void qq(KeyEvent event) {
    }

    @FXML
    private void goToResponsable(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Responsable.fxml"));
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

    @FXML
    private void goToHome(MouseEvent event) {
    }

    @FXML
    private void goToProfile(MouseEvent event) {
       
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();
            
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
           
            stage.setScene(scene);
            stage.show();
    }

     @FXML
    private void goToPersonnel(ActionEvent event) throws IOException {
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

    @FXML
    private void savePerso(ActionEvent event) throws SQLException {
        
         String role = "not set";
               String nNom = nom.getText();
               String nLogin =login.getText();
               if (role1.isSelected()) {
                   role = "responsable";
               }
               if (role2.isSelected()){
                   role = "admin";
               }
                Responsable newResp = new Responsable(0, nNom, nLogin, nNom, role);
               System.out.println("role edit-> "+role);
               
               ResponsableCRUD rc = new ResponsableCRUD ();
               
               if (rc.exist(nLogin)) {
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Login existe déja");
                    alert.setHeaderText("Un responsable avec le login: "+nLogin);
                    alert.setContentText("Merci de modifier le login:");

                    ButtonType buttonTypeOne = new ButtonType("Confirmer");
                    ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                    alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

                    Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){}else{}
               }else{
               rc.ajouterResponsable(newResp);
               }
            List<Responsable> listResponsable = rc.Affichertout_responsables();
            List<Responsable> listAdmins = rc.Affichertout_admins();
            respListV.clear();
            adminsListV.clear();
            if (!listResponsable.isEmpty()) {
                for (int i = 0; i < listResponsable.size(); i++) {
                    respListV.add(listResponsable.get(i));
                    System.out.println(listResponsable.get(i));
                }
            }
            personnelMatr.setCellValueFactory(new PropertyValueFactory<>("id"));
            personnelNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            personnelStatut.setCellValueFactory(new PropertyValueFactory<>("login"));
            
            TableResponsable.setItems(respListV);
            
            
            if (!listAdmins.isEmpty()) {
                for (int i = 0; i < listAdmins.size(); i++) {
                    adminsListV.add(listAdmins.get(i));
                    System.out.println(listAdmins.get(i));
                }
            }
            personnelMatr1.setCellValueFactory(new PropertyValueFactory<>("id"));
            personnelNom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
            personnelStatut1.setCellValueFactory(new PropertyValueFactory<>("login"));
            
            TableAdmins.setItems(adminsListV);
    }

    @FXML
    private void RemplirFormEdit(ActionEvent event) {
       Responsable resp = TableResponsable.getSelectionModel().getSelectedItem();
               nom.setText(resp.getNom());
               login.setText(resp.getLogin());
               role2.setSelected(false);
               role1.setSelected(true);
               id.setText(String.valueOf(resp.getId()));

    }

    @FXML
    private void deleteResp(ActionEvent event) {
        try {
            ResponsableCRUD pc = new ResponsableCRUD ();
            
            Responsable resp = TableResponsable.getSelectionModel().getSelectedItem();
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Merci de confirmer La supression");
        alert.setHeaderText("confirmer la suppression de: "+resp.getNom());
        alert.setContentText("Confirmer:");

        ButtonType buttonTypeOne = new ButtonType("Confirmer");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            pc.delete(resp);
            List<Responsable> listResponsable = pc.Affichertout_responsables();
            List<Responsable> listAdmins = pc.Affichertout_admins();
            respListV.clear();
            adminsListV.clear();
            if (!listResponsable.isEmpty()) {
                for (int i = 0; i < listResponsable.size(); i++) {
                    respListV.add(listResponsable.get(i));
                    System.out.println(listResponsable.get(i));
                }
            }
            personnelMatr.setCellValueFactory(new PropertyValueFactory<>("id"));
            personnelNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            personnelStatut.setCellValueFactory(new PropertyValueFactory<>("login"));
            
            TableResponsable.setItems(respListV);
            
            
            if (!listAdmins.isEmpty()) {
                for (int i = 0; i < listAdmins.size(); i++) {
                    adminsListV.add(listAdmins.get(i));
                    System.out.println(listAdmins.get(i));
                }
            }
            personnelMatr1.setCellValueFactory(new PropertyValueFactory<>("id"));
            personnelNom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
            personnelStatut1.setCellValueFactory(new PropertyValueFactory<>("login"));
            
            TableAdmins.setItems(adminsListV);
        } else {
            // ... user chose CANCEL or closed the dialog
        }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void rendreAdmin(ActionEvent event) {
         try {
            ResponsableCRUD pc = new ResponsableCRUD ();
            
            Responsable resp = TableResponsable.getSelectionModel().getSelectedItem();
            pc.rendreAdmin(resp);
            List<Responsable> listResponsable = pc.Affichertout_responsables();
            List<Responsable> listAdmins = pc.Affichertout_admins();
            respListV.clear();
            adminsListV.clear();
            if (!listResponsable.isEmpty()) {
                for (int i = 0; i < listResponsable.size(); i++) {
                    respListV.add(listResponsable.get(i));
                    System.out.println(listResponsable.get(i));
                }
            }
            personnelMatr.setCellValueFactory(new PropertyValueFactory<>("id"));
            personnelNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            personnelStatut.setCellValueFactory(new PropertyValueFactory<>("login"));
            
            TableResponsable.setItems(respListV);
            
            
            if (!listAdmins.isEmpty()) {
                for (int i = 0; i < listAdmins.size(); i++) {
                    adminsListV.add(listAdmins.get(i));
                    System.out.println(listAdmins.get(i));
                }
            }
            personnelMatr1.setCellValueFactory(new PropertyValueFactory<>("id"));
            personnelNom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
            personnelStatut1.setCellValueFactory(new PropertyValueFactory<>("login"));
            
            TableAdmins.setItems(adminsListV);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void rendreResp(ActionEvent event) {
        
        try {
            ResponsableCRUD pc = new ResponsableCRUD ();
            
            Responsable admin = TableAdmins.getSelectionModel().getSelectedItem();
            
            pc.rendreResponsable(admin);
            List<Responsable> listResponsable = pc.Affichertout_responsables();
            List<Responsable> listAdmins = pc.Affichertout_admins();
            respListV.clear();
            adminsListV.clear();
            if (!listResponsable.isEmpty()) {
                for (int i = 0; i < listResponsable.size(); i++) {
                    respListV.add(listResponsable.get(i));
                    System.out.println(listResponsable.get(i));
                }
            }
            personnelMatr.setCellValueFactory(new PropertyValueFactory<>("id"));
            personnelNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            personnelStatut.setCellValueFactory(new PropertyValueFactory<>("login"));
            
            TableResponsable.setItems(respListV);
            
            
            if (!listAdmins.isEmpty()) {
                for (int i = 0; i < listAdmins.size(); i++) {
                    adminsListV.add(listAdmins.get(i));
                    System.out.println(listAdmins.get(i));
                }
            }
            personnelMatr1.setCellValueFactory(new PropertyValueFactory<>("id"));
            personnelNom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
            personnelStatut1.setCellValueFactory(new PropertyValueFactory<>("login"));
            
            TableAdmins.setItems(adminsListV);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void EditResp(ActionEvent event) throws SQLException {
         Responsable resp = TableAdmins.getSelectionModel().getSelectedItem();
         
         String role = "not set";
               String nNom = nom.getText();
               String nLogin =login.getText();
               int currId = Integer.parseInt(id.getText());  
               if (role1.isSelected()) {
                   role = "responsable";
               }
               if (role2.isSelected()){
                   role = "admin";
               }
                Responsable newResp = new Responsable(currId, nNom, nLogin, nNom, role);
               System.out.println("role edit-> "+role);
               
               ResponsableCRUD rc = new ResponsableCRUD ();
               rc.modifierResponsableAdmin(newResp);
               
            List<Responsable> listResponsable = rc.Affichertout_responsables();
            List<Responsable> listAdmins = rc.Affichertout_admins();
            respListV.clear();
            adminsListV.clear();
            if (!listResponsable.isEmpty()) {
                for (int i = 0; i < listResponsable.size(); i++) {
                    respListV.add(listResponsable.get(i));
                    System.out.println(listResponsable.get(i));
                }
            }
            personnelMatr.setCellValueFactory(new PropertyValueFactory<>("id"));
            personnelNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            personnelStatut.setCellValueFactory(new PropertyValueFactory<>("login"));
            
            TableResponsable.setItems(respListV);
            
            
            if (!listAdmins.isEmpty()) {
                for (int i = 0; i < listAdmins.size(); i++) {
                    adminsListV.add(listAdmins.get(i));
                    System.out.println(listAdmins.get(i));
                }
            }
            personnelMatr1.setCellValueFactory(new PropertyValueFactory<>("id"));
            personnelNom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
            personnelStatut1.setCellValueFactory(new PropertyValueFactory<>("login"));
            
            TableAdmins.setItems(adminsListV);
               
    }

    @FXML
    private void deleteAdmin(ActionEvent event) {
        
        try {
            
            ResponsableCRUD pc = new ResponsableCRUD ();
            
            Responsable resp = TableAdmins.getSelectionModel().getSelectedItem();
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Merci de confirmer La supression");
        alert.setHeaderText("confirmer la suppression de: "+resp.getNom());
        alert.setContentText("Confirmer:");

        ButtonType buttonTypeOne = new ButtonType("Confirmer");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            pc.delete(resp);
            List<Responsable> listResponsable = pc.Affichertout_responsables();
            List<Responsable> listAdmins = pc.Affichertout_admins();
            respListV.clear();
            adminsListV.clear();
            if (!listResponsable.isEmpty()) {
                for (int i = 0; i < listResponsable.size(); i++) {
                    respListV.add(listResponsable.get(i));
                    System.out.println(listResponsable.get(i));
                }
            }
            personnelMatr.setCellValueFactory(new PropertyValueFactory<>("id"));
            personnelNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            personnelStatut.setCellValueFactory(new PropertyValueFactory<>("login"));
            
            TableResponsable.setItems(respListV);
            
            
            if (!listAdmins.isEmpty()) {
                for (int i = 0; i < listAdmins.size(); i++) {
                    adminsListV.add(listAdmins.get(i));
                    System.out.println(listAdmins.get(i));
                }
            }
            personnelMatr1.setCellValueFactory(new PropertyValueFactory<>("id"));
            personnelNom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
            personnelStatut1.setCellValueFactory(new PropertyValueFactory<>("login"));
            
            TableAdmins.setItems(adminsListV);
        }else{
        
        }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void RemplirFormEditAdmin(ActionEvent event) {
               Responsable resp = TableAdmins.getSelectionModel().getSelectedItem();
               id.setText(String.valueOf(resp.getId()));
               nom.setText(resp.getNom());
               login.setText(resp.getLogin());
               role2.setSelected(true);
               role1.setSelected(false);
    }
    
}
