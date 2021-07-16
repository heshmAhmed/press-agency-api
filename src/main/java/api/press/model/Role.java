package api.press.model;

public enum Role {
    ADMIN("admin"),
    VIEWER("viewer"),
    EDITOR("editor");
    private final String label;

    Role(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
