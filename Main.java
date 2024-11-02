import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        bank su = new bank();

        while (true) {
            System.out.println("---------------------");
            System.out.println("|  Banking system   |");
            System.out.println("---------------------");
            System.out.println("|1. Add customer    |");
            System.out.println("|2. Deposit         |");
            System.out.println("|3. Withdraw        |");
            System.out.println("|4. Check balance   |");
            System.out.println("|5. Exit            |");
            System.out.println("---------------------");
            System.out.println("Enter command (1 to 5): ");
            int number = obj.nextInt();
            obj.nextLine();

            if (number == 1) {
                System.out.println("Enter account number: ");
                String numbers = obj.nextLine();
                su.setAccountNumber(numbers);
                System.out.println("Customer added");

            }
            else if (number == 2) {
                System.out.println("Deposit amount: ");
                double amount = obj.nextDouble();
                su.deposit(amount);
                System.out.println("Customer deposited " + amount + " . Current balance is: " + su.getBalance() + " Euro.");
            }
            else if (number == 3) {
                System.out.println("Withdraw amount: ");
                double amount = obj.nextDouble();
                su.withdraw(amount);
                System.out.println("Withdraw " + amount + " . Current balance is: " + su.getBalance() + " Euro.");
            }
            else if (number == 4) {
                System.out.println("Balance for account " + su.getAccountNumber() + " is: " + su.getBalance() + " Euro.");
            }
            else if (number == 5) {
                System.out.println("Thank you for using Banking System!");
                break;
            }
        }
        obj.close();
    }
}