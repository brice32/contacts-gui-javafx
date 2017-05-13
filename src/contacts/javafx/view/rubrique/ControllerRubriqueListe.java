package contacts.javafx.view.rubrique;

import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXRubrique;
import contacts.javafx.model.IModelRubrique;
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

public class ControllerRubriqueListe implements IController {
	@FXML
	private ListView<FXRubrique> listViewRubriques;

	private IModelRubrique modelRubrique;

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
		modelRubrique.preparerModifier();
		// Main.showViewPersonneForm();
		managerGui.showView(EnumView.RubriqueForm);
	}

	@FXML
	private void doModifier() {
		ObservableList<FXRubrique> selectedItems = listViewRubriques.getSelectionModel().getSelectedItems();
		FXRubrique rubrique = (FXRubrique) selectedItems.get(0);
		modelRubrique.preparerModifier(rubrique);
		// Main.showViewPersonneForm();
		managerGui.showView(EnumView.RubriqueForm);
	}

	@FXML
	private void doSupprimer() {
		String message;
		ObservableList<FXRubrique> selectedItems = listViewRubriques.getSelectionModel().getSelectedItems();
		FXRubrique rubrique = (FXRubrique) selectedItems.get(0);
		// System.out.println("supprimer"+selectedItems);
		message = "Est-ce que vous voulez supprimer la rubrique : " + rubrique.getNom()
				+ "?";
		// if(Main.demanderConfirmation(message)){
		if (managerGui.demanderConfirmation(message)) {
			try {
				modelRubrique.supprimer(rubrique);
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
			modelRubrique.actualiserListe();
		} catch (ExceptionAppli e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			managerGui.afficherErreur(e);
		}
	}

	@FXML
	private void gererClic(MouseEvent event) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				doModifier();
			}
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

	@Override
	public void setManagerGui(IManagerGui managerGui) throws ExceptionAppli {
		this.managerGui = managerGui;
		modelRubrique = managerGui.getModel(IModelRubrique.class);

		listViewRubriques.setItems(modelRubrique.getRubriques());
		listViewRubriques.getSelectionModel().getSelectedItems().addListener((ListChangeListener<FXRubrique>) (c) -> {
			if (listViewRubriques.getSelectionModel().getSelectedItem() != null) {
				Modifier.setDisable(false);
				Supprimer.setDisable(false);
			} else {
				Modifier.setDisable(true);
				Supprimer.setDisable(true);
			}
		});
	}

}
