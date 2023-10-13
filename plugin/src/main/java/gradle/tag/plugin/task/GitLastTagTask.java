package gradle.tag.plugin.task;

import gradle.tag.plugin.command.ShellRunnerCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class GitLastTagTask extends DefaultTask {

    public static final String GIT_COMMAND_LAST_TAG_NAME = "git describe --abbrev=0 --tags";
    private final Logger log;

    public static final String GIT_COMMAND_LAST_COMMIT_SHA = "git rev-parse HEAD";
    public static final String GIT_COMMAND_TAGS = "git show-ref --tags";

    public GitLastTagTask() {
        log = LogManager.getLogger(GitLastTagTask.class);
    }

    @TaskAction
    public void getLastTag() {
        String resultLastTag = "";
        Optional<String> lastCommitShaOptional = ShellRunnerCommand.getInstance().execute(GIT_COMMAND_LAST_COMMIT_SHA);
        Optional<String> tagsOptional = ShellRunnerCommand.getInstance().execute(GIT_COMMAND_TAGS);
        if (tagsOptional.isPresent() && lastCommitShaOptional.isPresent()) {
            if (tagsOptional.get() != "" && lastCommitShaOptional.get() != null) {
                String lastTagSha = tagsOptional.get().substring(0, 40);
                log.info("Last tag sha: " + lastTagSha);
                log.info("Last commit sha: " + lastCommitShaOptional.get());
                if (lastTagSha.equals(lastCommitShaOptional.get())) {

                }
//                String[] tags = tagsOptional.get().split("\n");
//                log.info("ARRAY: " + tags[0] + " / " + tags[1]);
//                List<String> listTags = Arrays.asList(tags);
//                Collections.reverse(listTags);
//                log.info("LIST:" + listTags.get(0) + " / " + listTags.get(0));
            }
        }

//        if (lastCommitSha.isPresent() && lastTagSha.isPresent()) {
//            log.info("Last commit Sha: {}", lastCommitSha.get());
//            log.info("Last tag Sha: {}", lastTagSha.get().substring(0, 40));
//            if (lastCommitSha.get() != "" && lastTagSha.get() != "") {
//                if (lastTagSha.get().substring(0, 40).equals(lastCommitSha)) {
//                    Optional<String> lastTagName = ShellRunnerCommand.getInstance().execute(GIT_COMMAND_LAST_TAG_NAME);
//                    if (lastTagName.isPresent()) {
//                        if (lastTagName.get() != "") {
//                            resultLastTag = lastTagName.get();
//                        }
//                    }
//                }
//            }
//        }

        log.info("result:" + resultLastTag);

//        log.info("Last tag absent");
//        this.getExtensions().add("tagValue", resultLastTag);
    }
}
