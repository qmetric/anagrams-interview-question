package com.qmetric.anagrams;

import com.qmetric.anagrams.domain.Anagrams;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnagramsInFileTest
{
    @Test
    public void thatCanFindAnagramsInFile() throws Exception
    {
        final Anagrams anagrams = new AnagramsInFile().anagrams(wordListFile());

        final int totalAnagrams = anagrams.size();

        final String report = anagrams.report();

        assertEquals(totalAnagrams , 2);

        assertTrue(report.contains("a'b b'a"));

        assertTrue(report.contains("cdé édc"));
    }

    @Test(expected = IOException.class)
    public void thatThrowsExceptionIfWordListFileCannotBeFound() throws Exception
    {
        new AnagramsInFile().anagrams(new File("file/that/does/not/exist"));
    }

    private File wordListFile() throws Exception
    {
        return new File(this.getClass().getResource("/wordlist.txt").toURI());
    }
}
