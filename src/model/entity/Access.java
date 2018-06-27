package model.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Access {
  
  
  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Key key;
 
  @Persistent
  private String rol;
  
  @Persistent
  private String resource;
 
  //Estado del recurso
  @Persistent
  private boolean status;
 
 //Fecha de creacion del acceso
  @Persistent
  private String fecha;
  
  //Constructor 
  public Access(String rol,String resource){
    this.resource=resource;
    this.rol=rol;
    DateFormat format = new SimpleDateFormat("HH:mm:ss dd/MM/yy");
    fecha = format.format(Calendar.getInstance().getTime());
  }
  
  public String getDate(){
	  return fecha;
  }
  
  public String getKey() {
     return KeyFactory.keyToString(key);
  }
  
  public void setStatus(boolean status){
    this.status=status;
  }
  public boolean getStatus(){
    return this.status;
   }
  
  public String getRol() {
	return rol;
}

public void setRol(String rol) {
	this.rol = rol;
}

public String getResource() {
	return resource;
}

public void setResource(String resource) {
	this.resource = resource;
}

public String toString(){
    return "Acceso de "+ rol +"a "+ resource;
  }


}
