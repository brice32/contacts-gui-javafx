package contacts.javafx.model;

import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXAnnonce;
import javafx.collections.ObservableList;

public interface IModelAnnonce {

	void actualiserListe() throws ExceptionAppli;

	ObservableList<FXAnnonce> getAnnonces();

	void refresh() throws ExceptionAppli;

	FXAnnonce getAnnonceVue();

	void preparerAjouter();

	void preparerModifier(FXAnnonce annonce);

}
