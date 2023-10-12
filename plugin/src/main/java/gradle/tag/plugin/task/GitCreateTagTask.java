package gradle.tag.plugin.task;

import gradle.tag.plugin.command.ShellRunnerCommand;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class GitCreateTagTask extends DefaultTask {

    public static final String GIT_COMMAND_CREATE_TAG = "git tag %s";

    @TaskAction
    public void createGitTag() {
//        ShellRunnerCommand.getInstance().execute(String.format(GIT_COMMAND_CREATE_TAG, tagVersion));
    }
}
