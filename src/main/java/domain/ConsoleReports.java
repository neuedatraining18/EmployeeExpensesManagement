package domain;

import utils.ReportingPlatform;

import java.util.List;
import java.util.stream.Collectors;

public class ConsoleReports implements ReportingPlatform {
    List<ExpenseClaim> expenseClaims;

    public ConsoleReports(List<ExpenseClaim> expenseClaims) {
        this.expenseClaims = expenseClaims;
    }

    @Override
    public void allExpenseClaims() {
        for(ExpenseClaim claim: expenseClaims){
            System.out.println(claim);
        }
    }

    @Override
    public void claimsNotApproved() {
        expenseClaims.stream()
                .filter((b) ->
                {return !b.isApproved();})
                .forEach((b) ->{
                    System.out.println(b);
                });
    }

    @Override
    public void approvedButNotPaid() {
        expenseClaims.stream()
                .filter(claim -> claim.isApproved() && !claim.isPaid())
                .forEach(claim -> System.out.println(claim));
    }

    @Override
    public void claimGreaterThan200() {
        expenseClaims.stream()
                .filter(claim -> claim.getTotalAmount() > 200)
                .forEach(claim -> System.out.println(claim));
    }
}
