package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.*;

import static gitlet.Utils.*;

/**
 * Represents a gitlet repository.
 * <p>
 * A repository stores references to staging files and commits.
 */
public class Repository implements Serializable {

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
    public static final File BLOBS_DIR = join(GITLET_DIR, "blobs");
    public static final File COMMITS_DIR = join(GITLET_DIR, "commits");

    public TreeMap<String, String> BRANCHES = new TreeMap<>();
    public TreeMap<String, String> BRANCHES_ORIGIN = new TreeMap<>();
    public String HEAD;
    public String CURRENT_BRANCH;
    public List<String> CWD_FILES;
    public TreeMap<String, String> STAGING_ADD = new TreeMap<>();
    public TreeMap<String, String> STAGING_REMOVE = new TreeMap<>();
    public TreeMap<String, String> BLOBS = new TreeMap<>();

    /**
     * Constructor for the Repository class.
     * Initializes the repository and creates the initial commit.
     */
    public Repository() {
        super();

        GITLET_DIR.mkdir();
        STAGING_DIR.mkdir();
        STAGING_REMOVAL_DIR.mkdir();
        COMMITS_DIR.mkdir();
        BLOBS_DIR.mkdir();

        Commit c = new Commit();

        BRANCHES_ORIGIN.put("master", c.generateInitialId());
        BRANCHES.put("master", c.generateInitialId());
        HEAD = c.generateInitialId();
        CURRENT_BRANCH = "master";

        c.saveInitialCommit();

        saveRepository();
    }

    /**
     * Loads the repository from disk.
     *
     * @return the loaded repository.
     */
    public static Repository loadRepository() {
        File temp = join(GITLET_DIR, "repository");
        if (!temp.exists()) {
            System.out.println("Not in an initialized Gitlet directory.");
            System.exit(0);
        }

        return readObject(temp, Repository.class);
    }

    /**
     * Removes the specified branch.
     *
     * @param branch The name of the branch to remove.
     */
    public void removeBranch(String branch) {
        if (!BRANCHES.containsKey(branch)) {
            errorMessage("A branch with that name does not exist.");
        }
        if (branch.equals(CURRENT_BRANCH)) {
            errorMessage("Cannot remove the current branch.");
        }

        BRANCHES.remove(branch);
        saveRepository();
    }

    /**
     * Checks if a file exists in the specified directory.
     *
     * @param dir  The directory to check.
     * @param file The name of the file.
     * @return true if the file exists, false otherwise.
     */
    public boolean isFileInDirectory(File dir, String file) {
        File filename = join(dir, file);
        return filename.exists();
    }

    /**
     * Copies a file from one directory to another.
     *
     * @param file        The name of the file to copy.
     * @param origin      The origin directory.
     * @param destination The destination directory.
     */
    public void fileCopy(String file, File origin, File destination) {
        File originFile = join(origin, file);
        File destinationFile = join(destination, file);
        byte[] originContents = readContents(originFile);
        writeContents(destinationFile, originContents);
    }

    /**
     * Stages a file for removal or removes it from staging for addition.
     *
     * @param file The name of the file to be staged for removal.
     */
    public void removeFile(String file) {
        Commit c = Commit.loadCommit(HEAD);
        File f;
        String hash;
        byte[] b;
        File writeFile;
        Boolean isFileTracked = c.tracked.containsKey(file);

        if (!isFileTracked) {
            if (isFileInDirectory(STAGING_DIR, file)) {
                f = join(STAGING_DIR, file);
                f.delete();
                return;
            } else {
                errorMessage("No reason to remove the file.");
            }
        }

        if (isFileInDirectory(CWD_DIR, file)) {
            if (isFileInDirectory(STAGING_DIR, file)) {
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

        if (!isFileInDirectory(CWD_DIR, file)) {
            if (isFileInDirectory(STAGING_DIR, file)) {
                fileCopy(file, STAGING_DIR, STAGING_REMOVAL_DIR);
                f = join(STAGING_DIR, file);
                f.delete();
            } else {
                hash = getTrackedFileHash(c, file);
                b = readFileFromDirectory(BLOBS_DIR, hash);
                writeFile = join(STAGING_REMOVAL_DIR, file);
                writeContents(writeFile, b);
            }
        }
    }

    /**
     * Creates a new commit with the specified message and branch.
     *
     * @param message Log message for the commit.
     * @param branch  Branch that commit will use as a second parent in case it was called by merge.
     */
    public void createCommit(String message, String branch) {
        if (message.equals("")) {
            errorMessage("Please enter a commit message.");
        }

        List<String> staging = getStagingFiles();
        List<String> stagingRemoval = getStagingRemovalFiles();

        if (staging.size() == 0 && stagingRemoval.size() == 0) {
            errorMessage("No changes added to the commit.");
        }

        Commit c = new Commit(message);
        c.parent = HEAD;
        c.secondParent = null;
        if (branch != null) {
            c.secondParent = BRANCHES.get(branch);
        }
        c.tracked = new TreeMap<>();

        Commit prev = Commit.loadCommit(c.parent);

        if (prev.tracked != null) {
            c.tracked.putAll(prev.tracked);
        }

        for (String file : staging) {
            File stagingFile = join(STAGING_DIR, file);
            String hash = getFileHash(stagingFile);
            c.tracked.put(file, hash);
            File blobFile = join(BLOBS_DIR, hash);
            writeContents(blobFile, readContents(stagingFile));
            stagingFile.delete();
        }

        for (String file : stagingRemoval) {
            File stagingRemovalFile = join(STAGING_REMOVAL_DIR, file);
            c.tracked.remove(file);
            stagingRemovalFile.delete();
        }

        c.saveCommit();

        HEAD = c.generateId();
        BRANCHES.put(CURRENT_BRANCH, HEAD);

        if (branch != null) {
            BRANCHES.put(branch, HEAD);
        }

        saveRepository();
    }

    /**
     * Prints the log of commits from the current branch.
     */
    public void printLog() {
        String curr = HEAD;
        while (curr != null) {
            curr = printLog(curr);
        }
    }

    /**
     * Prints the global log of all commits.
     */
    public void printGlobalLog() {
        List<String> L = getListOfDirectoryFiles(COMMITS_DIR);
        for (String s : L) {
            printLog(s);
        }
    }

    /**
     * Finds and prints commits with the specified message.
     *
     * @param msg The commit message to search for.
     */
    public void find(String msg) {
        List<String> L = getListOfDirectoryFiles(COMMITS_DIR);
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

    /**
     * Prints the log for the specified commit ID.
     *
     * @param curr The commit ID.
     * @return The parent commit ID.
     */
    private String printLog(String curr) {
        Commit c = Commit.loadCommit(curr);
        System.out.println("===");
        System.out.println("commit " + curr);
        String temp1 = null;
        String temp2 = null;
        if (c.parent != null) {
            temp1 = c.parent.substring(0, 7);
        }
        if (c.secondParent != null && !c.secondParent.equals("")) {
            temp2 = c.secondParent.substring(0, 7);
        }
        if (c.parent != null && c.secondParent != null) {
            System.out.println("Merge: " + temp1 + " " + temp2);
        }
        System.out.println("Date: " + c.getTimestamp());
        System.out.println(c.message);
        System.out.println();
        return c.parent;
    }

    /**
     * Creates a new branch with the specified name.
     *
     * @param n The name of the branch.
     */
    public void branch(String n) {
        if (BRANCHES.containsKey(n)) {
            errorMessage("A branch with that name already exists.");
        }
        BRANCHES.put(n, HEAD);
        saveRepository();
    }

    /**
     * Prints the status of the repository.
     */
    public void status() {
        if (!GITLET_DIR.exists()) {
            errorMessage("Not in an initialized Gitlet directory.");
        }

        System.out.println("=== Branches ===");

        List<String> stg = getListOfDirectoryFiles(STAGING_DIR);
        Collections.sort(stg);
        List<String> rem = getListOfDirectoryFiles(STAGING_REMOVAL_DIR);
        Collections.sort(stg);
        List<String> CWD = getListOfDirectoryFiles(CWD_DIR);
        Collections.sort(CWD);

        for (String key : BRANCHES.keySet()) {
            if (key.equals(CURRENT_BRANCH)) {
                System.out.println("*" + key);
            } else {
                System.out.println(key);
            }
        }
        System.out.println();

        System.out.println("=== Staged Files ===");
        for (String a : stg) {
            if (!CWD.contains(a)) {
                fileCopy(a, STAGING_DIR, STAGING_REMOVAL_DIR);
                join(STAGING_DIR, a).delete();
            } else {
                System.out.println(a);
            }
        }

        System.out.println();
        System.out.println("=== Removed Files ===");
        for (String b : rem) {
            System.out.println(b);
        }
        System.out.println();
        System.out.println("=== Modifications Not Staged For Commit ===");
        System.out.println();
        System.out.println("=== Untracked Files ===");
        System.out.println();
    }

    /**
     * Retrieves a file from the specified directory.
     *
     * @param dir  The directory.
     * @param file The name of the file.
     * @return The file.
     */
    public File getFileFromString(File dir, String file) {
        File result = join(dir, file);
        if (result.exists()) {
            return result;
        } else {
            return result;
        }
    }

    /**
     * Computes the hash of a file.
     *
     * @param file The file.
     * @return The hash of the file.
     */
    public String getFileHash(File file) {
        byte[] temp = readContents(file);
        return getHash(temp);
    }

    /**
     * Computes the hash of an object.
     *
     * @param obj The object.
     * @return The hash of the object.
     */
    public String getHash(Serializable obj) {
        return sha1(obj);
    }

    /**
     * Retrieves the list of staging files.
     *
     * @return The list of staging files.
     */
    public List<String> getStagingFiles() {
        return plainFilenamesIn(Repository.STAGING_DIR);
    }

    /**
     * Retrieves the list of staging removal files.
     *
     * @return The list of staging removal files.
     */
    public List<String> getStagingRemovalFiles() {
        return plainFilenamesIn(Repository.STAGING_REMOVAL_DIR);
    }

    /**
     * Saves a commit to disk.
     *
     * @param c The commit to save.
     */
    public void saveCommit(Commit c) {
        File writeFile = join(Repository.COMMITS_DIR, "Commit_abc");
        writeObject(writeFile, this);
    }

    /**
     * Checks if the repository has branches.
     *
     * @return true if the repository has branches, false otherwise.
     */
    public boolean hasBranches() {
        return BRANCHES.size() > 0;
    }

    /**
     * Saves the current repository to disk.
     */
    public void saveRepository() {
        File writeFile = join(GITLET_DIR, "repository");
        writeObject(writeFile, this);
    }

    /**
     * Retrieves the list of files in a directory.
     *
     * @param directory The directory.
     * @return The list of files.
     */
    public List<String> getListOfDirectoryFiles(File directory) {
        return plainFilenamesIn(directory);
    }

    /**
     * Updates the branch with the specified hash.
     *
     * @param branch The branch to update.
     * @param hash   The hash to update the branch with.
     */
    public void updateBranch(String branch, String hash) {
        this.BRANCHES.put(branch, hash);
        saveRepository();
    }

    /**
     * Reads a file from a directory.
     *
     * @param dir  The directory.
     * @param file The name of the file.
     * @return The file contents as a byte array.
     */
    private byte[] readFileFromDirectory(File dir, String file) {
        return readContents(join(dir, file));
    }

    /**
     * Adds a file to staging.
     *
     * @param filename The name of the file to stage.
     */
    public void addFileToStaging(String filename) {
        if (isFileInDirectory(STAGING_REMOVAL_DIR, filename)) {
            fileCopy(filename, STAGING_REMOVAL_DIR, CWD_DIR);
            join(STAGING_REMOVAL_DIR, filename).delete();
            return;
        }

        if (isFileInDirectory(CWD_DIR, filename)) {
            fileCopy(filename, CWD_DIR, STAGING_DIR);
        }

        if (!isFileInDirectory(CWD_DIR, filename)) {
            errorMessage("File does not exist.");
        }

        Commit c = Commit.loadCommit(HEAD);

        String blobHash = c.tracked.get(filename);

        File stagingFile = join(STAGING_DIR, filename);
        String stagingHash = getFileHash(stagingFile);

        if (stagingHash.equals(blobHash)) {
            stagingFile.delete();
        }
    }

    /**
     * Checks if the staging area is empty.
     *
     * @return true if the staging area is empty, false otherwise.
     */
    public boolean isStagingEmpty() {
        List<String> temp = plainFilenamesIn(Repository.BLOBS_DIR);
        return temp.size() == 0;
    }

    /**
     * Checks out a file from the current commit.
     *
     * @param fileName The name of the file to check out.
     */
    public void checkout(String fileName) {
        checkout(HEAD, fileName);
    }

    /**
     * Checks out a file from a specific commit.
     *
     * @param commitId The ID of the commit to check out the file from.
     * @param fileName The name of the file to check out.
     */
    public void checkout(String commitId, String fileName) {
        List<String> L = plainFilenamesIn(COMMITS_DIR);
        Integer len = commitId.length();
        for (String i : L) {
            if (i.substring(0, len).equals(commitId)) {
                commitId = i;
                break;
            }
        }
        if (commitId.length() < 40) {

        }

        Commit c = Commit.loadCommit(commitId);
        if (c == null) {
            errorMessage("No commit with that id exists.");
        }

        String blob = getTrackedFileHash(c, fileName);
        if (blob == null) {
            errorMessage("File does not exist in that commit.");
        }

        writeBlobToCWD(blob, fileName);
    }

    /**
     * Checks out a branch.
     *
     * @param branch The branch to check out.
     */
    public void checkoutBranch(String branch) {
        if (!BRANCHES.containsKey(branch)) {
            errorMessage("No such branch exists.");
        }
        if (branch.equals(CURRENT_BRANCH)) {
            errorMessage("No need to checkout the current branch.");
        }

        resetHelper(BRANCHES.get(branch));

        CURRENT_BRANCH = branch;
        HEAD = BRANCHES.get(CURRENT_BRANCH);
        saveRepository();
    }

    /**
     * Resets the current branch to the specified commit.
     *
     * @param commitId The ID of the commit to reset to.
     */
    public void reset(String commitId) {
        List<String> L = plainFilenamesIn(COMMITS_DIR);
        Integer len = commitId.length();
        for (String i : L) {
            if (i.substring(0, len).equals(commitId)) {
                commitId = i;
                break;
            }
        }
        resetHelper(commitId);
        BRANCHES.put(CURRENT_BRANCH, commitId);
        HEAD = commitId;
        saveRepository();
    }

    /**
     * Helper method to reset the repository state to a specified commit.
     *
     * @param commitId The ID of the commit to reset to.
     */
    private void resetHelper(String commitId) {
        Commit c = Commit.loadCommit(commitId);
        File f;
        if (c == null) {
            errorMessage("No commit with that id exists");
        }
        List<String> cwdFiles = plainFilenamesIn(Repository.CWD_DIR);

        for (String fileName : cwdFiles) {
            if (!isFileTracked(HEAD, fileName) && isFileTracked(commitId, fileName)) {
                errorMessage("There is an untracked file in the way; delete it, or add and commit it first");
            }
            if (isFileTracked(HEAD, fileName) && !isFileTracked(commitId, fileName)) {
                f = join(CWD_DIR, fileName);
                f.delete();
            }
        }

        deleteAllFilesInDirectory(STAGING_DIR);
        deleteAllFilesInDirectory(STAGING_REMOVAL_DIR);

        for (String fileName : c.tracked.keySet()) {
            checkout(commitId, fileName);
        }
    }

    /**
     * Deletes all files in a directory.
     *
     * @param dir The directory.
     */
    private void deleteAllFilesInDirectory(File dir) {
        List<String> directory_files = plainFilenamesIn(dir);
        for (String fileName : directory_files) {
            File temp = join(dir, fileName);
            temp.delete();
        }
    }

    /**
     * Checks if a file is tracked in a specific commit.
     *
     * @param commitId The ID of the commit.
     * @param fileName The name of the file.
     * @return true if the file is tracked, false otherwise.
     */
    public boolean isFileTracked(String commitId, String fileName) {
        Commit c = Commit.loadCommit(commitId);
        return c.tracked.containsKey(fileName);
    }

    /**
     * Prints an error message and exits the program.
     *
     * @param msg The error message to print.
     */
    public void errorMessage(String msg) {
        System.out.println(msg);
        System.exit(0);
    }

    /**
     * Retrieves the hash of a tracked file in a commit.
     *
     * @param c         The commit.
     * @param fileName The name of the file.
     * @return The hash of the tracked file.
     */
    private String getTrackedFileHash(Commit c, String fileName) {
        return c.tracked.get(fileName);
    }

    /**
     * Writes a blob to the current working directory.
     *
     * @param blob      The blob hash.
     * @param fileName The name of the file.
     */
    private void writeBlobToCWD(String blob, String fileName) {
        File blobFile = join(BLOBS_DIR, blob);
        byte[] blobObject = readContents(blobFile);
        File cwdFile = join(CWD_DIR, fileName);
        writeContents(cwdFile, blobObject);
    }

    /**
     * Merges the specified branch into the current branch.
     *
     * @param branch The name of the branch to merge into the current branch.
     * @throws IllegalArgumentException if the branch is the current branch, has uncommitted changes,
     *                                  or if the branch does not exist.
     */
    public void merge(String branch) {
        if (branch.equals(CURRENT_BRANCH)) {
            errorMessage("Cannot merge a branch with itself.");
        }

        List<String> stgTest = plainFilenamesIn(STAGING_DIR);
        List<String> stgRemoval = plainFilenamesIn(STAGING_REMOVAL_DIR);

        if (plainFilenamesIn(STAGING_DIR).size() > 0) {
            errorMessage("You have uncommitted changes.");
        }

        if (plainFilenamesIn(STAGING_REMOVAL_DIR).size() > 0) {
            errorMessage("You have uncommitted changes.");
        }

        if (!BRANCHES.containsKey(branch)) {
            errorMessage("A branch with that name does not exist.");
        }

        List<String> cwdFiles = plainFilenamesIn(Repository.CWD_DIR);

        String splitId = getSplitPoint(branch);

        Integer conflictCount = 0;

        String currentId = HEAD;
        String givenId = BRANCHES.get(branch);

        boolean test1;
        boolean test2;

        for (String fileName : cwdFiles) {
            test1 = isFileTracked(HEAD, fileName);
            test2 = isFileTracked(givenId, fileName);

            if (!isFileTracked(HEAD, fileName) && isFileTracked(givenId, fileName)) {
                errorMessage("There is an untracked file in the way; delete it, or add and commit it first.");
            }
        }

        Commit splitpoint = Commit.loadCommit(splitId);
        Commit current = Commit.loadCommit(currentId);
        Commit given = Commit.loadCommit(givenId);

        Set<String> combinedKeys = new TreeSet<>(current.tracked.keySet());
        combinedKeys.addAll(given.tracked.keySet());
        List<String> combinedKeysList = new ArrayList<>(combinedKeys);

        for (String i : combinedKeysList) {
            String s = splitpoint.tracked.get(i);
            String c = current.tracked.get(i);
            String g = given.tracked.get(i);

            if (c == null && g == null) {
                continue;
            }

            if (c != null && c.equals(g)) {
                continue;
            }

            if (c != null && c.equals(s) && g == null) {
                removeFile(i);
                continue;
            }

            if (c == null && g != null && s != null && !g.equals(s)) {
                conflictCount += 1;
                processConflict(i, c, g);
                continue;
            }

            if (c != null && g == null && s != null && !c.equals(s)) {
                conflictCount += 1;
                processConflict(i, c, g);
                continue;
            }

            if (g != null && c != null && !g.equals(s) && c.equals(s)) {
                checkout(givenId, i);
                addFileToStaging(i);
                continue;
            }

            if (s == null && c == null && g != null) {
                checkout(givenId, i);
                addFileToStaging(i);
                continue;
            }

            if (s != null && g != null && c != null && !c.equals(s) && !g.equals(s) && !c.equals(g)) {
                conflictCount += 1;
                processConflict(i, c, g);
                continue;
            }
            if (s == null && g != null && c != null && !c.equals(g)) {
                conflictCount += 1;
                processConflict(i, c, g);
                continue;
            }
        }
        String message = "Merged " + branch + " into " + CURRENT_BRANCH + ".";
        createCommit(message, branch);

        if (conflictCount > 0) {
            System.out.println("Encountered a merge conflict.");
        }
    }

    /**
     * Processes a conflict between the current and given versions of a file, and stages the result.
     *
     * @param filename The name of the file in conflict.
     * @param current  The current version's blob ID of the file.
     * @param given    The given version's blob ID of the file.
     */
    private void processConflict(String filename, String current, String given) {
        String result = "<<<<<<< HEAD\n";
        String c;
        String g;

        if (current != null) {
            c = readContentsAsString(join(BLOBS_DIR, current));
            result = result.concat(c);
        }

        result = result.concat("=======\n");

        if (given != null) {
            g = readContentsAsString(join(BLOBS_DIR, given));
            result = result.concat(g);
        }

        result = result.concat(">>>>>>>\n");

        writeContents(join(CWD_DIR, filename), result);
        addFileToStaging(filename);
    }

    /**
     * Determines the split point between the current branch and a given branch.
     *
     * @param branch The name of the branch to compare with the current branch.
     * @return The commit ID of the split point between the current and given branch.
     * @throws IllegalArgumentException if the given branch is an ancestor of the current branch.
     */
    private String getSplitPoint(String branch) {
        String currentId = HEAD;
        String givenId = BRANCHES.get(branch);

        String result = null;

        Commit current = Commit.loadCommit(currentId);
        Commit given = Commit.loadCommit(givenId);

        List<String> currentParents = new ArrayList<>();
        List<String> givenParents = new ArrayList<>();

        currentParents.add(currentId);
        givenParents.add(givenId);

        while (current.parent != null) {
            currentParents.add(current.parent);

            if (current.secondParent != null) {
                currentParents.add(current.secondParent);
            }
            current = Commit.loadCommit(current.parent);
        }
        while (given.parent != null) {
            givenParents.add(given.parent);
            if (given.secondParent != null) {
                givenParents.add(given.secondParent);
            }
            given = Commit.loadCommit(given.parent);
        }

        for (String i : givenParents) {
            if (currentParents.contains(i)) {
                result = i;
                break;
            }
        }
        if (givenId.equals(result)) {
            errorMessage("Given branch is an ancestor of the current branch");
        }
        if (currentId.equals(result)) {
            checkoutBranch(branch);
            errorMessage("Current branch fast-forwarded");
        }

        return result;
    }
}
