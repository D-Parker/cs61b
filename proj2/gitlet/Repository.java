package gitlet;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.IOException;
import java.time.Instant;
import java.util.TreeMap;
import java.io.Serializable;
import static gitlet.Utils.*;

// TODO: any imports you need here

/**
 * Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class

 *  does at a high level.
 *  A repository stores references to staging files and commits.
 *
 *
 * @author TODO
 */
public class Repository implements Serializable{
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /**
     * The current working directory.
     */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /**
     * The .gitlet directory.
     */
    public static final File GITLET_DIR = join(CWD, ".gitlet");

    public static final File STAGING_DIR = join(GITLET_DIR, "staging");

    public static final File BLOBS_DIR = join(GITLET_DIR, "blobs");

    public static final File COMMITS_DIR = join(GITLET_DIR, "commits");

    public static String MASTER;

    // <branch_name, commit hash >
    public static TreeMap<String, String> BRANCHES;

    public static void addCommit() {
    }

//    public static Commit current_branch;

    /* TODO: fill in the rest of this class. */
//    public Repository(){
//    }

    public void init() {
        COMMITS_DIR.mkdir();
        GITLET_DIR.mkdir();
        STAGING_DIR.mkdir();
        BLOBS_DIR.mkdir();

        saveRepository();
    }

//    public static void setupPersistence() {
//    }

    public void saveRepository(){
        byte[] temp = serialize(this);

        File write_file = join(GITLET_DIR, "repository");

        writeContents(write_file, temp);
    }

    public static Repository loadRepository(){

        File temp = join(GITLET_DIR, "repository");

        byte[] abc = readContents(temp);

        Repository result = readObject(abc, Repository);
        return result;
    }

    // Writes the file to the staging folder
    public void addFile(String filename) {

        File temp = join(CWD, filename);

        if (temp.exists() == false) {
            System.out.println("File does not exist.");
            System.exit(0);
        }

        byte[] read_in = readContents(temp);
        File write_file = join(STAGING_DIR, filename);

        writeContents(write_file, read_in);
    }

    public static void makeCommit(String message) {

        Commit temp = new Commit();

        if (BRANCHES == null) {
            temp.ts = Instant.EPOCH;
            temp.message = "initial commit";
            temp.parent = null;
        }
        else {
            temp.ts = Instant.now();
            temp.message = message;
        }

//        temp.commitFiles();
        //
        String commit_hash = sha1(temp);

        byte[] branch;

        File file;

        if (BRANCHES == null) {
            BRANCHES.put("master", commit_hash);
        }
        BRANCHES.put("current_branch", commit_hash);

        branch = serialize(BRANCHES);
        file = join(COMMITS_DIR, "branches");
        writeObject(file, branch);
            ;

            // serialize and save branches to disk
        }

        public void addCommit(String abc){

            if (BRANCHES == null){
                BRANCHES.put("master", null);
                BRANCHES.put("current_branch", null);
                return;
            }
            // createCommit() with the files in staging



        }

//        public void new addBranch(String key, String value){
//        BRANCHES.put()
//    }

    }


