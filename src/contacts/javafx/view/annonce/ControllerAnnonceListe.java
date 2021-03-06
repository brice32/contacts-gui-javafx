package contacts.javafx.view.annonce;

import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXAnnonce;
import contacts.javafx.fxb.FXAnnonceur;
import contacts.javafx.model.IModelAnnonce;
import contacts.javafx.view.EnumView;
import contacts.javafx.view.IController;
import contacts.javafx.view.IManagerGui;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ControllerAnnonceListe implements IController {

	private IManagerGui managerGui;

	private IModelAnnonce modelAnnonce;

	@FXML
	private TableView<FXAnnonce> tableViewAnnonce;

	@FXML
	private TableColumn<FXAnnonce, String> titreColumn;

	@FXML
	private TableColumn<FXAnnonce, String> annonceurColumn;

	@FXML
	private TableColumn<FXAnnonce, String> categorieColumn;

	@FXML
	private TableColumn<FXAnnonce, String> zoneColumn;

	@FXML
	private TableColumn<FXAnnonce, String> statutColumn;

	@FXML
	private Button		modifierButton;

	@FXML
	private Button		supprimerButton;

	@FXML
	private Button		reviserButton;

	@Override
	public void setManagerGui(IManagerGui managerGui) throws ExceptionAppli {
		// TODO Auto-generated method stub
		this.managerGui=managerGui;
		modelAnnonce = managerGui.getModel(IModelAnnonce.class);

		tableViewAnnonce.setItems(modelAnnonce.getAnnonces());
		titreColumn.setCellValueFactory(a -> a.getValue().titreProperty());
		annonceurColumn.setCellValueFactory(a ->a.getValue().getAnnonceur().nomProperty());
		categorieColumn.setCellValueFactory(a ->a.getValue().getCategorie().libelleProperty());
		zoneColumn.setCellValueFactory(a ->a.getValue().getZone().nomProperty());
		statutColumn.setCellValueFactory(a ->a.getValue().statuteProperty());

		tableViewAnnonce.getSelectionModel().getSelectedItems().addListener((ListChangeListener<FXAnnonce>) (c) -> {
			if (tableViewAnnonce.getSelectionModel().getSelectedItem() != null) {
				modifierButton.setDisable(false);
				supprimerButton.setDisable(false);
				reviserButton.setDisable(false);
			}
		});
	}

	@FXML
	private void doRetour(){
		managerGui.reinit();
		managerGui.showView(EnumView.Info);
	}

	@FXML
	private void doRetourMenuAnnonce(){
		managerGui.reinit();
		managerGui.showView(EnumView.MenuAnnonce);
	}

	@FXML
	private void doModifier(){
		modelAnnonce.preparerModifier(tableViewAnnonce.getSelectionModel().getSelectedItem());
		managerGui.showView( EnumView.AnnonceForm );
	}
}
