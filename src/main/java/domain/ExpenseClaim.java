package domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ExpenseClaim {
    private int id;
    private int employeeId;
    private LocalDate dateOfClaim;
    //private double totalAmount;
    private boolean approved;
    private boolean paid;
    private ArrayList<ExpenseItem> expenseItems;

    public ExpenseClaim(int id, int employeeId, LocalDate dateOfClaim) {
        this.id = id;
        this.employeeId = employeeId;
        this.dateOfClaim = dateOfClaim;
        this.approved = false;
        this.paid = false;
        this.expenseItems = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public LocalDate getDateOfClaim() {
        return dateOfClaim;
    }

    public double getTotalAmount() {
        //BigDecimal total = BigDecimal.ZERO;
        double total = 0;
        for(ExpenseItem item: this.expenseItems) {
            total += item.getAmount();
            //total.add(...);
        }
        return total;
    }

    public boolean isApproved() {
        return approved;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public void setPaid(boolean paid) {
        //paid && !approved
        if(paid == true && this.approved == false){
            System.out.println("This item cannot be paid as it has not yet been approved.");
        }
        else {
            this.paid = paid;
        }
    }

    public ArrayList<ExpenseItem> getExpenseItems() {
        return expenseItems;
    }
    public void addExpenseItem(ExpenseItem item) {
        this.expenseItems.add(item);
    }

    @Override
    public String toString() {
        return "ExpenseClaim{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", dateOfClaim=" + dateOfClaim +
                ", approved=" + approved +
                ", paid=" + paid +
                ", expenseItems=" + expenseItems +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpenseClaim that = (ExpenseClaim) o;
        return id == that.id && employeeId == that.employeeId && approved == that.approved && paid == that.paid && Objects.equals(dateOfClaim, that.dateOfClaim) && Objects.equals(expenseItems, that.expenseItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeId, dateOfClaim, approved, paid, expenseItems);
    }
}
