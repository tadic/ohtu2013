
package Logic;

import java.util.List;
import java.util.Scanner;
import datapackage.DataMapper;
import olutopas.model.Beer;


public class ListBeers extends Komento{
    private DataMapper mapper;
    private Scanner scanner = new Scanner(System.in);
    
    public ListBeers(DataMapper mapper){
        this.mapper = mapper;
    }
    @Override
    public void suorita() {
        List<Beer> beers = mapper.getlistOfBeers();
        for (Beer beer : beers) {
            System.out.println(beer);
            if (beer.getRatings() != null && !beer.getRatings().isEmpty()) {
                System.out.println("  ratings given "+beer.getRatings().size() + " average " + beer.averageRating());
            } else {
                System.out.println("  no ratings");
            }
        }
        System.out.print("\npress enter to continue");
        scanner.nextLine();
    }
    
}
