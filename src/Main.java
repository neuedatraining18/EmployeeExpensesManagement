import domain.Employee;
import domain.ExpenseClaim;
import domain.ExpenseItem;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
     Employee employee1 = new Employee(1,"Mr","Matt","Thorn","Educator","IT");
        System.out.println(employee1.getFirstName());
        System.out.println(employee1.getJobTitle());

     ExpenseClaim claim = new ExpenseClaim(1,23, new Date(),40.10);
     System.out.println(claim.getDateOfClaim());
     claim.setPaid(true);
     System.out.println();
        claim.setApproved(true);
     ExpenseItem item = new ExpenseItem(1, 34, "hotel","The Grand Hotel", 56.88);



    }
}
