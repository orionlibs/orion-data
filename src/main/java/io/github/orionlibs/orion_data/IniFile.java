package io.github.orionlibs.orion_data;

import io.github.orionlibs.orion_tuple.Pair;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IniFile
{
    private static final String sectionRegEx = "\\[(.*)\\]";
    private static final String propertyRegEx = "(.*)=(.*)";
    private static Pattern sectionPattern;
    private static Pattern propertyPattern;


    public static Map<String, Set<Pair<String, String>>> load(InputStream stream)
    {
        try
        {
            return load(new String(stream.readAllBytes(), Charset.forName("UTF-8")).lines().toList());
        }
        catch(IOException e)
        {
            return Map.of();
        }
    }


    public static Map<String, Set<Pair<String, String>>> load(File file)
    {
        return load(file.toPath());
    }


    public static Map<String, Set<Pair<String, String>>> load(Path filePath)
    {
        try
        {
            return load(Files.readAllLines(filePath));
        }
        catch(IOException e)
        {
            return Map.of();
        }
    }


    public static Map<String, Set<Pair<String, String>>> load(List<String> content)
    {
        Map<String, Set<Pair<String, String>>> config = new HashMap<>();
        if(content != null)
        {
            initialiseSectionPattern();
            initialisePropertyPattern();
            String currentSectionName = null;
            for(String line : content)
            {
                Matcher sectionMatcher = sectionPattern.matcher(line);
                if(sectionMatcher.matches())
                {
                    currentSectionName = sectionMatcher.group(1);
                    if(!config.containsKey(currentSectionName))
                    {
                        config.put(currentSectionName, new HashSet<>());
                    }
                }
                else
                {
                    Matcher propertyMatcher = propertyPattern.matcher(line);
                    if(propertyMatcher.matches())
                    {
                        String propertyKey = propertyMatcher.group(1);
                        String propertyValue = propertyMatcher.group(2);
                        config.get(currentSectionName).add(Pair.of(propertyKey, propertyValue));
                    }
                }
            }
        }
        return config;
    }


    private static void initialiseSectionPattern()
    {
        if(sectionPattern == null)
        {
            sectionPattern = Pattern.compile(sectionRegEx);
        }
    }


    private static void initialisePropertyPattern()
    {
        if(propertyPattern == null)
        {
            propertyPattern = Pattern.compile(propertyRegEx);
        }
    }
}
