package contacts.javafx.view.annonceur;

import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXAnnonceur;
import contacts.javafx.fxb.FXMouvement;
import contacts.javafx.fxb.FXPersonne;
import contacts.javafx.model.EnumModeVue;
import contacts.javafx.model.IModelAnnonceur;
import contacts.javafx.model.IModelMouvement;
import contacts.javafx.model.IModelPersonne;
import contacts.javafx.view.EnumView;
import contacts.javafx.view.IController;
import contacts.javafx.view.IManagerGui;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ControllerAnnonceurListe implements IController {

	@FXML
	private TableView<FXAnnonceur> tableViewAnnonceur;

	@FXML
    private TableColumn<FXAnnonceur, String> nomColumn;

	@FXML
	private TableColumn<FXAnnonceur, String> emailColumn;


	@FXML
	private Label 	LabelIdAnnonceur;

	@FXML
	private Label 	LabelNom;

	@FXML
	private Label 	LabelTelephone;

	@FXML
	private Label 	LabelEmail;

	@FXML
	private Label 	LabelLieuNom;

	@FXML
	private Label   LabelLieuAdresse;

	@FXML
	private Label 	LabelLieuCp;

	@FXML
	private Label 	LabelLieuVille;

	@FXML
	private Label 	LabelSiteWeb;

	@FXML
	private Label 	LabelSolde;

	@FXML
	private Button buttonModifier;

	@FXML
	private Button buttonSupprimer;

	@FXML
	private Button buttonMouvement;

	@FXML
	private Button buttonListeMouvement;

	@FXML
	private Button buttonRetour;

	@FXML
	private Button buttonRetourMenuAnnonceur;

	private IManagerGui managerGui;

	private IModelAnnonceur modelAnnonceur;

	private IModelMouvement modelMouvement;

	private FXMouvement 	fxmouvement;

	@FXML
	private void doActualiser() {
		try {
			modelAnnonceur.actualiserListe();
			LabelSolde.setText("");
			tableViewAnnonceur.getSelectionModel().clearSelection();
		} catch (Exception e) {
			managerGui.afficherErreur(e);
		};
	}

	@FXML
	private void doAjouter() {
		modelAnnonceur.preparerAjouter();
		managerGui.showView( EnumView.AnnonceurForm );
	}

	@FXML
	private void doModifier() {
		modelAnnonceur.preparerModifier( tableViewAnnonceur.getSelectionModel().getSelectedItem() );
		managerGui.showView( EnumView.AnnonceurForm );
	}

	@FXML
	private void doSupprimer() {
		boolean reponse = managerGui.demanderConfirmation( "Confirmez-vous la suppression ?" );
		if ( reponse ) {
			try {
				modelAnnonceur.supprimer( tableViewAnnonceur.getSelectionModel().getSelectedItem() );
			} catch (Exception e) {
				managerGui.afficherErreur(e);
			}
		}
	}

	@FXML
	private void doMouvement(){
		modelMouvement.preparerAnnonceur(tableViewAnnonceur.getSelectionModel().getSelectedItem());
		managerGui.showView( EnumView.Mouvement );
	}

	@FXML
	private void doListeMouvement() throws ExceptionAppli{
		modelMouvement.preparerListe(tableViewAnnonceur.getSelectionModel().getSelectedItem());
		managerGui.showNewWindow( EnumView.AnnonceurMouvementListe );
	}

	@FXML
	private void doRetour(){
		managerGui.reinit();
		managerGui.showView(EnumView.Info);
	}

	@FXML
	private void doRetourMenuAnnonceur(){
		managerGui.reinit();
		managerGui.showView(EnumView.MenuAnnonce);
	}

	@Override
	public void setManagerGui(IManagerGui managerGui) throws ExceptionAppli {
		// TODO Auto-generated method stub

		LabelIdAnnonceur.setText("");
		LabelNom.setText("");
		LabelTelephone.setText("");
		LabelEmail.setText("");
		LabelLieuNom.setText("");
		LabelLieuAdresse.setText("");
		LabelLieuCp.setText("");
		LabelLieuVille.setText("");
		LabelSiteWeb.setText("");
		LabelSolde.setText("");

		this.managerGui = managerGui;
		modelAnnonceur = managerGui.getModel(IModelAnnonceur.class);
		modelMouvement = managerGui.getModel(IModelMouvement.class);

		tableViewAnnonceur.setItems(modelAnnonceur.getAnnonceurs());
		nomColumn.setCellValueFactory(a -> a.getValue().nomProperty());
		emailColumn.setCellValueFactory(a -> a.getValue().emailProperty());

		tableViewAnnonceur.getSelectionModel().getSelectedItems().addListener((ListChangeListener<FXAnnonceur>) (c) -> {
			if (tableViewAnnonceur.getSelectionModel().getSelectedItem() != null) {
//				int idAnnonceur = tableViewAnnonceur.getSelectionModel().getSelectedItem().getId();
				ObservableList<FXAnnonceur> selectedItems = tableViewAnnonceur.getSelectionModel().getSelectedItems();
				FXAnnonceur annonceur = (FXAnnonceur) selectedItems.get(0);
				LabelIdAnnonceur.setText( "" + annonceur.getId());
				LabelNom.setText(annonceur.getNom());
				LabelTelephone.setText(annonceur.getTelephone());
				LabelEmail.setText(annonceur.getEmail());
				LabelLieuNom.setText(annonceur.getLieuNom());
				LabelLieuAdresse.setText(annonceur.getLieuAdresse());
				LabelLieuCp.setText(annonceur.getLieuCp());
				LabelLieuVille.setText(annonceur.getLieuVille());
				LabelSiteWeb.setText(annonceur.getSiteWeb());
				try {
					fxmouvement=modelMouvement.getMouvementIdAnnonceur(annonceur.getId());
				} catch (ExceptionAppli e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
//					managerGui.afficherErreur(e);
				}
				LabelSolde.setText(Float.toString(fxmouvement.getSolde()));
				buttonModifier.setDisable(false);
				buttonSupprimer.setDisable(false);
				buttonMouvement.setDisable(false);
				buttonListeMouvement.setDisable(false);
			}
			else{
				LabelIdAnnonceur.setText("");
				LabelNom.setText("");
				LabelTelephone.setText("");
				LabelEmail.setText("");
				LabelLieuNom.setText("");
				LabelLieuAdresse.setText("");
				LabelLieuCp.setText("");
				LabelLieuVille.setText("");
				LabelSiteWeb.setText("");
				buttonModifier.setDisable(true);
				buttonSupprimer.setDisable(true);
				buttonMouvement.setDisable(true);
				buttonListeMouvement.setDisable(true);
			}
		});
		}
}
