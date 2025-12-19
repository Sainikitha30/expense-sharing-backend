package src;

import java.util.HashMap;
import java.util.Map;

public class Main {    public static void main(String[] args) {

        User alice = new User(1, "Alice");
        User bob = new User(2, "Bob");
        User charlie = new User(3, "Charlie");

        ExpenseManager manager = new ExpenseManager();

        // Expense 1: Alice paid 120 (Bob 40, Charlie 40)
        Map<User, Double> split1 = new HashMap<>();
        split1.put(bob, 40.0);
        split1.put(charlie, 40.0);
        Expense e1 = new Expense(120.0, alice, split1, "Lunch");
        manager.addExpense(e1);

        // Expense 2: Bob paid 75 (Alice 30, Charlie 45)
        Map<User, Double> split2 = new HashMap<>();
        split2.put(alice, 30.0);
        split2.put(charlie, 45.0);
        Expense e2 = new Expense(75.0, bob, split2, "Dinner");
        manager.addExpense(e2);

        System.out.println("==== Balances ====");
        manager.showBalances();

        // Settlement
        manager.settle(alice, bob, 30.0);

        System.out.println("==== Balances after settlement ====");
        manager.showBalances();
    }
}
