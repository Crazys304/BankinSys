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
        this.accountNumber = accountNumber;
    }

    public void deposit(double balance) {
        this.balance = this.balance + balance;
    }

    public void withdraw(double amount) {
        this.balance = this.balance - amount;
    }
}
