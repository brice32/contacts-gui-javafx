package contacts.javafx.view.systeme;

import contacts.commun.util.ExceptionAppli;
import contacts.commun.util.Roles;
import contacts.javafx.fxb.FXCompte;
import contacts.javafx.model.IModelConnexion;
import contacts.javafx.view.EnumView;
import contacts.javafx.view.IController;
import contacts.javafx.view.IManagerGui;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class ControllerMenuAnnonce implements IController {

	private IModelConnexion	modelConnexion;

	private IManagerGui		managerGui;
	// Composants de la vue

	@FXML
	private Label		labelTitre;
	@FXML
	private Label		labelMessage;
	@FXML
	private Label       labelNom;
	@FXML
	private Label       labelPrenom;
    @FXML
    private Label       labelAdministrateur;
    @FXML
    private Label       labelModerateur;
    @FXML
    private Label       labelSecretaire;
    @FXML
    private Button      buttonListeAnnonce;
    @FXML
    private Button      buttonListeAnnonceur;
    @FXML
	private Button      buttonRetour;
    @FXML
    private Button      buttonAnnonceListe;

	@Override
	public void setManagerGui(IManagerGui managerGui) throws ExceptionAppli {
		// TODO Auto-generated method stub
		this.managerGui = managerGui;

		labelTitre.textProperty().bind( new SimpleStringProperty("Menu De Anonnce") );
		labelMessage.textProperty().bind( new SimpleStringProperty("Choisissez Une Selection") );

		buttonListeAnnonce.setDisable(true);
		buttonListeAnnonceur.setDisable(true);

		modelConnexion = managerGui.getModel( IModelConnexion.class );
		FXCompte compteConnecte = modelConnexion.getCompteConnecte();
		labelNom.textProperty().bind( new SimpleStringProperty(compteConnecte.getNom()));
		labelPrenom.textProperty().bind( new SimpleStringProperty(compteConnecte.getPrenom()));

		if(compteConnecte.isInRole(Roles.ADMINISTRATEUR)){
			labelAdministrateur.setTextFill(Color.BLACK);
		}
		if(compteConnecte.isInRole("Modérateur")){
			labelModerateur.setTextFill(Color.BLACK);
		}
		if(compteConnecte.isInRole("Secrétaire")){
			labelSecretaire.setTextFill(Color.BLACK);
			buttonListeAnnonce.setDisable(false);
			buttonListeAnnonceur.setDisable(false);
		}
	}

	@FXML
	private void doListAnnonceur(){
		managerGui.showView(EnumView.AnnonceurListe);
	}

	@FXML
	private void doRetour(){
		managerGui.showView(EnumView.Info);
	}

	@FXML
	private void doAnnonceListe(){
		managerGui.showView(EnumView.AnnonceListe);
	}
}
