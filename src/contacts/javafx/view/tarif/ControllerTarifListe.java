package contacts.javafx.view.tarif;

import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXTarif;
import contacts.javafx.model.IModelTarif;
import contacts.javafx.view.EnumView;
import contacts.javafx.view.IController;
import contacts.javafx.view.IManagerGui;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ControllerTarifListe implements IController{

	
	@FXML
	private ListView<FXTarif> ListTarifViews;

	private IModelTarif modelTarif;

	@FXML
	private Button Modifier;

	@FXML
	private Button Supprimer;

	private IManagerGui managerGui;

	
	@FXML
	private void initialize() {

		// modelPersonne = Main.getModelPersonne();

	}

	@FXML
	private void doAjouter() {
		//modelTarif.preparerModifier();
		modelTarif.ValiderMiseAjour();
		// Main.showViewPersonneForm();
		managerGui.showView(EnumView.PersonneForm);
	}

	@FXML
	private void doModifier() {
		ObservableList<FXTarif> selectedItems = ListTarifViews.getSelectionModel().getSelectedItems();
		FXTarif tarif = (FXTarif) selectedItems.get(0);
		modelTarif.preparerModifier(tarif);
		// Main.showViewPersonneForm();
		managerGui.showView(EnumView.TarifForm);
	}

	@FXML
	private void doSupprimer() {
		String message;
		ObservableList<FXTarif> selectedItems = ListTarifViews.getSelectionModel().getSelectedItems();
		FXTarif tarif = (FXTarif) selectedItems.get(0);
		
		message = "Est-ce que vous voulez supprimer le tarif : " + selectedItems + "?";

		if (managerGui.demanderConfirmation(message)) {
			try {
				modelTarif.Supprimer(tarif);
			} catch (ExceptionAppli e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				managerGui.afficherErreur(e);
			}
		}
	}

	@FXML
	private void doActualiser() {
		modelTarif.actualiserListe();
	}

	@FXML
	private void gererClic(MouseEvent event) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				doModifier();
			}
		}
	}

	@Override
	public void setManagerGui(IManagerGui managerGui) throws ExceptionAppli {
		this.managerGui = managerGui;
		modelTarif = managerGui.getModel(IModelTarif.class);

		ListTarifViews.setItems(modelTarif.getTarifs());
		ListTarifViews.getSelectionModel().getSelectedItems().addListener((ListChangeListener<FXTarif>) (c) -> {
			if (ListTarifViews.getSelectionModel().getSelectedItem() != null) {
				Modifier.setDisable(false);
				Supprimer.setDisable(false);
			} else {
				Modifier.setDisable(true);
				Supprimer.setDisable(true);
			}
		});
	}
	
	
	
	
	
	


}
