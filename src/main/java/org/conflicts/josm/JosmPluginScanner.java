package org.conflicts.josm;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.conflicts.ConflictScanner;
import org.conflicts.dto.ConflictResult;

public class JosmPluginScanner {

    private LocalCache localCache = new LocalCache();

    List<URL> pluginUrls() throws IOException {

        List<URL> ret = new ArrayList<>();
        PluginListProvider provider = new PluginListProvider();

        List<JosmPlugin> listOfPlugins = provider.getListOfPlugins();

        for (JosmPlugin josmPlugin : listOfPlugins) {

            String cacheName = localCache.makeCacheName(josmPlugin);
            System.out.println("file to scan: " + cacheName);

            ret.add(new File(cacheName).toURL());
        }

        return ret;
    }

    void cachePlugins() {

    }

    public List<ConflictResult> findConflicts() throws IOException {

        List<URL> pluginUrls = pluginUrls();

        ConflictScanner cs = new ConflictScanner();

        return cs.scan(pluginUrls);
    }

}
