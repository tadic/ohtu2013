/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.Scanner;
import datapackage.DataMapper;
import olutopas.model.Beer;
import olutopas.model.Brewery;

/**
 *
 * @author ivantadic
 */
public class FindBrewery extends Komento{
    private Scanner scanner = new Scanner(System.in);
    DataMapper mapper;
    public FindBrewery(DataMapper mapper){
        this.mapper = mapper;
    }
    @Override
    public void suorita() {
                System.out.print("brewery to find: ");
        String n = scanner.nextLine();
        Brewery foundBrewery = mapper.brewerywithName(n);

        if (foundBrewery == null) {
            System.out.println(n + " not found");
            return;
        }

        System.out.println(foundBrewery);
        for (Beer bier : foundBrewery.getBeers()) {
            System.out.println("   " + bier.getName());
        }
        System.out.print("\npress enter to continue");
        scanner.nextLine();
    }
    
}
