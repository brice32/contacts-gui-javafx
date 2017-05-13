package contacts.javafx.view.zone;

import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXZone;
import contacts.javafx.model.IModelZone;
import contacts.javafx.view.EnumView;
import contacts.javafx.view.IController;
import contacts.javafx.view.IManagerGui;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ControllerZoneForm implements IController{

	@FXML
	private TextField	textFieldId;
	@FXML
	private TextField	textFieldNom;


	IModelZone modelZone;


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
			modelZone.ValiderMiseAJour();
			managerGui.showView(EnumView.ZoneListe);
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
		managerGui.showView( EnumView.ZoneListe );

	}

	@FXML
	private void doRetour(){
		managerGui.showView(EnumView.ZoneListe);
	}


	@Override
	public void setManagerGui(IManagerGui managerGui) throws ExceptionAppli {
		// TODO Auto-generated method stub
		this.managerGui = managerGui ;
//		modelPersonne = Main.getModelPersonne();
		modelZone = managerGui.getModel( IModelZone.class );
		FXZone zoneVue = modelZone.getZoneVue();
		textFieldId.textProperty().bind( Bindings.convert( zoneVue.idProperty() ) );
		textFieldNom.textProperty().bindBidirectional( zoneVue.nomProperty() );


	}





}
