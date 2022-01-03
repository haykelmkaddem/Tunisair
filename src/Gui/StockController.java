/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import entities.Personnel;
import entities.Stock;
import entities.Stock;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import services.StockCRUD;

/**
 * FXML Controller class
 *
 * @author mohamed ben samir
 */
public class StockController implements Initializable {

    @FXML
    private TableView<Stock> TableStock;
    @FXML
    private TableColumn<Stock, String> pn;
    @FXML
    private TableColumn<Stock, String> soc;
    @FXML
    private TableColumn<Stock, String> mag;
    @FXML
    private TableColumn<Stock, String> position;
    @FXML
    private TableColumn<Stock, String> des;
    @FXML
    private TableColumn<Stock, Integer> type;
    @FXML
    private TableColumn<Stock, Integer> stk;
    ObservableList<Stock> stockListV = FXCollections.observableArrayList();
    @FXML
    private TextField search;
    @FXML
    private Button goToResponsableBtn;
    @FXML
    private TextField inputPn;
    @FXML
    private TextField inputsoc;
    @FXML
    private TextField inputmag;
    @FXML
    private TextField inputpos;
    @FXML
    private TextField inputdes;
    @FXML
    private TextField inputtype;
    @FXML
    private TextField inputstk;
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
            StockCRUD pc = new StockCRUD ();
            List<Stock> listStock = pc.Affichertout_stocks();

            if (!listStock.isEmpty()) {
                for (int i = 0; i < listStock.size(); i++) {
                    stockListV.add(listStock.get(i));
                    System.out.println(listStock.get(i));
                }
            }
            
            pn.setCellValueFactory(new PropertyValueFactory<>("pn"));
            soc.setCellValueFactory(new PropertyValueFactory<>("soc"));
            mag.setCellValueFactory(new PropertyValueFactory<>("mag"));
            position.setCellValueFactory(new PropertyValueFactory<>("position"));
            des.setCellValueFactory(new PropertyValueFactory<>("designation"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            stk.setCellValueFactory(new PropertyValueFactory<>("stk"));
           
            
            TableStock.setItems(stockListV);
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
    private void qq(KeyEvent event) {
        Stock s= new Stock();
       
        try {
            StockCRUD pc = new StockCRUD ();
            List<Stock> listStock = pc.StockParPN(search.getText());
            stockListV.clear();
            if (!listStock.isEmpty()) {
                for (int i = 0; i < listStock.size(); i++) {
                    stockListV.add(listStock.get(i));
                    System.out.println(listStock.get(i));
                }
            }
            
            pn.setCellValueFactory(new PropertyValueFactory<>("pn"));
            soc.setCellValueFactory(new PropertyValueFactory<>("soc"));
            mag.setCellValueFactory(new PropertyValueFactory<>("mag"));
            position.setCellValueFactory(new PropertyValueFactory<>("position"));
            des.setCellValueFactory(new PropertyValueFactory<>("designation"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            stk.setCellValueFactory(new PropertyValueFactory<>("stk"));
           
            
            TableStock.setItems(stockListV);
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
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setMaximized(true);
            stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void goToDotat(ActionEvent event) throws IOException {
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

    @FXML
    private void ajouterStock(ActionEvent event) throws SQLException {
        Stock s = new Stock(null, inputPn.getText(), inputsoc.getText(), inputmag.getText(), inputpos.getText(), inputdes.getText(), 1, Integer.parseInt(inputstk.getText()));
        StockCRUD Sr = new StockCRUD();
        Sr.ajouterStock(s);
        
            List<Stock> listStock = Sr.Affichertout_stocks();

            if (!listStock.isEmpty()) {
                stockListV.clear();
                for (int i = 0; i < listStock.size(); i++) {
                    stockListV.add(listStock.get(i));
                    System.out.println(listStock.get(i));
                }
            }
            
            pn.setCellValueFactory(new PropertyValueFactory<>("pn"));
            soc.setCellValueFactory(new PropertyValueFactory<>("soc"));
            mag.setCellValueFactory(new PropertyValueFactory<>("mag"));
            position.setCellValueFactory(new PropertyValueFactory<>("position"));
            des.setCellValueFactory(new PropertyValueFactory<>("designation"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            stk.setCellValueFactory(new PropertyValueFactory<>("stk"));
           
            
            TableStock.setItems(stockListV);
    }
    
    
  
}
