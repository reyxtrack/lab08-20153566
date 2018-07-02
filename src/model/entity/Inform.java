package model.entity;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.KeyFactory;
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Inform {

 @PrimaryKey
 @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Key id;

 
 @Persistent private String rol;
 @Persistent private String inform;
 public String getRol() {
	return rol;
}

public void setRol(String rol) {
	this.rol = rol;
}
@Persistent private String type;
 @Persistent private Date fecha;
 @Persistent private String user;
 @Persistent private Boolean status;
 @Persistent private String email;

public String getName() {
	return user;
}

public void setName(String user) {
	this.user = user;
}

public Boolean getStatus() {
	return status;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public void setStatus(Boolean status) {
	this.status = status;
}

public void setFecha(Date fecha) {
	this.fecha = fecha;
}

public Inform( String user, String role, String email, String type, String inform) {
	this.user=user;
	this.inform = inform;
	this.email=email;
	this.type = type;
	this.rol=role;
	this.fecha = new Date();

}

public  String convertir(Date date){
	
Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
return formatter.format(date);
	
}


/**
 * @return the id
 */
 public String getId() { 

	 return KeyFactory.keyToString(id);
 }
 /**
 * @param id the id to set
 */
 public void setId(Key id) {
 this.id = id;
 }
 /**
 * @return the name
 */
 public String getInform() {
 return inform;
 }

 public void setInform(String inform) {
 this.inform = inform;
 }
 public String getType(){
	return this.type;
 }
 public void setType(String type){
	 this.type=type;
 }
 public String getFecha(){
		return this.fecha.toString();
 }
} 
