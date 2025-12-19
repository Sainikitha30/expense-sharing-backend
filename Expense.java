package src;
import java.util.Map;

public class Expense {

    private double amount;
    private User paidBy;
    private Map<User, Double> splits;
    private String splitType;

    public Expense(double amount, User paidBy, Map<User, Double> splits, String splitType) {
        this.amount = amount;
        this.paidBy = paidBy;
        this.splits = splits;
        this.splitType = splitType;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public Map<User, Double> getSplits() {
        return splits;
    }
}
