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
        this.tracked = new TreeMap<>();
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
        String result = sha1(temp);

        return result;
    }


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

    public List<String> getBlobFiles() {
        return plainFilenamesIn(Repository.BLOBS_DIR);
    }

    public List<String> getStagingFiles() {
        return plainFilenamesIn(Repository.STAGING_DIR);
    }

