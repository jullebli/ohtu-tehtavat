package statistics;

import statistics.matcher.*;

public class Main {

    public static void main(String[] args) {

        Statistics stats = new Statistics(new PlayerReaderImpl("https://nhlstatisticsforohtu.herokuapp.com/players.txt"));

        QueryBuilder query3 = new QueryBuilder();
        QueryBuilder query4 = new QueryBuilder();
        QueryBuilder query5 = new QueryBuilder();

        Matcher matc = query3.oneOf(
                query4.playsIn("PHI")
                        .hasAtLeast(10, "assists")
                        .hasFewerThan(5, "goals").build(),
                query5.playsIn("EDM")
                        .hasAtLeast(40, "points").build()
        ).build();

        for (Player player : stats.matches(matc)) {
            System.out.println(player);
        } 
    }
}
