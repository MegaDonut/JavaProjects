package bank.account.accountContext;

import Exceptions.WrongOperationException;
import bank.Bank;
import bank.account.BankAccountable;
import bank.transaction.Transactionable;

public class AccountContext {
    private interface State {
        void deposit(Double value) throws WrongOperationException;

        void withdraw(Double value) throws WrongOperationException;
    }

    public AccountContext(BankAccountable account, Bank bank) {
        this.account = account;
        this.bank = bank;
        state = new NoAuth();
    }

    private final Bank bank;
    private final BankAccountable account;
    private State state;

    private class Auth implements State {

        @Override
        public void deposit(Double value) {
            account.deposit(value);
        }

        @Override
        public void withdraw(Double value) throws WrongOperationException {
            account.withdraw(value);
        }
    }

    private class NoAuth implements State {

        @Override
        public void deposit(Double value) throws WrongOperationException {
            if (value > bank.getLimit()) throw new WrongOperationException("You take on a lot");
            account.deposit(value);
        }

        @Override
        public void withdraw(Double value) throws WrongOperationException {
            if (value > bank.getLimit()) throw new WrongOperationException("You take on a lot");
            account.withdraw(value);
        }
    }

    public void deposit(Double value) throws WrongOperationException {
        setAuth();
        state.deposit(value);
    }

    public void withdraw(Double value) throws WrongOperationException {
        setAuth();
        state.withdraw(value);
    }

    public void addTrans(Transactionable transaction) {
        account.addTrans(transaction);
    }

    public void setAuth() {
        if (account.watchUser().getPassport() != null && account.watchUser().getPassport() != null) {
            state = new Auth();
        }
    }
}
