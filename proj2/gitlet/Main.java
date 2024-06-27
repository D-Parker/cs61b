package gitlet;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author TODO
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) {
        // TODO: what if args is empty?
        // return if args are empty
        if (args.length == 0) {
            return;
        }

        // what if there is already a repository?


//        System.out.println(System.getProperty("user.dir"));
//        /Users/danielparker/Documents/cs61b/skeleton-sp21/proj2/gitlet


        String firstArg = args[0];
        String secondArg;
        if (args.length == 2){
            secondArg = args[1];
        }

        switch(firstArg) {
            case "init":
                // TODO: handle the `init` command
                Repository.init();
                break;
            case "add":
                // TODO: handle the `add [filename]` command
                Repository.addFile(secondArg);



                break;
            // TODO: FILL THE REST IN
//            case "":
//                break;
//            case "":
//                break;
//            case "":
//                break;
//            case "":
//                break;
//            case "":
//                break;
        }
    }
}
