package gitlet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;

import java.util.Set;
import java.io.File;
import java.io.Serializable;
import java.time.Instant;
// TODO: any imports you need here
import static gitlet.Utils.*;
//import static gitlet.Repository.*;
import java.util.Date; // TODO: You'll likely use this in this class
import java.util.List;
import java.util.TreeMap;
import java.util.Collection;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 * @author TODO
 */
public class Commit implements Serializable {
    /**
     * TODO: add instance variables here.
     * <p>
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    // instance variables
    public Instant ts;
    public String message;
    public String parent;
    public String second_parent;
    public TreeMap<String, String> tracked ;

    // constructor for init command only
    public Commit(){
        super();
        this.ts = Instant.EPOCH;
        this.message = "initial commit";
    }


    public Commit(String message){
        super();
        this.ts = Instant.now();
        this.message = message;
//        tracked = new TreeMap<>();

    }
    public String getTimestamp() {
        // Thu Jan 1 00:00:00 1970 +0000
        // Define the desired format
        ZonedDateTime zonedDateTime = ts.atZone(ZoneId.of("UTC"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM d HH:mm:ss yyyy Z");

        return zonedDateTime.format(formatter);
    }
    public String generateInitialId() {

        return generateId();
//        return sha1(ts.toString(), message);
    }
    public String generateId() {
//        List<String> temp = new List<>();
        List<Object> temp = new ArrayList<>();

        if(ts != null){
            temp.add(ts.toString());
        }
        if(message != null){
            temp.add(message);
//            temp.add(message.toString());
        }
        if(parent != null){
            temp.add(parent.toString());
        }
        if(second_parent != null){
            temp.add(second_parent.toString());
        }
        if(tracked != null){
            temp.add(tracked.toString());
        }

//        temp.add(message);
//        temp.add(parent.toString());
//        temp.add(second_parent.toString());
//        temp.add(tracked.toString());
//
//        for (List<String> i : temp){
//
//            if (i==null){
//                temp.remove(i);
//            }

        return sha1(temp);
    }





//    public String getHashFromCommit(){
//        List<String> temp = new ArrayList<>();
//        temp.add(this.ts.toString());
////        temp.add(message.toString());
////        temp.add(parent.toString());
////        temp.add(second_parent.toString());
////        temp.add(tracked.toString());
//        return sha1(temp);
//    }


//    public String getTimestamp() {
//        // Thu Jan 1 00:00:00 1970 +0000
//        DateFormat dateFormat = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy Z", Locale.ENGLISH);
//        return dateFormat.format(ts);
//    }
    // methods

    public void saveInitialCommit() {
        File write_file = join(Repository.COMMITS_DIR, this.generateInitialId());
        writeObject(write_file, this);
    }

    public void saveCommit() {
        File write_file = join(Repository.COMMITS_DIR, this.generateId());
        writeObject(write_file, this);
    }

    public static Commit loadCommit(String hash) {
        File file = join(Repository.COMMITS_DIR, hash);
        if (!file.exists()){
            return null;
        }
        return readObject(file, Commit.class);
    }

    public String getHash(Serializable obj) {
        return sha1(obj);
    }

    public String getCommitHash() {
        return getHash(this);
    }

//    public String getFileHash(File file) {
//        byte[] temp = readContents(file);
//        return getHash(temp);
//    }

    public List<String> getBlobFiles() {
        return plainFilenamesIn(Repository.BLOBS_DIR);
    }

    public List<String> getStagingFiles() {
        return plainFilenamesIn(Repository.STAGING_DIR);
    }

//    public void createTrackingMap() {
//
//        byte[] item_object;
//        File item_file;
//        String item_hash;
//        File tracking_file;
//
//        List<String> staging_files = getStagingFiles();
//
//        for (String item : staging_files) {
//            item_file = join(Repository.STAGING_DIR, item);
//            item_object = readContents(item_file);
//            item_hash = getFileHash(item_file);
//
//            // Set name of blob file
//            blob_file = join(Repository.BLOBS_DIR, item_hash);
//            // Add record to commit tree
//            tracked.put(item, item_hash);
//            // Write blob file to disk
//            writeContents(blob_file, item_object);
//            // Delete from staging folder
//            item_file.delete();
//        }
//    }
}


//            // read in file

//            // convert file to memory object

//            // compute hash from memory object
//            item_hash = sha1(item_object);
//            // generate name of blob file
//            blob_file = join(Repository.BLOBS_DIR, item_hash);
//            // add entry to the blob map for the commit
//            // write blob file to disk
//            writeContents(blob_file, item_object);


//    public List<String> staging_list;

//    public Commit(String message){
//        this.ts = Instant.now();
//        this.message = message;
//        parent = BRANCHES.getCurrentBranch();
//        second_parent = null;
//        blob_map = null;
//        this.staging_list = plainFilenamesIn(Repository.STAGING_DIR);
//    }


/**
 * Commit all files in staging folder
 */


//public void addCommit() {
//    // get the commit hash for the set of all of the files
//    List<String> staging_list = plainFilenamesIn(Repository.STAGING_DIR);
//}

//
////    public boolean isFirstCommit(){
////        List<String> blob_files = plainFilenamesIn(Repository.COMMITS_DIR);
////        return blob_files == null;
////    }
//
//
//}
//    public Commit(){
//        this.abc = abc;
//        // process the files until staging directories are empty
//        // generate hash for the commit object
//        // if branches reference is null, initiate master and current branches
//    }
//
//
//    public void commitFiles() {
//
//        // is staging directory empty?
//        // write method isDirectoryNull()
//
//        // 1. staged for addition 2. staged for removal
//


//        if (blob_files == null){
//            return;
//        }
//
//        File blob_file;
//        byte[] hash_object;
//        String hash;
//
//        // inherit file_blob_map from parent;
//        if (this.parent.abc != null) {
//            this.abc = parent.abc;
//        }
//
//        List<String> staging_files = plainFilenamesIn(Repository.STAGING_DIR);
//
//        if (staging_files==null){
//            return;
//        }
//
//        // inherit file_blob_map from parent
//
//        for (String file : staging_files) {
//
//            // read in file as object
//            // get hash from object
//            // write the file to blobs directory with hash as name
//
//            hash_object = readContents(file);
//            hash = sha1(hash_object);
//
//            // check if hash is already in file_blob_map
//            if (abc.containsKey(hash)) {
//                continue;
//            }
//
//
//            // generate new filename with hash
//            blob_file = join(Repository.BLOBS_DIR, hash);
//            // check if hash is already in blob directory
//            if (blob_files.contains(blob_file)){
//                continue;
//            }
//
//            //
//            abc.put(hash, blob_file);
//
//            //
//            writeContents(blob_file, hash_object);
//            // delete the file from staging
//            restrictedDelete(file);
//        }
//
//        // process staged for removal files
//
//        // serialize and save the commit object
//
//        //
//        // create the associated commit
//
////            byte[] readContents(File file)
//
//
////        static List<String> plainFilenamesIn(String dir) {
////            return plainFilenamesIn(new File(dir));
////        }
//
//
//    }
//
////    public void makeBlob() {
////
////
////    }
//
//
////    public Node current_branch;
////
////    public Commit(){
////        current_branch = createNode("initial_commit");
//////        current_branch = new Node("initial commit", null);
////    }
////
////    public Node createNode(String m){
////        return new Node(m, this.current_branch);
////    }
////
////
////    protected class Node {
////        String message;
////        Node parent;
////        Node(String m, Node p) {
////            message = m;
////            parent = p;
////        }
////    }
////
////    }
//
////    private void addNode(String message, Node n){
////        Node new_node = new Node(message, n);
////        n = new_node;
////
////    }
//
//
//}
//
///* TODO: fill in the rest of this class. */

