package contacts.javafx.view.annonceur;

import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXAnnonceur;
import contacts.javafx.fxb.FXCompte;
import contacts.javafx.model.IModelAnnonceur;
import contacts.javafx.model.IModelCompte;
import contacts.javafx.view.EnumView;
import contacts.javafx.view.IController;
import contacts.javafx.view.IManagerGui;
import contacts.javafx.view.util.StringBindingId;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControllerAnnonceurForm implements IController {

	// Composants de la vue
	@FXML
	private TextField textFieldIdAnnonceur;

	@FXML
	private TextField textFieldNom;

	@FXML
	private TextField textFieldTelephone;

	@FXML
	private TextField textFieldEmail;

	@FXML
	private TextField textFieldLieuNom;

	@FXML
	private TextField textFieldLieuAdresse;

	@FXML
	private TextField textFieldLieuCp;

	@FXML
	private TextField textFieldLieuVille;

	@FXML
	private TextField textFieldSiteWeb;

	@FXML
	private Button buttonRetour;

//	@FXML
//	private Button    buttonAnnuler;
//
//	@FXML
//	private Button    buttonOk;

	// Autres champs

	private IManagerGui managerGui;
	private IModelAnnonceur modelAnnonceur;
	private FXAnnonceur annonceurVue;

	@FXML
	private void doAnnuler() {
		managerGui.showView(EnumView.AnnonceurListe);
	}

	@FXML
	private void doValider()  {
		try {
			modelAnnonceur.ValiderMiseAJour();
			managerGui.showView( EnumView.AnnonceurListe );;
		} catch (Exception e) {
			managerGui.afficherErreur(e);
		}
	}

	@FXML
	private void doRetour(){
		managerGui.showView(EnumView.AnnonceurListe);
	}

	@Override
	public void setManagerGui(IManagerGui managerGui) throws ExceptionAppli {
		// Injection  de dépendances
				this.managerGui = managerGui;
				modelAnnonceur = managerGui.getModel( IModelAnnonceur.class );
				annonceurVue = modelAnnonceur.getAnnonceurVue();

				textFieldIdAnnonceur.textProperty().bind(new StringBindingId(annonceurVue.idProperty()));
				textFieldNom.textProperty().bindBidirectional(annonceurVue.nomProperty());
				textFieldTelephone.textProperty().bindBidirectional(annonceurVue.telephoneProperty());
				textFieldEmail.textProperty().bindBidirectional(annonceurVue.emailProperty());
				textFieldLieuNom.textProperty().bindBidirectional(annonceurVue.lieuNomProperty());
				textFieldLieuAdresse.textProperty().bindBidirectional(annonceurVue.lieuAdresseProperty());
				textFieldLieuCp.textProperty().bindBidirectional(annonceurVue.lieuCpProperty());
				textFieldLieuVille.textProperty().bindBidirectional(annonceurVue.lieuVilleProperty());
				textFieldSiteWeb.textProperty().bindBidirectional(annonceurVue.siteWebProperty());
	}

}
