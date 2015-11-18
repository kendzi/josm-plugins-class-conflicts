package org.conflicts;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.conflicts.dto.Module;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Some internal tests.
 */
@RunWith(JUnit4.class)
public class InternalTest {

    @SuppressWarnings("javadoc")
    @Test
    public void testAll() throws IOException

    {

        URL module1url = this.getClass().getResource("/module1.jar");

        ModuleReader mr = new ModuleReader();

        Module module1 = mr.read(module1url);

        Set<String> expectedClasses = new HashSet<>(Arrays.asList( //
                "sub_foo/sub_foo1.class", //
                "sub_foo/sub_foo2.class", //
                "foo/foo2.class", //
                "foo/foo1.class" //
        ));

        Assert.assertEquals(expectedClasses, module1.getClasses());

    }

}
