package exercise;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jshell.execution.Util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// BEGIN
class FileKV implements KeyValueStorage {

    private String pathToFile;
    private Map<String, String> mapString = new HashMap<>();

    FileKV(String pathToFile, Map<String, String> mapString) {
        this.pathToFile = pathToFile;
        this.mapString.putAll(mapString);
        Utils.writeFile(pathToFile, Utils.serialize(mapString));
    }

    @Override
    public void set(String key, String value) {
        var map = Utils.unserialize(Utils.readFile(pathToFile));
        map.put(key, value);
        Utils.writeFile(pathToFile, Utils.serialize(map));
    }

    @Override
    public void unset(String key) {
        var map = Utils.unserialize(Utils.readFile(pathToFile));
        map.remove(key);
        Utils.writeFile(pathToFile, Utils.serialize(map));
    }

    @Override
    public String get(String key, String defaultValue) {
        var map = Utils.unserialize(Utils.readFile(pathToFile));
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(Utils.unserialize(Utils.readFile(pathToFile)));
    }
}
// END
