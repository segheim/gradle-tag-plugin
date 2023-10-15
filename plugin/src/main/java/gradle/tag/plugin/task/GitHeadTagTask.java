package gradle.tag.plugin.task;

import gradle.tag.plugin.command.ShellRunnerCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.util.List;

public class GitHeadTagTask extends DefaultTask {

    public static final String GIT_COMMAND_LAST_TAG = "git tag --points-at HEAD";
    private static final Logger log = LogManager.getLogger(GitHeadTagTask.class);

    @TaskAction
    public void hasLastTag() {
        boolean resultLastTag = false;
        List<String> outputTagHead = ShellRunnerCommand.getInstance().execute(GIT_COMMAND_LAST_TAG);
        if (!outputTagHead.isEmpty()) {
            log.info("Last tag: " + outputTagHead.get(0));
            resultLastTag = true;
        }
        this.getExtensions().add("result", resultLastTag);
    }
}
