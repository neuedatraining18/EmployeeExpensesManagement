package ui;

import domain.ConsoleReports;
import domain.ExpenseClaim;
import domain.ExpenseItem;
import domain.ExpenseType;
import utils.DatabaseUtils;
import utils.ReportingPlatform;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class ExpensesUI {

    List<ExpenseClaim> expenseClaims = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    int highestExpenseItemId = 1;

    public void start() {
       setUpExampleClaims();
        String type = "";
        while (type.equals("Y") || !type.equals("N")) {
            System.out.println("Do you wish to enter an expense claim?(Y/N)");
            type = scanner.nextLine().toUpperCase();
            System.out.println(type);
            if (type.equals("Y")) {
                this.expenseClaims.add(requestExpenseClaim());
            }

        }

        runReports();

        DatabaseUtils databaseUtils = new DatabaseUtils();
        try {
            databaseUtils.saveData(expenseClaims);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void setUpExampleClaims() {
        ExpenseClaim ec1 = new ExpenseClaim(1, 200, LocalDate.now());
        ExpenseClaim ec2 = new ExpenseClaim(2, 210,LocalDate.now());
        ec1.setApproved(true);
        ec2.setApproved(true);
        ec1.setPaid(true);
        expenseClaims.add(ec1);
        expenseClaims.add(ec2);
    }

    public ExpenseClaim requestExpenseClaim() {
        System.out.println("Please provide expense claim info.");
        int empId = -1;
        while (empId < 1) {
            try {
                System.out.println("Please provide an employee id:");
                empId = Integer.parseInt(scanner.nextLine());
                if (empId < 1 ) {
                    System.out.println("Invalid employee ID entered - try again");
                }
            } catch (Exception e) {
                System.out.println("Invalid employee ID entered - try again");
            }
        }
        int claimId = this.expenseClaims.size()+1;
        ExpenseClaim claim = new ExpenseClaim(claimId,empId,LocalDate.now());
        int id = 1;
        claim.addExpenseItem(requestExpenseItem(id,claimId));
        String text = "";
        while (text.equals("Y") || !text.equals("N")) {
            System.out.println("Do you wish to add another expense item?(Y/N)");
            text = scanner.nextLine().toUpperCase();
            System.out.println(text);
            if (text.equals("Y"))
            {
            id=id++;
            claim.addExpenseItem(requestExpenseItem(id,claimId));
            }
        }
        return claim;
    }

    public ExpenseItem requestExpenseItem (int id, int claimId) {
        System.out.println("Please provide details for an Expense Item");
        ExpenseItem item;
        ExpenseType expType = null;
        int type = -1;
        while (type < 1 || type > 4) {
            System.out.println("Select Expense Type. 1 for travel, 2 for meal, 3 for accommodation, 4 for stationary:");
            type = Integer.parseInt(scanner.nextLine());
        switch (type) {
            case 1:
                expType = ExpenseType.TRAVEL;
                break;
            case 2:
                expType = ExpenseType.MEAL;
                break;
            case 3:
                expType = ExpenseType.ACCOMMODATION;
                break;
            case 4:
                expType = ExpenseType.STATIONARY;
                break;
            default:
                System.out.println("Illegal value, try again");
                break;
            }
        }
        System.out.println("Provide a description of the expense:");
        String desc = scanner.nextLine();
        System.out.println("Please provide an expense amount:");
        Double amount = Double.parseDouble(scanner.nextLine());
        item = new ExpenseItem(id,claimId,expType,desc,amount);
        return item;
    }

    public void runReports(){
        ReportingPlatform reports = new ConsoleReports(expenseClaims);
        boolean finishedRunningReports = false;
        while(!finishedRunningReports){
            System.out.println("What kind of expense report do you want? " +
                    "\n(A)List all expenses" +
                    "\n(B)List all expense claims that have not been approved" +
                    "\n(C)List all expense claims that have been approved but not paid" +
                    "\n(D)List all expense claims where the total is > $200" +
                    "\n(N)None");
            String option = scanner.nextLine().toUpperCase();
            switch(option){
                case "A":
                    reports.allExpenseClaims();
                    break;
                case "B":
                    reports.claimsNotApproved();
                    break;
                case "C":
                    reports.approvedButNotPaid();
                    break;
                case "D":
                    reports.claimGreaterThan200();
                    break;
                case "N":
                    finishedRunningReports = true;
                    break;
                default:
                    System.out.println("This is an invalid option - try again");
                    break;
            }
        }
    }



}
