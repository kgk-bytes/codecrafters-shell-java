import java.util.Scanner;
import java.util.Arrays;
import java.io.*;

public class Main {
    public static void notFound(String str) {

        System.out.println(str + ": not found");
    }

    public static void debugOn(String str) {

        System.out.printf("%s%n", str);
    }

    public static String getPath(String typeString) {
        Boolean pathFlag = Boolean.FALSE;
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
                pathFlag = Boolean.FALSE;
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
                        // System.out.println(fstr);
                        // System.out.println(subFstr);
                        if (subFstr.equalsIgnoreCase(typeString)) {
                            pathFlag = Boolean.TRUE;
                            // System.out.printf("CAT FOUND%s%n", f);
                            // File filefound = new File(f);
                            // System.out.println(typeString + " is " + filefound.getParent());
                            // System.out.println(typeString + " is " + f);
                            typeStringLoc = typeString + " is " + f;
                            break;
                        }
                    }
                }
            }
            // debugOn("COMMAND WHEN ??" + typeStringLoc);
            // typeStringLocdebug += "\t" + typeStringLoc;
            // if (pathFlag != Boolean.TRUE) {
            // // System.out.println("HELLO BEFORE pathFLAG" + pathFlag);
            // // System.out.println("INSIDE pathFLAG");
            // // notFound(typeString);
            // typeStringLoc = typeString + " : not found";
            // // System.out.println("typeStringLoc " + typeStringLoc);

            // }
        }
        // debugOn("typeStringLocdebug " + typeStringLocdebug);
        return typeStringLoc;
    }

    public static void main(String[] args) throws Exception {

        String[] built_in_commands = { "echo", "exit", "type" };
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine();

            if (!input.equalsIgnoreCase("exit 0")) {

                if (input.startsWith("echo")) {

                    System.out.println(input.substring("echo".length() + 1, input.length()));

                } else if (input.startsWith("type")) {

                    String typeString = input.substring("type".length() + 1, input.length());

                    if (Arrays.asList(built_in_commands).contains(typeString)) {
                        System.out.println(typeString + " is a shell builtin");
                        // break;

                    } else {
                        // debugOn("BEFORE PATH");
                        String typePath = getPath(typeString);
                        System.out.println(typePath);
                        // debugOn("AFTER PATH" + typePath);
                    }
                } else {
                    notFound(input);
                }
            } else {
                break;
            }

        }
    }
}
