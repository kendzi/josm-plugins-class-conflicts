package org.conflicts.dto;

import java.util.List;

public class ConflictResult {
    private final Module module1;
    private final Module module2;
    private final List<String> classes;
    private final List<String> resources;

    public ConflictResult(Module module1, Module module2, List<String> classes, List<String> resources) {
        super();
        this.module1 = module1;
        this.module2 = module2;
        this.classes = classes;
        this.resources = resources;
    }

    /**
     * @return the module1
     */
    public Module getModule1() {
        return module1;
    }

    /**
     * @return the module2
     */
    public Module getModule2() {
        return module2;
    }

    /**
     * @return the classes
     */
    public List<String> getClasses() {
        return classes;
    }

    /**
     * @return the resources
     */
    public List<String> getResources() {
        return resources;
    }

}
