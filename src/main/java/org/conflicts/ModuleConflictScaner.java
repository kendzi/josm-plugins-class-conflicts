package org.conflicts;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.conflicts.dto.ConflictResult;
import org.conflicts.dto.Module;

public class ModuleConflictScaner {

    public ConflictResult compare(Module m1, Module m2) {

        List<String> conflictsClassess = compareClasses(m1.getClasses(), m2.getClasses());

        return new ConflictResult(m1, m2, conflictsClassess, null);

    }

    private List<String> compareClasses(Set<String> classes1, Set<String> classes2) {

        List<String> ret = new ArrayList<>();

        for (String className : classes1) {
            if (classes2.contains(className)) {
                ret.add(className);
            }
        }
        return ret;
    }

}
