package model.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class User {

   
	 @Persistent
	 @PrimaryKey
	 private String key;
	 
    //Nombre del Usuario
    @Persistent
    private String name;

    @Persistent
    private boolean gender;

    public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}

	
    @Persistent
    private String email;
    
    @Persistent
    private String date;
    
    @Persistent
    private String role;

    @Persistent
    private boolean status;
    
    public User( String id, String name ,String role){
        this.key = id;
        this.name = name;
        this.status=true;
        this.email = id;
        this.role = role;
        DateFormat format = new SimpleDateFormat("HH:mm:ss dd/MM/yy");
        date = format.format(Calendar.getInstance().getTime());
    }
    public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public User(){
    	this.name="Panchito";
    	this.email="jhk@gjh";
    	this.role=null;
    }
        public String getId() {
        return this.key;
    }
    
    public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
	public void setId(String id) {
         this.key=id;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    //To String
    @Override
    public String toString() {
        return "User name: " + name + "\nUser role: " + role + "\n";
    }
}
