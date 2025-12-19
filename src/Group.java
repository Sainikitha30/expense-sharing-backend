package src;
import java.util.ArrayList;
import java.util.List;

public class Group {

    private int id;
    private String name;
    private List<User> members;
    private List<Expense> expenses;

    public Group(int id, String name) {
        this.id = id;
        this.name = name;
        this.members = new ArrayList<>();
        this.expenses = new ArrayList<>();
    }

    public void addMember(User user) {
        members.add(user);
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public List<User> getMembers() {
        return members;
    }
}
