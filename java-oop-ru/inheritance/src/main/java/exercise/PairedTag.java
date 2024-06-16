package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
class PairedTag extends Tag {

    private String label;
    private List<Tag> child;

    public PairedTag(String tagName, Map<String, String> attributes, String label, List<Tag> child) {
        super(tagName, attributes);
        this.label = label;
        this.child = child;
    }

    @Override
    public String toString() {
        String childs = child.isEmpty() ? "" :
                child.stream()
                .map(Tag::toString)
                .collect(Collectors.joining());

        String attributesResult = attributes.isEmpty() ? "" :
                attributes.entrySet()
                        .stream()
                        .map(entry -> entry.getKey() + "=\"" + entry.getValue() + "\"")
                        .collect(Collectors.joining(" ", " ", ""));

        return "<" + tagName + attributesResult + ">" + label + childs + "</" + tagName + ">";
    }
}
// END
