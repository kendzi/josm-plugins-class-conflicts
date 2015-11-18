package org.conflicts.josm;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.conflicts.decorator.RaportDecorator;
import org.conflicts.dto.ConflictResult;
import org.junit.Test;

/**
 * Check class conflicts inside josm classes.
 */
public class JosmPluginTest {

    /**
     * Download all plugins to local cache and check class conflicts inside
     * them.
     *
     * @throws IOException
     *             in any error
     */
    @Test
    public void testsConflictsInJosmPlugins() throws IOException {

        new JosmPluginDownloader().downloadPlugins();

        JosmPluginScanner scaner = new JosmPluginScanner();

        List<ConflictResult> findConflicts = scaner.findConflicts();

        List<String> results = new ArrayList<>();
        for (ConflictResult conflictResult : findConflicts) {
            String resultRaport = new RaportDecorator(conflictResult).toString();

            if (StringUtils.isNotBlank(resultRaport)) {
                results.add(resultRaport);
            }
        }

        // XXX
        if (results.size() != 0) {
            String collect = results.stream().collect(Collectors.joining(", "));

            fail("there are conflicts:\n" + collect);
        }
    }

}
