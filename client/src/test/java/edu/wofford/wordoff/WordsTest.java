package edu.wofford;

import org.junit.*;
import static org.junit.Assert.*;

public class WordsTest{
    private Words words;

    @Before
    public void setUp(){
      words = new Words("test");
    }

    /*@Test
    public void testIsWord(){
      words.setWord("sleep");
      assertEquals(true, words.isWord());
    }

    @Test
    public void testGetWord(){
      words.setWord("sleep");
      assertEquals("sleep", words.GetWord());
    }

    @Test
    public void testSetWord(){
      words.setWord("sleep");
      assertEquals("sleep", words.getWord());
    }
    @Test
    public void testSomething(){

    }
    */
    }
