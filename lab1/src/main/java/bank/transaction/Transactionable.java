package bank.transaction;

import Exceptions.TransactionException;
import Exceptions.WrongOperationException;

public interface Transactionable {
    void proceed() throws WrongOperationException, TransactionException;
    void undo() throws WrongOperationException, TransactionException;
}
