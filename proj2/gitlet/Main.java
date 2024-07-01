package gitlet;
import java.time.Instant;
import java.util.ResourceBundle;
import static gitlet.Repository.*;
import static gitlet.Commit.*;
import static gitlet.Utils.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Driver class for Gitlet, a subset of the Git version-control system.
 *
 * @author Michael Smith
 */
public class Main {
    /**
     * Usage: java gitlet.Main ARGS, where ARGS contains
     * <COMMAND> <OPERAND1> <OPERAND2> ...
     */
    public static void main(String[] args) {
        // TODO: what if args is empty?
        // this is for

//        Repository repo;
//        if (GITLET_DIR.exists()){
//            repo = loadRepository();
//        }


        validateNumArgs(args);
        // return if args are empty
        // what if there is already a repository?
//        System.out.println(System.getProperty("user.dir"));
//        /Users/danielparker/Documents/cs61b/skeleton-sp21/proj2/gitlet

        String cmd = args[0];
        String secondArg = null;
        if (args.length >= 2) {
            secondArg = args[1];
        }

        switch (cmd) {
            case "init":
                // TODO: handle the `init` command

                // need to create and save the repository
                Repository temp = new Repository();

                break;
            case "add":
                // TODO: handle the `add [filename]` command
                Repository.addFile(secondArg);
                break;
            // TODO: FILL THE REST IN
            case "commit":
        }
    }


                // cancel if staging directory is empty


                // if commit folder is empty
//                createInitialCommit();
//
//                // else if commit folder is not empty
//                createCommit(message);

//                repo = loadRepository();

//                if (plainFilenamesIn(Repository.STAGING_DIR).size()==0){
//                    return;
//                }

//                Commit c = new Commit();

                // update all of Commit c's instance variables

//                if (repo.hasBranches()==false){
//                    c.ts = Instant.EPOCH;
//                    c.message = "initial commit";
//                    c.parent = null;
//                    c.second_parent = null;
//                } else {
//                    c.ts = Instant.now();
//                    c.message = secondArg;
//                    c.parent = repo.BRANCHES.get("current_branch");
//                }
//
//                c.createBlobMap();

//                private String generateId() {
//                return sha1(getTimestamp(), message, parents.toString(), tracked.toString());
//
//                return sha1(Instant.now(), message, parent.toString(), secondparent.toString(), blob_map.toString());


                // Get hash for the commit

                // Save commit to disk





//                String c_hash = c.getCommitHash();

//                File temp1 = join(Repository.COMMITS_DIR, "temp");
//              writeObject(temp1, c);
////
//                String c_serial = readContentsAsString(temp1);
//                String c_hash = sha1(c_serial);

//                byte[] c_serial = readObject(c, Commit.class);
//                String c_hash = sha1(serialize(c));

//                if (repo.hasBranches()==false){
//                    repo.BRANCHES.put("master", c_hash);
//                    repo.BRANCHES.put("current_branch", c_hash);
//                    repo.BRANCHES.put("HEAD", c_hash);
//                } else {
//                    repo.BRANCHES.put("current_branch", c_hash);
//                    repo.BRANCHES.put("HEAD", c_hash);
//                }
//                c.saveCommit();
//                repo.saveRepository();




//                Commit.Commit(secondArg);
//
//
//                Repository repo = Repository.loadRepository();
//
//                Commit temp_commit = repo.newCommit();
//
//                temp_commit.addCommit();
//
//                Commit.Commit(secondArg);


//                if (MASTER==null){
//                    MASTER = new Commit();

//                else{
//                    MASTER.createNode(thirdArg);
//                }
//                break;
//            case "checkout -- [file name]":
//                break;
//            case "checkout [commit id] -- [file name]":
//                break;
//            case "log":
//                break;
//            case "":
//                break;



//    public static void validateNumArgs(String cmd, String[] args, int n) {
//        if (args.length != n) {
//            throw new RuntimeException(
//                    String.format("Invalid number of arguments for: %s.", cmd));
//        }
//    }

        // Validates commands for complete syntax


//    public static void validateArgs(String cmd, String[] args, int n) {
//        if (args.length != n) {
//            throw new RuntimeException(
//                    String.format("Invalid number of arguments for: %s.", cmd));
//        }
//    }



    public static void validateNumArgs(String[] args) {

        if (args.length == 0) {
            System.out.println("No arguments provided. Terminating program.");
            System.exit(0);
        }
        String cmd = args[0];
        if (cmd == "init" && args.length != 1) {
            throw new RuntimeException(
                    String.format("Invalid number of arguments for: %s.", cmd));
        }
        if (cmd == "init" && GITLET_DIR.exists()) {
            System.out.println("A Gitlet version-control system already exists in the current directory");
            System.exit(0);
        }
        if (cmd == "add" && args.length != 2) {
            throw new RuntimeException(
                    String.format("Invalid number of arguments for: %s.", cmd));
        }
        if (cmd == "commit" && args.length != 2) {
            throw new RuntimeException(
                    String.format("Invalid number of arguments for: %s.", cmd));
        }

//            if (cmd == "commit" && BLOBS_DIR. != 2) {
//                throw new RuntimeException(
//                        String.format("Invalid number of arguments for: %s.", cmd));
//        }

    }

}


