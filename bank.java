public class bank {
    private String accountNumber;
    private double balance = 0.0;

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setAccountNumber(String accountNumber) {
        if (accountNumber.trim().isEmpty()) {
            System.out.println("Account number cannot be empty");
        } else {
            this.accountNumber = accountNumber;
        }
    }

    public void deposit(double balance) {
        if (balance > 0.0) {
            this.balance = this.balance + balance;
            System.out.println("Customer deposited " + balance + " . Current balance is: " + getBalance() + " Euro.");

        } else {
            System.out.println("You can't deposit negative balance");
        }
    }

    public void withdraw(double amount) { // Withdraws from balance
        if (amount > 0.0 && amount <= balance) { // Checks if amount is above 0 and not bigger than balance
            this.balance = this.balance - amount;
            System.out.println("Withdraw " + amount + " . Current balance is: " + getBalance() + " Euro.");
        } else {
            System.out.println("You can't withdraw negative amount or bigger amount than in account");
        }
    }
}
