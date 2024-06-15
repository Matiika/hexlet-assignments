package exercise;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
class App {
    public static void swapKeyValue(KeyValueStorage map) {
        var originalMap = map.toMap();
        for (var key : originalMap.keySet()) {
            var valueHolder = map.get(key, "default");
            map.unset(key);
            map.set(valueHolder, key);
        }
    }
}
// END
