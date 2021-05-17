package api.press.model;

public enum PostType {
    Sports("sports"),
    Politics("politics"),
    Cinema("cinema");

    public final String label;
    private PostType(String label){
        this.label = label;
    }
}
