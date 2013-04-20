package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
public class StaticsticsTest {

    Statistics stats;
    PlayerReader pr;

    @Before
    public void setUp() {
        PlayerReader pr  = mock(PlayerReader.class);
        ArrayList<Player> playersL = new ArrayList<Player>();
            playersL.add(new Player("Semenko", "EDM", 4, 12));
            playersL.add(new Player("Lemieux", "PIT", 45, 54));
            playersL.add(new Player("Kurri", "EDM", 37, 53));
            playersL.add(new Player("Yzerman", "DET", 42, 56));
            playersL.add(new Player("Gretzky", "EDM", 35, 89));
       when(pr.getPlayers()).thenReturn(playersL);
        stats = new Statistics(pr);
    }
    @Test
    public void playerFound() {
        Player p = stats.search("Lemieux");
        assertEquals("PIT", p.getTeam());
        assertEquals(45, p.getGoals());
        assertEquals(54, p.getAssists());
        assertEquals(45 + 54, p.getPoints());

    }
    
    @Test
    public void teamMembersFound(){
        List<Player> players = stats.team("EDM");
        assertEquals(3, players.size());
        for (Player player : players) {
            assertEquals("EDM", player.getTeam());
        }
    }
    
    @Test
    public void topScorersFound(){
        List<Player> players = stats.topScorers(2);
        assertEquals(3, players.size());
        assertEquals("Gretzky", players.get(0).getName());
        assertEquals("Lemieux", players.get(1).getName());
    }
}
