package FinalProyect;

import java.io.File;
import java.nio.file.Files;

public enum Path {

    DATA("Github");
    public final static String PATHFILE= "lib" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator;

    private final String path;

    Path(String path) {
        this.path = path;
    }

    public String getPath() {
        return PATHFILE + path;
    }

    public String getGithub() {
        return PATHFILE + DATA.path + File.separator + path + ".txt";
    }

}
