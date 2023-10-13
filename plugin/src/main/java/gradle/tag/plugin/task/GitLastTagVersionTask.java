package gradle.tag.plugin.task;

import gradle.tag.plugin.command.ShellRunnerCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.util.Optional;

public class GitLastTagVersionTask extends DefaultTask {

    private static final Logger log = LogManager.getLogger(GitLastTagVersionTask.class);

    public static final String GIT_COMMAND_LAST_TAG_VERSION = "git describe --abbrev=0 --tags";

    @TaskAction
    public void getLastTagVersion() {
        String lastTagVersion = "";
        Optional<String> lastTagVersionOptional = ShellRunnerCommand.getInstance().execute(GIT_COMMAND_LAST_TAG_VERSION);
        if (lastTagVersionOptional.isPresent()) {
            if (lastTagVersionOptional.get() != "") {
                log.info("Build: {}.uncommitted", lastTagVersionOptional.get());
                lastTagVersion = lastTagVersionOptional.get();
            }
        }
        this.getExtensions().add("result", lastTagVersion);
    }
}
