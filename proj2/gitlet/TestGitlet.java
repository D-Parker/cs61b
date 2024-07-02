package gitlet;

import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

import static gitlet.Utils.join;
import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.TreeMap;
import gitlet.*;
import java.util.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.*;
import java.util.TreeMap;
import java.io.Serializable;
import static gitlet.Utils.*;
import java.io.File;
import java.io.IOException;



public class TestGitlet implements Serializable {

//    public static void appendToFile(File file, String content) {
//        // Use FileWriter in append mode (true) and wrap it with BufferedWriter
//        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
//            writer.write(content);
//            writer.newLine();
//        }


    @Test
    public void TestCheckout() {

        // commit #1
//        Repository abc = new Repository();
        Repository L = Repository.loadRepository();

//        File a2 = join(L.CWD_DIR, "a2.txt");
//        a2.createNewFile();
//        L.addFile("abc.txt");
//        L.createCommit("adding 3");

//        L.checkout("6f9d9ad3696270c4601b1546c8bbc748e492d06c","abc.txt");

        // commit #2
//        L.createCommit("adding file abc.txt");
//        L.createCommit("adding file abc.txt with change 1");
//
//        appendToFile(a1, "First change");

//        L.addFile("xyz.txt");
//        L.createCommit("adding xyz.txt");
//        L.addFile("abc.txt");
//        L.createCommit("adding abc.txt");
//
//        L.checkout("ebbf0e72a1cfcf61efa594af2bf56c7705462854","xyz.txt");

//        String g = L.BRANCHES.get("HEAD");
//        StdOut.print(g);
//
//        L.checkout("abc.txt");

        System.out.println(L.BRANCHES.get("master"));
        System.out.println(L.BRANCHES.get("HEAD"));
        System.out.println(L.BRANCHES.get("current_branch"));
    System.out.println(L.BRANCHES_ORIGIN.get("master"));




    }

    @Test
    public void TestloadRepository() {
        Repository abc = new Repository();
        abc = null;
        Repository L = Repository.loadRepository();
    }

    @Test
    public void TestRepositoryConstructor() {
        Repository abc = new Repository();
    }

//    @Test
//
//    public void TestRepositoryConstructor(){
////        Repository abc = new Repository();
////        System.out.println(abc.MASTER);
//
//        Repository temp = new Repository();
//        System.out.println(temp.STAGING_DIR);
//
//        temp.BRANCHES.put("master",null);
//        temp.BRANCHES.put("current",null);
//
//        Repository.addFile("abc.txt");
//        Repository.addFile("xyz.txt");
//
//        temp.saveRepository();
//
//        Commit c = new Commit();
//
////        c.createBlobMap();
////        System.out.println(c.blob_map.size());
//
//        List<String> temp2 = c.getStagingFiles();
//        System.out.println(temp2.size());
//
//
//
//        c.createBlobMap();
//
////        System.out.println(c.blob_map.size());
//
////        c.blob_map.size()
//
//
////        for (TreeMap<String, String> entry : c.blob_map) {
////            System.out.println(get(entry));
////        }
//
////        Repository base = Repository.loadRepository();
////        System.out.println(base.BRANCHES.size() );
//
////        Repository L = Repository.loadRepository();
////        System.out.println(L.MASTER);
//
//    }

    @Test

    public void TestAddFile(){
//        Repository abc = new Repository();
//        System.out.println(abc.MASTER);
        Repository.addFile("xyz.txt");
        Repository.addFile("jkl.txt");
        Repository.addFile("abc.txt");

    }


//    @Test

//    public void TestAddCommit(){
//        addCommit();
//
//    }



}
