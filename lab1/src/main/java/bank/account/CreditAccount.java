package bank.account;

import Exceptions.WrongOperationException;
import bank.transaction.Transactionable;
import lombok.Builder;
import user.User;

import java.util.LinkedList;
import java.util.List;

@Builder
public class CreditAccount implements BankAccountable, Comissionable {
    public static final Double ZERO = 0.;
    private Integer id;
    private Double balance;
    private Double creditLimit;
    private User user;
    private final List<Transactionable> transactionList = new LinkedList<>();

    @Override
    public Integer watchId() {
        return id;
    }

    @Override
    public User watchUser() {
        return user;
    }

    @Override
    public Double watchBalance() {
        return balance;
    }

    @Override
    public void deposit(Double value) {
        balance += value;
    }

    @Override
    public void withdraw(Double value) throws WrongOperationException {
        if (balance - value < -creditLimit) throw new WrongOperationException("Overdraft");
        balance -= value;
    }

    @Override
    public void addTrans(Transactionable transaction) {
        transactionList.add(transaction);
    }

    @Override
    public void chargeCommission(Double value) {
        if (balance > ZERO) return;
        balance -= value;
    }
}
