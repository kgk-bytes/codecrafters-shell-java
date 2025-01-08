import java.util.Scanner;
import java.util.Arrays;
import java.io.*;

public class Main {
    public static void println(String str) {

        System.out.println(str);
    }

    public static void print(String str) {

        System.out.print(str);
    }

    public static void notFound(String str) {

        System.out.println(str + ": not found");
    }

    public static void debugOn(String str) {
        System.out.printf("%s%n", str);
    }

    public static String getCwd(String command) throws Exception {
        File pwd = new File(command);
        return pwd.getCanonicalFile().getParent();
    }

    public static String getPath(String typeString) {
        // Boolean pathFlag = Boolean.FALSE;
        String path = System.getenv("PATH");
        String typeStringLoc = typeString + ": not found";
        // String typeStringLocdebug = "";

        if (path != null || !(path.equalsIgnoreCase("")) || typeString != null) {
            // System.out.println("INSIDE PATH LIST TYPE ");
            // System.out.printf("HELLO %s", path);
            String[] paths = path.split(":");

            // System.out.printf(Arrays.toString(paths));
            // System.out.println("BEFORE PRINTING PATHS ");
            for (String p : paths) {
                // pathFlag = Boolean.FALSE;
                // System.out.printf("%s%n", p);
                // System.out.println("FILES ->" + p);

                File file = new File(p);
                File[] files = file.listFiles();

                // System.out.printf(Arrays.toString(files));
                if (files != null) {
                    // System.out.println(Arrays.asList(files).contains(typeString));
                    for (File f : files) {
                        // System.out.printf("%s%n", f);
                        String fstr = f.toString();
                        String subFstr = fstr.substring(fstr.lastIndexOf(File.separator) + 1);
                        if (subFstr.equalsIgnoreCase(typeString)) {
                            // pathFlag = Boolean.TRUE;
                            typeStringLoc = typeString + " is " + f;
                            break;
                        }
                    }
                }
            }
        }
        return typeStringLoc;
    }

    public static String getPlainPath(String typeString) {
        // Boolean pathFlag = Boolean.FALSE;
        String path = System.getenv("PATH");
        String typeStringLoc = typeString + ": not found";
        // String typeStringLocdebug = "";

        if (path != null || !(path.equalsIgnoreCase("")) || typeString != null) {
            // System.out.println("INSIDE PATH LIST TYPE ");
            // System.out.printf("HELLO %s", path);
            String[] paths = path.split(":");

            // System.out.printf(Arrays.toString(paths));
            // System.out.println("BEFORE PRINTING PATHS ");
            for (String p : paths) {
                // pathFlag = Boolean.FALSE;
                // System.out.printf("%s%n", p);
                // System.out.println("FILES ->" + p);

                File file = new File(p);
                File[] files = file.listFiles();

                // System.out.printf(Arrays.toString(files));
                if (files != null) {
                    // System.out.println(Arrays.asList(files).contains(typeString));
                    for (File f : files) {
                        // System.out.printf("%s%n", f);
                        String fstr = f.toString();
                        String subFstr = fstr.substring(fstr.lastIndexOf(File.separator) + 1);
                        if (subFstr.equalsIgnoreCase(typeString)) {
                            // pathFlag = Boolean.TRUE;
                            typeStringLoc = f.toString();
                            break;
                        }
                    }
                }
            }
        }
        return typeStringLoc;
    }

    public static void executeCommand(String input) throws Exception {
        // pass the entire command because the command might come with its own arguments
        String command = input.split(" ")[0];
        // String commandArgs = input.split(" ")[1];
        // debugOn(command);
        // debugOn(commandArgs);
        String path = getPlainPath(command);
        File fPath = new File(path);
        // debugOn(path);
        if (command.equalsIgnoreCase("pwd")) {
            String cmd = "pwd";
            String printPWD = getCwd(cmd);
            System.out.println(printPWD);

        } else if (!fPath.isFile()) {
            println(command + ": command not found");
        } else {
            // println("HELLO AM HERE");
            String commandArgs = input.split(" ")[1];
            // println("IS TERE AN ARG");
            // println(commandArgs);
            // debugOn(command);
            // debugOn(commandArgs);
            String path0 = getPath(command);
            File fPath0 = new File(path0);

            String fullPath = (!commandArgs.equalsIgnoreCase("")) ? command + " " + commandArgs
                    : path + input.substring(command.length());

            // debugOn(fullPath);
            // System.out.println(Arrays.toString(fullPath.split(" ")));
            Process p = Runtime.getRuntime().exec(fullPath.split(" "));
            p.getInputStream().transferTo(System.out);
        }
    }

    public static void main(String[] args) throws Exception {

        String[] built_in_commands = { "echo", "exit", "type", "pwd" };
        Scanner scanner = new Scanner(System.in);

        while (true) {
            print("$ ");
            String input = scanner.nextLine();

            if (!input.equalsIgnoreCase("exit 0")) {

                if (input.startsWith("echo")) {

                    println(input.substring("echo".length() + 1, input.length()));

                } else if (input.startsWith("type")) {

                    String typeString = input.substring("type".length() + 1, input.length());

                    if (Arrays.asList(built_in_commands).contains(typeString)) {
                        println(typeString + " is a shell builtin");
                        // break;

                    } else {
                        // debugOn("BEFORE PATH");
                        String typePath = getPath(typeString);
                        println(typePath);
                        // debugOn("AFTER PATH" + typePath);
                    }
                } else {
                    // debugOn("AM I HERE");
                    // notFound(input);
                    // String typePath = getPath(input);
                    // System.out.println(typePath);
                    executeCommand(input);
                }
            } else {
                break;
            }

            // debugOn(input);
        }
    }
}
