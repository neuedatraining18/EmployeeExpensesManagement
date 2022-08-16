package utils;

public interface ReportingPlatform {

    public void allExpenseClaims();
    public void claimsNotApproved();
    public void approvedButNotPaid();
    public void claimGreaterThan200();

}
