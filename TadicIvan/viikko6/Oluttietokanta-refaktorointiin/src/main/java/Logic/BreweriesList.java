
package Logic;

import java.util.List;
import java.util.Scanner;
import datapackage.DataMapper;
import olutopas.model.Brewery;

public class BreweriesList extends Komento {
    private Scanner scanner = new Scanner(System.in);
    DataMapper mapper;
    
    public BreweriesList(DataMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public void suorita() {
        List<Brewery> breweries = mapper.getlistOfBreweries();
        for (Brewery brewery : breweries) {
            System.out.println(brewery);
        }
        System.out.print("\npress enter to continue");
        scanner.nextLine();
    }
    
    
}
