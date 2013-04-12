
package olutopas.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
    @Id
    private int id;
    private String username;
    
    @OneToMany (cascade = CascadeType.ALL)
    private List<Rating> ratings;

    public User(String username) {
        this.username = username;
        ratings = new ArrayList<Rating>();
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
     public void addRating(Rating rating){
         ratings.add(rating);
     }
    
    @Override
    public String toString(){
        return this.username;
    }
}
