package olutopas.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

    private String username;

    
    @OneToMany (cascade = CascadeType.ALL)
    private List<Rating> ratings;
    
    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
    
    public User() {
    }

    public User(String name) {
        this.username = name;
    }
    
    @Id
    private Integer id;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
}
