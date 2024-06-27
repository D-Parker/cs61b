package gitlet;

// TODO: any imports you need here
import static gitlet.Utils.*;
//import static gitlet.Repository.*;
import java.util.Date; // TODO: You'll likely use this in this class

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Commit {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    public static Node current_branch;

    public class Node {
        public String message;
        public Commit parent;

        public Node(String m, Node p) {
            message = m;
            parent = p;
        }
        public Node() {
            this.message = message;
            this.parent = parent;
        }
    }
    public Commit(){
        current_branch = new Node("initial commit", null);
    }
    public static void addNode(String message){
        Node new_node = new Node();
        new_node.message = message;
        new_node.parent = current_branch;
        current_branch = new_node;
    }








//    public Commit master;
//    public String id;
//    public String message;
//    public Commit parent;
//    public Commit current_branch;
//
//    // set up the master commit
//    public Commit(){
//        master = null;
//        id = "master";
//        message = "initial commit";
//        parent = null;
//    }
//
//    public createCommit(){
//        if (master == null){
//            master = new Commit()
//        }
//
//    }


//    public Node master;
//
//    public class Node {
//
//        public String id;
//        public String message;
//        public Node parent;
//        public Node(String i, String m, Node p){
//            id = i;
//            message = m;
//            parent = p;
//        }
//    }
//    public Commit(){
//        master = new Node("master","initial commit", null);
//    }


    /** The message of this Commit. */


    public Commit(String branch){}

//    public Commit(){
////        this.message = message;
////        this.commit_id = commit_id;
////        this.parent = parent;
//    }
//    public Commit(){
//        message = "initial commit";
//        commit_id = "master";
//        parent = null;
//    }
//
//    public Commit(String hash){
//        commit_id
//    }

    // if current branch is null, create master branch




    }

    /* TODO: fill in the rest of this class. */
}
