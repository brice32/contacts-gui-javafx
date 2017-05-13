package contacts.javafx.view.annonce;

import java.util.logging.Level;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

import contacts.commun.util.ExceptionAnomalie;
import contacts.commun.util.ExceptionAppli;
import contacts.javafx.view.IController;
import contacts.javafx.view.IManagerGui;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import contacts.javafx.fxb.FXAnnonce;
import contacts.javafx.fxb.FXAnnonceur;
import contacts.javafx.fxb.FXCategorie;
import contacts.javafx.fxb.FXRubrique;
import contacts.javafx.fxb.FXZone;
import contacts.javafx.model.IModelAnnonce;
import contacts.javafx.model.IModelAnnonceur;
import contacts.javafx.model.IModelCategorie;
import contacts.javafx.model.IModelRubrique;
import contacts.javafx.model.IModelZone;
import contacts.javafx.model.standard.ModelRubrique;
import contacts.javafx.view.DateTimePicker;
import contacts.javafx.view.EnumView;

public class ControllerAnnonceForm implements IController {

	private IManagerGui managerGui;

	private IModelAnnonce	modelAnnonce;

	private IModelAnnonceur modelAnnonceur;

	private IModelCategorie modelCategorie;

	private IModelRubrique  modelRubrique;

	private IModelZone 		modelZone;

	private FXAnnonce 	annonceVue;


	@FXML
	private ComboBox<FXAnnonceur> comboBoxAnnonceur;

	@FXML
	private ComboBox<FXCategorie> comboBoxCategorie;

	@FXML
	private ComboBox<FXRubrique>  comboBoxRubrique;

	@FXML
	private ComboBox<FXZone>	  comboxZone;

	@FXML
	private TextField			 textFieldTitre;

	@FXML
	private TextArea			 textAreaDescription;

	@FXML
	private JFXDatePicker dateDebutDatePicker;

	@FXML
	private JFXDatePicker dateFinDatePicker;

	@FXML
	private JFXTimePicker heureDebutPicker;

	@FXML
	private JFXTimePicker heureFinPicker;

	@FXML
	private TextField		lieuNomTextField;

	@FXML
	private TextField		lieuAdresseTextField;

	@FXML
	private TextField		lieuCpTextField;

	@FXML
	private TextField		lieuVilleTextField;

	@FXML
	private TextField		organisateurNomTextField;

	@FXML
	private TextField		organisateurSiteWebTextField;

	@FXML
	private TextField		organisateurEmailTextField;

	@FXML
	private TextField		organisateurTelephoneTextField;

	@Override
	public void setManagerGui(IManagerGui managerGui) throws ExceptionAppli {
		// TODO Auto-generated method stub
		this.managerGui=managerGui;
		modelAnnonce = managerGui.getModel(IModelAnnonce.class);
		modelAnnonceur = managerGui.getModel(IModelAnnonceur.class);
		modelAnnonceur.actualiserListe();
		modelRubrique = managerGui.getModel(IModelRubrique.class);
		modelRubrique.actualiserListe();
		modelCategorie = managerGui.getModel(IModelCategorie.class);
		modelCategorie.actualiserListe();
		modelZone	= 	managerGui.getModel(IModelZone.class);
		modelZone.actualiserListe();

		annonceVue = modelAnnonce.getAnnonceVue();


		textFieldTitre.textProperty().bind(annonceVue.titreProperty());
		textAreaDescription.textProperty().bind(annonceVue.descriptionProperty());
		dateDebutDatePicker.setValue(annonceVue.getDateDebutLD());
		dateFinDatePicker.setValue(annonceVue.getDateFinLD());
		heureDebutPicker.setValue(annonceVue.getHeureDebutLD());
		heureFinPicker.setValue(annonceVue.getHeureFinLD());
		lieuNomTextField.textProperty().bind(annonceVue.lieuNomProperty());
		lieuAdresseTextField.textProperty().bind(annonceVue.lieuAdresseProperty());
		lieuCpTextField.textProperty().bind(annonceVue.lieuCpProperty());
		lieuVilleTextField.textProperty().bind(annonceVue.lieuVilleProperty());
		organisateurNomTextField.textProperty().bind(annonceVue.organisateurNomProperty());
		organisateurSiteWebTextField.textProperty().bind(annonceVue.organisateurSiteWebProperty());
		organisateurEmailTextField.textProperty().bind(annonceVue.organisateurEmailProperty());
		organisateurTelephoneTextField.textProperty().bind(annonceVue.organisateurTelephoneProperty());

		comboBoxAnnonceur.setItems(modelAnnonceur.getAnnonceurs());
		comboBoxCategorie.setItems(modelCategorie.getCategories());
		comboBoxRubrique.setItems(modelRubrique.getRubriques());
		comboxZone.setItems(modelZone.getZones());

		comboBoxAnnonceur.setCellFactory((list) -> {
			return cellAnnonceur();
		});
		comboBoxAnnonceur.setButtonCell(cellAnnonceur());

		comboBoxCategorie.setCellFactory((list) -> {
			return cellCategorie();
		});
		comboBoxCategorie.setButtonCell(cellCategorie());

		try{
		int id;
		id=modelAnnonceur.numbreAnnonceur(annonceVue.getFxannonceur());
		comboBoxAnnonceur.getSelectionModel().select(modelAnnonceur.getAnnonceurs().get(id));
		id=modelCategorie.numbreCategorie(annonceVue.getFxcategorie());
		comboBoxCategorie.getSelectionModel().select(modelCategorie.getCategories().get(id));
		id=modelRubrique.numbreRubrique(annonceVue.getFxrubrique());
		comboBoxRubrique.getSelectionModel().select(modelRubrique.getRubriques().get(id));
		id=modelZone.numbreZone(annonceVue.getFxzone());
		comboxZone.getSelectionModel().select(modelZone.getZones().get(id));
		}catch (Exception e) {
			System.out.println("erreu dans ControllerAnnonce. numbreAnnonceur/numbreCategorie/numbreRubrique/numbreZone");
		}

	}

	public ListCell<FXAnnonceur> cellAnnonceur() {
		return new ListCell<FXAnnonceur>() {
			@Override
			protected void updateItem(FXAnnonceur item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null) {
					setText(null);
				} else {
					setText(item.nomProperty().get());
				}
			}
		};
	}

	public ListCell<FXCategorie> cellCategorie(){
		return new ListCell<FXCategorie>() {
			@Override
			protected void updateItem(FXCategorie item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null) {
					setText(null);
				} else {
					setText(item.libelleProperty().get());
				}
			}
		};


	}

	@FXML
	private void doRetour(){
//		managerGui.reinit();
		managerGui.showView(EnumView.AnnonceListe);
	}

	@FXML
	private void doAnnuler(){
		managerGui.reinit();
		managerGui.showView(EnumView.AnnonceListe);
	}

	@FXML
	private void doOk(){
		try {
			modelAnnonce.ValiderMiseAJour();
			managerGui.showView( EnumView.AnnonceListe );;
		} catch (Exception e) {
			managerGui.afficherErreur(e);
		}
	}

}
