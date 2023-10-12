package gradle.tag.plugin.task;

import gradle.tag.plugin.command.ShellRunnerCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.util.Optional;

public class GitLastTagVersionTask extends DefaultTask {

    private static final Logger log = LogManager.getLogger(GitLastTagVersionTask.class);

    public static final String GIT_COMMAND_LAST_TAG_VERSION = "git symbolic-ref --short HEAD";

    @TaskAction
    public void getLastTagVersion() {
//        Optional<String> lastTagVersion = ShellRunnerCommand.getInstance().execute(GIT_COMMAND_LAST_TAG_VERSION);
//        log.info("Build: {}.uncommitted", lastTagVersion.get());
//        this.getExtensions().add("result", lastTagVersion.isPresent());
//        return lastTagVersion.isPresent();
    }
}
