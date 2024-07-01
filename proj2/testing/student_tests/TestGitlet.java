package testing.student_tests;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.TreeMap;
import gitlet.*;
import java.util.*;

public class TestGitlet  {

    @Test

    public void TestRepositoryConstructor(){
//        Repository abc = new Repository();
//        System.out.println(abc.MASTER);

        Repository temp = new Repository();
        System.out.println(temp.STAGING_DIR);

        temp.BRANCHES.put("master",null);
        temp.BRANCHES.put("current",null);

        Repository.addFile("abc.txt");
        Repository.addFile("xyz.txt");

        temp.saveRepository();

        Commit c = new Commit();

//        c.createBlobMap();
//        System.out.println(c.blob_map.size());

        List<String> temp2 = c.getStagingFiles();
        System.out.println(temp2.size());



        c.createBlobMap();

        System.out.println(c.blob_map.size());

//        c.blob_map.size()


//        for (TreeMap<String, String> entry : c.blob_map) {
//            System.out.println(get(entry));
//        }

//        Repository base = Repository.loadRepository();
//        System.out.println(base.BRANCHES.size() );

//        Repository L = Repository.loadRepository();
//        System.out.println(L.MASTER);

    }

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
