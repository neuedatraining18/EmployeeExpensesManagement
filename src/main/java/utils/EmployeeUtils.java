package utils;
import customExceptions.InvalidEmployeeIdException;
import customExceptions.NameTooShortException;

public class EmployeeUtils {
    public Integer validateEmployeeId (String empId) throws InvalidEmployeeIdException {
    try {
        Integer id = Integer.parseInt(empId);
        return id;
    }
    catch (NumberFormatException e)
        {
                throw new InvalidEmployeeIdException();
        }
    }

    public void validateEmpoyeeName(String firstName, String surname) throws NameTooShortException {
        if(firstName.length() + surname.length() < 6)
        {
             throw new NameTooShortException();
        }
    }
}
