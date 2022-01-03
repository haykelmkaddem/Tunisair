/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import entities.Personnel;
import entities.Responsable;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import services.PersonnelCRUD;
import services.ResponsableCRUD;

/**
 * FXML Controller class
 *
 * @author mohamed ben samir
 */
public class DashboardController implements Initializable {

    @FXML
    private TableView<Personnel> TablePersonnel;
    @FXML
    private TableColumn<Personnel, String> personnelMatr;
    @FXML
    private TableColumn<Personnel, String> personnelNom;
    @FXML
    private TableColumn<Personnel, String> personnelStatut;
    @FXML
    private TableColumn<Personnel, String> personnelSoc;
    @FXML
    private TableColumn<Personnel, String> personnelMag;
    @FXML
    private TableColumn<Personnel, String> personnelServ;
    @FXML
    private TableColumn<Personnel, String> personnelApp;
    @FXML
    private TableColumn<Personnel, String> personnelCodeQ;
    @FXML
    private TableColumn<Personnel, String> personnelQual;
    @FXML
    private TableColumn<Personnel, String> personnelAss;
    ObservableList<Personnel> usersListV = FXCollections.observableArrayList();
    @FXML
    private Button btnSavePerso;
    @FXML
    private TextField persoMatr;
    @FXML
    private TextField persoNom;
    @FXML
    private TextField persoStat;
    @FXML
    private TextField persoMAg;
    @FXML
    private TextField persoSoc;
    @FXML
    private TextField persoServ;
    @FXML
    private TextField perso;
    @FXML
    private TextField persoCQ;
    @FXML
    private TextField persoQ;
    @FXML
    private Button goToResponsableBtn;
    @FXML
    private Button btnSavePerso1;
    @FXML
    private TextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
            PersonnelCRUD pc = new PersonnelCRUD ();
            List<Personnel> listUsers = pc.Affichertout_users();
            
            if (!listUsers.isEmpty()) {
                for (int i = 0; i < listUsers.size(); i++) {
                    usersListV.add(listUsers.get(i));
                    System.out.println(listUsers.get(i));
                }
            }
            
            personnelMatr.setCellValueFactory(new PropertyValueFactory<>("matricule"));
            personnelNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            personnelStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
            personnelSoc.setCellValueFactory(new PropertyValueFactory<>("societe"));
            personnelMag.setCellValueFactory(new PropertyValueFactory<>("magasin"));
            personnelServ.setCellValueFactory(new PropertyValueFactory<>("service"));
            personnelApp.setCellValueFactory(new PropertyValueFactory<>("applicabilite"));
            personnelCodeQ.setCellValueFactory(new PropertyValueFactory<>("CodeQualification"));
            personnelQual.setCellValueFactory(new PropertyValueFactory<>("Qualfication"));
            personnelAss.setCellValueFactory(new PropertyValueFactory<>("assurance"));
            
            TablePersonnel.setItems(usersListV);
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void savePerso(ActionEvent event) throws SQLException {
        PersonnelCRUD Pc =new PersonnelCRUD();
      Personnel P = new Personnel( persoMatr.getText(),
              persoNom.getText(),
              persoStat.getText(),
              persoMAg.getText(),
              persoServ.getText(),
              persoSoc.getText(),
              perso.getText(),
              persoCQ.getText(),
              persoQ.getText()
              );
      Pc.ajouterMembre(P);
      try {
            List<Personnel> listUsers = Pc.Affichertout_users();
            
            if (!listUsers.isEmpty()) {
                usersListV.clear();
                for (int i = 0; i < listUsers.size(); i++) {
                    usersListV.add(listUsers.get(i));
                    System.out.println(listUsers.get(i));
                }
            }
            
            personnelMatr.setCellValueFactory(new PropertyValueFactory<>("matricule"));
            personnelNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            personnelStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
            personnelSoc.setCellValueFactory(new PropertyValueFactory<>("societe"));
            personnelMag.setCellValueFactory(new PropertyValueFactory<>("magasin"));
            personnelServ.setCellValueFactory(new PropertyValueFactory<>("service"));
            personnelApp.setCellValueFactory(new PropertyValueFactory<>("applicabilite"));
            personnelCodeQ.setCellValueFactory(new PropertyValueFactory<>("CodeQualification"));
            personnelQual.setCellValueFactory(new PropertyValueFactory<>("Qualfication"));
            personnelAss.setCellValueFactory(new PropertyValueFactory<>("assurance"));
            
            TablePersonnel.setItems(usersListV);
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void qq(KeyEvent event) {
        Personnel p= new Personnel();
       
        try {
            PersonnelCRUD pc = new PersonnelCRUD ();
            List<Personnel> listPersonnel = pc.PersonnelParMatr(search.getText());
            usersListV.clear();
            if (!listPersonnel.isEmpty()) {
                for (int i = 0; i < listPersonnel.size(); i++) {
                    usersListV.add(listPersonnel.get(i));
                    System.out.println(listPersonnel.get(i));
                }
            }
            
            personnelMatr.setCellValueFactory(new PropertyValueFactory<>("matricule"));
            personnelNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            personnelStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
            personnelSoc.setCellValueFactory(new PropertyValueFactory<>("societe"));
            personnelMag.setCellValueFactory(new PropertyValueFactory<>("magasin"));
            personnelServ.setCellValueFactory(new PropertyValueFactory<>("service"));
            personnelApp.setCellValueFactory(new PropertyValueFactory<>("applicabilite"));
            personnelCodeQ.setCellValueFactory(new PropertyValueFactory<>("CodeQualification"));
            personnelQual.setCellValueFactory(new PropertyValueFactory<>("Qualfication"));
            personnelAss.setCellValueFactory(new PropertyValueFactory<>("assurance"));
            
            TablePersonnel.setItems(usersListV);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void goToHome(MouseEvent event) throws IOException {
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
    private void remplirForm(ActionEvent event) {
               Personnel p = TablePersonnel.getSelectionModel().getSelectedItem();
               persoMatr.setText(p.getMatricule());
               persoNom.setText(p.getNom());
               persoStat.setText(p.getStatut());
               persoCQ.setText(p.getCodeQualification());
               persoMAg.setText(p.getMagasin());
               persoQ.setText(p.getQualfication());
               persoSoc.setText(p.getSociete());
               persoServ.setText(p.getService());
               

    }

    @FXML
    private void deletePersonnel(ActionEvent event) {
        try {
            PersonnelCRUD pc = new PersonnelCRUD ();
            
            Personnel p = TablePersonnel.getSelectionModel().getSelectedItem();
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Merci de confirmer La supression");
        alert.setHeaderText("confirmer la suppression de: "+p.getNom());
        alert.setContentText("Confirmer:");

        ButtonType buttonTypeOne = new ButtonType("Confirmer");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            pc.deletePersonnel(p);
            List<Personnel> listPersonnel = pc.Affichertout_users();
            usersListV.clear();
            if (!listPersonnel.isEmpty()) {
                for (int i = 0; i < listPersonnel.size(); i++) {
                    usersListV.add(listPersonnel.get(i));
                    System.out.println(listPersonnel.get(i));
                }
            }
            personnelMatr.setCellValueFactory(new PropertyValueFactory<>("matricule"));
            personnelNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            personnelStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
            personnelSoc.setCellValueFactory(new PropertyValueFactory<>("societe"));
            personnelMag.setCellValueFactory(new PropertyValueFactory<>("magasin"));
            personnelServ.setCellValueFactory(new PropertyValueFactory<>("service"));
            personnelApp.setCellValueFactory(new PropertyValueFactory<>("applicabilite"));
            personnelCodeQ.setCellValueFactory(new PropertyValueFactory<>("CodeQualification"));
            personnelQual.setCellValueFactory(new PropertyValueFactory<>("Qualfication"));
            personnelAss.setCellValueFactory(new PropertyValueFactory<>("assurance"));
            
            TablePersonnel.setItems(usersListV);
            
           
        } else {
            // ... user chose CANCEL or closed the dialog
        }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void modifierPerso(ActionEvent event) throws SQLException {
            Personnel p = TablePersonnel.getSelectionModel().getSelectedItem();

                Personnel newp = new Personnel(persoMatr.getText(), persoNom.getText(), persoStat.getText(), persoMAg.getText(), persoServ.getText(), persoSoc.getText(), null, persoCQ.getText(), persoQ.getText());
               
               PersonnelCRUD pc = new PersonnelCRUD ();
               pc.modifierMembre(newp);
List<Personnel> listPersonnel = pc.Affichertout_users();
            usersListV.clear();
            if (!listPersonnel.isEmpty()) {
                for (int i = 0; i < listPersonnel.size(); i++) {
                    usersListV.add(listPersonnel.get(i));
                    System.out.println(listPersonnel.get(i));
                }
            }
            personnelMatr.setCellValueFactory(new PropertyValueFactory<>("matricule"));
            personnelNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            personnelStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
            personnelSoc.setCellValueFactory(new PropertyValueFactory<>("societe"));
            personnelMag.setCellValueFactory(new PropertyValueFactory<>("magasin"));
            personnelServ.setCellValueFactory(new PropertyValueFactory<>("service"));
            personnelApp.setCellValueFactory(new PropertyValueFactory<>("applicabilite"));
            personnelCodeQ.setCellValueFactory(new PropertyValueFactory<>("CodeQualification"));
            personnelQual.setCellValueFactory(new PropertyValueFactory<>("Qualfication"));
            personnelAss.setCellValueFactory(new PropertyValueFactory<>("assurance"));
            
            TablePersonnel.setItems(usersListV);
    }
    
}
