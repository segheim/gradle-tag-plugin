package gradle.tag.plugin.task;

import gradle.tag.plugin.command.ShellRunnerCommand;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.util.Optional;

public class GitUncommittedChangeTask extends DefaultTask {

    private static final String GIT_COMMAND_UNCOMMITTED_CHANGES = "git diff";
    private static final String GIT_COMMAND_UNCOMMITTED_CHANGES_CACHED = "git diff --cached";

    private final Logger log;

    public GitUncommittedChangeTask() {
        log = LogManager.getLogger(GitUncommittedChangeTask.class);
        Configurator.setRootLevel(Level.INFO);
    }

    @TaskAction
    public void checkUncommittedChanges() {
        boolean isUncommittedChanges = false;
        Optional<String> uncommittedChanges = ShellRunnerCommand.getInstance().execute(GIT_COMMAND_UNCOMMITTED_CHANGES);
        Optional<String> uncommittedChangesCached = ShellRunnerCommand.getInstance().execute(GIT_COMMAND_UNCOMMITTED_CHANGES_CACHED);

        if(uncommittedChanges.isPresent()) {
            log.info("Commit present: {}", uncommittedChanges.get());
            isUncommittedChanges = true;
            if (uncommittedChanges.get() == "") {
                isUncommittedChanges = false;
            }
        }
        if(uncommittedChangesCached.isPresent()) {
            log.info("CACHEDCommit present: {}", uncommittedChangesCached.get());
            isUncommittedChanges = true;
            if (uncommittedChangesCached.get() == "") {
                log.info("EMPTY cached commit");
                isUncommittedChanges = false;
            }
        }
        log.info("result: " + isUncommittedChanges);
        this.getExtensions().add("result", isUncommittedChanges);
    }
}
