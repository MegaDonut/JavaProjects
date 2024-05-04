package bank.transaction;

import Exceptions.TransactionException;
import Exceptions.WrongOperationException;
import bank.account.accountContext.AccountContext;

/**
 * Транзакция перевода между аккаунтами
 */
public class TransferTransaction implements Transactionable {
    private interface State extends Transactionable {
    }

    /**
     * Конструктор
     * По умолчанию состояние InProcess
     */
    public TransferTransaction(Double value, AccountContext contextFrom, AccountContext contextTo) {
        this.value = value;
        this.contextFrom = contextFrom;
        this.contextTo = contextTo;
        state = new InProcess();
    }

    private final Double value;
    private final AccountContext contextFrom;
    private final AccountContext contextTo;
    /**
     * Состояние транзакции
     */
    private State state;

    /**
     * Состояние - транзакция выполнена
     */
    private class Done implements State {

        @Override
        public void proceed() throws TransactionException {
            throw new TransactionException("WTF");
        }

        @Override
        public void undo() throws WrongOperationException {
            contextTo.withdraw(value);
            contextFrom.deposit(value);
        }
    }

    /**
     * Состояние - транзакция в процессе
     */
    private class InProcess implements State {

        @Override
        public void proceed() throws WrongOperationException {
            contextFrom.withdraw(value);
            contextTo.deposit(value);
            contextFrom.addTrans(this);
            contextTo.addTrans(this);
        }

        @Override
        public void undo() throws TransactionException {
            throw new TransactionException("WTF");
        }
    }

    /**
     * Состояние - транзакция отменена
     */
    private class Expired implements State {

        @Override
        public void proceed() throws TransactionException {
            throw new TransactionException("WTF");
        }

        @Override
        public void undo() throws TransactionException {
            throw new TransactionException("WTF");
        }
    }

    /**
     * Выполнить транзакцию
     *
     * @throws WrongOperationException
     * @throws TransactionException
     */
    @Override
    public void proceed() throws WrongOperationException, TransactionException {
        state.proceed();
        state = new Done();
    }

    /**
     * Отменить транзакцию
     *
     * @throws WrongOperationException
     * @throws TransactionException
     */
    @Override
    public void undo() throws WrongOperationException, TransactionException {
        state.undo();
        state = new Expired();
    }
}
