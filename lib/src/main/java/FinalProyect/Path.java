package FinalProyect;

public enum Path {

    DATA("Github");
    public final static String PATHFILE= "/lib/src/main/resources/";

    private final String path;

    Path(String path) {
        this.path = path;
    }

    public String getPath() {
        return PATHFILE + path;
    }

    public String getGithub() {
        return PATHFILE + DATA.path + "/" + path + ".txt";
    }

}
