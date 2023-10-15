package gradle.tag.plugin.task;

import gradle.tag.plugin.command.ShellRunnerCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.util.List;

public class GitCreateTagTask extends DefaultTask {

    private static final Logger log = LogManager.getLogger(GitCreateTagTask.class);

    public static final String GIT_COMMAND_CURRENT_BRANCH = "git symbolic-ref --short HEAD";
    public static final String GIT_COMMAND_LAST_TAG = "git describe --abbrev=0 --tags";
    public static final String GIT_COMMAND_CREATE_TAG = "git tag %s";
    public static final String GIT_COMMAND_PUSH_REMOTE = "git push origin %s";

    public static final String QA_BRANCH_NAME = "qa";
    public static final String DEV_BRANCH_NAME = "dev";
    public static final String STAGE_BRANCH_NAME = "stage";
    public static final String MASTER_BRANCH_NAME = "master";

    public static final String RC_POSTFIX_FOR_STAGE_BRANCH = "-rc";
    public static final String SNAPSHOT_POSTFIX_FOR_OTHER_BRANCHES = "-SNAPSHOT";

    public static final String TAG_NAME = "v%d.%d";

    public static final int TAG_MAJOR_BEGIN_INDEX = 1;
    public static final int TAG_MAJOR_END_INDEX = 2;
    public static final int TAG_MINOR_BEGIN_INDEX = 3;
    public static final int TAG_MAJOR_NUMBER_VERSION = 0;

    @TaskAction
    public void createGitTag() {
        List<String> outputCurrentBranches = ShellRunnerCommand.getInstance().execute(GIT_COMMAND_CURRENT_BRANCH);
        if (!outputCurrentBranches.isEmpty()) {
            String currentBranch = outputCurrentBranches.get(0);
            List<String> outputLastTag = ShellRunnerCommand.getInstance().execute(GIT_COMMAND_LAST_TAG);
            String tagVersion = "v1.0";
            if (!outputLastTag.isEmpty()) {
                String lastTag = outputLastTag.get(0);
                if (currentBranch.equals(QA_BRANCH_NAME) || currentBranch.equals(DEV_BRANCH_NAME)) {
                    tagVersion = incrementVersion(lastTag, false);
                } else if (currentBranch.equals(STAGE_BRANCH_NAME)) {
                    tagVersion = incrementVersion(lastTag, false) + RC_POSTFIX_FOR_STAGE_BRANCH;
                } else if (currentBranch.equals(MASTER_BRANCH_NAME)) {
                    tagVersion = incrementVersion(lastTag, true);
                } else {
                    tagVersion = lastTag + SNAPSHOT_POSTFIX_FOR_OTHER_BRANCHES;
                }
            }
            log.info("Tag: {}", tagVersion);
            ShellRunnerCommand.getInstance().execute(String.format(GIT_COMMAND_CREATE_TAG, tagVersion));
            ShellRunnerCommand.getInstance().execute(String.format(GIT_COMMAND_PUSH_REMOTE, tagVersion));
        }
    }

    private String incrementVersion(String tagVersion, boolean flagMajorVersion) {
        Integer majorVersion = Integer.parseInt(tagVersion.substring(TAG_MAJOR_BEGIN_INDEX, TAG_MAJOR_END_INDEX));
        Integer minorVersion = Integer.parseInt(tagVersion.substring(TAG_MINOR_BEGIN_INDEX));
        String version;
        if (flagMajorVersion) {
            majorVersion++;
            version = String.format(TAG_NAME, majorVersion, TAG_MAJOR_NUMBER_VERSION);
        } else {
            minorVersion++;
            version = String.format(TAG_NAME, majorVersion, minorVersion);
        }
        return version;
    }
}
