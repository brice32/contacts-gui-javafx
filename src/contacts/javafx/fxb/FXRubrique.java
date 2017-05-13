package contacts.javafx.fxb;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FXRubrique {
	private final IntegerProperty id = new SimpleIntegerProperty();
	private final StringProperty nom = new SimpleStringProperty();
	private final ObservableList<FXRubrique> rubriques = FXCollections.observableArrayList();

	public FXRubrique(){
		
	}
	
	public FXRubrique(int id,String nom){
		setId(id);
		setNom(nom);
	}


	public final StringProperty nomProperty() {
		return this.nom;
	}
	


	public final String getNom() {
		return this.nomProperty().get();
	}
	


	public final void setNom(final String nom) {
		this.nomProperty().set(nom);
	}


	public final IntegerProperty idProperty() {
		return this.id;
	}
	


	public final int getId() {
		return this.idProperty().get();
	}
	


	public final void setId(final int id) {
		this.idProperty().set(id);
	}

	@Override
	public String toString() {
		return getNom();
		
	}
	
	


	
	
}
