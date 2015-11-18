package org.conflicts;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.conflicts.dto.Module;

public class ModuleReader {

    public Module read(URL url) throws IOException {
        InputStream inputStream = url.openStream();

        Module module = new Module();
        module.setName(url.toString());

        Set<String> classes = new HashSet<>();
        Set<String> resources = new HashSet<>();
        Set<String> submodules = new HashSet<>();
        try {
            read(inputStream, classes, resources, submodules);
        } catch (EOFException e) {
            System.out.println("cant read corupted zip: " + url.toString());
        }
        module.setClasses(classes);
        module.setResources(resources);
        module.setSubModules(submodules);

        return module;
    }

    private void read(InputStream inputStream, Set<String> classes, Set<String> resources, Set<String> submodules)
            throws IOException {

        ZipInputStream zis = new ZipInputStream(inputStream);
        ZipEntry nextEntry = null;
        while ((nextEntry = zis.getNextEntry()) != null) {

            if (!nextEntry.isDirectory()) {

                String fileName = nextEntry.getName();
                if (fileName.endsWith(".class")) {
                    classes.add(fileName);
                } else if (fileName.endsWith(".jar")) {
                    submodules.add(fileName);
                    System.out.println("find submodule: " + fileName);
                    read(zis, classes, resources, submodules);

                } else {
                    resources.add(fileName);
                }
            }
        }

    }

}
