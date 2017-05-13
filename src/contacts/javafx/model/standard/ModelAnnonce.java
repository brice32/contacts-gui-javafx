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
			mapper.update(mapper.map(dto.getAnnonceur()), annonce.getFxannonceur());
			mapper.update(mapper.map(dto.getRubrique()), annonce.getFxrubrique());
			mapper.update(mapper.map(dto.getZone()), annonce.getFxzone());
			mapper.update(mapper.map(dto.getCategorie()), annonce.getFxcategorie());
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
		mapper.update(annonce.getFxannonceur(), annonceVue.getFxannonceur());
		mapper.update(annonce.getFxcategorie(), annonceVue.getFxcategorie());
		mapper.update(annonce.getFxrubrique(), annonceVue.getFxrubrique());
		mapper.update(annonce.getFxzone(), annonceVue.getFxzone());
	}

	@Override
	public void refresh() throws ExceptionAppli {
		actualiserListe();
	}

	@Override
	public void ValiderMiseAJour() throws ExceptionAppli {

		// Crée un objet contenant le données pour la mise à jour
		DtoAnnonce dto = mapper.map(annonceVue);

		// Effectue la mise à jour
		if (modeVue == CREER) {
			int id = serviceAnnonce.inserer(dto);
			annonceVue.setId(id);
			annonceCourant = mapper.update(annonceVue, new FXAnnonce());
			annonces.add(annonceCourant);
		}
		if (modeVue == MODIFIER) {
			serviceAnnonce.modifier(dto);
			mapper.update(annonceVue, annonceCourant);
		}
	}

	public void setMapper(IMapperDtoFX mapper) {
		this.mapper = mapper;
	}

	public void setServiceAnnonce(IServiceAnnonce serviceAnnonce) {
		this.serviceAnnonce = serviceAnnonce;
	}
}
