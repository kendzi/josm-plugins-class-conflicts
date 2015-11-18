package org.conflicts.decorator;

import java.util.List;

import org.conflicts.dto.ConflictResult;

public class RaportDecorator {
    private final ConflictResult conflictResult;

    public RaportDecorator(ConflictResult conflictResult) {
        this.conflictResult = conflictResult;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        List<String> classes = conflictResult.getClasses();
        // XXX
        if (classes != null && classes.size() > 0) {
            sb.append("Conflict between ");
            sb.append(conflictResult.getModule1().getName());
            sb.append(", ");
            sb.append(conflictResult.getModule2().getName());
            sb.append(", classes in conflict: \n");

            ;
            for (String string : classes) {
                sb.append("- ");
                sb.append(string);
                sb.append("\n");
            }
        }

        return sb.toString();
    }
}
