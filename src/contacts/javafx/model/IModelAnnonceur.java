package contacts.javafx.model;

import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXAnnonceur;
import javafx.collections.ObservableList;

public interface IModelAnnonceur {

	void actualiserListe() throws ExceptionAppli;

	ObservableList<FXAnnonceur> getAnnonceurs();

	void supprimer(FXAnnonceur annonceur) throws ExceptionAppli;

	FXAnnonceur getAnnonceurVue();

	void preparerModifier(FXAnnonceur annonceur);

	public void preparerAjouter();

	void ValiderMiseAJour() throws ExceptionAppli;

	// Initialisation
	void refresh() throws ExceptionAppli;

	int numbreAnnonceur(FXAnnonceur annonce) throws ExceptionAppli;

}