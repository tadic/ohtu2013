
package olutopas.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Rating {
   @Id
   private Integer id;
   private int points;

      
    @ManyToOne
    private User user;;
    @ManyToOne
    private Beer beer;

    public Rating(Beer beer, User user, int points) {
        this.points = points;
        this.user = user;
        this.beer = beer;
    }

    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }
    
    public Rating() {
    }


    public Rating(int points) {
        this.points = points;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

 
}
