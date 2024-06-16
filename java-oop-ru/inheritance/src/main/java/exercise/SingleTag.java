package exercise;

import java.util.Map;
import java.util.stream.Collectors;

// BEGIN
class SingleTag extends Tag {

    public SingleTag(String tagName, Map<String, String> attributes) {
        super(tagName, attributes);
    }

    @Override
    public String toString() {
        String attributesResult = attributes.isEmpty() ? "" :
                attributes.entrySet()
                        .stream()
                        .map(entry -> entry.getKey() + "=\"" + entry.getValue() + "\"")
                        .collect(Collectors.joining(" ", " ", ""));

        return "<" + tagName + attributesResult + ">";
    }
}
// END
