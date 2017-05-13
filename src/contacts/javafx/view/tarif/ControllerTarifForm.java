package contacts.javafx.view.tarif;

import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXTarif;
import contacts.javafx.model.IModelCompte;
import contacts.javafx.model.IModelTarif;
import contacts.javafx.view.EnumView;
import contacts.javafx.view.IController;
import contacts.javafx.view.IManagerGui;
import contacts.javafx.view.util.StringBindingId;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ControllerTarifForm implements IController{

	
	  @FXML
	 private TextField  TextFieldDate;
	  @FXML 
	 private TextField  TextFiedTarifConference;
	  @FXML 
	 private TextField  TextFieldTarifReief;
	  @FXML 
	 private TextField  TextFiedTarifStage;

	  private IManagerGui			managerGui;
	  private IModelTarif     modeltarif;
	  private FXTarif      tarifVue;
	  
	  // actions 
	  
	  public void DoAnnuler(){
		  
		  managerGui.showView(EnumView.TarifListe);
		  
	  }
	  
	  @FXML
		private void doValider()  {
			try {
				//modeltarif.validerMiseAJour();
				managerGui.showView( EnumView.TarifListe );;
			} catch (Exception e) {
				managerGui.afficherErreur(e);
			}
		}
	  
	  public void setManagerGui(IManagerGui managerGui) throws ExceptionAppli {

			// Injection  de dépendances
			this.managerGui = managerGui;
			modeltarif = managerGui.getModel( IModelTarif.class );
			tarifVue = modeltarif.getTarifsVue();

			// Data binding
			TextFieldDate.textProperty().bindBidirectional( tarifVue.DateProperty());
			TextFieldTarifReief.textProperty().unbindBidirectional( tarifVue.tarifReliefProperty());
			TextFiedTarifStage.textProperty().unbindBidirectional( tarifVue.tarifStageProperty());
			TextFiedTarifConference.textProperty().unbindBidirectional( tarifVue.tarifReliefProperty());

	  }
		
	}
