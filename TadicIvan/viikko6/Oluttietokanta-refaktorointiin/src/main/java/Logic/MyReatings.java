/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.Scanner;
import datapackage.DataMapper;
import olutopas.model.Rating;
import olutopas.model.User;

/**
 *
 * @author ivantadic
 */
class MyReatings extends Komento {
    private Scanner scanner = new Scanner(System.in);
    private DataMapper mapper;
    private User user;
    
    public MyReatings(DataMapper mapper, User user) {
        this.mapper = mapper;
        this.user = user;
    }

    @Override
    public void suorita() {
        user = mapper.getUserByName(user.getName());
        
        System.out.println("Ratings by " + user.getName());
        for (Rating rating : user.getRatings()) {
            System.out.println(rating);
        }
        System.out.print("\npress enter to continue");
        scanner.nextLine();
    }
    
}
