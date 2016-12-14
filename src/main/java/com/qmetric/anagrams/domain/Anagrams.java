package com.qmetric.anagrams.domain;

import java.util.Collection;
import java.util.List;

import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;

public class Anagrams
{
    private final Collection<List<String>> anagramsCollection;

    public Anagrams(final Collection<List<String>> anagramsCollection)
    {
        this.anagramsCollection = anagramsCollection;
    }

    public int size()
    {
        return anagramsCollection.size();
    }

    public String report()
    {
        return anagramsCollection.stream().map(this::spaceSeparatedAnagrams).collect(joining(lineSeparator()));
    }

    private String spaceSeparatedAnagrams(final List<String> anagrams)
    {
        return anagrams.stream().collect(joining(" "));
    }
}
