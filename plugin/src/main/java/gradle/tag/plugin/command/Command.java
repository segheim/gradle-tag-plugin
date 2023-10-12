package gradle.tag.plugin.command;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

interface Command {

    Optional<String> execute(String command) throws IOException;

}