package contacts.javafx.model.standard;

import contacts.commun.dto.DtoRubrique;
import contacts.commun.dto.DtoZone;
import contacts.commun.service.IServiceRubrique;
import contacts.commun.service.IServiceZone;
import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXAnnonceur;
import contacts.javafx.fxb.FXRubrique;
import contacts.javafx.model.IModelRubrique;
import contacts.javafx.util.mapper.IMapperDtoFX;
import contacts.javafx.view.IManagerGui;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelRubrique implements IModelRubrique {

	// donnees observables

		private final ObservableList<FXRubrique> rubriques = FXCollections.observableArrayList(
				c ->  new Observable[] { c.nomProperty()  }
			);
		private final FXRubrique rubriqueVue= new FXRubrique(99, "XXXXX");

		private static FXRubrique rubriqueCourant = new FXRubrique();

		private IManagerGui managerGui;


		private IServiceRubrique serviceRubrique;

		private IMapperDtoFX mapper;

		public void actualiserListe() throws ExceptionAppli{
			rubriques.clear();
			for( DtoRubrique dto : serviceRubrique.listerTout() ) {
				FXRubrique rubrique = mapper.map( dto );
					rubriques.add(rubrique);
				}
			}
		@Override
		public ObservableList<FXRubrique> getRubriques() {
			// TODO Auto-generated method stub
			return rubriques;
		}

		@Override
		public void supprimer(FXRubrique rubrique) throws ExceptionAppli{
			serviceRubrique.supprimer(rubrique.getId());
			rubriques.remove(rubrique);
		}

		@Override
		public FXRubrique getRubriqueVue(){
			return rubriqueVue;
		}

		@Override
		public void preparerModifier( FXRubrique rubrique ){
			if(rubrique!=null){
			rubriqueCourant=rubrique;
//			copierDonnees(personne, personneVue);
			mapper.update(rubrique, rubriqueVue);
			}
			else{
				rubriqueCourant=new FXRubrique();
//				copierDonnees(personneCourant, personneVue);
				mapper.update(rubriqueCourant, rubriqueVue);
			}
		}

		@Override
		public void preparerModifier(){
			rubriqueCourant=new FXRubrique();
//			personnes.add(personneCourant);
//			copierDonnees(personneCourant, personneVue);
			mapper.update(rubriqueCourant, rubriqueVue);
		}

		@Override
		public void refresh() throws ExceptionAppli {
		 actualiserListe();
		}

		@Override
		public void ValiderMiseAJour( ) throws ExceptionAppli{
//			int dernierId;
			String message="";
			String Nom=rubriqueVue.getNom();

			if(Nom==null||Nom.length()==0){
				message+="Le nom de la zone ne doit pas etre vide.\n";
			}else if(Nom.length()>=25){
				message+="La longueur de la zone ne doit pas exceder 25 caracteres.\n";
			}

			if(message.length()==0){
				if(rubriqueVue.getId()==0){

				}

//				copierDonnees(personneVue, personneCourant);

//				if(!personnes.contains(personneCourant)){
				if(rubriqueVue.getId()==0){
					/*������Щ����Ϊ���ܹ���ȷ�ĸ���ID��ֵ�������tm��mapper�ķ������Զ���Ū����*/
//					if(personnes.size()!=0){
//					dernierId=personnes.get(personnes.size()-1).getId();
//					personneCourant.setId(dernierId+1);
//					}
//					else{
//						personneCourant.setId(1);
//					}
//					personnes.add(personneCourant);
//					DtoPersonne dtopersonne = mapper.map(personneCourant);
//					servicePersonne.inserer(dtopersonne);
					int id = serviceRubrique.inserer( mapper.map( rubriqueVue ) );
					rubriqueVue.setId( id );
					rubriques.add(rubriqueCourant);

				}else{
					serviceRubrique.modifier(mapper.map( rubriqueVue ));

				}
				mapper.update( mapper.map( serviceRubrique.retrouver( rubriqueVue.getId()) ), rubriqueVue );
				mapper.update(rubriqueVue, rubriqueCourant);
			}else{
	/*AlertType.ERROR
	AlertType.WARNING
	AlertType.INFORMATION*/
//				Main.afficherMessage(message, AlertType.ERROR);
//				managerGui.afficherErreur( message );
				throw new ExceptionAppli(message) {
				};
			}

		}

		@Override
		public int numbreRubrique(FXRubrique rubriqueSelected) throws ExceptionAppli {
			int id = 0;

			for( FXRubrique rubrique : rubriques ){
				if(rubrique.getId()==rubriqueSelected.getId()){
					break;
				}
				id++;
			}
			return id;
		}

		public void setServiceRubrique(IServiceRubrique serviceRubrique) {
			this.serviceRubrique = serviceRubrique;
		}
		public void setMapper(IMapperDtoFX mapper) {
			this.mapper = mapper;
		}
		}
