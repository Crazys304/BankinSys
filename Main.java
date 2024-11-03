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
// Custom exception to check if account number is not empty
class EmptyAccountException extends Exception {
    public EmptyAccountException() {
        super("Account number cannot be empty. \n");
    }
}
// Exception for checking if customer already exists
class CustomerExistException extends Exception {
    public CustomerExistException() {
        super("Customer already exists. \n");
    }
}
// Exception for checking if deposit is greater than 0
class GreaterThanZeroException extends Exception {
    public GreaterThanZeroException() {
        super("Amount must be greater than zero. \n");
    }
}
// Exception for checking if account has needed funds
class TooMuchWithdrawException extends Exception {
    public TooMuchWithdrawException() {
        super("Error: Insufficient funds. \n");
    }
}

public class Main {
    // Checks if the input is between the right numbers
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
    // Checks if account number is not empty
    public static void checkEmptyAccount(String account) throws EmptyAccountException {
        if (account.trim().isEmpty()) {
            throw new EmptyAccountException();
        }
    }
    // Checks if one customer exists
    public static void checkCustomerExist(int customer) throws CustomerExistException {
        if (customer == 1) {
            throw new CustomerExistException();
        }
    }
    // Checks if deposit is more than 0
    public static void checkAmount(double amount) throws GreaterThanZeroException {
        if (amount < 0.0) {
            throw new GreaterThanZeroException();
        }
    }
    // Checks if withdraw isn't bigger than the amount in account
    public static void checkWithdrawAmount(double amount, double bankAccount) throws TooMuchWithdrawException {
        if (amount > bankAccount) {
            throw new TooMuchWithdrawException();
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

                if (number == 1) {
                    try {
                        checkCustomerExist(exists); // Checks if customer already exists
                        System.out.println("Enter account number (3 big letters 3 numbers: ");
                        String accNumber = obj.nextLine();
                        boolean isValid = accNumber.matches("^[A-Z]{3}[0-9]{3}$");
                        while (!isValid) {
                            try {
                                System.out.println(
                                        "|Invalid account number.|\n" +
                                                "|Enter a new one?       |\n" +
                                                "-------------------------\n" +
                                                "|1. Yes                 |\n" +
                                                "|2. No                  |\n" +
                                                "-------------------------\n"
                                );
                                int input_second = obj.nextInt();
                                obj.nextLine();

                                if (input_second == 1) {
                                    System.out.println("Enter the number: ");
                                    accNumber = obj.nextLine();
                                    isValid = accNumber.matches("^[A-Z]{3}[0-9]{3}$");
                                } else if (input_second == 2) {
                                    isValid = true;
                                } else {
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

                        checkEmptyAccount(accNumber); // Checks if account number is not empty
                        if (accNumber.isEmpty() | accNumber.matches("^[A-Z]{3}[0-9]{3}$")) {
                            su.setAccountNumber(accNumber); // Sets account number in bank class
                            System.out.println("Customer " + su.getAccountNumber() + " added");
                            exists++; // Increments customer count
                        }
                    } catch (EmptyAccountException | CustomerExistException e) {
                        System.out.println(e.getMessage()); // Prints empty account exception message
                    }
                } else if (number == 2) {
                    if (exists == 0) {
                        System.out.println("Please add an account before making a deposit. \n");
                        continue;
                    }
                    try {
                        System.out.println("Deposit amount: ");
                        double amount = obj.nextDouble();

                        checkAmount(amount);// Checks if deposit amount is valid

                        su.deposit(amount); // Calls deposit method from bank class
                        System.out.println("Customer deposited " + amount + " . Current balance is: " + su.getBalance() + " Euro.");
                    } catch (GreaterThanZeroException e) {
                        System.out.println(e.getMessage()); // Prints deposit exception message
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. \n");
                        obj.nextLine();
                    }
                } else if (number == 3) {
                    if (exists == 0) {
                        System.out.println("Please add an account before making a withdrawal. \n");
                        continue;
                    }
                    try {
                        System.out.println("In account: " + su.getBalance() + "\nWithdraw amount: ");
                        double amount = obj.nextDouble();

                        checkWithdrawAmount(amount, su.getBalance()); // Checks if amount is not larger than balance

                        su.withdraw(amount); // Calls withdraw method from bank class
                        System.out.println("Withdraw " + amount + " . Current balance is: " + su.getBalance() + " Euro.");
                    } catch (TooMuchWithdrawException e) {
                        System.out.println(e.getMessage()); // Prints too much withdraw exception message
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. \n");
                        obj.nextLine();
                    }
                } else if (number == 4) {
                    if (exists == 0) {
                        System.out.println("Please add an account before checking account balance. \n");
                        continue;
                    }
                    // Prints account number and account balance form bank class
                    System.out.println("Balance for account " + su.getAccountNumber() + " is: " + su.getBalance() + " Euro.");
                } else if (number == 5) {
                    System.out.println("Thank you for the feedback and have a great day! \nExiting...");
                    obj.close();
                    System.exit(0);
                } else {
                    try {
                        InputException(number); // Checks if menu option input is between 1 to 5
                    } catch (InvalidInputException e) {
                        System.out.println(e.getMessage()); // Prints out menu exception message
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. \n");
                obj.nextLine();
            }
        }
    }
}