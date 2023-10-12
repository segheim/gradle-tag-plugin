package gradle.tag.plugin.task;

import gradle.tag.plugin.command.ShellRunnerCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.util.Optional;

public class GItCurrentBranchTask extends DefaultTask {

    private static final Logger log = LogManager.getLogger(GItCurrentBranchTask.class);

    public static final String GIT_COMMAND_CURRENT_BRANCH = "git rev-parse --abbrev-ref HEAD";

    @TaskAction
    public void getGitCurrentBranch() {
//        Optional<String> currentBranch = ShellRunnerCommand.getInstance().execute(GIT_COMMAND_CURRENT_BRANCH);
//        log.info("Current brunch: {}", currentBranch.get());
//        return currentBranch.isPresent() ? currentBranch.get() : "";
    }
}
