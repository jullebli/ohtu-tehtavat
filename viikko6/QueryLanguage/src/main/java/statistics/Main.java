package statistics;

import statistics.matcher.*;

public class Main {

    public static void main(String[] args) {

        Statistics stats = new Statistics(new PlayerReaderImpl("https://nhlstatisticsforohtu.herokuapp.com/players.txt"));

        QueryBuilder query = new QueryBuilder();

        Matcher m = query.playsIn("NYR").build();

        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }

        System.out.println("");

        QueryBuilder query1 = new QueryBuilder();

        Matcher ma = query1.playsIn("NYR")
                .hasAtLeast(5, "goals")
                .hasFewerThan(10, "goals").build();

        for (Player player : stats.matches(ma)) {
            System.out.println(player);
        }
    }
}