package gradle.tag.plugin.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

public class ShellRunnerCommand implements Command{

    private static final Logger log = LogManager.getLogger(ShellRunnerCommand.class);

    private ShellRunnerCommand() {}

    @Override
    public Optional<String> execute(String command) {

        try {
            Process process = Runtime.getRuntime().exec(command);
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("OUTPUT" + output);
                log.info("ShellRunnerCommand: output result: {}", output.toString());
                return Optional.of(output.toString());
            } else {
                log.error("ShellRunnerCommand: process is not normal terminate");
                return Optional.empty();
            }

        } catch (IOException | InterruptedException e) {
            log.error("Occur exception, when read", e);
            return Optional.empty();
        }
    }

        public static ShellRunnerCommand getInstance() {
            return Holder.INSTANCE;
        }

        private static class Holder {
            public static final ShellRunnerCommand INSTANCE = new ShellRunnerCommand();
        }

}
