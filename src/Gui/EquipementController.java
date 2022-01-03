/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import entities.Dotation;
import entities.Equipement;
import entities.Personnel;
import entities.Stock;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import services.DotationCRUD;
import services.EquipementCRUD;
import services.PersonnelCRUD;
import services.StockCRUD;

/**
 * FXML Controller class
 *
 * @author mohamed ben samir
 */
public class EquipementController implements Initializable {

    @FXML
    private TableView<Equipement> TableEqu;
    @FXML
    private TableColumn<Equipement, String> pn;
    @FXML
    private TableColumn<Equipement, String> sn;
    @FXML
    private TableColumn<Equipement, String> sit;
    @FXML
    private TableColumn<Equipement, String> Soc;
    @FXML
    private TableColumn<Equipement, String> mag;
    @FXML
    private TableColumn<Equipement, String> dotat;
    @FXML
    private TableColumn<Equipement, String> lieu;
    @FXML
    private TableColumn<Equipement, String> dm;
    @FXML
    private TableColumn<Equipement, String> pp;
    @FXML
    private TableColumn<Equipement, String> des;
    ObservableList<Equipement> equipementListV = FXCollections.observableArrayList();
    @FXML
    private TextField search;
    @FXML
    private TextField equipementInputpn;
    @FXML
    private TextField equipementInputsn;
    @FXML
    private TextField equipementInputSituation;
    @FXML
    private TextField equipementInputMagasin;
    @FXML
    private TextField equipementInputSociete;
    @FXML
    private TextField equipementInputLieu;
    @FXML
    private TextField equipementInputprop;
    @FXML
    private Button btnSaveEquipement;
    @FXML
    private TextField search2;
    @FXML
    private TableView<Personnel> TablePersonnel;
    @FXML
    private TableColumn<Personnel, String> personnelMatr;
    @FXML
    private TableColumn<Personnel, String> personnelNom;
    @FXML
    private TableColumn<Personnel, String> personnelSoc;
    @FXML
    private TableColumn<Personnel, String> personnelMag;
    @FXML
    private Button btnSavePEC;
    ObservableList<Personnel> usersListV = FXCollections.observableArrayList();
    @FXML
    private DatePicker datePrise;
    int action =0; //0-> Ajouter; 1-> Modifier; 2-> Supprimer.
    @FXML
    private TextField equipementInputStock;
    @FXML
    private TableColumn<Equipement, String> stk;
    @FXML
    private Button goToResponsableBtn;
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
            LocalDate now = LocalDate.now();  
            System.out.println(now);  
            datePrise.setValue(now);
            
        try {
            EquipementCRUD Ec = new EquipementCRUD ();
            List<Equipement> listEquipement = Ec.Affichertout_equipements();

            if (!listEquipement.isEmpty()) {
                for (int i = 0; i < listEquipement.size(); i++) {
                    equipementListV.add(listEquipement.get(i));
                }
            }
           
            pn.setCellValueFactory(new PropertyValueFactory<>("pn"));
            sn.setCellValueFactory(new PropertyValueFactory<>("sn"));
            sit.setCellValueFactory(new PropertyValueFactory<>("situation"));
            Soc.setCellValueFactory(new PropertyValueFactory<>("societe"));
            mag.setCellValueFactory(new PropertyValueFactory<>("magasin"));
            dotat.setCellValueFactory(new PropertyValueFactory<>("dotat"));
            lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
            dm.setCellValueFactory(new PropertyValueFactory<>("derniermouvement"));
            pp.setCellValueFactory(new PropertyValueFactory<>("proprietaire"));
            stk.setCellValueFactory(new PropertyValueFactory<>("stock"));
            des.setCellValueFactory(new PropertyValueFactory<>("designation"));
           
            
            TableEqu.setItems(equipementListV);
            
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
            personnelSoc.setCellValueFactory(new PropertyValueFactory<>("societe"));
            personnelMag.setCellValueFactory(new PropertyValueFactory<>("magasin"));
          
            
            TablePersonnel.setItems(usersListV);
            
            
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
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
    private void goToStock(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("stock.fxml"));
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
        try {
            EquipementCRUD Ec = new EquipementCRUD ();
            List<Equipement> listEquipement = Ec.EquipementParSN(search.getText());
            equipementListV.clear();
            if (!listEquipement.isEmpty()) {
                for (int i = 0; i < listEquipement.size(); i++) {
                    equipementListV.add(listEquipement.get(i));
                    System.out.println(listEquipement.get(i));
                }
            }

            pn.setCellValueFactory(new PropertyValueFactory<>("pn"));
            sn.setCellValueFactory(new PropertyValueFactory<>("sn"));
            sit.setCellValueFactory(new PropertyValueFactory<>("situation"));
            Soc.setCellValueFactory(new PropertyValueFactory<>("societe"));
            mag.setCellValueFactory(new PropertyValueFactory<>("magasin"));
            dotat.setCellValueFactory(new PropertyValueFactory<>("dotat"));
            lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
            dm.setCellValueFactory(new PropertyValueFactory<>("derniermouvement"));
            pp.setCellValueFactory(new PropertyValueFactory<>("proprietaire"));
            stk.setCellValueFactory(new PropertyValueFactory<>("stock"));
            des.setCellValueFactory(new PropertyValueFactory<>("designation"));
           
            
            TableEqu.setItems(equipementListV);
            
            
            
          
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void saveEquipement(ActionEvent event) throws SQLException {
      
      EquipementCRUD Ec =new EquipementCRUD();
      Equipement E = new Equipement( equipementInputpn.getText(),
              equipementInputsn.getText(),
              equipementInputSituation.getText(),
              equipementInputSociete.getText(),
              equipementInputMagasin.getText(),
              equipementInputLieu.getText(),
              equipementInputprop.getText(),
              Integer.parseInt(equipementInputStock.getText()));
     
StockCRUD SC = new StockCRUD();
Boolean OK = SC.StockParPN1(equipementInputpn.getText());
if (!OK){ 
Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("PN n'existe pas");
        alert.setHeaderText("Il n'y a aucun stock avec ce P/N");
        alert.setContentText("merci de saisir unPN valide");

        ButtonType buttonTypeOne = new ButtonType("OK");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
           
           
        } else {
            // ... user chose CANCEL or closed the dialog
        }

}
else {
 Ec.ajouterEquipement(E);
}

      List<Equipement> listEquipement = Ec.Affichertout_equipements();
            equipementListV.clear();
            if (!listEquipement.isEmpty()) {
                for (int i = 0; i < listEquipement.size(); i++) {
                    equipementListV.add(listEquipement.get(i));
                }
            }
          
            pn.setCellValueFactory(new PropertyValueFactory<>("pn"));
            sn.setCellValueFactory(new PropertyValueFactory<>("sn"));
            sit.setCellValueFactory(new PropertyValueFactory<>("situation"));
            Soc.setCellValueFactory(new PropertyValueFactory<>("societe"));
            mag.setCellValueFactory(new PropertyValueFactory<>("magasin"));
            dotat.setCellValueFactory(new PropertyValueFactory<>("dotat"));
            lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
            dm.setCellValueFactory(new PropertyValueFactory<>("derniermouvement"));
            pp.setCellValueFactory(new PropertyValueFactory<>("proprietaire"));
            //pp.setCellValueFactory(new PropertyValueFactory<>("position"));
            des.setCellValueFactory(new PropertyValueFactory<>("designation"));
            stk.setCellValueFactory(new PropertyValueFactory<>("stock"));
           
            
            TableEqu.setItems(equipementListV);
    }

    @FXML
    private void searchPersonnel(KeyEvent event) throws SQLException {
            PersonnelCRUD pc = new PersonnelCRUD ();
            List<Personnel> listUsers = pc.Search(search2.getText());
            usersListV.clear();
            if (!listUsers.isEmpty()) {
                for (int i = 0; i < listUsers.size(); i++) {
                    usersListV.add(listUsers.get(i));
                    System.out.println(listUsers.get(i));
                }
            }
            
            personnelMatr.setCellValueFactory(new PropertyValueFactory<>("matricule"));
            personnelNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            personnelSoc.setCellValueFactory(new PropertyValueFactory<>("societe"));
            personnelMag.setCellValueFactory(new PropertyValueFactory<>("magasin"));
           
            
            TablePersonnel.setItems(usersListV);
    }

    @FXML
    private void savePEC(ActionEvent event) throws SQLException, IOException {
        
        Equipement equi = TableEqu.getSelectionModel().getSelectedItem();
        Personnel perso = TablePersonnel.getSelectionModel().getSelectedItem();
        System.out.println(equi.getPn()); 
        System.out.println(perso.getNom()); 
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Merci de confirmer");
        alert.setHeaderText("Nom du personnel: "+perso.getNom()
                +"\nMatricule du personnel: "+perso.getMatricule()
                +"\nS/N de l'quipement: "+equi.getSn()+"\nDate de prise : "+datePrise.getValue().toString());
        alert.setContentText("Confirmer:");

        ButtonType buttonTypeOne = new ButtonType("Confirmer");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
           Dotation dot= new Dotation(null, equi.getSn(), perso.getMatricule(), datePrise.getValue().toString(), null);
           DotationCRUD Dc = new DotationCRUD();
           Dc.ajouterDotation(dot);
           
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }

    @FXML
    private void onTableClicked(MouseEvent event) {
       
    }

    private void editPersonnel(ActionEvent event) {
      Equipement equi = TableEqu.getSelectionModel().getSelectedItem();
      equipementInputpn.setText(equi.getPn());
      equipementInputsn.setText(equi.getSn());
      equipementInputSituation.setText(equi.getSituation());
      equipementInputMagasin.setText(equi.getMagasin());
      equipementInputSociete.setText(equi.getSociete());
      equipementInputLieu.setText(equi.getLieu());
      equipementInputprop.setText(equi.getProprietaire());
      equipementInputStock.setText(String.valueOf(equi.getStock()) );
    }

    @FXML
    private void editEquipement(ActionEvent event) {
         Equipement equi = TableEqu.getSelectionModel().getSelectedItem();
      equipementInputpn.setText(equi.getPn());
      equipementInputsn.setText(equi.getSn());
      equipementInputSituation.setText(equi.getSituation());
      equipementInputMagasin.setText(equi.getMagasin());
      equipementInputSociete.setText(equi.getSociete());
      equipementInputLieu.setText(equi.getLieu());
      equipementInputprop.setText(equi.getProprietaire());
      equipementInputStock.setText(String.valueOf(equi.getStock()));
      
    }

    @FXML
    private void deleteEquipement(ActionEvent event) throws SQLException {
        EquipementCRUD Ec = new EquipementCRUD();
        Equipement equi = TableEqu.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Merci de confirmer La supression");
        alert.setHeaderText("confirmer la suppression de l'quipement avec S/N : "+equi.getSn());
        alert.setContentText("Confirmer:");

        ButtonType buttonTypeOne = new ButtonType("Confirmer");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            Ec.deleteEquipement(equi);
        } else {
            // ... user chose CANCEL or closed the dialog
        }
         List<Equipement> listEquipement = Ec.Affichertout_equipements();
            equipementListV.clear();
            if (!listEquipement.isEmpty()) {
                for (int i = 0; i < listEquipement.size(); i++) {
                    equipementListV.add(listEquipement.get(i));
                }
            }
          
            pn.setCellValueFactory(new PropertyValueFactory<>("pn"));
            sn.setCellValueFactory(new PropertyValueFactory<>("sn"));
            sit.setCellValueFactory(new PropertyValueFactory<>("situation"));
            Soc.setCellValueFactory(new PropertyValueFactory<>("societe"));
            mag.setCellValueFactory(new PropertyValueFactory<>("magasin"));
            dotat.setCellValueFactory(new PropertyValueFactory<>("dotat"));
            lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
            dm.setCellValueFactory(new PropertyValueFactory<>("derniermouvement"));
            pp.setCellValueFactory(new PropertyValueFactory<>("proprietaire"));
            //pp.setCellValueFactory(new PropertyValueFactory<>("position"));
            des.setCellValueFactory(new PropertyValueFactory<>("designation"));
           
            
            TableEqu.setItems(equipementListV);

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
    
}
