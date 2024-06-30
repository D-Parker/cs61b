package gitlet;

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
/**
 * Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 * @author TODO
 */
public class Commit implements Serializable{
    /**
     * TODO: add instance variables here.
     * <p>
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    public Instant ts;
    public String message;
    public String parent;
    public String second_parent;
    public TreeMap<String, String> blob_map;
}
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


//    public void addCommit() {
//
//        // get the commit hash for the set of all of the files
//        List<String> staging_list = plainFilenamesIn(Repository.STAGING_DIR);
//
//        byte[] item_object;
//        File item_file;
//        String item_hash;
//        File blob_file;
//
//
//        for (String item : staging_list) {
//
//            // read in file
//            item_file= join(Repository.STAGING_DIR, item);
//            // convert file to memory object
//            item_object = readContents(item_file);
//            // compute hash from memory object
//            item_hash = sha1(item_object);
//            // generate name of blob file
//            blob_file = join(Repository.BLOBS_DIR, item_hash);
//            // add entry to the blob map for the commit
//
//            blob_map.put(item, item_hash);
//
//            // write blob file to disk
//            writeContents(blob_file, item_object);
//        }
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
//        List<String> blob_files = plainFilenamesIn(Repository.BLOBS_DIR);
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

