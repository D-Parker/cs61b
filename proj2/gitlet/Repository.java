package gitlet;

import java.io.File;
import java.io.IOException;

import static gitlet.Utils.*;

// TODO: any imports you need here

/** Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Repository {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");

    public static final File STAGING_DIR = join(GITLET_DIR, "staging");

    public String current_branch = "master";

    /* TODO: fill in the rest of this class. */
    public Repository(){
    }

    public static void init(){
        Repository temp = Repository();
        CWD.mkdir();
        GITLET_DIR.mkdir();
        STAGING_DIR.mkdir();
    }

//    public static void setupPersistence() {
//    }

    // write the file to the staging folder
    public static void addFile(String filename){

        File temp = join(CWD, filename);

        byte[] input = readContents(temp);
        File write_file = join(STAGING_DIR, filename);

        writeContents(write_file, input);
    }
}
