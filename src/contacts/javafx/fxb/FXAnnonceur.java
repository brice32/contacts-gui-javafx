package contacts.javafx.fxb;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FXAnnonceur {

	//Champs
	private final IntegerProperty	id			= new SimpleIntegerProperty();
	private final StringProperty	nom	     	= new SimpleStringProperty();
	private final StringProperty	telephone	= new SimpleStringProperty();
	private final StringProperty	email		= new SimpleStringProperty();
	private final StringProperty	lieuNom		= new SimpleStringProperty();
	private final StringProperty	lieuAdresse	= new SimpleStringProperty();
	private final StringProperty	lieuCp		= new SimpleStringProperty();
	private final StringProperty	lieuVille	= new SimpleStringProperty();
	private final StringProperty	siteWeb		= new SimpleStringProperty();

	//get & set
	public final IntegerProperty idProperty() {
		return this.id;
	}

	public final int getId() {
		return this.idProperty().get();
	}

	public final void setId(final int id) {
		this.idProperty().set(id);
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

	public final StringProperty telephoneProperty() {
		return this.telephone;
	}

	public final String getTelephone() {
		return this.telephoneProperty().get();
	}

	public final void setTelephone(final String telephone) {
		this.telephoneProperty().set(telephone);
	}

	public final StringProperty emailProperty() {
		return this.email;
	}

	public final String getEmail() {
		return this.emailProperty().get();
	}

	public final void setEmail(final String email) {
		this.emailProperty().set(email);
	}

	public final StringProperty lieuNomProperty() {
		return this.lieuNom;
	}

	public final String getLieuNom() {
		return this.lieuNomProperty().get();
	}

	public final void setLieuNom(final String lieuNom) {
		this.lieuNomProperty().set(lieuNom);
	}

	public final StringProperty lieuAdresseProperty() {
		return this.lieuAdresse;
	}

	public final String getLieuAdresse() {
		return this.lieuAdresseProperty().get();
	}

	public final void setLieuAdresse(final String lieuAdresse) {
		this.lieuAdresseProperty().set(lieuAdresse);
	}

	public final StringProperty lieuCpProperty() {
		return this.lieuCp;
	}

	public final String getLieuCp() {
		return this.lieuCpProperty().get();
	}

	public final void setLieuCp(final String lieuCp) {
		this.lieuCpProperty().set(lieuCp);
	}

	public final StringProperty lieuVilleProperty() {
		return this.lieuVille;
	}

	public final String getLieuVille() {
		return this.lieuVilleProperty().get();
	}

	public final void setLieuVille(final String lieuVille) {
		this.lieuVilleProperty().set(lieuVille);
	}

	public final StringProperty siteWebProperty() {
		return this.siteWeb;
	}

	public final String getSiteWeb() {
		return this.siteWebProperty().get();
	}

	public final void setSiteWeb(final String siteWeb) {
		this.siteWebProperty().set(siteWeb);
	}

	//Constructeur
	public FXAnnonceur(){

	}

	public FXAnnonceur(int id,String nom,String telephone,String email,String lieuNom,String lieuAdresse,String lieuCp,String lieuVille,String siteWeb){
		this.setId(id);
		this.setNom(nom);
		this.setTelephone(telephone);
		this.setEmail(email);
		this.setLieuNom(lieuNom);
		this.setLieuAdresse(lieuAdresse);
		this.setLieuCp(lieuCp);
		this.setLieuVille(lieuVille);
		this.setSiteWeb(siteWeb);
	}

}
