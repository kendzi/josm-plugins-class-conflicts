package org.conflicts.josm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class JosmPluginDownloader {

    private LocalCache localCache = new LocalCache();

    public void downloadPlugins() throws IOException {
        PluginListProvider provider = new PluginListProvider();

        List<JosmPlugin> plugins = provider.getListOfPlugins();

        for (JosmPlugin josmPlugin : plugins) {

            String name = localCache.makeCacheName(josmPlugin);

            download(name, josmPlugin.getUrl());
        }
    }

    private void download(String name, String urlStr) throws IOException {

        File file = new File(name);
        if (file.exists()) {
            return;
        }

        file.getParentFile().getAbsoluteFile().mkdirs();

        InputStream is = new URL(urlStr).openStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        IOUtils.copy(is, baos);

        baos.close();
        is.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        FileOutputStream fos = new FileOutputStream(file);

        IOUtils.copy(bais, fos);

        bais.close();
        fos.close();

        System.out.println("downloaded file: " + file.getAbsolutePath());
    }

    public static void main(String[] args) throws IOException {

        new JosmPluginDownloader().downloadPlugins();
    }
}
