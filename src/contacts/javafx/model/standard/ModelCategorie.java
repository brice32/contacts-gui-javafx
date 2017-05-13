package contacts.javafx.model.standard;

import static contacts.javafx.model.EnumModeVue.CREER;
import static contacts.javafx.model.EnumModeVue.MODIFIER;

import java.util.Comparator;

import contacts.commun.dto.DtoAnnonceur;
import contacts.commun.dto.DtoCategorie;
import contacts.commun.service.IServiceAnnonceur;
import contacts.commun.service.IServiceCategorie;
import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXAnnonceur;
import contacts.javafx.fxb.FXCategorie;
import contacts.javafx.fxb.FXCompte;
import contacts.javafx.model.EnumModeVue;
import contacts.javafx.model.IModelCategorie;
import contacts.javafx.util.mapper.IMapperDtoFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelCategorie implements IModelCategorie {

	private final ObservableList<FXCategorie> categories = FXCollections.observableArrayList();
	// a ->new Observable[]{ a.nomProperty(), a.emailProperty()}
	private final FXCategorie categorieVue = new FXCategorie();

	private FXCategorie categorieCourant;

	private IServiceCategorie serviceCategorie;

	private IMapperDtoFX mapper;

	private EnumModeVue modeVue;


	@Override
	public void actualiserListe() throws ExceptionAppli {
		// TODO Auto-generated method stub
		categories.clear();
		for (DtoCategorie dto : serviceCategorie.listerTout()) {
			FXCategorie categorie = mapper.map(dto);
			categories.add(categorie);
		}
	}

	@Override
	public ObservableList<FXCategorie> getCategories() {
		// TODO Auto-generated method stub
		return categories;
	}

	@Override
	public void supprimer(FXCategorie categorie) throws ExceptionAppli {
		// TODO Auto-generated method stub
		serviceCategorie.supprimer(categorie.getIdCategorie());
		categories.remove(categorie);
	}

	@Override
	public FXCategorie getCategorieVue() {
		// TODO Auto-generated method stub
		return categorieVue;
	}

	@Override
	public void preparerModifier(FXCategorie categorie) {
		// TODO Auto-generated method stub
		modeVue = MODIFIER;
		categorieCourant = categorie;
		mapper.update(categorie, categorieVue);
	}

	@Override
	public void preparerAjouter() {
		// TODO Auto-generated method stub
		modeVue = CREER;
		mapper.update( new FXCategorie(), categorieVue );
	}

	@Override
	public void ValiderMiseAJour() throws ExceptionAppli {
		// TODO Auto-generated method stub
		// Crée un objet contenant le données pour la mise à jour
				DtoCategorie dto = mapper.map(categorieVue);

				// Effectue la mise à jour
				if (modeVue == CREER) {
					int id = serviceCategorie.inserer(dto);
					categorieVue.setIdCategorie(id);
					categorieCourant = mapper.update(categorieVue, new FXCategorie());
					categories.add(categorieCourant);
				}
				if (modeVue == MODIFIER) {
					serviceCategorie.modifier(dto);
					mapper.update(categorieVue, categorieCourant);
				}
				// Trie la liste
		        trierListe();
	}

	@Override
	public void refresh() throws ExceptionAppli {
		// TODO Auto-generated method stub
		actualiserListe();
	}

	public void setMapper(IMapperDtoFX mapper) {
		this.mapper = mapper;
	}

	public void setServiceCategorie(IServiceCategorie serviceCategorie) {
		this.serviceCategorie = serviceCategorie;
	}

	@Override
	public int numbreCategorie(FXCategorie categorieSelected) throws ExceptionAppli {
		int id = 0;

		for( FXCategorie categorie : categories ){
			if(categorie.getIdCategorie()==categorieSelected.getIdCategorie()){
				break;
			}
			id++;
		}
		return id;
	}

    // Méthodes auxiliaires

    private void trierListe() {
		FXCollections.sort( categories,
            (Comparator<FXCategorie>) ( c1, c2) -> {
                return ( c1.libelleProperty().get().toUpperCase().compareTo(c2.libelleProperty().get().toUpperCase()));
		});
    }

}
