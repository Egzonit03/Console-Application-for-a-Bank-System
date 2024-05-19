package ConsoleApplicationForABankSystem;

public class FlatFeeTransaction extends Transaction {
    private double fee;

    public FlatFeeTransaction(double amount, String fromAccountId, String toAccountId, String reason, double fee) {
        super(amount, fromAccountId, toAccountId, reason);
        this.fee = fee;
    }

    @Override
    public double calculateFee() {
        return fee;
    }
}
