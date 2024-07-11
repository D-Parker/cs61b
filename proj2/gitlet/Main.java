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
                File rf = join(GITLET_DIR, "repository");
                if (rf.exists()) {
                    System.out.println("A Gitlet version-control system already exists in the current directory");
                    System.exit(0);
                }

                Repository temp = new Repository();

                break;
            case "add":
                // TODO: handle the `add [filename]` command
                Repository K = loadRepository();
                K.addFileToStaging(secondArg);
                break;
            // TODO: FILL THE REST IN
            case "commit":
                Repository repo = loadRepository();
                repo.createCommit(secondArg, null);
                break;
            case "checkout":
                Repository r = loadRepository();
                if (args.length == 3) {
                    if (!args[1].equals("--")) {
                        System.out.println("Incorrect operands.");
                        System.exit(0);
                    }
                    r.checkout(args[2]);

                }
                if (args.length == 4) {
                    if (!args[2].equals("--")) {
                        System.out.println("Incorrect operands.");
                        System.exit(0);
                    }
                    r.checkout(args[1], args[3]);

                }
                if (args.length == 2) {
                    r.checkoutBranch(args[1]);

                }

                break;
            case "log":
                Repository x = loadRepository();
                x.printLog();
                break;
            case "global-log":
                Repository xy = loadRepository();
                xy.printGlobalLog();
                break;
            case "find":
                Repository xyz = loadRepository();
                xyz.find(args[1]);
                break;
            case "status":
                Repository d = loadRepository();
                d.status();
                break;
            case "rm":
                Repository y = loadRepository();
                y.removeFile(args[1]);
                break;
            case "branch":
                Repository yy = loadRepository();
                yy.branch(args[1]);
                break;
            case "rm-branch":
                Repository jj = loadRepository();
                jj.removeBranch(args[1]);
                break;
            case "reset":
                Repository jk = loadRepository();
                jk.reset(args[1]);
                break;
            case "merge":
                Repository jkl = loadRepository();
                jkl.merge(args[1]);
                break;
            default:
                System.out.println("No command with that name exists.");
                System.exit(0);
                break;
        }
    }

    public static void validateNumArgs(String[] args) {

        if (args.length == 0) {
            System.out.println("Please enter a command.");
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


