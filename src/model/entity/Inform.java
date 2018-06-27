package model.entity;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Inform {

 @PrimaryKey
 @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long id;

 @Persistent private String inform;
 @Persistent private String type;
 @Persistent private String rol;
 @Persistent private String gender;
 @Persistent private Date fecha;
 @Persistent private String name;
 
 public Inform( String type, String inform, String rol, String gender, String name) {
	this.inform = inform;
	this.type = type;
	this.rol = rol;
	this.gender = gender;
	this.name = name;
	this.fecha = new Date();

}

public String getRol() {
	return rol;
}

public void setRol(String rol) {
	this.rol = rol;
}

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public  String convertir(Date date){
	
Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
return formatter.format(date);
	
}


/**
 * @return the id
 */
 public Long getId() { 

 return id;
 }
 /**
 * @param id the id to set
 */
 public void setId(Long id) {
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
