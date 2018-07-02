package model.entity;


import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@PersistenceCapable
public class Role {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key id;

    @Persistent
    private String name;

    @Persistent
    private String createDate;

    @Persistent
    private boolean status;

   

    public Role(String name, boolean status) {
        this.name = name;
        this.status = status;
        //this.accessesList = accessesList;
        DateFormat df = new SimpleDateFormat("HH:mm:ss dd/MM/yy");
        createDate = df.format(Calendar.getInstance().getTime());
    }

    //Getters y Setters
    public String getId() {
        return KeyFactory.keyToString(id);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

   

    @Override
    public String toString() {
        return "Role name: " + name +"\n";
    }

}
