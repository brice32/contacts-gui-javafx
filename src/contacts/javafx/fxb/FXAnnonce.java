package contacts.javafx.fxb;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FXAnnonce {

	//Champs
	private final IntegerProperty idAnnonce			= new SimpleIntegerProperty();
	private final StringProperty  titre			   	= new SimpleStringProperty();
	private final StringProperty  description		= new SimpleStringProperty();
	private final StringProperty  statute			= new SimpleStringProperty();
	private final JFXDatePicker dateDebut   	    = new JFXDatePicker();
	private final JFXDatePicker dateFin				= new JFXDatePicker();
	private final JFXTimePicker heureDebut			= new JFXTimePicker();
	private final JFXTimePicker heureFin			= new JFXTimePicker();
	private final StringProperty lieuNom			= new SimpleStringProperty();
	private final StringProperty lieuAdresse		= new SimpleStringProperty();
	private final StringProperty lieuCp				= new SimpleStringProperty();
	private final StringProperty lieuVille			= new SimpleStringProperty();
	private final StringProperty animateurNom		= new SimpleStringProperty();
	private final StringProperty animateurQualification		= new SimpleStringProperty();
	private final StringProperty organisateurNom			= new SimpleStringProperty();
	private final StringProperty organisateurSiteWeb		= new SimpleStringProperty();
	private final StringProperty organisateurEmail			= new SimpleStringProperty();
	private final StringProperty organisateurTelephone		= new SimpleStringProperty();

	private final FXAnnonceur	annonceur			= new FXAnnonceur();
	private final FXCategorie   categorie         = new FXCategorie();
	private final FXRubrique	rubrique			= new FXRubrique();
	private final FXZone		zone				= new FXZone();

	//get & set
	public final IntegerProperty idAnnonceProperty() {
		return this.idAnnonce;
	}

	public final int getIdAnnonce() {
		return this.idAnnonceProperty().get();
	}

	public final void setIdAnnonce(final int idAnnonce) {
		this.idAnnonceProperty().set(idAnnonce);
	}

	public final StringProperty titreProperty() {
		return this.titre;
	}

	public final String getTitre() {
		return this.titreProperty().get();
	}

	public final void setTitre(final String titre) {
		this.titreProperty().set(titre);
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

	public final StringProperty statuteProperty() {
		return this.statute;
	}

	public final String getStatute() {
		return this.statuteProperty().get();
	}

	public final void setStatute(final String statute) {
		this.statuteProperty().set(statute);
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

	public final StringProperty animateurNomProperty() {
		return this.animateurNom;
	}

	public final String getAnimateurNom() {
		return this.animateurNomProperty().get();
	}

	public final void setAnimateurNom(final String animateurNom) {
		this.animateurNomProperty().set(animateurNom);
	}

	public final StringProperty animateurQualificationProperty() {
		return this.animateurQualification;
	}

	public final String getAnimateurQualification() {
		return this.animateurQualificationProperty().get();
	}

	public final void setAnimateurQualification(final String animateurQualification) {
		this.animateurQualificationProperty().set(animateurQualification);
	}

	public final StringProperty organisateurNomProperty() {
		return this.organisateurNom;
	}

	public final String getOrganisateurNom() {
		return this.organisateurNomProperty().get();
	}

	public final void setOrganisateurNom(final String organisateurNom) {
		this.organisateurNomProperty().set(organisateurNom);
	}

	public final StringProperty organisateurSiteWebProperty() {
		return this.organisateurSiteWeb;
	}

	public final String getOrganisateurSiteWeb() {
		return this.organisateurSiteWebProperty().get();
	}

	public final void setOrganisateurSiteWeb(final String organisateurSiteWeb) {
		this.organisateurSiteWebProperty().set(organisateurSiteWeb);
	}

	public final StringProperty organisateurEmailProperty() {
		return this.organisateurEmail;
	}

	public final String getOrganisateurEmail() {
		return this.organisateurEmailProperty().get();
	}

	public final void setOrganisateurEmail(final String organisateurEmail) {
		this.organisateurEmailProperty().set(organisateurEmail);
	}

	public final StringProperty organisateurTelephoneProperty() {
		return this.organisateurTelephone;
	}

	public final String getOrganisateurTelephone() {
		return this.organisateurTelephoneProperty().get();
	}

	public final void setOrganisateurTelephone(final String organisateurTelephone) {
		this.organisateurTelephoneProperty().set(organisateurTelephone);
	}

	public final LocalDate getDateDebutLD() {
		return dateDebut.getValue();
	}

	public final void setDateDebutLD(LocalDate dateDebutLD) {
		dateDebut.setValue(dateDebutLD);
	}

	public final Date getDateDebut(){
//		LocalDate localdate = dateDebut.getValue();
////		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MMMM-dd");
////		String formattedString = localdate.format(formatter);
//		Date date = new Date(localdate.getYear(),localdate.getMonthValue(),localdate.getDayOfMonth());
//		return date;
		return java.sql.Date.valueOf(dateDebut.getValue());
	}

	public final void setDateDebut(final Date dateDebut2){
		LocalDate ld = new java.sql.Date(dateDebut2.getTime()).toLocalDate();
		dateDebut.setValue(ld);
	}

	public final JFXDatePicker getDateDebutJFX(){
		return dateDebut;
	}

	public final void setDateFin(final Date dateFin2) {
		LocalDate ld = new java.sql.Date(dateFin2.getTime()).toLocalDate();
		dateFin.setValue(ld);
	}

	public final LocalDate	getDateFinLD(){
		return dateFin.getValue();
	}

	public final void	setDateFinLD(LocalDate dateFinLD){
		dateFin.setValue(dateFinLD);
	}

	public final JFXDatePicker getDateFinJFX(){
		return dateFin;
	}

	public final Date getDateFin() {
		return java.sql.Date.valueOf(dateFin.getValue());
	}

	public final JFXTimePicker getHeureDebutJFX(){
		return heureDebut;
	}

	public final LocalTime getHeureDebutLD(){
		return heureDebut.getValue();
	}

	public final void setHeureDebutLD(LocalTime heureDebutLD){
		heureDebut.setValue(heureDebutLD);
	}

	public final void setHeureDebut(final Time heureDebut2){
		LocalTime lt = new java.sql.Time(heureDebut2.getHours(),heureDebut2.getMinutes(),heureDebut2.getSeconds()).toLocalTime();
		heureDebut.setValue(lt);
	}

	public final Time getHeureDebut(){
		return java.sql.Time.valueOf(heureDebut.getValue());
	}

	public final JFXTimePicker getHeureFinJFX(){
		return heureFin;
	}

	public final LocalTime	getHeureFinLD(){
		return	heureFin.getValue();
	}

	public final void	setHeureFinLD(LocalTime heureFinLD){
		heureFin.setValue(heureFinLD);
	}

	public final Time getHeureFin(){
		return java.sql.Time.valueOf(heureFin.getValue());
	}

	public void setHeureFin(Time heureFin2) {
		LocalTime lt = new java.sql.Time(heureFin2.getHours(),heureFin2.getMinutes(),heureFin2.getSeconds()).toLocalTime();
		heureFin.setValue(lt);
	}





	public FXAnnonceur getAnnonceur() {
		return annonceur;
	}

	public FXRubrique getRubrique() {
		return rubrique;
	}

	public FXZone getZone() {
		return zone;
	}

	public FXCategorie getCategorie() {
		return categorie;
	}





	//Constructeur
	public FXAnnonce(){

	}

	public FXAnnonce(int idAnnonce,String titre,String description,String statute,Date dateDebut,Date dateFin,Time heureDebut,Time heureFin,
			String lieuNom,String lieuAdresse,String lieuCp,String lieuVille,String animateurNom,String animateurQualification,String organisateurNom,
			String organisateurSiteWeb,String organisateurEmail,String organisateurTelephone){
		this.setIdAnnonce(idAnnonce);
		this.setTitre(titre);
		this.setDescription(description);
		this.setStatute(statute);
		this.setDateDebut(dateDebut);
		this.setDateFin(dateFin);
		this.setHeureDebut(heureDebut);
	    this.setHeureFin(heureFin);
		this.setLieuNom(lieuNom);
		this.setLieuAdresse(lieuAdresse);
		this.setLieuCp(lieuCp);
		this.setLieuVille(lieuVille);
		this.setAnimateurNom(animateurNom);
		this.setAnimateurQualification(animateurQualification);
		this.setOrganisateurNom(organisateurNom);
		this.setOrganisateurSiteWeb(organisateurSiteWeb);
		this.setOrganisateurEmail(organisateurEmail);
		this.setOrganisateurTelephone(organisateurTelephone);

	}




}
