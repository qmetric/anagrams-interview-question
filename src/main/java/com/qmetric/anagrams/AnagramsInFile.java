package com.qmetric.anagrams;

import com.qmetric.anagrams.domain.Anagrams;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

class AnagramsInFile
{
    private static final Predicate<List<String>> ANAGRAMS_ONLY = l -> l.size() > 1;

    Anagrams anagrams(final File file) throws IOException
    {
        final Set<List<String>> anagramsSet =
                        Files.lines(file.toPath(), UTF_8)
                        .collect(groupingBy(sortedCharacters()))
                        .entrySet()
                        .stream()
                        .map(Map.Entry::getValue)
                        .filter(ANAGRAMS_ONLY)
                        .collect(toSet());

        return new Anagrams(anagramsSet);
    }

    private Function<String, String> sortedCharacters()
    {
        return word -> word.chars().sorted().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }

    public static void main(String[] args)
    {
        if (args.length < 1)
        {
            System.out.println("Please provide the path to the word list file e.g anagrams /path/to/wordlist.txt"); // NOSONAR
            return;
        }

        final File file = new File(args[0]);

        try
        {
            final AnagramsInFile list = new AnagramsInFile();

            final Anagrams anagrams = list.anagrams(file);

            System.out.println(anagrams.report()); // NOSONAR

            System.out.println(String.format("Total Anagrams %s", anagrams.size())); // NOSONAR

        }
        catch (Exception e) // NOSONAR
        {
            throw new RuntimeException(String.format("Failed to read word list file: %s", file.getAbsolutePath())); // NOSONAR
        }
    }
}
