package BadgeAndMembershipClient.common;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class IOUtil {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    private static final String SEPARATOR = "______________________________________________________________________";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_YELLOW_BOLD = "\033[1;33m";
    private static final String ANSI_BLACK_BOLD = "\033[1;30m";
    private static Scanner sc = new Scanner(System.in);

    public static void printTitle(String title) {
        printTitle(title, 0);
    }

    public static void printTitle(String title, int lineSpaceBeforeTitle) {
        // create space between titles
        for (int i = 0; i < lineSpaceBeforeTitle; i++)
            System.out.println();
        System.out.println(ANSI_YELLOW_BOLD + SEPARATOR);
        System.out.println();
        System.out.println("  " + title);
        System.out.println(SEPARATOR + ANSI_RESET);
    }

    /**
     * Method to display label and get input string from user in console
     *
     * @param label
     * @return inputString
     */
    public static String getInput(String label) {
        String input = "";
        while (input.isBlank()) {
            System.out.println();
            System.out.print(label + ": ");
            input = sc.nextLine();
            if (input.isBlank()) {
                System.out.println();
                System.out.println(ANSI_RED + "Input cannot be blank. Please try again." + ANSI_RESET);
            }
        }
        return input;
    }

    /**
     * Method to display label and get input string from user in console
     *
     * @param label
     * @return inputString
     */
    public static String getNumberInput(String label) {
        while (true) {
            String input = getInput(label);
            if (Util.isInteger(input)) {
                return input;
            }
            System.out.println();
            System.out.println(ANSI_RED + "Invalid Input. Please enter a number." + ANSI_RESET);
        }
    }

    /**
     * Method to display the options and get valid option input from user
     *
     * @param options
     * @return input value
     */
    public static String getSelectedOption(HashMap<String, String> options) {
        if (options.size() == 0)
            return null;
        System.out.println();
        for (Entry<String, String> e : options.entrySet()) {
            System.out.println(String.format("  %s. %s", e.getKey(), e.getValue()));
        }
        String selectedOption = null;
        boolean validInput = false;
        while (!validInput) {
            selectedOption = getInput("Enter option");
            validInput = options.containsKey(selectedOption);
            if (!validInput) {
                System.out.println();
                System.out.println(ANSI_RED + "You entered an invalid option. Please try again." + ANSI_RESET);
            }
        }
        System.out.println(SEPARATOR);
        return selectedOption;
    }

    /**
     * Method to print exception message when operation fails
     *
     * @param msg
     */
    public static void printExceptionMessage(String msg) {
        System.out.println();
        System.out.println(ANSI_RED + "_[Operation failed]___________________________________________________");
        System.out.println(msg);
        System.out.println(SEPARATOR + ANSI_RESET);
    }

    /**
     * Method to print message when operation succeeds
     *
     * @param msg
     */
    public static void printSuccessMessage(String msg) {
        System.out.println();
        System.out.println(ANSI_GREEN + SEPARATOR);
        System.out.println(msg);
        System.out.println(SEPARATOR + ANSI_RESET);
    }

    /**
     * Method to print message
     *
     * @param msg
     */
    public static void printMessage(String msg) {
        System.out.println();
        System.out.println(SEPARATOR);
        System.out.println(msg);
        System.out.println(SEPARATOR);
    }

    /**
     * Method to pause console. Must press enter to continue
     */
    public static void pauseConsole() {
        sc.nextLine();
    }

}
