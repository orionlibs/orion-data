package io.github.orionlibs.orion_data;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextFileDetector implements FileTypeDetector
{
    public static boolean isTextFile(InputStream stream) throws IOException
    {
        return isTextFile(stream.readAllBytes());
    }


    public static boolean isTextFile(File file)
    {
        return isTextFile(file.toPath());
    }


    public static boolean isTextFile(Path filePath)
    {
        try
        {
            return isTextFile(Files.readAllBytes(filePath));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }


    public static boolean isTextFile(byte[] content)
    {
        int sampleSize = 1024;
        int lengthToCheck = Math.min(sampleSize, content.length);
        for(int i = 0; i < lengthToCheck; i++)
        {
            byte b = content[i];
            // Check for non-printable binary characters
            if(b < 0x09 || (b > 0x0D && b < 0x20) || b == 0x7F)
            {
                return false;
            }
        }
        return true;
    }
}
