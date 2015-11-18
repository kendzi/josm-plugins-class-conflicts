package org.conflicts.dto;

import java.util.HashSet;
import java.util.Set;

public class Module {

    private String name;

    private Set<String> classes = new HashSet<>();

    private Set<String> resources = new HashSet<>();

    private Set<String> subModules = new HashSet<>();

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the classes
     */
    public Set<String> getClasses() {
        return classes;
    }

    /**
     * @param classes
     *            the classes to set
     */
    public void setClasses(Set<String> classes) {
        this.classes = classes;
    }

    /**
     * @return the resources
     */
    public Set<String> getResources() {
        return resources;
    }

    /**
     * @param resources
     *            the resources to set
     */
    public void setResources(Set<String> resources) {
        this.resources = resources;
    }

    /**
     * @return the subModules
     */
    public Set<String> getSubModules() {
        return subModules;
    }

    /**
     * @param subModules
     *            the subModules to set
     */
    public void setSubModules(Set<String> subModules) {
        this.subModules = subModules;
    }

}
