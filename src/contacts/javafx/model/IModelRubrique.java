package contacts.javafx.model;

import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXRubrique;
import javafx.collections.ObservableList;

public interface IModelRubrique {
	void actualiserListe() throws ExceptionAppli;

	ObservableList<FXRubrique> getRubriques();

	void supprimer(FXRubrique rubrique) throws ExceptionAppli;

	FXRubrique getRubriqueVue();

	void preparerModifier(FXRubrique rubrique);

	void preparerModifier();

	void ValiderMiseAJour() throws ExceptionAppli;



	// Initialisation
	void refresh() throws ExceptionAppli;

	int numbreRubrique(FXRubrique rubriqueSelected) throws ExceptionAppli;




}
