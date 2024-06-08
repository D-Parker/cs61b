package flik;


import java.util.Iterator;

import org.junit.Test;
import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Optional;


public class FlikTest {

    @Test
    public void isSameNumberTest() {


        int a = 5;
        int b = 5;
        int c = 9;

        assertTrue(Flik.isSameNumber(a, b) );

        assertFalse(Flik.isSameNumber(b, c) );

        int e = 128;
        int f = 128;

//        Flik.isSameNumber(e, f);

        assertTrue(Flik.isSameNumber(e, f) );


    }
}