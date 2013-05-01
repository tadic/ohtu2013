/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.List;
import java.util.Scanner;
import datapackage.DataMapper;
import olutopas.model.User;

/**
 *
 * @author ivantadic
 */
class UsersList extends Komento {
    private DataMapper mapper;
    private Scanner scanner = new Scanner(System.in);
    
    public UsersList(DataMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void suorita() {
        List<User> users = mapper.getUsersList();
        for (User user : users) {
            System.out.println(user.getName() + " " + user.getRatings().size() + " ratings");
        }
        System.out.print("\npress enter to continue");
        scanner.nextLine();
    }
    
}
