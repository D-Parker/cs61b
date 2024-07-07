package gitlet;


import java.io.*;

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
    public static final File CWD_DIR = join(CWD);

    public static final File GITLET_DIR = join(CWD_DIR, ".gitlet");

    public static final File STAGING_DIR = join(GITLET_DIR, "staging");

    public static final File STAGING_REMOVAL_DIR = join(GITLET_DIR, "staging_removal");


//    public static final File STAGING_DIR = GITLET_DIR;

//    public static final File STAGING_DIR =  BUFFER_DIR;

    public static final File BLOBS_DIR = join(GITLET_DIR, "blobs");

    public static final File COMMITS_DIR = join(GITLET_DIR, "commits");

//    public static String MASTER = "abc123";

    // <branch_name, commit hash >
    public TreeMap<String, String> BRANCHES = new TreeMap<>();
    public TreeMap<String, String> BRANCHES_ORIGIN = new TreeMap<>();

    public List<String> CWD_FILES;

    public TreeMap<String, String> STAGING_ADD = new TreeMap<>();
    public TreeMap<String, String> STAGING_REMOVE = new TreeMap<>();

    public TreeMap<String, String> BLOBS = new TreeMap<>();

    // get strings from all the filenames in staging
    // iterate through the strings

//    public static Commit current_branch;

    /* TODO: fill in the rest of this class. */

    // This constructor is run just once to initialize the repository
    public Repository() {
        super();

        GITLET_DIR.mkdir();
        STAGING_DIR.mkdir();
        STAGING_REMOVAL_DIR.mkdir();
        COMMITS_DIR.mkdir();
        BLOBS_DIR.mkdir();

        Commit c = new Commit();
        // generate commit hash

        // Set master branch
        BRANCHES_ORIGIN.put("master", c.generateInitialId());
        BRANCHES.put("master", c.generateInitialId());
        BRANCHES.put("current_branch", "master");
        BRANCHES.put("HEAD", c.generateInitialId());

        c.saveInitialCommit();

        saveRepository();
    }

    public void removeFile(String file) {

        File f;
        Commit c;
        String head_hash;
        byte[] f_object;
        // case: file is staged for addition
        // remove file from staging folder
        if (getStagingFiles().contains(file)) {
            f = getFileFromString(STAGING_DIR, file);
            f.delete();
            return;
        }
        // case: file was tracked in most recent commit
        // Stage for removal and remove file from working directory
        head_hash = BRANCHES.get("HEAD");
        c = Commit.loadCommit(head_hash);

        f = getFileFromString(CWD_DIR, file);
        if (c.tracked.containsKey(file)) {
            addFileToDirectory(STAGING_REMOVAL_DIR, file);
            f.delete();
            return;
        }
        System.out.println("No reason to remove the file.");
        return;
        }


    // Create commits after the initial one.
    public void createCommit(String message) {

        if (message==null){
            System.out.println("Please enter a commit message.");
            return;
        }

        if (getStagingFiles()==null){
            System.out.println("No changes added to the commit");
            return;
        }


        Commit c = new Commit(message);
        c.parent = BRANCHES.get("HEAD");
        c.second_parent = BRANCHES.get("HEAD");

        c.tracked = new TreeMap<>();

        Commit prev = Commit.loadCommit(c.parent);

        if (prev.tracked != null) {
            c.tracked.putAll(prev.tracked);
        }

        List<String> staging = getStagingFiles();
        List<String> staging_removal = getStagingRemovalFiles();

        // Populate tracking map
        //        write all files from Staging_add folder to blobs folder, update tracking for this commit
        for (String file : staging) {
            File staging_file = join(STAGING_DIR, file);
            String hash = getFileHash(staging_file);
            c.tracked.put(file, hash);
            File blob_file = join(BLOBS_DIR, hash);
            writeContents(blob_file, readContents(staging_file));
            staging_file.delete();
        }
        // remove files in the staging_removal folder from the tracked tree in the current commit
        for (String file : staging_removal) {
            File staging_removal_file = join(STAGING_REMOVAL_DIR, file);
            c.tracked.remove(file);
            staging_removal_file.delete();
        }

        c.saveCommit();

        String hash = c.generateId();
        String current_branch = BRANCHES.get("current_branch");
        BRANCHES.put(current_branch, hash);
        BRANCHES.put("HEAD", hash);

        saveRepository();
    }

    public void printLog() {
        String curr = BRANCHES.get("HEAD");
        while (curr != null) {
            curr = printLog(curr);
        }
    }

    public void printGlobalLog(){
        List<String> L = getListOfDirectoryFiles(COMMITS_DIR);
        for (String s: L ){
            printLog(s);
        }
    }

    public void find(String msg){
        List<String> L = getListOfDirectoryFiles(COMMITS_DIR);
        Commit c;
        for (String curr: L ){
            c = Commit.loadCommit(curr);
            if (c.message == msg){
                System.out.println(curr);
            }
        }
    }

    private String printLog(String curr){
        Commit c = Commit.loadCommit(curr);
        System.out.println("===");
        System.out.println("commit " + curr);
        System.out.println("Date: " + c.getTimestamp());
        System.out.println(c.message);
        System.out.println();
        return c.parent;
    }

    public void status(){
        System.out.println("=== Branches ===");
        String m = BRANCHES.get("master");
        List<String> STG = getListOfDirectoryFiles(STAGING_DIR);
        Collections.sort(STG);
        List<String> REM = getListOfDirectoryFiles(STAGING_REMOVAL_DIR);
        Collections.sort(STG);

        String temp;

        for (String key: BRANCHES.keySet()){
            temp = BRANCHES.get(key);
            if (temp == m){
                System.out.println("*" + key);
            } else {
                System.out.println(key);
            }
        }
        System.out.println();
        System.out.println("=== Staged Files ===");
        for (String a: STG){
            System.out.println(a);
        }
        System.out.println();
        System.out.println("=== Removed Files ===");
        for (String b: REM){
            System.out.println(b);
        }
        System.out.println();
    }


    public File getFileFromString(File dir, String file) {
        File result = join(dir, file);
        if (result.exists()) {
            return result;
        } else return result;
    }

    public String getFileHash(File file) {
        byte[] temp = readContents(file);
        return getHash(temp);
    }

    public String getHash(Serializable obj) {
        return sha1(obj);
    }

    public List<String> getStagingFiles() {
        return plainFilenamesIn(Repository.STAGING_DIR);
    }

    public List<String> getStagingRemovalFiles() {
        return plainFilenamesIn(Repository.STAGING_REMOVAL_DIR);
    }

    public void saveCommit(Commit c) {
        File write_file = join(Repository.COMMITS_DIR, "Commit_abc");
//        File write_file = join(Repository.COMMITS_DIR, this.generateId());
        writeObject(write_file, this);
    }

    // for commits after the initial commit
//    public void createCommit(String message){
//
//        Commit c = new Commit(message);
//        // get commit hash and save commit
//
//        c.parent = BRANCHES.get("HEAD");
//
//        // fill in the tracked object
//
//        // save the commit with hash name
//        c.saveCommit();
//    }

    public boolean hasBranches() {
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

    public void updateBranch(String branch, String hash) {
        this.BRANCHES.put(branch, hash);
    }


//    public Commit newCommit() {
//        return new Commit();
//    }

    public static Repository loadRepository() {
        File temp = join(GITLET_DIR, "repository");
        return readObject(temp, Repository.class);
    }

    public void checkout(String file) {
        String recent_commit = BRANCHES.get("HEAD");
        checkout(recent_commit, file);
//        String recent_commit = BRANCHES.get(commit_id);
//        Commit c = Commit.loadCommit(recent_commit);
//        String blob = c.tracked.get(file);
//        File blob_file = join(BLOBS_DIR, blob);
//        byte[] blob_object = readContents(blob_file);
//        File cwd_file = join(CWD_DIR, file);
//        writeContents(cwd_file, blob_object);
    }

    public void checkout(String commit_id, String file) {
//        String recent_commit = BRANCHES.get(commit_id);
        Commit c = Commit.loadCommit(commit_id);
        String blob = c.tracked.get(file);
        File blob_file = join(BLOBS_DIR, blob);
        byte[] blob_object = readContents(blob_file);
        File cwd_file = join(CWD_DIR, file);
        writeContents(cwd_file, blob_object);
    }

    // Writes the file to the staging folder
    public void addFileToDirectory(File dir, String filename) {

        File temp = join(CWD_DIR, filename);

        if (temp.exists() == false) {
            System.out.println("File does not exist.");
            System.exit(0);
        }
        byte[] read_in = readContents(temp);
        File write_file = join(dir, filename);

        writeContents(write_file, read_in);
    }

    public boolean isStagingEmpty() {
        List<String> temp = plainFilenamesIn(Repository.BLOBS_DIR);
        return temp.size() == 0;
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
//            ;

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




