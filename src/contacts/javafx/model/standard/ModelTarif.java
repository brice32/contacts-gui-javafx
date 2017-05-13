package contacts.javafx.model.standard;

import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXTarif;
import contacts.javafx.model.IModelTarif;
import contacts.javafx.util.mapper.IMapperDtoFX;
import contacts.javafx.view.IManagerGui;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelTarif implements IModelTarif {
	
	private final ObservableList<FXTarif> tarifs = FXCollections.observableArrayList(p ->new Observable[]{ p.DateProperty(), p.tarifConferenceProperty(), p.tarifReliefProperty() , p.tarifStageProperty()});
	 
	private final FXTarif TarifsVue = new FXTarif(99, 0.0f, 0.0f,0.0f, " ");
	
	private static FXTarif TarifsCourant = new FXTarif();
	
	private IManagerGui managerGui;
	private IMapperDtoFX mapper;
    
	


	/* (non-Javadoc)
	 * @see contacts.javafx.model.standard.IModelTarif#getTarifs()
	 */
	/* (non-Javadoc)
	 * @see contacts.javafx.model.standard.IModelTarif#getTarifs()
	 */
	@Override
	public ObservableList<FXTarif> getTarifs() {
		return tarifs;
	}
	
	
	/* (non-Javadoc)
	 * @see contacts.javafx.model.standard.IModelTarif#getTarifsVue()
	 */
	/* (non-Javadoc)
	 * @see contacts.javafx.model.standard.IModelTarif#getTarifsVue()
	 */
	@Override
	public FXTarif getTarifsVue() {
		return TarifsVue;
	}


	/* (non-Javadoc)
	 * @see contacts.javafx.model.standard.IModelTarif#actualiserListe()
	 */
	/* (non-Javadoc)
	 * @see contacts.javafx.model.standard.IModelTarif#actualiserListe()
	 */
	@Override
	public void actualiserListe(){
		tarifs.clear();
		
		
		tarifs.add(new FXTarif(1,0.0f , 0.0f, 0.0f,"20/01/1995"));
		tarifs.add(new FXTarif(2,0.0f , 0.0f, 0.0f,"20/01/1995"));
		tarifs.add(new FXTarif(3,0.0f , 0.0f, 0.0f,"20/01/1995"));
		tarifs.add(new FXTarif(4,0.0f , 0.0f, 0.0f,"20/01/1995"));
		
		
	}

		
/* (non-Javadoc)
 * @see contacts.javafx.model.standard.IModelTarif#Supprimer(contacts.javafx.fxb.FXTarif)
 */
/* (non-Javadoc)
 * @see contacts.javafx.model.standard.IModelTarif#Supprimer(contacts.javafx.fxb.FXTarif)
 */
@Override
public void Supprimer(FXTarif tarif) throws ExceptionAppli{
	//serviceTarif.supprimer(tarif.getId());
	tarifs.remove(tarif);
}
// probleme sur la creation de la  lasse Iservicetarif à faire plutard

/* (non-Javadoc)
 * @see contacts.javafx.model.standard.IModelTarif#refresh()
 */

/* (non-Javadoc)
 * @see contacts.javafx.model.standard.IModelTarif#preparerModifier(contacts.javafx.fxb.FXTarif)
 */
@Override
public void preparerModifier( FXTarif tarif ){
	if(tarif!=null){
	TarifsCourant=tarif;
//	copierDonnees(personne, personneVue);
	mapper.update(tarif, TarifsVue);
	}
	else{
		TarifsCourant=new FXTarif();
//		copierDonnees(personneCourant, personneVue);
		mapper.update(TarifsCourant, TarifsVue);
	}
}

/* (non-Javadoc)
 * @see contacts.javafx.model.standard.IModelTarif#ValiderMiseAjour()
 */
@Override
public void ValiderMiseAjour(){
	mapper.update(TarifsVue, TarifsCourant);
	
}
/* (non-Javadoc)
 * @see contacts.javafx.model.standard.IModelTarif#refresh()
 */
@Override
public void refresh() throws ExceptionAppli {
 actualiserListe();
}
}
