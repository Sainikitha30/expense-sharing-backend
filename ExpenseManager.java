package src;

import java.util.HashMap;
import java.util.Map;

public class ExpenseManager {

    private Map<User, Map<User, Double>> balances = new HashMap<>();

    public void addExpense(Expense expense) {
        User paidBy = expense.getPaidBy();

        for (Map.Entry<User, Double> entry : expense.getSplits().entrySet()) {
            User user = entry.getKey();
            double amount = entry.getValue();

            if (user.equals(paidBy)) continue;

            addBalance(user, paidBy, amount);
        }
    }

    private void addBalance(User from, User to, double amount) {
        balances.putIfAbsent(from, new HashMap<>());
        balances.putIfAbsent(to, new HashMap<>());

        double fromTo = balances.get(from).getOrDefault(to, 0.0);
        double toFrom = balances.get(to).getOrDefault(from, 0.0);

        if (toFrom > 0) {
            double net = toFrom - amount;
            balances.get(to).remove(from);

            if (net > 0) {
                balances.get(to).put(from, net);
            } else if (net < 0) {
                balances.get(from).put(to, -net);
            }
        } else {
            balances.get(from).put(to, fromTo + amount);
        }
    }

    public void settle(User from, User to, double amount) {
        if (!balances.containsKey(from)) return;

        double owed = balances.get(from).getOrDefault(to, 0.0);

        if (owed <= amount) {
            balances.get(from).remove(to);
        } else {
            balances.get(from).put(to, owed - amount);
        }
    }

    public void showBalances() {
        for (User from : balances.keySet()) {
            for (Map.Entry<User, Double> e : balances.get(from).entrySet()) {
                if (e.getValue() > 0) {
                    System.out.printf(
                        "%s owes %s: $%.2f%n",
                        from.getName(),
                        e.getKey().getName(),
                        e.getValue()
                    );
                }
            }
        }
    }
}
