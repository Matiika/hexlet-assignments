package exercise;

// BEGIN
class LabelTag implements TagInterface {

    private String label;
    private TagInterface component;

    public LabelTag(String label, TagInterface component) {
        this.label = label;
        this.component = component;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public TagInterface getComponent() {
        return component;
    }

    public void setComponent(TagInterface component) {
        this.component = component;
    }

    @Override
    public String render() {
        return "<label>" + label + component.render() + "</label>";
    }
}
// END
