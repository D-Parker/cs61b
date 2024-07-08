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

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;


// TODO: any imports you need here

/**
 * Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 * <p>
 *  does at a high level.
 *  A repository stores references to staging files and commits.
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

    public String HEAD;
    public String CURRENT_BRANCH;

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
        HEAD = c.generateInitialId();
        CURRENT_BRANCH = "master";

        c.saveInitialCommit();

        saveRepository();
    }


//    public void reset(String commit_id) {
//        Commit given = Commit.loadCommit(commit_id);
//        if (given == null) {
//            System.out.println("No commit with that id exists");
//            System.exit(0);
//        }
//
//        Commit current = Commit.loadCommit(BRANCHES.get(CURRENT_BRANCH));
//
//        // Moves current branch's head to the given commit node
//        BRANCHES.put(CURRENT_BRANCH, commit_id);
//
//        List<String> cwd_files = getListOfDirectoryFiles(CWD_DIR);
//
//        // Check for untracked files in the current commit
//        for (String file : cwd_files){
//            if (!current.tracked.containsKey(file)) {
//                System.out.println("There is an untracked file in the way; delete it, or add and commit it first");
//                System.exit(0);
//            }
//        }
//
//        // checkout all of the files in the given branch
//        for (String file : given.tracked.keySet()) {
//            checkout(commit_id, file);
//        }
//        // remove all tracked files that are not in the given commit
//        for (String file : current.tracked.keySet()) {
//            if (!given.tracked.containsKey(file)) {
//                removeFile(file);
//            }
//        }
//    }


    public void removeBranch(String branch) {

        if (BRANCHES.containsKey(branch) == false) {

            errorMessage("A branch with that name does not exist.");
//            System.out.println();
//            System.exit(0);
        }
        if (branch.equals(CURRENT_BRANCH)) {
            errorMessage("Cannot remove the current branch.");
//            System.out.println("Cannot remove the current branch.");
//            System.exit(0);
        }

        BRANCHES.remove(branch);
        saveRepository();
    }

    public boolean isFileInDirectory(File dir, String file) {
        File filename = join(dir, file);
        return filename.exists();
    }

    public void fileCopy(String file, File origin, File destination) {
        File origin_file = join(origin, file);
        File destination_file = join(destination, file);
        byte[] origin_contents = readContents(origin_file);
        writeContents(destination_file, origin_contents);
    }

    public void removeFile(String file) {

        // Get current commit
        Commit c = Commit.loadCommit(HEAD);

        // The file to be removed
        File f;

        // Hash if needed to retrieve blob
        String hash;

        byte[] b;

        File write_file;

        // is file in CWD, staging_add, staging_rem, current commit?
//        isFileInDirectory(CWD_DIR, file);
//        isFileInDirectory(STAGING_DIR, file);
//        isFileInDirectory(STAGING_REMOVAL_DIR, file);
//        Boolean isFileTracked = c.tracked.containsKey(file);

        Boolean isFileTracked = c.tracked.containsKey(file);


        // If file isn't tracked in current commit but is staged for addition, remove from staging and return.
        // If file isn't tracked and is not staged for addition, do nothing.
        if (isFileTracked == false) {
            if (isFileInDirectory(STAGING_DIR, file) == true) {
                f = join(STAGING_DIR, file);
                f.delete();
                return;
            } else {
                errorMessage("No reason to remove the file.");
            }
        }

        // Next are cases when file is tracked in the current commit

        // If file is in CWD and staging_addition, remove from staging
        // If file is in CWD but not in staging_addition, stage for removal
        // If file is not in CWD but is in staging addition, stage for removal and remove from staging_add
        // If file is not in CWD and is not in staging addition ?? how to stage for removal?
        //      Get the blob from the recent commit and put it in stage for removal

        if (isFileInDirectory(CWD_DIR, file) == true) {
            if (isFileInDirectory(STAGING_DIR, file) == true) {
                f = join(STAGING_DIR, file);
                f.delete();
                return;
            } else {
                fileCopy(file, CWD_DIR, STAGING_REMOVAL_DIR);
                f = join(CWD_DIR, file);
                f.delete();
                return;
            }
        }


        if (isFileInDirectory(CWD_DIR, file) == false) {
            if (isFileInDirectory(STAGING_DIR, file) == true) {
                fileCopy(file, STAGING_DIR, STAGING_REMOVAL_DIR);
                f = join(STAGING_DIR, file);
                f.delete();
                return;
            } else {
                hash = getTrackedFileHash(c, file);
                b = readFileFromDirectory(BLOBS_DIR, hash);
                write_file = join(STAGING_REMOVAL_DIR, file);
                writeContents(write_file, b);
            }
        }


        // Case 1: if file is currently staged for addition and is in CWD, remove from stage_addition.
        // If it is staged for addition but has been deleted from CWD, stage for removal.
        // stage it for removal (copy file from stage_add to stage_remove.
//        // Then unstage it from stage_addition.
//        if (isFileInDirectory(STAGING_DIR, file) == true) {
//            if (isFileInDirectory(CWD_DIR, file) == true) {
//                f = join(STAGING_DIR, file);
//                f.delete();
//                return;
//            } else if (isFileInDirectory(CWD_DIR, file) == false) {
//                fileCopy(file, STAGING_DIR, STAGING_REMOVAL_DIR);
//                f = join(STAGING_DIR, file);
//                f.delete();
//                return;
//            }
//        }

        // If a file is in STAGING_REMOVAL, then it will removed from tracking in the next commit.

        // Case 2: if file is tracked in the current commit and is in CWD, stage it for removal and remove file from CWD.
        // If the file is not tracked in the current commit, do not remove.

//        if (isFileTracked == true) {
//            if (isFileInDirectory(CWD_DIR, file) == true) {
//                fileCopy(file, CWD_DIR, STAGING_REMOVAL_DIR);
//                f = join(CWD_DIR, file);
//                f.delete();
//            }
//        } else {
//            errorMessage("No reason to remove the file.");
//        }

//        String head_hash;
//        byte[] f_object;

        // case: file is staged for addition
        // remove file from staging folder


//        File f = join(STAGING_DIR, file);
//        if (f.exists()==true){
//            f.delete();
//        }
//        if (getStagingFiles().contains(file)) {
//            f = join(STAGING_DIR, file);
////            getFileFromString(STAGING_DIR, file);
//            f.delete();
//            return;
//        }
        // case: file was tracked in most recent commit
        // Stage for removal and remove file from working directory
//        head_hash = HEAD;
//        c = Commit.loadCommit(head_hash);

//        List<String> sf = getStagingFiles();
//        List<String> rf = getStagingRemovalFiles();

//        if (sf.contains(file) == false && c.tracked.containsKey(file) == false) {
//            errorMessage("No reason to remove the file.");
//        }
//
//        if (sf.contains(file) == true) {
//            join(STAGING_DIR, file).delete();
//        }


//        f = getFileFromString(CWD_DIR, file);
//        f = join(CWD_DIR, file);
//        if (c.tracked.containsKey(file) == true && f.exists() == true) {
//            fileCopy(file, CWD_DIR, STAGING_REMOVAL_DIR);
////            addFileToDirectory(file, STAGING_REMOVAL_DIR);
//            f.delete();
//            return;
//        }
        // case: unstaged, untracked file

    }
//        System.out.println("No reason to remove the file.");
//        return;


    // Create commits after the initial one.
    public void createCommit(String message) {

        if (message.equals("")) {
            errorMessage("Please enter a commit message.");
//            System.out.println("Please enter a commit message.");
//            return;
        }

        List<String> staging = getStagingFiles();
        List<String> staging_removal = getStagingRemovalFiles();

        if (staging.size() == 0 && staging_removal.size() == 0) {
            errorMessage("No changes added to the commit.");
//            System.out.println("No changes added to the commit.");
//            return;
        }


        Commit c = new Commit(message);
        c.parent = HEAD;
        c.second_parent = null;

        c.tracked = new TreeMap<>();

        Commit prev = Commit.loadCommit(c.parent);

        if (prev.tracked != null) {
            c.tracked.putAll(prev.tracked);
        }


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

//        String hash = c.generateId();
        HEAD = c.generateId();
        BRANCHES.put(CURRENT_BRANCH, HEAD);
//        String current_branch = BRANCHES.get("current_branch");
//        BRANCHES.put(current_branch, hash);
//        BRANCHES.put("HEAD", hash);

        saveRepository();
    }

    public void printLog() {
        String curr = HEAD;
        while (curr != null) {
            curr = printLog(curr);
        }
    }

    public void printGlobalLog() {
        List<String> L = getListOfDirectoryFiles(COMMITS_DIR);
        for (String s : L) {
            printLog(s);
        }
    }

    public void find(String msg) {
        List<String> L = getListOfDirectoryFiles(COMMITS_DIR);
//        Collections.sort(L);

        int counter = 0;
        for (String curr : L) {
            Commit g = Commit.loadCommit(curr);
            String z = g.message;
            if (z.equals(msg)) {
                System.out.println(curr);
                counter += 1;
            }
        }
        if (counter == 0) {
            System.out.println("Found no commit with that message");
        }
    }

    private String printLog(String curr) {
        Commit c = Commit.loadCommit(curr);
        System.out.println("===");
        System.out.println("commit " + curr);
        System.out.println("Date: " + c.getTimestamp());
        System.out.println(c.message);
        System.out.println();
        return c.parent;
    }

    public void branch(String n) {
        if (BRANCHES.containsKey(n)) {
            errorMessage("A branch with that name already exists.");
        }
        BRANCHES.put(n, HEAD);
        saveRepository();
    }

    public void status() {
        System.out.println("=== Branches ===");
//        String m = CURRENT_BRANCH;
        List<String> STG = getListOfDirectoryFiles(STAGING_DIR);
        Collections.sort(STG);
        List<String> REM = getListOfDirectoryFiles(STAGING_REMOVAL_DIR);
        Collections.sort(STG);
        List<String> CWD = getListOfDirectoryFiles(CWD_DIR);
        Collections.sort(CWD);


        for (String key : BRANCHES.keySet()) {
            if (key == CURRENT_BRANCH) {
                System.out.println("*" + key);
            } else {
                System.out.println(key);
            }
        }
        System.out.println();
        // If the file is in Staging, but has been deleted from CWD using Unix rm,
        // then move to staging removal and remove file from staging_add.
        System.out.println("=== Staged Files ===");
        for (String a : STG) {
            if (!CWD.contains(a)) {
                fileCopy(a, STAGING_DIR, STAGING_REMOVAL_DIR);
                join(STAGING_DIR, a).delete();
            } else {
                System.out.println(a);
            }
        }

        System.out.println();
        System.out.println("=== Removed Files ===");
        for (String b : REM) {
            System.out.println(b);
        }
        System.out.println();
        System.out.println("=== Modifications Not Staged For Commit ===");
        System.out.println();
        System.out.println("=== Untracked Files ===");
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
        saveRepository();
    }


    public static Repository loadRepository() {
        File temp = join(GITLET_DIR, "repository");
        return readObject(temp, Repository.class);
    }

//    public void checkout(String file) {
//        String recent_commit = HEAD;
//        checkout(recent_commit, file);
//    }

//    public void checkoutBranch(String branch) {
//        Commit c = Commit.loadCommit(BRANCHES.get(branch));
//        for (String i : c.tracked.keySet()){
//            String blob = c.tracked.get(i);
//            File blob_file = join(BLOBS_DIR, blob);
//            byte[] blob_object = readContents(blob_file);
//            File cwd_file = join(CWD_DIR, i);
//            writeContents(cwd_file, blob_object);
//        }
//        CURRENT_BRANCH = branch;
//        HEAD = BRANCHES.get(branch);
//    }
//    public void checkout(String commit_id, String file) {
//        Commit c = Commit.loadCommit(commit_id);
//        String blob = c.tracked.get(file);
//        File blob_file = join(BLOBS_DIR, blob);
//        byte[] blob_object = readContents(blob_file);
//        File cwd_file = join(CWD_DIR, file);
//        writeContents(cwd_file, blob_object);
//    }


    private byte[] readFileFromDirectory(File dir, String file) {
        return readContents(join(dir, file));
    }

    // Writes the file to the staging folder
    public void addFileToStaging(String filename) {

        if (isFileInDirectory(STAGING_REMOVAL_DIR, filename)) {
            fileCopy(filename, STAGING_REMOVAL_DIR, CWD_DIR);
            join(STAGING_REMOVAL_DIR, filename).delete();
            return;
        }

        // Add file to staging
        if (isFileInDirectory(CWD_DIR, filename) == true) {
            fileCopy(filename, CWD_DIR, STAGING_DIR);
        }

        if (!isFileInDirectory(CWD_DIR, filename)) {
            errorMessage("File does not exist.");
        }
        // Get current commit
        Commit c = Commit.loadCommit(HEAD);

        String blob_hash = c.tracked.get(filename);


        // Get hash code for the staged file
        File staging_file = join(STAGING_DIR, filename);
        String staging_hash = getFileHash(staging_file);
//        File blob = join(BLOBS_DIR, hash);

        // If an identical copy of this file is already being tracked, remove it from staging.
        if (staging_hash.equals(blob_hash)) {
//        if (blob.exists()) {
            staging_file.delete();
            return;
        }

//        fileCopy(filename, CWD_DIR, STAGING_DIR);

//        byte[] read_in_cwd_file = readContents(cwd_file);

//        writeContents(join(STAGING_DIR, filename), read_in_cwd_file);

//        addFileToDirectory(read_in_cwd_file, STAGING_DIR);
    }

    public boolean isStagingEmpty() {
        List<String> temp = plainFilenamesIn(Repository.BLOBS_DIR);
        return temp.size() == 0;
    }

    public void checkout(String file_name) {
        checkout(HEAD, file_name);
    }

    public void checkout(String commit_id, String file_name) {
        Commit c = Commit.loadCommit(commit_id);
        if (c == null) {
            errorMessage("No commit with that id exists");
        }

        String blob = getTrackedFileHash(c, file_name);

        if (blob == null) {
            errorMessage("File does not exist in that commit");
        }

        writeBlobToCWD(blob, file_name);
    }

    public void checkoutBranch(String branch) {
        if (!BRANCHES.containsKey(branch)) {
            errorMessage("No such branch exists.");
        }
        if (branch == CURRENT_BRANCH) {
            errorMessage("No need to checkout the current branch.");
        }

        String commit_id = BRANCHES.get(branch);
        reset(commit_id);
        CURRENT_BRANCH = branch;
        saveRepository();
    }

    public void reset(String commit_id) {

        Commit c = Commit.loadCommit(commit_id);
        if (c == null) {
            errorMessage("No commit with that id exists");
        }

        List<String> cwd_files = plainFilenamesIn(Repository.CWD_DIR);

        for (String file_name : cwd_files) {
            if (isFileTracked(HEAD, file_name) == false) {
                if (isFileTracked(commit_id, file_name) == true) {
                    errorMessage("There is an untracked file in the way; delete it, or add and commit it first");
                }
            }
        }

        deleteAllFilesInDirectory(Repository.STAGING_DIR);
        deleteAllFilesInDirectory(Repository.STAGING_REMOVAL_DIR);


//        Commit c = Commit.loadCommit(commit_id);

        for (String file_name : c.tracked.keySet()) {
            checkout(commit_id, file_name);
        }

        HEAD = commit_id;
        saveRepository();
    }

    private void deleteAllFilesInDirectory(File dir) {
        List<String> directory_files = plainFilenamesIn(dir);
        for (String file_name : directory_files) {
            File temp = join(dir, file_name);
            temp.delete();
        }
    }


    public boolean isFileTracked(String commit_id, String file_name) {
        Commit c = Commit.loadCommit(commit_id);
        return c.tracked.containsKey(file_name);
    }

    public void errorMessage(String msg) {
        System.out.println(msg);
        System.exit(0);
    }

    private String getTrackedFileHash(Commit c, String file_name) {
        return c.tracked.get(file_name);
    }

    private void writeBlobToCWD(String blob, String file_name) {
        File blob_file = join(BLOBS_DIR, blob);
        byte[] blob_object = readContents(blob_file);
        File cwd_file = join(CWD_DIR, file_name);
        writeContents(cwd_file, blob_object);
    }

//    public void merge(String branch){
//
//        String current_id = HEAD;
//        String branch_id = BRANCHES.get(branch);
//
//        Commit c = Commit.loadCommit(current_id);
//        Commit f = Commit.loadCommit(branch_id);
//
//        List<String> L = ArrayList<String>;
//
//        List<String> G = ArrayList<String>;
//
//        while(c.parent != null){
//            L.add(c.parent);
//            c = Commit.loadCommit(c.parent);
//        }
//        while(f.parent != null){
//            G.add(f.parent);
//            f = Commit.loadCommit(f.parent);
//        }
//        String splitpoint = null;
//        for (String i : L){
//            if (G.contains(i)){
//                splitpoint = i;
//            }
//        }
//        if (branch_id == splitpoint){
//            errorMessage("Given branch is an ancestor of the current branch");
//        }
//
//
//
//
////        findSplitPoint
//    }


}
