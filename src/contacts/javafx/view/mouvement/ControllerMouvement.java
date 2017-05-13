package contacts.javafx.view.mouvement;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import com.sun.javafx.scene.control.SelectedCellsMap;

import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXAnnonceur;
import contacts.javafx.fxb.FXCompte;
import contacts.javafx.fxb.FXMouvement;
import contacts.javafx.model.EnumModeVue;
import contacts.javafx.model.IModelAnnonceur;
import contacts.javafx.model.IModelMouvement;
import contacts.javafx.model.IModelPersonne;
import contacts.javafx.view.EnumView;
import contacts.javafx.view.IController;
import contacts.javafx.view.IManagerGui;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;
import sun.security.pkcs11.Secmod.ModuleType;

public class ControllerMouvement implements IController {

	@FXML
	private TextField textFieldMontant;

	@FXML
	private TextField textFieldLibelle;

	@FXML
	private TextArea textAreaDescription;

	@FXML
	private Button buttonAjouter;

	@FXML
	private Button buttonretour;

	@FXML
	private ComboBox<FXAnnonceur> comboBoxFXAnnonceur;

	@FXML
	private Label labelSolde;

	private IManagerGui managerGui;

	private IModelMouvement modelMouvement;

	private IModelAnnonceur modelAnnonceur;

	private FXMouvement mouvementVue;

	private FXMouvement mouvementTempo;

	private EnumModeVue modeMouvementVue;

	private ObservableList<FXAnnonceur> annonceurs;

	@Override
	public void setManagerGui(IManagerGui managerGui) throws ExceptionAppli {
		// TODO Auto-generated method stub
		this.managerGui = managerGui;
		labelSolde.setText("0.0");
		// System.out.println("ok");
		modelAnnonceur = managerGui.getModel(IModelAnnonceur.class);
		modelMouvement = managerGui.getModel(IModelMouvement.class);
		if (!modelMouvement.modeModifier()) {
			modelMouvement.preparerAjouter();
		}
		mouvementVue = modelMouvement.getMouvementVue();
		// pour afficher
		// if(modeMouvementVue == EnumModeVue.CREER){
		if (!modelMouvement.modeModifier()) {
			comboBoxFXAnnonceur.setItems(modelAnnonceur.getAnnonceurs());
		} else {
			// annonceurs.clear();
			// annonceurs.add(modelMouvement.getAnnonceur());
			// comboBoxFXAnnonceur.setItems(annonceurs);
			ObservableList<FXAnnonceur> annonceurs = FXCollections.observableArrayList();
			annonceurs.add(mouvementVue.getAnnonceur());
			comboBoxFXAnnonceur.setItems(annonceurs);
			comboBoxFXAnnonceur.getSelectionModel().select(0);
			// comboBoxFXAnnonceur.setd;
		}
		comboBoxFXAnnonceur.setCellFactory((list) -> {
			// return new ListCell<FXAnnonceur>() {
			// @Override
			// protected void updateItem(FXAnnonceur item, boolean empty) {
			// super.updateItem(item, empty);
			// if (item == null) {
			// setText(null);
			// } else {
			// setText(item.nomProperty().get() );
			// }
			// }
			// };
			return callAnnonceur();
		});
		comboBoxFXAnnonceur.setButtonCell(
				// new ListCell<FXAnnonceur>(){
				// protected void updateItem(FXAnnonceur item, boolean empty) {
				// super.updateItem(item, empty);
				// if (item == null) {
				// setText(null);
				// } else {
				// setText(item.nomProperty().get() );
				// }
				// }
				// }
				callAnnonceur());

		DecimalFormat nf = new DecimalFormat("0.00");
		textFieldMontant.textProperty().bindBidirectional(mouvementVue.montantProperty(),
				new NumberStringConverter(nf));
		textFieldLibelle.textProperty().bindBidirectional(mouvementVue.libelleProperty());
		textAreaDescription.textProperty().bindBidirectional(mouvementVue.descriptionProperty());

		comboBoxFXAnnonceur.getSelectionModel().selectedIndexProperty().addListener(c -> {
			buttonAjouter.setDisable(false);
		});

		if (modelMouvement.modeModifier()) {
			FXAnnonceur selectedItem = comboBoxFXAnnonceur.getSelectionModel().getSelectedItem();
			try {
				mouvementTempo = modelMouvement.getMouvementIdAnnonceur(selectedItem.getId());
			} catch (ExceptionAppli e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mouvementVue = modelMouvement.getMouvementVue();
			labelSolde.setText(Float.toString(mouvementTempo.getSolde()));
			buttonAjouter.setDisable(false);
		} else {
			comboBoxFXAnnonceur.getSelectionModel().selectedItemProperty()
					.addListener((options, oldValue, newValue) -> {
						if (comboBoxFXAnnonceur.getSelectionModel().getSelectedItem() != null) {
							FXAnnonceur selectedItem = comboBoxFXAnnonceur.getSelectionModel().getSelectedItem();
							try {
								mouvementTempo = modelMouvement.getMouvementIdAnnonceur(selectedItem.getId());
							} catch (ExceptionAppli e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							mouvementVue = modelMouvement.getMouvementVue();
							labelSolde.setText(Float.toString(mouvementTempo.getSolde()));
						} else {
							labelSolde.setText("0.0");
						}
					});
		}
	}

	// méthode auxiliaire
	public ListCell<FXAnnonceur> callAnnonceur() {
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

	@FXML
	public void doAjouter() {
		boolean reponse = managerGui.demanderConfirmation("Confirmez-vous charger " + textFieldMontant.getText() + " à "
				+ comboBoxFXAnnonceur.getSelectionModel().getSelectedItem().getNom() + " ?");
		if (reponse) {
			try {
				modelMouvement.mettreVueAnnonceur(comboBoxFXAnnonceur.getSelectionModel().getSelectedItem());
				modelMouvement.mettreVueSolde(mouvementTempo.getSolde());
				modelMouvement.ValiderMiseAJour();
				managerGui.reinit();
				retour();
			} catch (ExceptionAppli e) {
				managerGui.afficherErreur(e);
			}
		}
	}

	@FXML
	public void doRetour() {
		managerGui.reinit();
		retour();
	}

	private void retour() {
		// si mode creer, retour page info. si mode modifier,retour page
		// annonceur
		if (!modelMouvement.modeModifier()) {
			managerGui.showView(EnumView.Info);
		} else {
			managerGui.showView(EnumView.AnnonceurListe);
		}
	}
}
