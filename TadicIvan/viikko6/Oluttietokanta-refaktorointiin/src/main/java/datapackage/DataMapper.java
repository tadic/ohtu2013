/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datapackage;

import java.util.List;
import olutopas.model.Beer;
import olutopas.model.Brewery;
import olutopas.model.Rating;
import olutopas.model.User;

/**
 *
 * @author ivantadic
 */
public interface DataMapper {
    public Brewery brewerywithName(String n);
    public List<Brewery> getlistOfBreweries();
    public List<Beer> getlistOfBeers();
    public Beer getBeerByName(String name);

    public List<User> getUsersList();

    public void saveBrewery(Brewery brewery);

    public void saveRating(Rating rating);

    public User getUserByName(String name);

}
