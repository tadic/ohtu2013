/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.playerReader;

import ohtuesimerkki.Reader;
import java.util.ArrayList;
import java.util.List;
import ohtuesimerkki.Player;
import ohtuesimerkki.Statistics;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ivantadic
 */
public class StatisticsTest {
    
   Statistics stats;
   Reader readerStub = new Reader() {
        @Override
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
   
   @Before
   public void setUp(){
       stats = new Statistics(readerStub);
   }
   @Test
   public void testSearchExists() {
       Player instance = readerStub.getPlayers().get(1);
       Player result = stats.search(instance.getName());
       assertTrue(comparePlayers(instance, result));
   }
   @Test
   public void testNotExists() {
       Player result = stats.search("Ivan");
       assertNull(result);
   }
   @Test
   public void testTeam(){
       Player instance = new Player("Lemieux", "PIT", 45, 54);
       List<Player> pList = stats.team("PIT");
       assertEquals(1, pList.size());
       assertTrue(comparePlayers(instance, pList.get(0)));
       
   }
   @Test
   public void testTopScores(){
       List<Player> resultList = stats.topScorers(2);
       ArrayList<Player> expectedList = new ArrayList<Player>();
       expectedList.add(new Player("Gretzky", "EDM", 35, 89));
       expectedList.add(new Player("Lemieux", "PIT", 45, 54));
       for (int i=0; i<2; i++){
           assertTrue(comparePlayers(expectedList.get(i), resultList.get(i)));
       }
   }
   
   private boolean comparePlayers(Player p1, Player p2){
       boolean result = true;
       result = result && (p1.getName().equals(p2.getName()));
       result = result && (p1.getTeam().equals(p2.getTeam()));
       result = result && (p1.getGoals() == p2.getGoals());
       result = result && (p1.getAssists() == p2.getAssists());
       return result;
   }
}
