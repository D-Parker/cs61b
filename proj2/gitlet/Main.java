package gitlet;

import java.util.ResourceBundle;

import static gitlet.Repository.*;
import static gitlet.Commit.*;

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
        validateNumArgs(args);
        // return if args are empty
        // what if there is already a repository?
//        System.out.println(System.getProperty("user.dir"));
//        /Users/danielparker/Documents/cs61b/skeleton-sp21/proj2/gitlet

        String cmd = args[0];
        String secondArg = null;
//        String thirdArg ;
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

                // adding file to staging doesn't involve the repo

                Repository.addFile(secondArg);

//                repo.addFile(secondArg);

                break;
            // TODO: FILL THE REST IN
            case "commit":

                // load the repository into memory

                Commit.Commit(secondArg);


                Repository repo = Repository.loadRepository();

                Commit temp_commit = repo.newCommit();

                temp_commit.addCommit();

                Commit.Commit(secondArg);


//                if (MASTER==null){
//                    MASTER = new Commit();

//                else{
//                    MASTER.createNode(thirdArg);
//                }
                break;
//            case "checkout -- [file name]":
//                break;
//            case "checkout [commit id] -- [file name]":
//                break;
//            case "log":
//                break;
//            case "":
//                break;
        }


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
    }

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

    }
        
}


