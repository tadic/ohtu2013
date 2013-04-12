package olutopas;

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
    User user;

    public Application(EbeanServer server) {
        this.server = server;
    }

    public void startApp(boolean newDatabase){
        if (newDatabase) {
            seedDatabase();
        }

        do {
            System.out.println("Login (give ? to register a new user)");
            System.out.print("username: ");
            String username = scanner.nextLine();
            if (username.equals("?")){
                createNewUser();
            } else if ((user = findUserByName(username))!=null){
                run();
            } else {
                System.out.println("User does not exists!");
            }
        } while (true);
    }
    
    public void run() {
        System.out.println("Welcome " + user.getUsername() + "!");

        while (true) {
            menu();
            System.out.print("> ");
            String command = scanner.nextLine();

            if (command.equals("0")) {
                break;
            } else if (command.equals("1")) {
                findBrewery();
            } else if (command.equals("2")) {
                findBeer();
            } else if (command.equals("3")) {
                addBeer();
            } else if (command.equals("4")) {
                listBreweries();
            } else if (command.equals("5")) {
                deleteBeer();
            } else if (command.equals("y")) {
                listUsers();
            } else if (command.equals("6")) {
                listBeers();
            } else if (command.equals("7")) {
                addBrewery();
            } else if (command.equals("8")) {
                deleteBrewery();
            } else if (command.equals("t")) {
                showMyRatings();
            } else {
                System.out.println("unknown command");
            }

            System.out.print("\npress enter to continue");
            scanner.nextLine();
        }

        System.out.println("bye");
        System.exit(0);
    }

    private void menu() {
        System.out.println("");
        System.out.println("1   find brewery");
        System.out.println("2   find beer");
        System.out.println("3   add beer");
        System.out.println("4   list breweries");
        System.out.println("5   delete beer");
        System.out.println("6   list beers");
        System.out.println("7   add brewery");
        System.out.println("8   delete brewery");
        System.out.println("y   list users");
        System.out.println("t   show my ratings");
        System.out.println("0   quit");
        System.out.println("");
    }

    // jos kanta on luotu uudelleen, suoritetaan tämä ja laitetaan kantaan hiukan dataa
    private void seedDatabase() throws OptimisticLockException {
        Brewery brewery = new Brewery("Schlenkerla");
        brewery.addBeer(new Beer("Urbock"));
        brewery.addBeer(new Beer("Lager"));
              
        Beer b = new Beer("Märzen");
        server.save(b);
        
        User user1 = new User("Ivan");
        User user2 = new User("Pekka");
        User user3 = new User("Luka");
        server.save(user1);
        server.save(user2);
            
        Rating rating = new Rating(b, user1, 4);

        server.save(rating);

        // tallettaa myös luodut oluet, sillä Brewery:n OneToMany-mappingiin on määritelty
        // CascadeType.all
        server.save(brewery);

        // luodaan olut ilman panimon asettamista
   
        // jotta saamme panimon asetettua, tulee olot lukea uudelleen kannasta
        b = server.find(Beer.class, b.getId());        
        brewery = server.find(Brewery.class, brewery.getId());        
        brewery.addBeer(b);
        server.save(brewery);
        
        server.save(new Brewery("Paulaner"));
    }

    private void findBeer() {
        System.out.print("beer to find: ");
        String n = scanner.nextLine();
        Beer foundBeer = server.find(Beer.class).where().like("name", n).findUnique();

        if (foundBeer == null) {
            System.out.println(n + " not found");
            return;
        }
        System.out.println("found: " + foundBeer);
        printRatingsForBreer(foundBeer);
        System.out.println("    Not avalible currently!");
        
        setRatingForBeer(foundBeer);
    }
    private void printRatingsForBreer(Beer beer){
        List <Rating> ratings = beer.getRatings();//server.find(Rating.class).where().like("beer_id", beer.getId().toString()).findList();
        if (ratings==null){
            System.out.println("    No ratings for now...");
        } else {
            System.out.print("    number of ratings: " + ratings.size());
        }
        System.out.println(" average grade: " + getAvarageGrade(ratings));
    }

    private void findBrewery() {
        System.out.print("brewery to find: ");
        String n = scanner.nextLine();
        Brewery foundBrewery = server.find(Brewery.class).where().like("name", n).findUnique();

        if (foundBrewery == null) {
            System.out.println(n + " not found");
            return;
        }

        System.out.println(foundBrewery);
        for (Beer bier : foundBrewery.getBeers()) {
            System.out.println("   " + bier.getName());
        }
    }

    private void listBreweries() {
        List<Brewery> breweries = server.find(Brewery.class).order().asc("name").findList();
        for (Brewery brewery : breweries) {
            System.out.println(brewery);
        }
    }

    private void addBeer() {
        System.out.print("to which brewery: ");
        String name = scanner.nextLine();
        Brewery brewery = server.find(Brewery.class).where().like("name", name).findUnique();

        if (brewery == null) {
            System.out.println(name + " does not exist");
            return;
        }

        System.out.print("beer to add: ");

        name = scanner.nextLine();

        Beer exists = server.find(Beer.class).where().like("name", name).findUnique();
        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }

        brewery.addBeer(new Beer(name));
        server.save(brewery);
        System.out.println(name + " added to " + brewery.getName());
    }

    private void deleteBeer() {
        System.out.print("beer to delete: ");
        String n = scanner.nextLine();
        Beer beerToDelete = server.find(Beer.class).where().like("name", n).findUnique();

        if (beerToDelete == null) {
            System.out.println(n + " not found");
            return;
        }

        server.delete(beerToDelete);
        System.out.println("deleted: " + beerToDelete);

    }

    private void listUsers() {
        List<User> users = server.find(User.class).findList();
        for (User  us: users) {
            System.out.println(us.toString());
        }
    }

    private void listBeers() {
         List<Beer> beers = server.find(Beer.class).order().asc("name").findList();
        for (Beer  beer: beers) {
            System.out.println(beer.toString());
            printRatingsForBreer(beer);
        }
    }

    private void addBrewery() {
        System.out.print("brewery to add: ");

        String name = scanner.nextLine();

        Brewery exists = server.find(Brewery.class).where().like("name", name).findUnique();
        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }

        server.save(new Brewery(name));
        System.out.println(name + " added to db");
    }

    private void deleteBrewery() {
        System.out.print("Brewery to delete: ");
        String n = scanner.nextLine();
        Brewery brewToDelete = server.find(Brewery.class).where().like("name", n).findUnique();

        if (brewToDelete == null) {
            System.out.println(n + " not found");
            return;
        }

        server.delete(brewToDelete);
        System.out.println("deleted: " + brewToDelete);
    }

    private void createNewUser() {
        System.out.print("Give username: ");
        String name = scanner.nextLine();

        User exists = server.find(User.class).where().like("username", name).findUnique();
        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }
        server.save(new User(name));
        System.out.println(name + " added to Users...");
    }

    private User findUserByName(String name) {
        return server.find(User.class).where().like("username", name).findUnique();
    }

    private double getAvarageGrade(List<Rating> ratings) {
        double d = 0.0;
        for (Rating r:ratings){
            d+= r.getPoints();
        }
        return d/(ratings.size());
    }

    private void setRatingForBeer(Beer beer) {
        System.out.print("give rating (leave emtpy if not): ");
        String r = scanner.nextLine();
        int rating = 0;
        if (r.length()==0){
            return;
        } else {
            rating = Integer.parseInt(r);
        }
        server.save(new Rating(beer, user, rating));
        System.out.println(beer.getName() + " is rated by " + user.getUsername() + " with: " + rating);
    }

    private void showMyRatings() {
        System.out.println("Ratings by " + user.getUsername());
        List <Rating> ratings = server.find(Rating.class).where().like("user_id", Integer.toString(user.getId())).findList();
        if (ratings==null || ratings.isEmpty()){
            System.out.println("    No ratings for now...");
            return;
        }
        for (Rating r:ratings){
            System.out.println(r.getBeer().toString() + " " + r.getPoints() + " points");
        }
    }
}
