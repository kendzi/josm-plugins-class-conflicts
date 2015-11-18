package org.conflicts.josm;

public class LocalCache {

    public String makeCacheName(JosmPlugin josmPlugin) {

        return getFileDirectiory() + josmPlugin.getVersion() + "_" + josmPlugin.getFile();
    }

    private String getFileDirectiory() {
        return "./cache/";
    }
}
