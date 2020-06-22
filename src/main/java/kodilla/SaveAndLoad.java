//package kodilla;
//
//import com.google.gson.Gson;
//
//import java.io.*;
//import java.util.HashMap;
//import java.util.Map;
//import java.io.Serializable;
//
//public class SaveAndLoad implements Serializable {
//    File rankingPath = new File("ranking.txt");
//    Map<String, Long> ranking = new HashMap<>();
//    Gson gson = new Gson();
//    Human player = new Human();
//    Computer cpu = new Computer();
//
//    public void saveToRanking (Map<Player, File> map) throws IOException {
//        map.put(player, rankingPath);
//        map.put(cpu, rankingPath);
//        gson.toJson(map, new FileWriter(rankingPath));
//
//    }
//}
