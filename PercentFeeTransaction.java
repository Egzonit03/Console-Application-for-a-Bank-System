package ConsoleApplicationForABankSystem;

public class PercentFeeTransaction extends Transaction {
    private double percent;

    public PercentFeeTransaction(double amount, String fromAccountId, String toAccountId, String reason, double percent) {
        super(amount, fromAccountId, toAccountId, reason);
        this.percent = percent;
    }

    @Override
    public double calculateFee() {
        return amount * percent / 100;
    }
}
