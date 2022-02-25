/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connexion3a27;

import edu.esprit.entities.Personne;
import edu.esprit.services.PersonneService;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjouterPersonneController implements Initializable {
    private PreparedStatement preparedStatement;
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_prenom;
    @FXML
    private Button btn;
    @FXML
    private Button btn2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        addPerson(event);
    }    
    
    
    @FXML
    private void addPerson(ActionEvent event) {
        String nom = txt_nom.getText();
        String prenom = txt_prenom.getText();
        Personne p = new Personne(44, nom, prenom);
        PersonneService ps= new PersonneService();
        ps.ajouterPersonne(p);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherPersonne.fxml"));
            Parent root = loader.load();
            AfficherPersonneController ac =loader.getController();
//            ac.setList(ps.afficherPersonne().toString());
//            ac.loadDate();
            
            txt_nom.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
            
    }
    

    @FXML
    private void switchToAfficherListePersonne(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherPersonne.fxml"));
            Parent root = loader.load();
            AfficherPersonneController ac =loader.getController();
//            ac.setList(ps.afficherPersonne().toString());
//            ac.loadDate();
            
            txt_nom.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }




    
    
}
