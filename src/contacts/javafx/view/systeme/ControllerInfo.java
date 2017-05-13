package contacts.javafx.view.systeme;

import contacts.commun.util.ExceptionAppli;
import contacts.commun.util.Roles;
import contacts.javafx.fxb.FXCompte;
import contacts.javafx.model.IModelConnexion;
import contacts.javafx.model.IModelInfo;
import contacts.javafx.model.IModelMouvement;
import contacts.javafx.view.EnumView;
import contacts.javafx.view.IController;
import contacts.javafx.view.IManagerGui;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class ControllerInfo implements IController {

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
    private Button		buttonGestionUtilisateur;
    @FXML
    private Button      buttonGestionAnnonce;

    private IModelMouvement modelMouvement;
	// Injecteurs

	@Override
	public void setManagerGui( IManagerGui managerGui ) throws ExceptionAppli {

		//initialiser etat disable des buttons
		buttonGestionUtilisateur.setDisable(true);

		// Injection  de dÃ©pendances
		IModelInfo modelInfo = managerGui.getModel( IModelInfo.class );
		modelMouvement = managerGui.getModel( IModelMouvement.class );
		this.managerGui = managerGui;
		// Data binding
		labelTitre.textProperty().bind( modelInfo.titreProperty() );
		labelMessage.textProperty().bind( modelInfo.messageProperty() );
		modelConnexion = managerGui.getModel( IModelConnexion.class );
		FXCompte compteConnecte = modelConnexion.getCompteConnecte();
		labelNom.textProperty().bind( new SimpleStringProperty(compteConnecte.getNom()));
		labelPrenom.textProperty().bind( new SimpleStringProperty(compteConnecte.getPrenom()));

		if(compteConnecte.isInRole(Roles.ADMINISTRATEUR)){
			labelAdministrateur.setTextFill(Color.BLACK);
			buttonGestionUtilisateur.setDisable(false);
		}
		if(compteConnecte.isInRole("Modérateur")){
			labelModerateur.setTextFill(Color.BLACK);
		}
		if(compteConnecte.isInRole("Secrétaire")){
			labelSecretaire.setTextFill(Color.BLACK);
		}
	}

	@FXML
	private void doGestionUtilisateur() {
		managerGui.showView(EnumView.CompteListe);
	}

	@FXML
	private void doGestionAnnonce(){
		managerGui.showView(EnumView.MenuAnnonce);
	}

	@FXML
	private void doGestionConfiguration(){
		managerGui.showView(EnumView.MenuConfiguration);
	}

	@FXML
	private void doMouvement(){
		modelMouvement.preparerAjouter();
		managerGui.showView(EnumView.Mouvement);
	}
}
