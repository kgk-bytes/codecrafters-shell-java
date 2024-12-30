import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // Uncomment this block to pass the first stage
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine();

            if (!input.equalsIgnoreCase("exit 0")) {

                if (input.startsWith("echo")) {
                    System.out.println(input.substring("echo".length() + 1, input.length()));
                } else if (input.startsWith("type")) {
                    String lit0 = input.substring("type".length() + 1, input.length());
                    if (lit0.equalsIgnoreCase("echo")) {
                        System.out.println("echo is a shell builtin");
                    } else if (lit0.equalsIgnoreCase("exit")) {
                        System.out.println("exit is a shell builtin");
                    } else if (lit0.equalsIgnoreCase("type")) {
                        System.out.println("type is a shell builtin");
                    } else {
                        System.out.println(lit0 + ": not found");
                    }
                } else {
                    System.out.println(input + ": command not found");
                }
            } else {
                return;
            }

        }
    }
}
