package gitlet;

import java.io.File;
import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static gitlet.Utils.*;

/**
 * Represents a gitlet commit object.
 */
public class Commit implements Serializable {
    /**
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     * <p>
     * ts is the timestamp when the Commit is made.
     * Message is the Commit message.
     * Parent is the hash code of the parent commit
     * Second is the hash code of the second parent if there was a merge.
     * Tracked is a treemap of the files tracked by the Commit and the versions of these files.
     */

        Instant ts;
        String message;
        String parent;
        String secondParent;
        TreeMap<String, String> tracked;

    /**
     * This method is a Commit constructor that does just the initial commit in a repository.
     */
    public Commit() {
        super();
        this.ts = Instant.EPOCH;
        this.message = "initial commit";
        this.tracked = new TreeMap<>();
    }

    /**
     * This is the constructor for methods following the initial one.
     *
     * @param message The message associated with the commit.
     */
    public Commit(String message) {
        super();
        this.ts = Instant.now();
        this.message = message;
    }

    /**
     * Loads a Commit from disk into memory.
     *
     * @param hash The hash code for the commit
     * @return The Commit object.
     */
    public static Commit loadCommit(String hash) {
        File file = join(Repository.COMMITS_DIR, hash);
        if (!file.exists()) {
            return null;
        }
        return readObject(file, Commit.class);
    }

    /**
     * This returns the current timestamp in this format: Thu Jan 1 00:00:00 1970 +0000.
     *
     * @return The formatted timestamp.
     */
    public String getTimestamp() {


        ZonedDateTime zonedDateTime = ts.atZone(ZoneId.of("UTC"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM d HH:mm:ss yyyy Z");

        return zonedDateTime.format(formatter);
    }

    /**
     * This returns a 40-character hashcode.
     *
     * @return The hashcode for the initial commit.
     */
    public String generateInitialId() {

        return generateId();
    }

    /**
     * This returns a 40-character hashcode.
     *
     * @return The hashcode for the subsequent commits.
     */
    public String generateId() {
        List<Object> temp = new ArrayList<>();

        if (ts != null) {
            temp.add(ts.toString());
        }
        if (message != null) {
            temp.add(message);
        }
        if (parent != null) {
            temp.add(parent);
        }
        if (secondParent != null) {
            temp.add(secondParent);
        }
        if (tracked != null) {
            temp.add(tracked.toString());
        }
        String result = sha1(temp);

        return result;
    }

    /**
     * Saves the initial commit to disk.
     */
    public void saveInitialCommit() {
        File writeFile = join(Repository.COMMITS_DIR, this.generateInitialId());
        writeObject(writeFile, this);
    }

    /**
     * Saves subsequent commits to disk.
     */
    public void saveCommit() {
        File writeFile = join(Repository.COMMITS_DIR, this.generateId());
        writeObject(writeFile, this);
    }

    /**
     * Gets hash code for object.
     *
     * @param obj The object that the hash is being generated from.
     * @return The 40-char hash code
     */
    public String getHash(Serializable obj) {
        return sha1(obj);
    }

    /**
     * Wrapper to get hash from a commit.
     *
     * @return Returns 40-char hash code.
     */
    public String getCommitHash() {
        return getHash(this);
    }

    /**
     * Gets list of all blob files.
     *
     * @return List of all files in the blob directory.
     */
    public List<String> getBlobFiles() {
        return plainFilenamesIn(Repository.BLOBS_DIR);
    }

    /**
     * Gets the namees of all files in the staging directory.
     *
     * @return List of files in the directory.
     */
    public List<String> getStagingFiles() {
        return plainFilenamesIn(Repository.STAGING_DIR);
    }
}






