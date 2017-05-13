package contacts.javafx.view.configuration;

import java.util.Collections;
import java.util.Comparator;

import contacts.commun.util.ExceptionAppli;
import contacts.commun.util.Roles;
import contacts.javafx.fxb.FXCategorie;
import contacts.javafx.fxb.FXCompte;
import contacts.javafx.model.IModelCategorie;
import contacts.javafx.model.IModelCompte;
import contacts.javafx.view.EnumView;
import contacts.javafx.view.IController;
import contacts.javafx.view.IManagerGui;
import contacts.javafx.view.util.StringBindingId;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;


public class ControllerCategorieForm implements IController {


	// Composants de la vue

	@FXML
	private TextField			textFieldId;
	@FXML
	private TextField			textFieldLibelle;


	// Autres champs

	private IManagerGui			managerGui;
	private IModelCategorie		modelCategorie;
	private FXCategorie 		categorieVue;


	// Actions

	@FXML
	private void doAnnuler() {
		managerGui.showView( EnumView.CategorieListe );;
	}

	@FXML
	private void doValider()  {
		try {
			modelCategorie.ValiderMiseAJour();
			managerGui.showView( EnumView.CategorieListe );;
		} catch (Exception e) {
			managerGui.afficherErreur(e);
		}
	}

	@FXML
	private void doRetour(){
		managerGui.showView(EnumView.CategorieListe);
	}

	// Initialisation du Controller

    @Override
	public void setManagerGui(IManagerGui managerGui) throws ExceptionAppli {

		// Injection  de dépendances
		this.managerGui = managerGui;
		modelCategorie = managerGui.getModel( IModelCategorie.class );
		categorieVue = modelCategorie.getCategorieVue();

		// Data binding
		textFieldId.textProperty().bind(new StringBindingId(categorieVue.idCategorieProperty()));
		textFieldLibelle.textProperty().bindBidirectional( categorieVue.libelleProperty() );
	}



}
