package sec.project.domain;

import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Signup extends AbstractPersistable<Long> {

    private int uid;
    private String name;
    private String address;
    private String username;
    private String password;
    private String website;

    public Signup() {
        super();
    }

    public Signup(String name, String address, String username, String password, String website) {
        this();
        this.name = name;
        this.address = address;
        this.username = username;
        this.password = password;
        this.website = website;
    }
    
    public Signup(int uid, String name, String address, String website) {
        this();
        this.uid = uid;
        this.name = name;
        this.address = address;
        this.username = username;
        this.password = password;
        this.website = website;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

}
