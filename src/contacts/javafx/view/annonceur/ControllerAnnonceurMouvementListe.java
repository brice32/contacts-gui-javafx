package contacts.javafx.view.annonceur;

import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXAnnonceur;
import contacts.javafx.fxb.FXMouvement;
import contacts.javafx.model.IModelAnnonceur;
import contacts.javafx.model.IModelMouvement;
import contacts.javafx.view.IController;
import contacts.javafx.view.IManagerGui;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class ControllerAnnonceurMouvementListe implements IController {

	@FXML
	private TableView<FXMouvement> tableViewMouvement;

	@FXML
	private TableColumn<FXMouvement, Number> montantColumn;

	@FXML
	private TableColumn<FXMouvement, String> dateColumn;

	@FXML
	private TableColumn<FXMouvement, String> heureColumn;

	@FXML
	private TableColumn<FXMouvement, Number> soldeColumn;

	@FXML
	private TableColumn<FXMouvement, String> libelleColumn;

	@FXML
	private Label	idMouvementLabel;

	@FXML
	private Label   montantLabel;

	@FXML
	private Label   dateLabel;

	@FXML
	private Label   heureLabel;

	@FXML
	private Label   soldeLabel;

	@FXML
	private Label   libelleLabel;

	@FXML
	private TextArea  descriptionTextArea;

	private IManagerGui managerGui;

	private IModelAnnonceur modelAnnonceur;

	private IModelMouvement modelMouvement;


	@Override
	public void setManagerGui(IManagerGui managerGui) throws ExceptionAppli {
		// TODO Auto-generated method stub
		this.managerGui = managerGui;
		modelMouvement = managerGui.getModel(IModelMouvement.class);
		tableViewMouvement.setItems(modelMouvement.getMouvements());
		montantColumn.setCellValueFactory(a -> a.getValue().montantProperty());
		dateColumn.setCellValueFactory(a -> a.getValue().dateProperty());
		heureColumn.setCellValueFactory(a -> a.getValue().heureProperty());
		soldeColumn.setCellValueFactory(a -> a.getValue().soldeProperty());
		libelleColumn.setCellValueFactory(a -> a.getValue().libelleProperty());

		idMouvementLabel.setText("");
		montantLabel.setText("");
		dateLabel.setText("");
		heureLabel.setText("");
		soldeLabel.setText("");
		libelleLabel.setText("");
		descriptionTextArea.setText("");

		tableViewMouvement.getSelectionModel().getSelectedItems().addListener((ListChangeListener<FXMouvement>) (c) -> {
				if (tableViewMouvement.getSelectionModel().getSelectedItem() != null) {
				ObservableList<FXMouvement> selectedItems = tableViewMouvement.getSelectionModel().getSelectedItems();
				FXMouvement mouvement = (FXMouvement) selectedItems.get(0);
				idMouvementLabel.setText(""+mouvement.getIdMouvement());
				montantLabel.setText(""+mouvement.getMontant());
				dateLabel.setText(mouvement.getDate());
				heureLabel.setText(mouvement.getHeure());
				soldeLabel.setText(""+mouvement.getSolde());
				libelleLabel.setText(mouvement.getLibelle());
				descriptionTextArea.setText(mouvement.getDescription());
				}else{
					idMouvementLabel.setText("");
					montantLabel.setText("");
					dateLabel.setText("");
					heureLabel.setText("");
					soldeLabel.setText("");
					libelleLabel.setText("");
					descriptionTextArea.setText("");
				}
		});


	}

}
