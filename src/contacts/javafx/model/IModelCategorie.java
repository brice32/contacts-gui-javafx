package contacts.javafx.model;

import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXAnnonceur;
import contacts.javafx.fxb.FXCategorie;
import javafx.collections.ObservableList;

public interface IModelCategorie {

	void actualiserListe() throws ExceptionAppli;

	ObservableList<FXCategorie> getCategories();

	void supprimer(FXCategorie categorie) throws ExceptionAppli;

	FXCategorie getCategorieVue();

	void preparerModifier(FXCategorie categorie);

	public void preparerAjouter();

	void ValiderMiseAJour() throws ExceptionAppli;

	// Initialisation
	void refresh() throws ExceptionAppli;

	int numbreCategorie(FXCategorie categorieSelected) throws ExceptionAppli;

}