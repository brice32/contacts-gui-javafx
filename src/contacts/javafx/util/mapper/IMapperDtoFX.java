package contacts.javafx.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import contacts.commun.dto.DtoAnnonce;
import contacts.commun.dto.DtoAnnonceur;
import contacts.commun.dto.DtoCategorie;
import contacts.commun.dto.DtoCompte;
import contacts.commun.dto.DtoMouvement;
import contacts.commun.dto.DtoPersonne;
import contacts.commun.dto.DtoRubrique;
import contacts.commun.dto.DtoTarif;
import contacts.commun.dto.DtoTelephone;
import contacts.commun.dto.DtoZone;
import contacts.javafx.fxb.FXAnnonce;
import contacts.javafx.fxb.FXAnnonceur;
import contacts.javafx.fxb.FXCategorie;
import contacts.javafx.fxb.FXCompte;
import contacts.javafx.fxb.FXMouvement;
import contacts.javafx.fxb.FXPersonne;
import contacts.javafx.fxb.FXRubrique;
import contacts.javafx.fxb.FXTarif;
import contacts.javafx.fxb.FXTelephone;
import contacts.javafx.fxb.FXZone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


@Mapper( uses=IMapperDtoFX.FactoryObsservableList.class  )
public interface IMapperDtoFX {

	IMapperDtoFX INSTANCE = Mappers.getMapper( IMapperDtoFX.class );


	// Compte

	FXCompte update( FXCompte source, @MappingTarget FXCompte target );

	FXCompte map( DtoCompte source );

	DtoCompte map( FXCompte source );

	FXPersonne map( DtoPersonne source);

	DtoPersonne map( FXPersonne source );

	FXTelephone map( DtoTelephone source );

	DtoTelephone map( FXTelephone source);

	FXAnnonceur map( DtoAnnonceur source );

	DtoAnnonceur map( FXAnnonceur source );

	FXCategorie map( DtoCategorie source );

	DtoCategorie map ( FXCategorie source);

	FXMouvement map( DtoMouvement source );

	DtoMouvement map( FXMouvement source );

	FXAnnonceur update( FXAnnonceur source, @MappingTarget FXAnnonceur cible);

	FXPersonne update( FXPersonne source, @MappingTarget FXPersonne cible );

	FXCategorie update( FXCategorie source, @MappingTarget FXCategorie cible );

	FXMouvement update( FXMouvement source, @MappingTarget FXMouvement cible );

	FXTelephone duplicate( FXTelephone source );

	ObservableList<FXTelephone> duplicate( ObservableList<FXTelephone> source );

	FXAnnonceur duplicate1( FXAnnonceur source );
//
	ObservableList<FXAnnonceur> duplicate1( ObservableList<FXAnnonceur> source );
    // Classe auxiliaire

	FXZone update( FXZone source, @MappingTarget FXZone cible);

	FXZone map( DtoZone source );

	DtoZone map( FXZone source );

	FXRubrique update( FXRubrique source, @MappingTarget FXRubrique cible);

	FXRubrique map( DtoRubrique source );

	DtoRubrique map( FXRubrique source );

	FXTarif map( DtoTarif source );

	FXTarif update( FXTarif source, @MappingTarget FXTarif cible);

	DtoTarif map( FXTarif source);

	FXAnnonce map( DtoAnnonce source );

	DtoAnnonce map( FXAnnonce source );

	FXAnnonce update (FXAnnonce source, @MappingTarget FXAnnonce cible );

    public static class FactoryObsservableList {

    	ObservableList<String> createObsListString() {
    		return FXCollections.observableArrayList();
    	}

    	ObservableList<FXTelephone> createObsListFXTelephone() {
    		 return FXCollections.observableArrayList();
    	}

    	ObservableList<FXAnnonceur> createObsListFXAnnonceur() {
   		 return FXCollections.observableArrayList();
   	}
    }

}