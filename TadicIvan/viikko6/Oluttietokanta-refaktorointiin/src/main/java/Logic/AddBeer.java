
package Logic;

import java.util.Scanner;
import datapackage.DataMapper;
import olutopas.model.Beer;
import olutopas.model.Brewery;


class AddBeer extends Komento {
private DataMapper mapper;
    private Scanner scanner = new Scanner(System.in);
    public AddBeer(DataMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void suorita() {
        System.out.print("to which brewery: ");
        String name = scanner.nextLine();
        Brewery brewery = mapper.brewerywithName(name);

        if (brewery == null) {
            System.out.println(name + " does not exist");
            return;
        }

        System.out.print("beer to add: ");

        name = scanner.nextLine();

        Beer exists = mapper.getBeerByName(name);
        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }

        brewery.addBeer(new Beer(name));
        mapper.saveBrewery(brewery);
        System.out.println(name + " added to " + brewery.getName());
        System.out.print("\npress enter to continue");
        scanner.nextLine();
    }
    
}
