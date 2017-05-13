package contacts.javafx.model.standard;

import static contacts.javafx.model.EnumModeVue.CREER;
import static contacts.javafx.model.EnumModeVue.MODIFIER;

import contacts.commun.dto.DtoAnnonce;
import contacts.commun.dto.DtoAnnonceur;
import contacts.commun.service.IServiceAnnonce;
import contacts.commun.service.IServiceAnnonceur;
import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXAnnonce;
import contacts.javafx.fxb.FXAnnonceur;
import contacts.javafx.fxb.FXCategorie;
import contacts.javafx.fxb.FXRubrique;
import contacts.javafx.fxb.FXZone;
import contacts.javafx.model.EnumModeVue;
import contacts.javafx.model.IModelAnnonce;
import contacts.javafx.util.mapper.IMapperDtoFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelAnnonce implements IModelAnnonce {

	private final ObservableList<FXAnnonce> annonces = FXCollections.observableArrayList();
	// a ->new Observable[]{ a.nomProperty(), a.emailProperty()}
	private final FXAnnonce annonceVue = new FXAnnonce();

	private FXAnnonce annonceCourant;

	private IServiceAnnonce serviceAnnonce;

	private IMapperDtoFX mapper;

	private EnumModeVue modeVue;

	@Override
	public void actualiserListe() throws ExceptionAppli {
		annonces.clear();
		for (DtoAnnonce dto : serviceAnnonce.listerTout()){
			FXAnnonce annonce = mapper.map(dto);
			mapper.update(mapper.map(dto.getAnnonceur()), annonce.getAnnonceur());
			mapper.update(mapper.map(dto.getRubrique()), annonce.getRubrique());
			mapper.update(mapper.map(dto.getZone()), annonce.getZone());
			mapper.update(mapper.map(dto.getCategorie()), annonce.getCategorie());
			annonces.add(annonce);
		}
	}

	@Override
	public ObservableList<FXAnnonce> getAnnonces() {
		return annonces;
	}

	@Override
	public FXAnnonce getAnnonceVue() {
		// TODO Auto-generated method stub
		return annonceVue;
	}

	@Override
	public void preparerAjouter() {
		// TODO Auto-generated method stub
		modeVue = CREER;
		mapper.update( new FXAnnonce(), annonceVue );
	}

	@Override
	public void preparerModifier(FXAnnonce annonce) {
		modeVue = MODIFIER;
		annonceCourant = annonce;
		mapper.update(annonce, annonceVue);
		mapper.update(annonce.getAnnonceur(), annonceVue.getAnnonceur());
		mapper.update(annonce.getCategorie(), annonceVue.getCategorie());
		mapper.update(annonce.getRubrique(), annonceVue.getRubrique());
		mapper.update(annonce.getZone(), annonceVue.getZone());
	}

	@Override
	public void refresh() throws ExceptionAppli {
		actualiserListe();
	}

	@Override
	public void ValiderMiseAJour() throws ExceptionAppli {

		// Crée un objet contenant le données pour la mise à jour
		DtoAnnonce dto = mapper.map(annonceVue);
		dto.setAnnonceur(mapper.map(annonceVue).getAnnonceur());
		dto.setCategorie(mapper.map(annonceVue).getCategorie());
		dto.setRubrique(mapper.map(annonceVue).getRubrique());
		dto.setZone(mapper.map(annonceVue).getZone());

		// Effectue la mise à jour
		if (modeVue == CREER) {
			int id = serviceAnnonce.inserer(dto);
			annonceVue.setIdAnnonce(id);
			annonceCourant = mapper.update(annonceVue, new FXAnnonce());
			mapper.update(annonceVue.getAnnonceur(), annonceCourant.getAnnonceur());
			mapper.update(annonceVue.getCategorie(), annonceCourant.getCategorie());
			mapper.update(annonceVue.getRubrique(), annonceCourant.getRubrique());
			mapper.update(annonceVue.getZone(), annonceCourant.getZone());
			annonces.add(annonceCourant);
		}
		if (modeVue == MODIFIER) {
			serviceAnnonce.modifier(dto);
			mapper.update(annonceVue, annonceCourant);
			mapper.update(annonceVue.getAnnonceur(), annonceCourant.getAnnonceur());
			mapper.update(annonceVue.getCategorie(), annonceCourant.getCategorie());
			mapper.update(annonceVue.getRubrique(), annonceCourant.getRubrique());
			mapper.update(annonceVue.getZone(), annonceCourant.getZone());
		}
	}

	@Override
	public void mettreVueAnnonceur(FXAnnonceur annonceur){
		mapper.update(annonceur, annonceVue.getAnnonceur());
	}

	@Override
	public void mettreVueCategorie(FXCategorie categorie){
		mapper.update(categorie, annonceVue.getCategorie());
	}

	@Override
	public void mettreVueRubrique(FXRubrique rubrique){
		mapper.update(rubrique, annonceVue.getRubrique());
	}

	@Override
	public void mettreVueZone(FXZone zone){
		mapper.update(zone, annonceVue.getZone());
	}

	public void setMapper(IMapperDtoFX mapper) {
		this.mapper = mapper;
	}

	public void setServiceAnnonce(IServiceAnnonce serviceAnnonce) {
		this.serviceAnnonce = serviceAnnonce;
	}
}
