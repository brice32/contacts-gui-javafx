package contacts.javafx.model;

import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXZone;
import javafx.collections.ObservableList;

public interface IModelZone {
	void actualiserListe() throws ExceptionAppli;

	ObservableList<FXZone> getZones();

	void supprimer(FXZone zone) throws ExceptionAppli;

	FXZone getZoneVue();

	void preparerModifier(FXZone zone);

	void preparerModifier();

	void ValiderMiseAJour() throws ExceptionAppli;



	// Initialisation
	void refresh() throws ExceptionAppli;

	int numbreZone(FXZone zoneSelected) throws ExceptionAppli;



}
