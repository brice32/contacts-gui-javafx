package contacts.javafx.model;

import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXAnnonce;
import contacts.javafx.fxb.FXAnnonceur;
import contacts.javafx.fxb.FXCategorie;
import contacts.javafx.fxb.FXRubrique;
import contacts.javafx.fxb.FXZone;
import javafx.collections.ObservableList;

public interface IModelAnnonce {

	void actualiserListe() throws ExceptionAppli;

	ObservableList<FXAnnonce> getAnnonces();

	void refresh() throws ExceptionAppli;

	FXAnnonce getAnnonceVue();

	void preparerAjouter();

	void preparerModifier(FXAnnonce annonce);

	void ValiderMiseAJour() throws ExceptionAppli;

	void mettreVueAnnonceur(FXAnnonceur annonceur);

	void mettreVueCategorie(FXCategorie categorie);

	void mettreVueRubrique(FXRubrique rubrique);

	void mettreVueZone(FXZone zone);

}
