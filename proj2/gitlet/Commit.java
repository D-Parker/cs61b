package gitlet;


import java.io.File;
import java.time.Instant;
// TODO: any imports you need here
import static gitlet.Utils.*;
//import static gitlet.Repository.*;
import java.util.Date; // TODO: You'll likely use this in this class
import java.util.List;
import java.util.TreeMap;

/**
 * Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 * @author TODO
 */
public class Commit {
    /**
     * TODO: add instance variables here.
     * <p>
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */
//    Instant zeroTimestamp = Instant.EPOCH;
    public Instant ts;
    public String message;
    public String parent;
    public String second_parent;
    public TreeMap<String, File> file_blob_map;

    /**
     * Commit all files in staging folder
     */

//    public boolean isFirstCommit(){
//        List<String> blob_files = plainFilenamesIn(Repository.COMMITS_DIR);
//        return blob_files == null;
//    }

    public commit(){


        // process the files until staging directories are empty
        // generate hash for the commit object
        // if branches reference is null, initiate master and current branches


    }


    public void commitFiles() {

        // is staging directory empty?
        // write method isDirectoryNull()

        // 1. staged for addition 2. staged for removal

        List<String> blob_files = plainFilenamesIn(Repository.BLOBS_DIR);

        if (blob_files == null){
            return;
        }

        File blob_file;
        byte[] hash_object;
        String hash;

        // inherit file_blob_map from parent;
        if (parent.file_blob_map != null) {
            file_blob_map = new TreeMap<>(parent.file_blob_map);
        }

        List<String> staging_files = plainFilenamesIn(Repository.STAGING_DIR);

        if (staging_files==null){
            return;
        }

        // inherit file_blob_map from parent

        for (String file : staging_files) {

            // read in file as object
            // get hash from object
            // write the file to blobs directory with hash as name

            hash_object = readContents(file);
            hash = sha1(hash_object);

            // check if hash is already in file_blob_map
            if (file_blob_map.containsKey(hash)) {
                continue;
            }


            // generate new filename with hash
            blob_file = join(Repository.BLOBS_DIR, hash);
            // check if hash is already in blob directory
            if (blob_files.contains(blob_file)){
                continue;
            }

            //
            file_blob_map.put(hash, blob_file);

            //
            writeContents(blob_file, hash_object);
            // delete the file from staging
            restrictedDelete(file);
        }

        // process staged for removal files

        // serialize and save the commit object

        //
        // create the associated commit

//            byte[] readContents(File file)


//        static List<String> plainFilenamesIn(String dir) {
//            return plainFilenamesIn(new File(dir));
//        }


    }

    public void makeBlob() {


    }


//    public Node current_branch;
//
//    public Commit(){
//        current_branch = createNode("initial_commit");
////        current_branch = new Node("initial commit", null);
//    }
//
//    public Node createNode(String m){
//        return new Node(m, this.current_branch);
//    }
//
//
//    protected class Node {
//        String message;
//        Node parent;
//        Node(String m, Node p) {
//            message = m;
//            parent = p;
//        }
//    }
//
//    }

//    private void addNode(String message, Node n){
//        Node new_node = new Node(message, n);
//        n = new_node;
//
//    }


}

/* TODO: fill in the rest of this class. */

