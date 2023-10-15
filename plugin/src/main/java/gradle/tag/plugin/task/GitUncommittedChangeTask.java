package gradle.tag.plugin.task;

import gradle.tag.plugin.command.ShellRunnerCommand;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.util.List;

public class GitUncommittedChangeTask extends DefaultTask {

    private static final String GIT_COMMAND_UNCOMMITTED_CHANGES = "git diff";
    private static final String GIT_COMMAND_UNCOMMITTED_CHANGES_CACHED = "git diff --cached";
    public static final String GIT_COMMAND_LAST_TAG_VERSION = "git describe --abbrev=0 --tags";

    private final Logger log;

    public GitUncommittedChangeTask() {
        log = LogManager.getLogger(GitUncommittedChangeTask.class);
        Configurator.setRootLevel(Level.INFO);
    }

    @TaskAction
    public void checkUncommittedChanges() {
        boolean isUncommittedChanges = false;
        boolean isUncommittedChangesCached = false;
        List<String> outputUncommittedChanges = ShellRunnerCommand.getInstance().execute(GIT_COMMAND_UNCOMMITTED_CHANGES);
        List<String> outputUncommittedChangesCached = ShellRunnerCommand.getInstance().execute(GIT_COMMAND_UNCOMMITTED_CHANGES_CACHED);

        if (!outputUncommittedChanges.isEmpty()) {
            log.info("Commit present: {}", outputUncommittedChanges.get(0));
            isUncommittedChanges = true;
        }

        if (!outputUncommittedChangesCached.isEmpty()) {
            log.info("CACHEDCommit present: {}", outputUncommittedChangesCached.get(0));
            isUncommittedChangesCached = true;
        }

        boolean result = isUncommittedChanges || isUncommittedChangesCached;
        log.info("result: " + result);
        if (result) {
            List<String> outputLastTagVersion = ShellRunnerCommand.getInstance().execute(GIT_COMMAND_LAST_TAG_VERSION);
            if (!outputLastTagVersion.isEmpty()) {
                log.info("Build: {}.uncommitted", outputLastTagVersion.get(0));
            }
        }
        log.info("Uncommitted Result: " + result);
        this.getExtensions().add("result", result);
    }
}
