package ConsoleApplicationForABankSystem;

public abstract class Transaction {
    protected double amount;
    protected String fromAccountId;
    protected String toAccountId;
    protected String reason;

    public Transaction(double amount, String fromAccountId, String toAccountId, String reason) {
        this.amount = amount;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.reason = reason;
    }

    public abstract double calculateFee();

    public double getAmount() {
        return amount;
    }

    public String getFromAccountId() {
        return fromAccountId;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return String.format("Transaction [amount=%.2f, from account=%s, to account=%s, reason=%s, fee=%.2f]",
                amount, fromAccountId, toAccountId, reason, calculateFee());
    }
}
