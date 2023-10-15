package gradle.tag.plugin;

import gradle.tag.plugin.command.ShellRunnerCommand;

public class App {
    public static void main(String[] args) {
//        String command = "git tag %s";
//        System.out.println(ShellRunnerCommand.getInstance().execute(String.format(command, "v7.0")));
        String command = "git push origin %s";
//        System.out.println(ShellRunnerCommand.getInstance().execute(String.format(command, "v7.0")));

    }
}
