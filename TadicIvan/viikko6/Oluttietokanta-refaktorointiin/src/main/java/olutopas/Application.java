package olutopas;

import Logic.Komento;
import datapackage.EbeanSQLDatamapper;
import datapackage.DataMapper;
import Logic.KomentoTehdas;
import com.avaje.ebean.EbeanServer;
import java.util.List;
import java.util.Scanner;
import javax.persistence.OptimisticLockException;
import olutopas.model.Beer;
import olutopas.model.Brewery;
import olutopas.model.Rating;
import olutopas.model.User;

public class Application {

    private EbeanServer server;
    private Scanner scanner = new Scanner(System.in);
    private User user;
    private final KomentoTehdas komennot;

    public Application(DataMapper mapper) {
        this.server = ((EbeanSQLDatamapper)mapper).getServer();
        this.komennot = new KomentoTehdas(mapper);
    }

    public void run(boolean newDatabase) {
        if (newDatabase) {
            seedDatabase();
        }

        login();
        komennot.addUserAndInitialize(user);
        System.out.println("\nWelcome to Ratebeer " + user.getName());

        while (true) {
            menu();
            System.out.print("> ");
            String command = scanner.nextLine();
            Komento komento = komennot.hae(command);
            if ( komento!=null) {
                komento.suorita();
                continue;
            }
            
            System.out.println("unknown command");
            
            System.out.print("\npress enter to continue");
            scanner.nextLine();
        }

    }

    private void menu() {
        System.out.println("");
        System.out.println("1   find brewery");
        System.out.println("2   find/rate beer");
        System.out.println("3   add beer");
        System.out.println("4   list breweries");
        System.out.println("6   list beers");
        System.out.println("8   add brewery");
        System.out.println("9   show my ratings");
        System.out.println("0   list users");
        System.out.println("q   quit");
        System.out.println("");
    }

    private void seedDatabase() throws OptimisticLockException {
        Brewery brewery = new Brewery("Schlenkerla");
        brewery.addBeer(new Beer("Urbock"));
        brewery.addBeer(new Beer("Lager"));
        server.save(brewery);
        // luodaan olut ilman panimon asettamista
        Beer b = new Beer("Märzen");
        server.save(b);
        // jotta saamme panimon asetettua, tulee olot lukea uudelleen kannasta
        b = server.find(Beer.class, b.getId());
        brewery = server.find(Brewery.class, brewery.getId());
        brewery.addBeer(b);
        server.save(brewery);
        server.save(new Brewery("Paulaner"));
        server.save(new User("mluukkai"));
    }


    private void login() {
        while (true) {
            System.out.println("\nLogin (give ? to register a new user)\n");

            System.out.print("username: ");
            String name = scanner.nextLine();

            if (name.equals("?")) {
                registerUser();
                continue;
            }

            user = server.find(User.class).where().like("name", name).findUnique();

            if (user != null) {
                break;
            }
            System.out.println("unknown user");
        }
    }

    private void registerUser() {
        System.out.println("Register a new user");
        System.out.print("give username: ");
        String name = scanner.nextLine();
        User u = server.find(User.class).where().like("name", name).findUnique();
        if (u != null) {
            System.out.println("user already exists!");
            return;
        }
        server.save(new User(name));
        System.out.println("user created!\n");
    }

    private void addRating(Beer foundBeer, int value) {
        Rating rating = new Rating(foundBeer, user, value);
        server.save(rating);
        System.out.println(rating.toString());
    }
    public User getCuttenrUser(){
        return user;
    }
}
