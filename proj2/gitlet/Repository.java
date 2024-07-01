package gitlet;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.*;
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
public class Repository implements Serializable {
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

//    public static String MASTER = "abc123";

    // <branch_name, commit hash >
    public TreeMap<String, String> BRANCHES = new TreeMap<>();
    public TreeMap<String, String> BRANCHES_ORIGIN = new TreeMap<>();

    public List<String> CWD_FILES ;

    public TreeMap<String, String> STAGING_ADD = new TreeMap<>();
    public TreeMap<String, String> STAGING_REMOVE = new TreeMap<>();

    // get strings from all the filenames in staging
    // iterate through the strings

//    public static Commit current_branch;

    /* TODO: fill in the rest of this class. */

    // This constructor is run just once to initialize the repository
    public Repository() {
//        super();
//        if BRANCHES size is zero
//        BRANCHES.put("master",null);
//        BRANCHES.put("current",null);
//        BRANCHES.put("HEAD",null);

        GITLET_DIR.mkdir();
        COMMITS_DIR.mkdir();
        STAGING_DIR.mkdir();
        BLOBS_DIR.mkdir();

        Commit c = new Commit();
        // generate commit hash

        // Set master branch
        BRANCHES_ORIGIN.put("master", c.generateId());
        BRANCHES.put("master", c.generateId());
        BRANCHES.put("current", "master");
        BRANCHES.put("HEAD", c.generateId());

        c.saveCommit();
        c = null;

        saveRepository();
    }


//    public void createRepository(){
//        new Repository();
//
//        createInitialCommit();
//
//    }

    public Commit createInitialCommit(){

        Commit c = new Commit();
        // generate commit hash

        // Set master branch
        BRANCHES_ORIGIN.put("master", c.generateId());
        BRANCHES.put("master", c.generateId());
        BRANCHES.put("current", "master");
        BRANCHES.put("HEAD", c.generateId());

        saveCommit(c);

        return c;

    }

    public void saveCommit(Commit c) {
        File write_file = join(Repository.COMMITS_DIR, "Commit_abc");
//        File write_file = join(Repository.COMMITS_DIR, this.generateId());
        writeObject(write_file, this);
    }

    // for commits after the initial commit
    public void createCommit(String message){

        Commit c = new Commit(message);
        // get commit hash and save commit

        c.parent = BRANCHES.get("HEAD");

        // fill in the tracked object

        // save the commit with hash name
        c.saveCommit();
    }

    public boolean hasBranches(){
        return BRANCHES.size() > 0;
    }

    // Save current repository to disk
    public void saveRepository() {
        File write_file = join(GITLET_DIR, "repository");
        writeObject(write_file, this);
    }

    public List<String> getListOfDirectoryFiles(File directory) {
        return plainFilenamesIn(directory);
    }

    public void updateBranch(String branch, String hash){
        this.BRANCHES.put(branch, hash);
    }


//    public Commit newCommit() {
//        return new Commit();
//    }

    public static Repository loadRepository() {
        File temp = join(GITLET_DIR, "repository");
        return readObject(temp, Repository.class);
    }

    // Writes the file to the staging folder
    public static void addFile(String filename) {

        File temp = join(CWD, filename);

        if (temp.exists() == false) {
            System.out.println("File does not exist.");
            System.exit(0);
        }

        byte[] read_in = readContents(temp);
        File write_file = join(STAGING_DIR, filename);

        writeContents(write_file, read_in);
    }

    public boolean isStagingEmpty(){
        List<String> temp = plainFilenamesIn(Repository.BLOBS_DIR);
        return temp.size()==0;
    }

}


//    public static void makeCommit(String message) {
//
//        Commit temp = new Commit();
//
//        if (BRANCHES == null) {
//            temp.ts = Instant.EPOCH;
//            temp.message = "initial commit";
//            temp.parent = null;
//        }
//        else {
//            temp.ts = Instant.now();
//            temp.message = message;
//        }

//        temp.commitFiles();
        //
//        String commit_hash = sha1(temp);
//
//        byte[] branch;
//
//        File file;
//
//        if (BRANCHES == null) {
//            BRANCHES.put("master", commit_hash);
//        }
//        BRANCHES.put("current_branch", commit_hash);
//
//        branch = serialize(BRANCHES);
//        file = join(COMMITS_DIR, "branches");
//        writeObject(file, branch);
            ;

            // serialize and save branches to disk


//        public void addCommit(String abc){
//
//            if (BRANCHES == null){
//                BRANCHES.put("master", null);
//                BRANCHES.put("current_branch", null);
//                return;
//            }
            // createCommit() with the files in staging





//        public void new addBranch(String key, String value){
//        BRANCHES.put()
//    }




