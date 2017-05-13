package contacts.javafx.model.standard;




import contacts.commun.dto.DtoZone;
import contacts.commun.service.IServiceZone;
import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXAnnonceur;
import contacts.javafx.fxb.FXZone;
import contacts.javafx.model.IModelZone;
import contacts.javafx.util.mapper.IMapperDtoFX;
import contacts.javafx.view.IManagerGui;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ModelZone implements IModelZone {

	// donnees observables

	private final ObservableList<FXZone> zones = FXCollections.observableArrayList(
			c ->  new Observable[] { c.nomProperty()  }
		);
	private final FXZone zoneVue= new FXZone(99, "XXXXX");

	private static FXZone zoneCourant = new FXZone();

	private IManagerGui managerGui;


	private IServiceZone serviceZone;

	private IMapperDtoFX mapper;

	public void actualiserListe() throws ExceptionAppli{
		zones.clear();
		for( DtoZone dto : serviceZone.listerTout() ) {
			FXZone zone = mapper.map( dto );
				zones.add(zone);
			}
		}
	@Override
	public ObservableList<FXZone> getZones() {
		// TODO Auto-generated method stub
		return zones;
	}

	@Override
	public void supprimer(FXZone zone) throws ExceptionAppli{
		serviceZone.supprimer(zone.getId());
		zones.remove(zone);
	}

	@Override
	public FXZone getZoneVue(){
		return zoneVue;
	}

	@Override
	public void preparerModifier( FXZone zone ){
		if(zone!=null){
		zoneCourant=zone;
//		copierDonnees(personne, personneVue);
		mapper.update(zone, zoneVue);
		}
		else{
			zoneCourant=new FXZone();
//			copierDonnees(personneCourant, personneVue);
			mapper.update(zoneCourant, zoneVue);
		}
	}

	@Override
	public void preparerModifier(){
		zoneCourant=new FXZone();
//		personnes.add(personneCourant);
//		copierDonnees(personneCourant, personneVue);
		mapper.update(zoneCourant, zoneVue);
	}

	@Override
	public void refresh() throws ExceptionAppli {
	 actualiserListe();
	}

	@Override
	public void ValiderMiseAJour( ) throws ExceptionAppli{
//		int dernierId;
		String message="";
		String Nom=zoneVue.getNom();

		if(Nom==null||Nom.length()==0){
			message+="Le nom de la zone ne doit pas etre vide.\n";
		}else if(Nom.length()>=25){
			message+="La longueur de la zone ne doit pas exceder 25 caracteres.\n";
		}

		if(message.length()==0){
			if(zoneVue.getId()==0){

			}

//			copierDonnees(personneVue, personneCourant);

//			if(!personnes.contains(personneCourant)){
			if(zoneVue.getId()==0){
				/*������Щ����Ϊ���ܹ���ȷ�ĸ���ID��ֵ�������tm��mapper�ķ������Զ���Ū����*/
//				if(personnes.size()!=0){
//				dernierId=personnes.get(personnes.size()-1).getId();
//				personneCourant.setId(dernierId+1);
//				}
//				else{
//					personneCourant.setId(1);
//				}
//				personnes.add(personneCourant);
//				DtoPersonne dtopersonne = mapper.map(personneCourant);
//				servicePersonne.inserer(dtopersonne);
				int id = serviceZone.inserer( mapper.map( zoneVue ) );
				zoneVue.setId( id );
				zones.add(zoneCourant);

			}else{
				serviceZone.modifier(mapper.map( zoneVue ));

			}
			mapper.update( mapper.map( serviceZone.retrouver( zoneVue.getId()) ), zoneVue );
			mapper.update(zoneVue, zoneCourant);
		}else{
/*AlertType.ERROR
AlertType.WARNING
AlertType.INFORMATION*/
//			Main.afficherMessage(message, AlertType.ERROR);
//			managerGui.afficherErreur( message );
			throw new ExceptionAppli(message) {
			};
		}

	}

	@Override
	public int numbreZone(FXZone zoneSelected) throws ExceptionAppli {
		int id = 0;

		for( FXZone zone : zones ){
			if(zone.getId()==zoneSelected.getId()){
				break;
			}
			id++;
		}
		return id;
	}

	public void setServiceZone(IServiceZone serviceZone) {
		this.serviceZone = serviceZone;
	}
	public void setMapper(IMapperDtoFX mapper) {
		this.mapper = mapper;
	}
	 }


