package bank.account;

import Exceptions.UnexpectedDtoException;
import Exceptions.WrongOperationException;
import bank.account.dto.AccountDto;
import bank.account.dto.DepositAccountDto;
import bank.transaction.Transactionable;
import lombok.Builder;
import user.User;

import java.util.LinkedList;
import java.util.List;

@Builder
public class DepositAccount implements BankAccountable, Timeable, Chargable{
    public static final Double YEAR = 365.0;
    public static final Double ZERO = 0.;

    private Integer term;
    private Integer id;
    private User user;
    private final List<Transactionable> transactionList = new LinkedList<>();

    private Double balance;
    private Double percentAcc;
    private Integer currDay;
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
        if (currDay < term) throw new WrongOperationException("Too early, baby");
        if (balance - value < ZERO) throw new WrongOperationException("Overdraft");

        balance -= value;
    }

    @Override
    public void addTrans(Transactionable transaction) {
        transactionList.add(transaction);
    }

    @Override
    public void chargeInterest() {
        balance += percentAcc;
        percentAcc = ZERO;
    }

    @Override
    public void tick(AccountDto dto) throws UnexpectedDtoException {
        if (!(dto instanceof DepositAccountDto)) throw new UnexpectedDtoException("");
        currDay++;
        percentAcc += (((DepositAccountDto) dto).getDepositPercentFunc().apply(balance) / YEAR) * balance;
    }
}
