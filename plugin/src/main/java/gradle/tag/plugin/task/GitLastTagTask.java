package gradle.tag.plugin.task;

import gradle.tag.plugin.command.ShellRunnerCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.util.Optional;

public class GitLastTagTask extends DefaultTask {

    public static final String GIT_COMMAND_LAST_TAG = "git tag --points-at HEAD";
    private static final Logger log = LogManager.getLogger(GitLastTagTask.class);

    public static final String GIT_COMMAND_LAST_COMMIT_SHA = "git rev-parse HEAD";
    public static final String GIT_COMMAND_TAGS = "git show-ref --tags";

    @TaskAction
    public void hasLastTag() {
        boolean resultLastTag = false;
        Optional<String> lastTagOptional = ShellRunnerCommand.getInstance().execute(GIT_COMMAND_LAST_TAG);
        if (lastTagOptional.isPresent()) {
            if (lastTagOptional.get() != "") {
                log.info("Last tag: " + lastTagOptional.get());
                resultLastTag = true;
            }
        }
        this.getExtensions().add("result", resultLastTag);
    }
}
