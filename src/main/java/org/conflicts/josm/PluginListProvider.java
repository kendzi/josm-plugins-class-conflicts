package org.conflicts.josm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PluginListProvider {

    public List<JosmPlugin> getListOfPlugins() throws IOException {
        List<JosmPlugin> ret = new ArrayList<>();
        URL url = new URL("https://josm.openstreetmap.de/plugin");
        InputStream io = url.openStream();

        BufferedReader in = new BufferedReader(new InputStreamReader(io));

        JosmPlugin jp = null;

        String line = null;
        while ((line = in.readLine()) != null) {

            if (jp == null && isUrl(line)) {
                jp = new JosmPlugin();
                jp.setUrl(readUrl(line));
                jp.setFile(readName(line));

            } else if (jp != null && readVersion(line) != null) {
                jp.setVersion(readVersion(line));
                ret.add(jp);
                jp = null;
            }

        }

        return ret;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new PluginListProvider().getListOfPlugins());
    }

    private String readVersion(String line) {
        String pluginStr = "Plugin-Version: ";
        int indexOf = line.indexOf(pluginStr);

        if (indexOf != -1) {
            return line.substring(indexOf + pluginStr.length()).trim();
        }
        return null;
    }

    private boolean isUrl(String line) {
        int indexOf = line.indexOf(".jar;");

        return indexOf != -1;
    }

    private String readName(String line) {
        int indexOf = line.indexOf(".jar;");

        if (indexOf != -1) {
            return line.substring(0, indexOf + 4).trim();
        }
        return null;
    }

    private String readUrl(String line) {
        int indexOf = line.indexOf(".jar;");

        if (indexOf != -1) {
            return line.substring(indexOf + 5).trim();
        }
        return null;
    }

}
