package ohtu;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        System.out.println("json-muotoinen data:");
        System.out.println(bodyText);

        Gson mapper = new Gson();
        List<Player> players = mapper.fromJson(bodyText, new TypeToken<List<Player>>() {}.getType());
        List<Player> sortedPlayers = players.stream()
               // .filter(s -> s.getNationality().equals("FIN"))
                .filter(Main::finnishFilter)
                .sorted(Comparator.comparingInt(Player::getPoints).reversed())
                .collect(Collectors.toList());

        System.out.println("Oliot:");
        for (Player player : sortedPlayers) {
                System.out.println(player);
        }
    }
    
    public static boolean finnishFilter(Player player) {
        return player.getNationality().equals("FIN");
    }
}
