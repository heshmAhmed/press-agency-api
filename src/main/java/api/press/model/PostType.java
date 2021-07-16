package api.press.model;

public enum PostType {
    POLITICS("politics"),
    SPORTS("sports"),
    CINEMA("cinema");
    private final String label;

    PostType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
