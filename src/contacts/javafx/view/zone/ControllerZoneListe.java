package contacts.javafx.view.zone;

import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXZone;
import contacts.javafx.model.IModelZone;
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

public class ControllerZoneListe implements IController{

	@FXML
	private ListView<FXZone> listViewZones;

	private IModelZone modelZone;

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
		modelZone.preparerModifier();
		// Main.showViewPersonneForm();
		managerGui.showView(EnumView.ZoneForm);
	}

	@FXML
	private void doModifier() {
		ObservableList<FXZone> selectedItems = listViewZones.getSelectionModel().getSelectedItems();
		FXZone zone = (FXZone) selectedItems.get(0);
		modelZone.preparerModifier(zone);
		// Main.showViewPersonneForm();
		managerGui.showView(EnumView.ZoneForm);
	}

	@FXML
	private void doSupprimer() {
		String message;
		ObservableList<FXZone> selectedItems = listViewZones.getSelectionModel().getSelectedItems();
		FXZone zone = (FXZone) selectedItems.get(0);
		// System.out.println("supprimer"+selectedItems);
		message = "Est-ce que vous voulez supprimer cette Zone : " + zone.getNom()
				+ "?";
		// if(Main.demanderConfirmation(message)){
		if (managerGui.demanderConfirmation(message)) {
			try {
				modelZone.supprimer(zone);
			} catch (ExceptionAppli e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				managerGui.afficherErreur(e);
			}
		}
	}

	@FXML
	private void doActualiser() {
		try {
			modelZone.actualiserListe();
		} catch (ExceptionAppli e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			managerGui.afficherErreur(e);
		}
	}

	@FXML
	private void doRetour(){
		managerGui.reinit();
		managerGui.showView(EnumView.Info);
	}

	@FXML
	private void doRetourMenuConfiguration(){
		managerGui.reinit();
		managerGui.showView(EnumView.MenuConfiguration);
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
		modelZone = managerGui.getModel(IModelZone.class);

		listViewZones.setItems(modelZone.getZones());
		listViewZones.getSelectionModel().getSelectedItems().addListener((ListChangeListener<FXZone>) (c) -> {
			if (listViewZones.getSelectionModel().getSelectedItem() != null) {
				Modifier.setDisable(false);
				Supprimer.setDisable(false);
			} else {
				Modifier.setDisable(true);
				Supprimer.setDisable(true);
			}
		});
	}

}
