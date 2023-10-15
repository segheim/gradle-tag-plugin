package gradle.tag.plugin.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShellRunnerCommand implements Command {

    private static final Logger log = LogManager.getLogger(ShellRunnerCommand.class);

    private ShellRunnerCommand() {
    }

    @Override
    public List<String> execute(String command) {

        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            List<String> output = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                output.add(line);
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("OUTPUT" + output);
                log.info("ShellRunnerCommand: output result: {}", output.toString());
                return output;
            } else {
                log.error("ShellRunnerCommand: process is not normal terminate");
                return Collections.emptyList();
            }

        } catch (IOException | InterruptedException e) {
            log.error("Occur exception, when read", e);
            return Collections.emptyList();
        }
    }

    public static ShellRunnerCommand getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShellRunnerCommand INSTANCE = new ShellRunnerCommand();
    }

}
