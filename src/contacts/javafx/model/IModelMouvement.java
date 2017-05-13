package contacts.javafx.model;

import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXAnnonceur;
import contacts.javafx.fxb.FXMouvement;
import javafx.collections.ObservableList;

public interface IModelMouvement {

	void actualiserListe() throws ExceptionAppli;

	ObservableList<FXMouvement> getMouvements();

	void supprimer(FXMouvement mouvement) throws ExceptionAppli;

	FXMouvement getMouvementVue();

	void preparerModifier(FXMouvement mouvement);

	public void preparerAjouter();

	void ValiderMiseAJour() throws ExceptionAppli;

	// Initialisation
	void refresh() throws ExceptionAppli;

	void mettreVueAnnonceur(FXAnnonceur selectedItem);

	FXMouvement getMouvementIdAnnonceur(int idAnnonceur) throws ExceptionAppli;

	void mettreVueSolde(float solde);

	void preparerAnnonceur(FXAnnonceur annonceur);

	FXAnnonceur getAnnonceur();

	boolean modeModifier();

	void preparerListe(FXAnnonceur fxannonceur) throws ExceptionAppli;

}