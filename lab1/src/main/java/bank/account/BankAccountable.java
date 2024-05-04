package bank.account;

import Exceptions.WrongOperationException;
import bank.transaction.Transactionable;
import user.User;

public interface BankAccountable {
    Integer watchId();

    User watchUser();

    Double watchBalance();

    void deposit(Double value);

    void withdraw(Double value) throws WrongOperationException;

    void addTrans(Transactionable transaction);
}
