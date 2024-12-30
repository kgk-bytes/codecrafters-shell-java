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
                } else {
                    System.out.println(input + ": command not found");
                }
            } else {
                return;
            }

        }
    }
}
