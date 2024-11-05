import java.util.Scanner;
import java.util.InputMismatchException;

// Creates a custom exception used for menu input detection
class InvalidInputException extends Exception {
    public InvalidInputException(int number) {
        super("Invalid option. Input numbers between 1 and 5. \n"); // message for
    }
}
// Creates a custom exception used for sub menu input detection
class InvalidInputSubMenuException extends Exception {
    public InvalidInputSubMenuException(int number) {
        super("Invalid option. Input numbers between 1 and 2. \n");
    }
}
// Exception for checking if customer already exists
class CustomerExistException extends Exception {
    public CustomerExistException() {
        super("Customer already exists. \n");
    }
}

public class Main {
    // Checks if the menu input is between the right numbers
    public static void InputException(int number) throws InvalidInputException {
        if (number < 1 || number > 5) {
            throw new InvalidInputException(number);
        }
    }
    // Checks if the sub menu input is between the right numbers
    public static void InputSubException(int number) throws InvalidInputSubMenuException {
        if (number < 1 || number > 2) {
            throw new InvalidInputSubMenuException(number);
        }
    }
    // Checks if one customer exists
    public static void checkCustomerExist(int customer) throws CustomerExistException {
        if (customer == 1) {
            throw new CustomerExistException();
        }
    }

    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        bank su = new bank(); // Initializing the bank class to manage account operations
        int exists = 0; // Tracks if a customer account has been added

        while (true) {
            try {
                System.out.println(
                        "---------------------" +
                                "\n|  Banking system   |" +
                                "\n---------------------" +
                                "\n|1. Add customer    |" +
                                "\n|2. Deposit         |" +
                                "\n|3. Withdraw        |" +
                                "\n|4. Check balance   |" +
                                "\n|5. Exit            |" +
                                "\n---------------------"
                );
                System.out.println("Enter command (1 to 5): ");
                int number = obj.nextInt();
                obj.nextLine();

                if (number == 1) { // Add customer
                    try {
                        checkCustomerExist(exists); // Checks if customer already exists
                        System.out.println("Enter account number: ");
                        String accNumber = obj.nextLine();
                        boolean isValid = accNumber.matches("^[a-zA-Z]{3}[0-9]{3}$"); // Checks if account number matches the pattern
                        while (!isValid) { // while isValid is not true, prints submenu
                            try {
                                System.out.println(
                                        "|Invalid account number.|\n" +
                                                "|Enter a new one?       |\n" +
                                                "-------------------------\n" +
                                                "|1. Yes                 |\n" +
                                                "|2. No                  |\n" +
                                                "-------------------------"
                                );
                                int input_second = obj.nextInt();
                                obj.nextLine();

                                if (input_second == 1) {
                                    System.out.println("Enter account number again: ");
                                    accNumber = obj.nextLine();
                                    isValid = accNumber.matches("^[a-zA-Z]{3}[0-9]{3}$"); // Checks again if it matches
                                } else if (input_second == 2) {
                                    isValid = true; // Exits loop
                                } else {
                                    // Invalid input handling
                                    try {
                                        InputSubException(input_second);
                                    } catch (InvalidInputSubMenuException e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. \n");
                                obj.nextLine();
                            }
                        }
                        if (accNumber.isEmpty()) {
                            System.out.println(" ");
                            continue;
                        } else if (accNumber.matches("^[a-zA-Z]{3}[0-9]{3}$")) {
                            su.setAccountNumber(accNumber.toUpperCase()); // Sets account number in bank class
                            System.out.println("Customer " + su.getAccountNumber() + " added");
                            exists++; // Adds that customer has been added
                        }
                    } catch (CustomerExistException e) {
                        System.out.println(e.getMessage()); // Prints empty account or that customer already exists exception message
                    }
                } else if (number == 2) {
                    if (exists == 0) { // If account hasn't been added, can't do deposit
                        System.out.println("Please add an account before making a deposit. \n");
                        continue;
                    }
                    try {
                        System.out.println("Deposit amount: ");
                        double amount = obj.nextDouble();
                        su.deposit(amount); // Calls deposit method from bank class
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. \n");
                        obj.nextLine();
                    }
                } else if (number == 3) {
                    if (exists == 0) { // If account hasn't been added, can't do withdrawal
                        System.out.println("Please add an account before making a withdrawal.");
                        continue;
                    }
                    try {
                        // Prints out account balance and asks how much to withdraw
                        System.out.println("In account: " + su.getBalance() + "\nWithdraw amount: ");
                        double amount = obj.nextDouble();
                        su.withdraw(amount); // Calls withdraw method from bank class
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. \n");
                        obj.nextLine();
                    }
                } else if (number == 4) {
                    if (exists == 0) { // If account hasn't been added, can't check balance
                        System.out.println("Please add an account before checking account balance. ");
                        continue;
                    }
                    // Prints account number and account balance form bank class
                    System.out.println("Balance for account " + su.getAccountNumber() + " is: " + su.getBalance() + " Euro.");
                } else if (number == 5) {
                    System.out.println("Thank you for the feedback and have a great day! \nExiting...");
                    obj.close();
                    System.exit(0); // Exits the program
                } else {
                    try {
                        InputException(number); // Checks if menu option input is between 1 to 5
                    } catch (InvalidInputException e) {
                        System.out.println(e.getMessage()); // Prints out menu exception message
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                obj.nextLine();
            }
        }
    }
}