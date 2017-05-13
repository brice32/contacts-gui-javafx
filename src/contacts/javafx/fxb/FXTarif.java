package contacts.javafx.fxb;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FXTarif {

	private final IntegerProperty id = new SimpleIntegerProperty();
	private final FloatProperty tarifConference = new SimpleFloatProperty();
	private final FloatProperty tarifRelief = new SimpleFloatProperty();
	private final FloatProperty tarifStage = new SimpleFloatProperty();
	private final StringProperty date = new SimpleStringProperty();
	public FXTarif(){
		
	}
	
	public FXTarif(int i,float f,float g,float h,String string){
		setId(i);
		settarifConference(f);
		settarifStage(g);
		settarifRelief(h);
	    SetDate(string);	
	} 
   
	public final IntegerProperty idProperty() {
		return this.id;
	}
	
	public final int getId(){
		return this.idProperty().get();
			}
	public final void setId(final int id){
		this.idProperty().set(id);
	}
	
   public final FloatProperty tarifConferenceProperty(){
	   return this.tarifConference;
   }
   public final float gettarifConference(){
	   return this.tarifConferenceProperty().get();
   }
   public void settarifConference(final float tarifConference){
	   this.tarifConferenceProperty().set(tarifConference);
   }
   
   public final FloatProperty tarifReliefProperty(){
	   return this.tarifRelief;
   }
   public final float gettarifRelief(){
	   return this.tarifReliefProperty().get();
	      }
   public void settarifRelief(final float tarifRelief){
	   this.tarifReliefProperty().set(tarifRelief);
   }
   public final  FloatProperty tarifStageProperty(){
	   return this.tarifStage;
   }
   public final float gettarifStage(){
	   return this.tarifStageProperty().get();
   }
   public void settarifStage(final float tarifStage){
	   this.tarifStageProperty().set(tarifStage);
   }
   public final StringProperty DateProperty(){
	   return this.DateProperty();
   }
   public final String getDate(){
	   return this.DateProperty().get();
   }
   public void SetDate(final String Date){
	   this.DateProperty().set(Date);
   }
	public String toString(){
		return gettarifConference() +" "+ gettarifRelief()+""+gettarifConference()+""+getDate();
	}
	
}
