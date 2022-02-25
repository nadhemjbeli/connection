/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connexion3a27;

import edu.esprit.entities.Personne;
import edu.esprit.tools.MaConnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AfficherPersonneController implements Initializable {

    private TextField list;
    @FXML
    private TableColumn<Personne, Integer> txt_id;
    @FXML
    private TableColumn<Personne, String> txt_nom;
    @FXML
    private TableColumn<Personne, String> txt_prenom;
    @FXML
    private TableView<Personne> table_personne;
    
    private ObservableList<Personne>personneList = FXCollections.observableArrayList();
    private String querry = null;
    private Connection mc;
    private Stage stage;
    private Scene scene;
    private PreparedStatement preparedStatement;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_refresh;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_edit;
    @FXML
    private Button btn_search;
    
    
    
    public AfficherPersonneController() {
        mc=MaConnexion.getInstance().getCnx();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
        
    }    

    public void setList(String list) {
        this.list.setText(list);
    }
    
    private void loadDate() {
        MaConnexion m = MaConnexion.getInstance();
        refreshTable();
        txt_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        txt_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        txt_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        
        table_personne.setItems(personneList);
    }
    
    @FXML
    public void refreshTable(){
        personneList.clear();
        
        querry = "SELECT * FROM personne ORDER BY id";
        
        try {
            preparedStatement=mc.prepareStatement(querry);
            ResultSet resultSet = preparedStatement.executeQuery();
            Personne personne;
            
            while(resultSet.next()){
                personne = new Personne(resultSet.getInt("id"),
                                        resultSet.getString("nom"),
                                        resultSet.getString("prenom"));
                personneList.add(personne);
                table_personne.setItems(personneList);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @FXML
    private void switchToAddPerson(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterPersonne.fxml"));
            Parent root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
