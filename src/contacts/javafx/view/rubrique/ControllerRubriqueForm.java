package contacts.javafx.view.rubrique;

import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXRubrique;
import contacts.javafx.model.IModelRubrique;
import contacts.javafx.view.EnumView;
import contacts.javafx.view.IController;
import contacts.javafx.view.IManagerGui;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ControllerRubriqueForm implements IController{
	@FXML
	private TextField	textFieldId;
	@FXML
	private TextField	textFieldNom;


	IModelRubrique modelRubrique;


	private IManagerGui managerGui;

//	@FXML
//	private void initialize(){
//
//
//
//	}

	@FXML
	private void doValider(){

		try {
			modelRubrique.ValiderMiseAJour();
			managerGui.showView(EnumView.RubriqueListe);
		} catch (ExceptionAppli e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			managerGui.afficherErreur(e);
		}
//		Main.showViewPersonneListe();


	}
	@FXML
	private void doAnnuler(){
//		Main.showViewPersonneListe();
		managerGui.showView( EnumView.RubriqueListe );

	}

	@FXML
	private void doRetour(){
		managerGui.showView( EnumView.RubriqueListe );
	}


	@Override
	public void setManagerGui(IManagerGui managerGui) throws ExceptionAppli {
		// TODO Auto-generated method stub
		this.managerGui = managerGui ;
//		modelPersonne = Main.getModelPersonne();
		modelRubrique = managerGui.getModel( IModelRubrique.class );
		FXRubrique rubriqueVue = modelRubrique.getRubriqueVue();
		textFieldId.textProperty().bind( Bindings.convert( rubriqueVue.idProperty() ) );
		textFieldNom.textProperty().bindBidirectional( rubriqueVue.nomProperty() );


	}





}
