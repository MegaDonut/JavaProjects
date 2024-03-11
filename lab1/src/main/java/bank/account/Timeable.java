package bank.account;

import Exceptions.UnexpectedDtoException;
import bank.account.dto.AccountDto;

public interface Timeable {
    void tick(AccountDto dto) throws UnexpectedDtoException;
}
