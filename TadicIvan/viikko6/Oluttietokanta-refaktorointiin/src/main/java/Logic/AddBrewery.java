/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.Scanner;
import datapackage.DataMapper;
import olutopas.model.Brewery;


/**
 *
 * @author ivantadic
 */
class AddBrewery extends Komento {
    private Scanner scanner = new Scanner(System.in);
    private DataMapper mapper;
    public AddBrewery(DataMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void suorita() {
        System.out.print("brewery to add: ");
        String name = scanner.nextLine();
        Brewery brewery = mapper.brewerywithName(name);

        if (brewery != null) {
            System.out.println(name + " already exists!");
            return;
        }

        mapper.saveBrewery(new Brewery(name));
        System.out.print("\npress enter to continue");
        scanner.nextLine();
    }
    
    
}
