package gradle.tag.plugin;

import gradle.tag.plugin.command.ShellRunnerCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

public class App {
    public static void main(String[] args) {
//        List<String> commands = List.of("cmd.exe", "/c", "git describe --exact-match --tags HEAD");
//
        Optional<String> optional = ShellRunnerCommand.getInstance().execute("git describe --exact-match --tags HEAD");
        System.out.println(optional.get());

//        try {
//            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "gradle -version");
//        processBuilder.command("cmd.exe", "/c", "git describe --exact-match --tags HEAD");
//            Process process = processBuilder.start();
//            Process process = Runtime.getRuntime().exec("git status");
//            StringBuilder output = new StringBuilder();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                output.append(line + "\n");
//            }
//
//            int exitVal = process.waitFor();
//            if (exitVal == 0) {
//                System.out.println("!!!!!" + output);
//            } else {
//                System.out.println("ERROR");
//            }
//
//        } catch (IOException | InterruptedException e) {
//            System.out.println("IO EXception");
//        }
    }
}
