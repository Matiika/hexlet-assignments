package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] array) {
        MaxThread maxThread = new MaxThread(array);
        MinThread minThread = new MinThread(array);

        maxThread.start();
        minThread.start();

        try {
            maxThread.join();
            minThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Map<String, Integer> mapMaxMin = new HashMap<>();
        mapMaxMin.put("min", minThread.getMinNumber());
        mapMaxMin.put("max", maxThread.getMaxNumber());
        return mapMaxMin;
    }
    // END
}
