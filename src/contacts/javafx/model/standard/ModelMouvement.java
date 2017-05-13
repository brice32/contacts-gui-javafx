package contacts.javafx.model.standard;

import static contacts.javafx.model.EnumModeVue.CREER;
import static contacts.javafx.model.EnumModeVue.MODIFIER;

import contacts.commun.dto.DtoAnnonceur;
import contacts.commun.dto.DtoMouvement;
import contacts.commun.service.IServiceAnnonceur;
import contacts.commun.service.IServiceMouvement;
import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXAnnonceur;
import contacts.javafx.fxb.FXMouvement;
import contacts.javafx.model.EnumModeVue;
import contacts.javafx.model.IModelMouvement;
import contacts.javafx.util.mapper.IMapperDtoFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelMouvement implements IModelMouvement {

	private final ObservableList<FXMouvement> mouvements = FXCollections.observableArrayList();
	// a ->new Observable[]{ a.nomProperty(), a.emailProperty()}
	private final FXMouvement mouvementVue = new FXMouvement();

	private final FXAnnonceur annonceurVue = new FXAnnonceur();

	private FXMouvement mouvementCourant;

	private IServiceMouvement serviceMouvement;

	private IMapperDtoFX mapper;

	private static EnumModeVue modeVue;

	public void setMapper(IMapperDtoFX mapper) {
		this.mapper = mapper;
	}

	public void setServiceMouvement(IServiceMouvement serviceMouvement) {
		this.serviceMouvement = serviceMouvement;
	}

	@Override
	public void actualiserListe() throws ExceptionAppli {
		// TODO Auto-generated method stub
//		mouvements.clear();
//		for (DtoMouvement dto : serviceMouvement.listerTout()) {
//			FXMouvement mouvement = mapper.map(dto);
//			mouvements.add(mouvement);
//		}
		mouvements.clear();


	}

	@Override
	public FXMouvement getMouvementIdAnnonceur(int idAnnonceur) throws ExceptionAppli {

		FXMouvement fxmouvement = new FXMouvement();
		mapper.update(mapper.map(serviceMouvement.retrouverIdannonceur(idAnnonceur)), fxmouvement);
		return fxmouvement;

	}

	@Override
	public ObservableList<FXMouvement> getMouvements() {
		// TODO Auto-generated method stub
		return mouvements;
	}

	@Override
	public void supprimer(FXMouvement mouvement) throws ExceptionAppli {
		// TODO Auto-generated method stub
		serviceMouvement.supprimer(mouvement.getIdMouvement());
		mouvements.remove(mouvement);
	}

	@Override
	public FXMouvement getMouvementVue() {
		// TODO Auto-generated method stub
		return mouvementVue;
	}

	@Override
	public void preparerModifier(FXMouvement mouvement) {
		// TODO Auto-generated method stub
		modeVue = MODIFIER;
		mouvementCourant = mouvement;
		mapper.update(mouvement, mouvementVue);
	}

	@Override
	public void preparerAjouter() {
		// TODO Auto-generated method stub
		modeVue = CREER;
		mapper.update(new FXMouvement(), mouvementVue);
	}

	@Override
	public void ValiderMiseAJour() throws ExceptionAppli {
		// TODO Auto-generated method stub
		// Crée un objet contenant le données pour la mise à jour
//		mouvementVue.setSolde(serviceMouvement.retrouverIdannonceur(mouvementCourant.getAnnonceur().getId()).getSolde());
		DtoMouvement dto = mapper.map(mouvementVue);

		// Effectue la mise à jour
//		if (modeVue == CREER) {
//			int id = serviceMouvement.inserer(dto);
//			mouvementVue.setIdMouvement(id);
//			mouvementCourant = mapper.update(mouvementVue, new FXMouvement());
//			mouvements.add(mouvementCourant);
//		}
//		if (modeVue == MODIFIER) {
			serviceMouvement.inserer(dto);
//			mapper.update(mouvementVue, mouvementCourant);
//		}
	}

	@Override
	public void refresh() throws ExceptionAppli {
		// TODO Auto-generated method stub
		actualiserListe();
	}

	@Override
	public void mettreVueAnnonceur(FXAnnonceur annonceur){
		mapper.update(annonceur, mouvementVue.getAnnonceur());
	}

	@Override
	public void mettreVueSolde(float solde){
		mouvementVue.setSolde(solde);
	}

	@Override
	public void preparerAnnonceur(FXAnnonceur annonceur) {
		modeVue = MODIFIER;
		mapper.update( annonceur, annonceurVue );
		mapper.update(new FXMouvement(), mouvementVue);
	    mettreVueAnnonceur(annonceur);
	}

	@Override
	public boolean modeModifier(){
		if(modeVue == MODIFIER){
			return true;
		}
		return false;
	}

	@Override
	public FXAnnonceur getAnnonceur(){
		return annonceurVue;
	}

	@Override
	public void preparerListe(FXAnnonceur fxannonceur) throws ExceptionAppli{
		mettreVueAnnonceur(fxannonceur);
		retouveListe(fxannonceur);
	}

	private void retouveListe(FXAnnonceur fxAnnonceur) throws ExceptionAppli{
		int id = fxAnnonceur.getId();
		mouvements.clear();
		FXMouvement fxmouvement;
		for( DtoMouvement dtoMouvement : serviceMouvement.retouverListe(id)){
			fxmouvement = mapper.map(dtoMouvement);
			mapper.update(mapper.map(dtoMouvement.getAnnonceur()), fxmouvement.getAnnonceur());
			mouvements.add(fxmouvement);
		}
	}
}
