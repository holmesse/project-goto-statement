package edu.wofford;

import org.junit.*;
import static ord.junit.Asser.*;

public class WordsTest{
    private Words words;

    @Before
    public void setUp(){
      words = new Words();
    }

    @Test
    public void testIsWord(){
      words.setWord("sleep");
      assertEquals(true, words.isWord());
    }
    @Test
    public void testGetWord(){
      words.setWord("sleep");
      assertEquals("sleep", words.GetWord());
    }
    }
