package io.github.orionlibs.orion_data;

import java.io.IOException;
import java.io.InputStream;
import java.util.TimeZone;
import org.apache.commons.io.IOUtils;

public class ATest
{
    static
    {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        System.setProperty("active.execution.profile", OrionDomain.testing);
    }

    protected String loadResourceAsString(String fileLocation)
    {
        try
        {
            return IOUtils.toString(this.getClass().getResourceAsStream(fileLocation));
        }
        catch(IOException e)
        {
            return "";
        }
    }


    protected InputStream loadResourceAsStream(String fileLocation)
    {
        return this.getClass().getResourceAsStream(fileLocation);
    }
}
