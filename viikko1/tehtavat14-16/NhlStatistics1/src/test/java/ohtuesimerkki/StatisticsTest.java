package ohtuesimerkki;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void searchReturnsNull() {
        assertEquals(null, stats.search("Karalahti"));
    }

    @Test
    public void searchReturnsPlayer() {
        assertEquals("Semenko", stats.search("nko").getName());
    }

    @Test
    public void teamReturnsPlayers() {
        List<String> expected = Arrays.asList("Semenko", "Kurri", "Gretzky");
        List<String> actual = stats.team("EDM").stream().map(p -> p.getName()).collect(Collectors.toList());
        assertEquals(expected, actual);
    }

    @Test
    public void topScorersReturnsRightNames() {
        List<String> expected = Arrays.asList("Gretzky", "Lemieux", "Yzerman");
        List<String> actual = stats.topScorers(3).stream().map(p -> p.getName()).collect(Collectors.toList());
        assertEquals(expected, actual);
    }

    @Test
    public void toScorerTest() {
        assertEquals(5, stats.topScorers(8).size());
    }
}
