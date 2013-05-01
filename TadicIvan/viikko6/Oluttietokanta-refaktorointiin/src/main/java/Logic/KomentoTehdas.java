
package Logic;

import Logic.FindBeer;
import Logic.FindBrewery;
import Logic.ListBeers;
import java.util.HashMap;
import datapackage.DataMapper;
import olutopas.model.User;

/**
 *
 * @author ivantadic
 */
public class KomentoTehdas {
    HashMap<String, Komento> mappi;
    DataMapper mapper;
    User user;
    public KomentoTehdas(DataMapper mapper){
        this.mapper = mapper;
        
    }
    
    private void initialise(){
        mappi = new HashMap<String, Komento>();
        mappi.put("6", new ListBeers(mapper));
        mappi.put("2", new FindBeer(mapper, user));
        mappi.put("3", new AddBeer(mapper));
        mappi.put("8", new AddBrewery(mapper));
        mappi.put("9", new MyReatings(mapper, user));
        mappi.put("1", new FindBrewery(mapper));
        mappi.put("4", new BreweriesList(mapper));
        mappi.put("0", new UsersList(mapper));
        mappi.put("q", new Quit(mapper));
    }
    public Komento hae(String command){
        return mappi.get(command);
    }
    public void addUserAndInitialize(User user){
        this.user = user;
        initialise();
    }
}
