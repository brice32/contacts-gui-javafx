package contacts.javafx.model.standard;

import static contacts.javafx.model.EnumModeVue.CREER;
import static contacts.javafx.model.EnumModeVue.MODIFIER;

import contacts.commun.dto.DtoAnnonceur;
import contacts.commun.dto.DtoCompte;
import contacts.commun.service.IServiceAnnonceur;
import contacts.commun.service.IServiceCompte;
import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXAnnonceur;
import contacts.javafx.fxb.FXCompte;
import contacts.javafx.model.EnumModeVue;
import contacts.javafx.model.IModelAnnonceur;
import contacts.javafx.util.mapper.IMapperDtoFX;
import contacts.javafx.view.IManagerGui;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelAnnonceur implements IModelAnnonceur {

	private final ObservableList<FXAnnonceur> annonceurs = FXCollections.observableArrayList();
	// a ->new Observable[]{ a.nomProperty(), a.emailProperty()}
	private final FXAnnonceur annonceurVue = new FXAnnonceur();

	private FXAnnonceur annonceurCourant;

	private IServiceAnnonceur serviceAnnonceur;

	private IMapperDtoFX mapper;

	private EnumModeVue modeVue;

	@Override
	public void actualiserListe() throws ExceptionAppli {
		// TODO Auto-generated method stub
		annonceurs.clear();
		for (DtoAnnonceur dto : serviceAnnonceur.listerTout()) {
			FXAnnonceur annonceur = mapper.map(dto);
			annonceurs.add(annonceur);
		}
	}

	@Override
	public ObservableList<FXAnnonceur> getAnnonceurs() {
		// TODO Auto-generated method stub
		return annonceurs;
	}

	@Override
	public void supprimer(FXAnnonceur annonceur) throws ExceptionAppli {

		serviceAnnonceur.supprimer(annonceur.getId());
		annonceurs.remove(annonceur);

	}

	@Override
	public FXAnnonceur getAnnonceurVue() {
		// TODO Auto-generated method stub
		return annonceurVue;
	}

	@Override
	public void preparerModifier(FXAnnonceur annonceur) {
		modeVue = MODIFIER;
		annonceurCourant = annonceur;
		mapper.update(annonceur, annonceurVue);
	}

	@Override
	public void preparerAjouter() {
		// TODO Auto-generated method stub
		modeVue = CREER;
		mapper.update( new FXAnnonceur(), annonceurVue );
	}

	@Override
	public void ValiderMiseAJour() throws ExceptionAppli {

		// Crée un objet contenant le données pour la mise à jour
		DtoAnnonceur dto = mapper.map(annonceurVue);

		// Effectue la mise à jour
		if (modeVue == CREER) {
			int id = serviceAnnonceur.inserer(dto);
			annonceurVue.setId(id);
			annonceurCourant = mapper.update(annonceurVue, new FXAnnonceur());
			annonceurs.add(annonceurCourant);
		}
		if (modeVue == MODIFIER) {
			serviceAnnonceur.modifier(dto);
			mapper.update(annonceurVue, annonceurCourant);
		}
	}

	@Override
	public int numbreAnnonceur(FXAnnonceur annonceurSelected) throws ExceptionAppli {
		int id = 0;

		for( FXAnnonceur annonceur : annonceurs ){
			if(annonceur.getId()==annonceurSelected.getId()){
				break;
			}
			id++;
		}
		return id;
	}

	@Override
	public void refresh() throws ExceptionAppli {
		actualiserListe();
	}

	public void setMapper(IMapperDtoFX mapper) {
		this.mapper = mapper;
	}

	public void setServiceAnnonceur(IServiceAnnonceur serviceAnnonceur) {
		this.serviceAnnonceur = serviceAnnonceur;
	}


}
