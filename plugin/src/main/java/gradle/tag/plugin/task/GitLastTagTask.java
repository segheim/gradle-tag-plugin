package gradle.tag.plugin.task;

import gradle.tag.plugin.command.ShellRunnerCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.util.Optional;

public class GitLastTagTask extends DefaultTask {

    private final Logger log;

    public static final String GIT_COMMAND_LAST_TAG = "git describe --abbrev=0 --tags";

    public GitLastTagTask() {
        log = LogManager.getLogger(GitLastTagTask.class);
    }

    @TaskAction
    public void getLastTag() {
        Optional<String> lastTag = ShellRunnerCommand.getInstance().execute(GIT_COMMAND_LAST_TAG);
        if (lastTag.isPresent()){
            log.info("Last tag: {}", lastTag.get());
            if (lastTag.get() != "") {
                this.getExtensions().add("tagValue", lastTag.get());
            }
        }
        log.info("Last tag absent");
        this.getExtensions().add("tagValue", "");
    }
}
