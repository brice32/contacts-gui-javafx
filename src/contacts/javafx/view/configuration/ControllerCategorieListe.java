package contacts.javafx.view.configuration;

import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXCategorie;
import contacts.javafx.fxb.FXCompte;
import contacts.javafx.model.IModelCategorie;
import contacts.javafx.model.IModelCompte;
import contacts.javafx.view.EnumView;
import contacts.javafx.view.IController;
import contacts.javafx.view.IManagerGui;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


public class ControllerCategorieListe implements IController {


	// Composants de la vue

	@FXML
	private ListView<FXCategorie>	listView;
	@FXML
	private Button				buttonModifier;
	@FXML
	private Button				buttonSupprimer;
	@FXML
	private Button				buttonMemos;


	// Autres champs

	private IManagerGui			managerGui;
	private IModelCategorie		modelCategorie;



	// Actions

	@FXML
	private void doActualiser() {
		try {
			modelCategorie.actualiserListe();
			listView.getSelectionModel().clearSelection();
		} catch (Exception e) {
			managerGui.afficherErreur(e);
		};
	}

	@FXML
	private void doAjouter() {
		modelCategorie.preparerAjouter();
		managerGui.showView( EnumView.CategorieForm );
	}

	@FXML
	private void doModifier() {
		modelCategorie.preparerModifier( listView.getSelectionModel().getSelectedItem() );
		managerGui.showView( EnumView.CategorieForm );
	}

	@FXML
	private void doSupprimer() {
		boolean reponse = managerGui.demanderConfirmation( "Confirmez-vous la suppression�?" );
		if ( reponse ) {
			try {
				modelCategorie.supprimer( listView.getSelectionModel().getSelectedItem() );
			} catch (Exception e) {
				managerGui.afficherErreur(e);
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

	// Gestion des évènements

	// Clic sur la liste
	@FXML
	private void gererClicSurListe( MouseEvent event ) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				doModifier();
			}
		}
	}


	// Initialisation du Controller

    @Override
	public void setManagerGui(IManagerGui managerGui) throws ExceptionAppli {

		// Injection  de dépendances
		this.managerGui = managerGui;
		modelCategorie = managerGui.getModel( IModelCategorie.class );


		// Configuration de l'objet ListView

		// Data binding
		listView.setItems( modelCategorie.getCategories() );

		// Affichage
		listView.setCellFactory( (list) -> {
		    return new ListCell<FXCategorie>() {
		        @Override
		        protected void updateItem(FXCategorie item, boolean empty) {
		            super.updateItem(item, empty);
		            if (item == null) {
		                setText(null);
		            } else {
		                setText(item.libelleProperty().get() );
		            }
		        }
		    };
		});

		// Comportement si modificaiton de la séleciton
		listView.getSelectionModel().getSelectedItems().addListener(
				(ListChangeListener<FXCategorie>) (c) -> {
					 configurerBoutons();
		});

		// Comportement si changement du contenu
		listView.getItems().addListener( (ListChangeListener<FXCategorie>) (c) -> {
			c.next();
			// Après insertion d'un élément, le sélectionne
			// Après suppression d'un élément, sélectionne le suivant
			if ( c.wasAdded() || c.wasRemoved() ) {
				listView.getSelectionModel().clearSelection();
				listView.getSelectionModel().select( c.getFrom());
				listView.getFocusModel().focus(c.getFrom());
				listView.scrollTo( c.getFrom());
				listView.requestFocus();
			}
		});
	}


	// Méthodes auxiliaires

	private void configurerBoutons() {
		int nbSelections = listView.getSelectionModel().getSelectedItems().size();
		if ( nbSelections == 1 ) {
			buttonModifier.setDisable(false);
			buttonSupprimer.setDisable(false);
		} else {
			buttonModifier.setDisable(true);
			buttonSupprimer.setDisable(true);
		}
	}

}
