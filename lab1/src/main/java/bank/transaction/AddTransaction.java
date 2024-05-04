package bank.transaction;

import Exceptions.TransactionException;
import Exceptions.WrongOperationException;
import bank.account.accountContext.AccountContext;

/**
 * Транзакция пополнения аккаунта
 */
public class AddTransaction implements Transactionable {
    private interface State extends Transactionable {
    }

    /**
     * Конструктор
     * По умолчанию состояние InProcess
     */
    public AddTransaction(Double value, AccountContext context) {
        this.value = value;
        this.context = context;
        state = new InProcess();
    }

    private final Double value;
    private final AccountContext context;
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
            context.withdraw(value);
        }
    }

    /**
     * Состояние - транзакция в процессе
     */
    private class InProcess implements State {

        @Override
        public void proceed() throws WrongOperationException {
            context.deposit(value);
            context.addTrans(this);
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
