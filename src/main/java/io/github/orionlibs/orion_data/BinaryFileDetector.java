package io.github.orionlibs.orion_data;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public class BinaryFileDetector implements FileTypeDetector
{
    public static boolean isBinaryFile(InputStream stream) throws IOException
    {
        return !TextFileDetector.isTextFile(stream);
    }


    public static boolean isBinaryFile(File file)
    {
        return !TextFileDetector.isTextFile(file);
    }


    public static boolean isBinaryFile(Path filePath)
    {
        return !TextFileDetector.isTextFile(filePath);
    }


    public static boolean isBinaryFile(byte[] content)
    {
        return !TextFileDetector.isTextFile(content);
    }
}
