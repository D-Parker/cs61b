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

    public Node current_branch;

    public Commit(){
        current_branch = createNode("initial_commit");
//        current_branch = new Node("initial commit", null);
    }

    public Node createNode(String m){
        return new Node(m, this.current_branch);
    }


    protected class Node {
        String message;
        Node parent;
        Node(String m, Node p) {
            message = m;
            parent = p;
        }
    }

    }

//    private void addNode(String message, Node n){
//        Node new_node = new Node(message, n);
//        n = new_node;
//
//    }




    /* TODO: fill in the rest of this class. */

