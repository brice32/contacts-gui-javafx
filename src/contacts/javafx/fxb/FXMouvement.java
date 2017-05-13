package contacts.javafx.fxb;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FXMouvement {

	//Champs
	private final IntegerProperty	idMouvement			= new SimpleIntegerProperty();
//	private final IntegerProperty	idAnnonceur			= new SimpleIntegerProperty();
	private final FloatProperty     montant             = new SimpleFloatProperty();
	private final StringProperty    date                = new SimpleStringProperty();
	private final StringProperty    heure               = new SimpleStringProperty();
	private final FloatProperty     solde               = new SimpleFloatProperty();
	private final StringProperty    libelle             = new SimpleStringProperty();
	private final StringProperty    description         = new SimpleStringProperty();
	private final FXAnnonceur annonceur = new FXAnnonceur();

	//get & set


	//Constructeur
	public FXMouvement(){

	}

	public FXMouvement(int idMouvement, float montant, String date, String heure, float solde, String lebelle, String description ){
		this.setIdMouvement(idMouvement);
//		this.setIdAnnonceur(idAnnonceur);
		this.setMontant(montant);
		this.setDate(date);
		this.setHeure(heure);
		this.setSolde(solde);
		this.setLibelle(lebelle);
		this.setDescription(description);
	}

	public final IntegerProperty idMouvementProperty() {
		return this.idMouvement;
	}


	public final int getIdMouvement() {
		return this.idMouvementProperty().get();
	}


	public final void setIdMouvement(final int idMouvement) {
		this.idMouvementProperty().set(idMouvement);
	}


//	public final IntegerProperty idAnnonceurProperty() {
//		return this.idAnnonceur;
//	}
//
//
//	public final int getIdAnnonceur() {
//		return this.idAnnonceurProperty().get();
//	}
//
//
//	public final void setIdAnnonceur(final int idAnnonceur) {
//		this.idAnnonceurProperty().set(idAnnonceur);
//	}


	public final FloatProperty montantProperty() {
		return this.montant;
	}


	public final float getMontant() {
		return this.montantProperty().get();
	}


	public final void setMontant(final float montant) {
		this.montantProperty().set(montant);
	}


	public final StringProperty dateProperty() {
		return this.date;
	}


	public final String getDate() {
//		Date sqlDate=new Date(0);
//		try {
//			String date = new String(this.dateProperty().get());
//			DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());
//			java.util.Date dateUtil = df.parse(date);
//			sqlDate = new java.sql.Date(dateUtil.getTime());
//
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return sqlDate;
		return dateProperty().get();
	}


	public final void setDate(final String date) {
//		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.FRANCE);
//		this.dateProperty().set(df.format(date));
		this.dateProperty().set(date);
	}


	public final StringProperty heureProperty() {
		return this.heure;
	}


	public final String getHeure() {
		 return heureProperty().get();

	}


	public final void setHeure(final String heure) {
//		this.heureProperty().set(heure.toString());
		this.heureProperty().set(heure);
	}


	public final FloatProperty soldeProperty() {
		return this.solde;
	}


	public final float getSolde() {
		return this.soldeProperty().get();
	}


	public final void setSolde(final float solde) {
		this.soldeProperty().set(solde);
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


	public final StringProperty descriptionProperty() {
		return this.description;
	}


	public final String getDescription() {
		return this.descriptionProperty().get();
	}


	public final void setDescription(final String description) {
		this.descriptionProperty().set(description);
	}

	public FXAnnonceur getAnnonceur() {
		return annonceur;
	}

}
