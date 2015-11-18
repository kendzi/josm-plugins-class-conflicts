package org.conflicts;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.conflicts.dto.ConflictResult;
import org.conflicts.dto.Module;

public class ConflictScanner {

    public List<ConflictResult> scan(List<URL> pluginUrls) throws IOException {

        ModuleReader mr = new ModuleReader();
        ModuleConflictScaner mcs = new ModuleConflictScaner();

        List<Module> modules = new ArrayList<>();
        List<ConflictResult> results = new ArrayList<>();
        for (URL url : pluginUrls) {
            Module module = mr.read(url);

            modules.add(module);
        }

        for (int i = 0; i < modules.size(); i++) {

            Module m1 = modules.get(i);
            for (int j = i + 1; j < modules.size(); j++) {
                Module m2 = modules.get(j);

                results.add(mcs.compare(m1, m2));
            }
        }

        return results;
    }

}
