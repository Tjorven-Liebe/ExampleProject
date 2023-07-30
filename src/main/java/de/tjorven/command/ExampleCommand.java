package de.tjorven.command;

import java.util.List;

public class ExampleCommand extends AbstractCommand {

    @Override
    boolean command() {
        getMessage("");

        return false;
    }

    @Override
    List<String> tab() {
        return null;
    }
}
