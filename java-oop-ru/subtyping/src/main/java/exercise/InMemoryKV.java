package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
class InMemoryKV implements KeyValueStorage {

    private Map<String, String> mapString = new HashMap<>();

    InMemoryKV(Map<String, String> mapString) {
        this.mapString.putAll(mapString);
    }

    @Override
    public void set(String key, String value) {
        mapString.put(key, value);
    }

    @Override
    public void unset(String key) {
        mapString.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        return mapString.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(mapString);
    }
}

// END
