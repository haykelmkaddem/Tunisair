/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import entities.Dotation;
import entities.Dotation;
import entities.Personnel;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import services.DotationCRUD;
import services.DotationCRUD;

/**
 * FXML Controller class
 *
 * @author mohamed ben samir
 */
public class DotationController implements Initializable {

    @FXML
    private TextField search;
    @FXML
    private TableView<Dotation> TableEqu;
    @FXML
    private TableColumn<Dotation, String> code;
    @FXML
    private TableColumn<Dotation, String> sn;
    @FXML
    private TableColumn<Dotation, String> ds;
    @FXML
    private TableColumn<Dotation, String> dr;
    @FXML
    private TableColumn<Dotation, String> matricule;
    @FXML
    private TableColumn<Dotation, String> livree;
    @FXML
    private TextField search211;
    @FXML
    private Button btnSaveDotation;
    private DatePicker datePrise;
    @FXML
    private Button btnSavePEC;
        ObservableList<Dotation> dotationListV = FXCollections.observableArrayList();
        ObservableList<Dotation> dotationListR = FXCollections.observableArrayList();
    @FXML
    private Text snText;
    @FXML
    private Text matrText;
    @FXML
    private Text MagText;
    @FXML
    private Text SocText;
    @FXML
    private TextField search1;
    @FXML
    private TableView<Dotation> TableRetour;
    @FXML
    private TableColumn<Dotation, String> tableRetourCode;
    @FXML
    private TableColumn<Dotation, String> TableRetourMatr;
    @FXML
    private TableColumn<Dotation, String> TableRetourSn;
    @FXML
    private Button goToResponsableBtn;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LocalDate now = LocalDate.now();  
            System.out.println(now);  
            
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

            DotationCRUD Ec = new DotationCRUD ();
            List<Dotation> listDotation;
        try {
            listDotation = Ec.Affichertout_Dotations();
            if (!listDotation.isEmpty()) {
                for (int i = 0; i < listDotation.size(); i++) {
                    dotationListV.add(listDotation.get(i));
                }
            }
           
            code.setCellValueFactory(new PropertyValueFactory<>("codedotation"));
            sn.setCellValueFactory(new PropertyValueFactory<>("sn"));
            ds.setCellValueFactory(new PropertyValueFactory<>("datesortie"));
            dr.setCellValueFactory(new PropertyValueFactory<>("dateretour"));
            matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
            livree.setCellValueFactory(new PropertyValueFactory<>("livree"));
            
            
           
            
            TableEqu.setItems(dotationListV);
            
            listDotation = Ec.Affichertout_DotationsRetour();
            dotationListR.clear();
            if (!listDotation.isEmpty()) {
                for (int i = 0; i < listDotation.size(); i++) {
                    dotationListR.add(listDotation.get(i));
                }
            }
           
            tableRetourCode.setCellValueFactory(new PropertyValueFactory<>("codedotation"));
            TableRetourSn.setCellValueFactory(new PropertyValueFactory<>("sn"));
            TableRetourMatr.setCellValueFactory(new PropertyValueFactory<>("matricule"));
           
           
            
            TableRetour.setItems(dotationListR);
        } catch (SQLException ex) {
            Logger.getLogger(DotationController.class.getName()).log(Level.SEVERE, null, ex);
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
          
            DotationCRUD Ec = new DotationCRUD ();
            dotationListV.clear();
            List<Dotation> listDotation;
        try {
            listDotation =Ec.DotationParCode(search.getText());
            if (!listDotation.isEmpty()) {
                for (int i = 0; i < listDotation.size(); i++) {
                    dotationListV.add(listDotation.get(i));
                }
            }
           
            code.setCellValueFactory(new PropertyValueFactory<>("codedotation"));
            sn.setCellValueFactory(new PropertyValueFactory<>("sn"));
            ds.setCellValueFactory(new PropertyValueFactory<>("datesortie"));
            dr.setCellValueFactory(new PropertyValueFactory<>("dateretour"));
            matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
            livree.setCellValueFactory(new PropertyValueFactory<>("livree"));
            
           
            
            TableEqu.setItems(dotationListV);
        } catch (SQLException ex) {
            Logger.getLogger(DotationController.class.getName()).log(Level.SEVERE, null, ex);
        }
                      
    }

    @FXML
    private void searchPersonnel(KeyEvent event) {
    }


    @FXML
    private void onTableClicked(MouseEvent event) {
    }


    @FXML
    private void goToEqui(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("equipement.fxml"));
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
    private void showLivree(ActionEvent event) {
        DotationCRUD Ec = new DotationCRUD ();
            List<Dotation> listDotation;
            dotationListV.clear();
        try {
            listDotation = Ec.Affichertout_DotationsLivree();
            if (!listDotation.isEmpty()) {
                for (int i = 0; i < listDotation.size(); i++) {
                    dotationListV.add(listDotation.get(i));
                }
            }
           
            code.setCellValueFactory(new PropertyValueFactory<>("codedotation"));
            sn.setCellValueFactory(new PropertyValueFactory<>("sn"));
            ds.setCellValueFactory(new PropertyValueFactory<>("datesortie"));
            dr.setCellValueFactory(new PropertyValueFactory<>("dateretour"));
            matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
            livree.setCellValueFactory(new PropertyValueFactory<>("livree"));
            
           
            
            TableEqu.setItems(dotationListV);
        } catch (SQLException ex) {
            Logger.getLogger(DotationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showRetour(ActionEvent event) {
        DotationCRUD Ec = new DotationCRUD ();
            List<Dotation> listDotation;
        try {
            dotationListV.clear();
            listDotation = Ec.Affichertout_DotationsRetour();
            if (!listDotation.isEmpty()) {
                for (int i = 0; i < listDotation.size(); i++) {
                    dotationListV.add(listDotation.get(i));
                }
            }
           
            code.setCellValueFactory(new PropertyValueFactory<>("codedotation"));
            sn.setCellValueFactory(new PropertyValueFactory<>("sn"));
            ds.setCellValueFactory(new PropertyValueFactory<>("datesortie"));
            dr.setCellValueFactory(new PropertyValueFactory<>("dateretour"));
            matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
            livree.setCellValueFactory(new PropertyValueFactory<>("livree"));
            
           
            
            TableEqu.setItems(dotationListV);
        } catch (SQLException ex) {
            Logger.getLogger(DotationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showAll(ActionEvent event) {
        DotationCRUD Ec = new DotationCRUD ();
            List<Dotation> listDotation;
        try {
            dotationListV.clear();
            listDotation = Ec.Affichertout_Dotations();
            if (!listDotation.isEmpty()) {
                for (int i = 0; i < listDotation.size(); i++) {
                    dotationListV.add(listDotation.get(i));
                }
            }
           
            code.setCellValueFactory(new PropertyValueFactory<>("codedotation"));
            sn.setCellValueFactory(new PropertyValueFactory<>("sn"));
            ds.setCellValueFactory(new PropertyValueFactory<>("datesortie"));
            dr.setCellValueFactory(new PropertyValueFactory<>("dateretour"));
            matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
            livree.setCellValueFactory(new PropertyValueFactory<>("livree"));
            
           
            
            TableEqu.setItems(dotationListV);
        } catch (SQLException ex) {
            Logger.getLogger(DotationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void trouverParCode(ActionEvent event) {
        DotationCRUD Ec = new DotationCRUD ();
            List<Dotation> listDotation;
        try {
            listDotation = Ec.Afficher_DotationsparCode(search211.getText());
            if (!listDotation.isEmpty()) {
                for (int i = 0; i < listDotation.size(); i++) {
                    
                    snText.setText(listDotation.get(i).getSn());
                    matrText.setText(listDotation.get(i).getMatricule());
                    if (listDotation.get(i).getLivree().equalsIgnoreCase("oui")) {
                        btnSaveDotation.setDisable(true);
                        btnSaveDotation.setText("Déja Livrée!");

                    }else{
                        btnSaveDotation.setDisable(false);
                        btnSaveDotation.setText("Accepter");

                       
                    }
                }
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(DotationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Traiter(ActionEvent event) {
                Dotation dot = TableEqu.getSelectionModel().getSelectedItem();
                search211.setText(dot.getCodedotation());
        
                    snText.setText(dot.getSn());
                    matrText.setText(dot.getMatricule());
                if (dot.getLivree().equalsIgnoreCase("oui")) {
                        btnSaveDotation.setDisable(true);
                        btnSaveDotation.setText("Déja Livrée!");

                    }else{
                        btnSaveDotation.setDisable(false);
                        btnSaveDotation.setText("Accepter");

                    }
    }

    @FXML
    private void saveDotation(ActionEvent event) throws SQLException {
        
        DotationCRUD dc= new DotationCRUD();
        dc.modifierDotation(search211.getText());
        DotationCRUD Ec = new DotationCRUD ();
        btnSaveDotation.setDisable(true);
        btnSaveDotation.setText("Dotion confirmé !");
        
            List<Dotation> listDotation;
        
            dotationListV.clear();
            listDotation = Ec.Affichertout_Dotations();
            if (!listDotation.isEmpty()) {
                for (int i = 0; i < listDotation.size(); i++) {
                    dotationListV.add(listDotation.get(i));
                }
            }
           
            code.setCellValueFactory(new PropertyValueFactory<>("codedotation"));
            sn.setCellValueFactory(new PropertyValueFactory<>("sn"));
            ds.setCellValueFactory(new PropertyValueFactory<>("datesortie"));
            dr.setCellValueFactory(new PropertyValueFactory<>("dateretour"));
            matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
            livree.setCellValueFactory(new PropertyValueFactory<>("livree"));
            
           
            
            TableEqu.setItems(dotationListV);
            listDotation = Ec.Affichertout_DotationsRetour();
            dotationListR.clear();
            if (!listDotation.isEmpty()) {
                for (int i = 0; i < listDotation.size(); i++) {
                    dotationListR.add(listDotation.get(i));
                }
            }
           
            tableRetourCode.setCellValueFactory(new PropertyValueFactory<>("codedotation"));
            TableRetourSn.setCellValueFactory(new PropertyValueFactory<>("sn"));
            TableRetourMatr.setCellValueFactory(new PropertyValueFactory<>("matricule"));
           
           
            
            TableRetour.setItems(dotationListR);
    }

    @FXML
    private void confirmerRetour(ActionEvent event) throws SQLException {
        Dotation dot = TableRetour.getSelectionModel().getSelectedItem();
        List<Dotation> listDotation;

        DotationCRUD dc= new DotationCRUD();
        dc.modifierDotationRetour(dot.getCodedotation());
        listDotation = dc.Affichertout_DotationsRetour();
            dotationListR.clear();
            if (!listDotation.isEmpty()) {
                for (int i = 0; i < listDotation.size(); i++) {
                    dotationListR.add(listDotation.get(i));
                }
            }
           
            tableRetourCode.setCellValueFactory(new PropertyValueFactory<>("codedotation"));
            TableRetourSn.setCellValueFactory(new PropertyValueFactory<>("sn"));
            TableRetourMatr.setCellValueFactory(new PropertyValueFactory<>("matricule"));
           
           
            
            TableRetour.setItems(dotationListR);
    }

    @FXML
    private void qq1(KeyEvent event) {
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
