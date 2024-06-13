package exercise;

import exercise.Home;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// BEGIN
class App {
    public static List<String> buildApartmentsList(List<Home> homes, int count) {
        if (homes.size() < count || count == 0) {
            return new ArrayList<String>();
        }
        List<String> topByArea = new ArrayList<>();
        Collections.sort(homes);
        for (int i = 0; i < count; i++) {
            topByArea.add(homes.get(i).toString());
        }
        return topByArea;
    }
}
// END
