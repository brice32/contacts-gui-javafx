package contacts.javafx.model;

import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXTarif;
import javafx.collections.ObservableList;

public interface IModelTarif {

	/* (non-Javadoc)
	 * @see contacts.javafx.model.standard.IModelTarif#getTarifs()
	 */
	ObservableList<FXTarif> getTarifs();

	/* (non-Javadoc)
	 * @see contacts.javafx.model.standard.IModelTarif#getTarifsVue()
	 */
	FXTarif getTarifsVue();

	/* (non-Javadoc)
	 * @see contacts.javafx.model.standard.IModelTarif#actualiserListe()
	 */
	void actualiserListe();

	/* (non-Javadoc)
	 * @see contacts.javafx.model.standard.IModelTarif#Supprimer(contacts.javafx.fxb.FXTarif)
	 */
	void Supprimer(FXTarif tarif) throws ExceptionAppli;
	// probleme sur la creation de la  lasse Iservicetarif à faire plutard

	void preparerModifier(FXTarif tarif);

	void ValiderMiseAjour();

	void refresh() throws ExceptionAppli;

}