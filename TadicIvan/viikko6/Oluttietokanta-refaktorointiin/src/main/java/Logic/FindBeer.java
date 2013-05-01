/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.Scanner;
import datapackage.DataMapper;
import olutopas.model.Beer;
import olutopas.model.Rating;
import olutopas.model.User;

/**
 *
 * @author ivantadic
 */
class FindBeer extends Komento {
    private Scanner scanner = new Scanner(System.in);
    DataMapper mapper;
    private User user;
    
    public FindBeer(DataMapper mapper, User user) {
        this.mapper = mapper;
        this.user = user;
    }

    @Override
    public void suorita() {
        System.out.print("beer to find: ");
        String n = scanner.nextLine();
        Beer foundBeer = mapper.getBeerByName(n);

        if (foundBeer == null) {
            System.out.println(n + " not found");
            return;
        }

        System.out.println(foundBeer);

        if (foundBeer.getRatings() != null && !foundBeer.getRatings().isEmpty()) {
            System.out.println("  number of ratings: "+ foundBeer.getRatings().size() + " average " + foundBeer.averageRating());
        } else {
            System.out.println("no ratings");
        }

        System.out.print("give rating (leave emtpy if not): ");
        try {
            int rating = Integer.parseInt(scanner.nextLine());
            addRating(foundBeer, rating);
        } catch (Exception e) {
        }
        System.out.print("\npress enter to continue");
        scanner.nextLine();
    }
    private void addRating(Beer foundBeer, int value) {
        Rating rating = new Rating(foundBeer, user, value);
        mapper.saveRating(rating);
        System.out.println(rating.toString());
    }
    
}
