/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datapackage;

import datapackage.DataMapper;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.Transaction;
import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.SQLitePlatform;
import java.util.List;
import olutopas.model.Beer;
import olutopas.model.Brewery;
import olutopas.model.Rating;
import olutopas.model.User;

/**
 *
 * @author ivantadic
 */
public class EbeanSQLDatamapper implements DataMapper{
    private final Class[] luokat;
    private final boolean dropAndCreate;
    private final String tietokantaUrl;
    private EbeanServer server;

    public EbeanSQLDatamapper(String tietokantaUrl ,boolean dropAndCreate, Class... luokat){
        this.luokat = luokat;
        this.dropAndCreate = dropAndCreate;
        this.tietokantaUrl = tietokantaUrl;
        init();
    }

    private void init() {
        ServerConfig config = new ServerConfig();
        config.setName("beerDb");

        DataSourceConfig sqLite = new DataSourceConfig();
        sqLite.setDriver("org.sqlite.JDBC");
        sqLite.setUsername("mluukkai");
        sqLite.setPassword("mluukkai");
        sqLite.setUrl(tietokantaUrl);
        config.setDataSourceConfig(sqLite);
        config.setDatabasePlatform(new SQLitePlatform());
        config.getDataSourceConfig().setIsolationLevel(Transaction.READ_UNCOMMITTED);
        

        config.setDefaultServer(false);
        config.setRegister(false);

        for (Class luokka : luokat) {
            config.addClass(luokka);
        }             

        if (dropAndCreate) {
            config.setDdlGenerate(true);
            config.setDdlRun(true);
            //config.setDebugSql(true);
        }
        server = EbeanServerFactory.create(config);
        //return EbeanServerFactory.create(config);

    }
    
    @Override
    public Brewery brewerywithName(String n) {
        return server.find(Brewery.class).where().like("name", n).findUnique();
    }

    @Override
    public List<Brewery> getlistOfBreweries() {
        return server.find(Brewery.class).findList();
    }

    @Override
    public List<Beer> getlistOfBeers() {
        return server.find(Beer.class).orderBy("brewery.name").findList();
    }

    public EbeanServer getServer() {
        return server;
    }

    @Override
    public Beer getBeerByName(String name) {
        return  server.find(Beer.class).where().like("name", name).findUnique();
    }

    @Override
    public List<User> getUsersList() {
        return server.find(User.class).findList();
    }

    @Override
    public void saveBrewery(Brewery brewery) {
        server.save(brewery);
    }

    @Override
    public void saveRating(Rating rating) {
        server.save(rating);
    }

    @Override
    public User getUserByName(String name) {
         return server.find(User.class).where().like("name", name).findUnique();
    }
}
