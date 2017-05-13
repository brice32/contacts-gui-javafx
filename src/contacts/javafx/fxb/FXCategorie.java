package contacts.javafx.fxb;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FXCategorie {

	//Champs
	private final IntegerProperty	idCategorie			= new SimpleIntegerProperty();
	private final StringProperty	libelle	     	    = new SimpleStringProperty();

	//get & set


	//Constructeur
	public FXCategorie(){

	}

	public FXCategorie(int idCategorie,String libelle){
		this.setIdCategorie(idCategorie);
		this.setLibelle(libelle);
	}

	public final IntegerProperty idCategorieProperty() {
		return this.idCategorie;
	}


	public final int getIdCategorie() {
		return this.idCategorieProperty().get();
	}


	public final void setIdCategorie(final int idCategorie) {
		this.idCategorieProperty().set(idCategorie);
	}


	public final StringProperty libelleProperty() {
		return this.libelle;
	}


	public final String getLibelle() {
		return this.libelleProperty().get();
	}


	public final void setLibelle(final String libelle) {
		this.libelleProperty().set(libelle);
	}


}
