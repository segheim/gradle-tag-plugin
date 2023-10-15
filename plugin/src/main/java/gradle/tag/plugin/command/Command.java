package gradle.tag.plugin.command;

import java.io.IOException;
import java.util.List;

interface Command {

    List<String> execute(String command) throws IOException;

}
