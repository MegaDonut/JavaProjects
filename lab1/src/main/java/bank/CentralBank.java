package bank;

import bank.account.BankAccountable;
import bank.account.accountContext.AccountContext;
import bank.transaction.Transactionable;
import bank.transaction.TransferTransaction;
import bank.transaction.dto.TransactionDto;
import bank.transaction.dto.TransferTransactionDto;
import lombok.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * Центральный банк.
 * Занимается регистрацией новых банков
 * Обеспечивает связь между банками, посредством создания транзакций.
 */
@Data
public class CentralBank {
    private final List<Bank> banks = new LinkedList<>();

    /**
     * Регистрация нового банка
     *
     * @param fun - функция вычисления процента по депозитному счету
     */
    public void createBank(Integer id, Double limit, Double comm, Double percent, Function<Double, Double> fun) {
        banks.add(Bank.builder().comm(comm).percent(percent).depositFunc(fun).limit(limit).id(id).build());
    }

    /**
     * Создание транзакций для переводов между счетами различных банков
     *
     * @param transactionDto - объект, хранящий в себе информацию для создания транзакции
     */
    public Transactionable createTransaction(TransactionDto transactionDto) {
        return switch (transactionDto) {
            case TransferTransactionDto dto -> {
                Bank bankFrom = getBank(dto.getFromBankId());
                BankAccountable accountFrom = bankFrom.getAccount(dto.getFromAccId());
                AccountContext contextFrom = new AccountContext(accountFrom, bankFrom);

                Bank bankTo = getBank(dto.getToBankId());
                BankAccountable accountTo = bankTo.getAccount(dto.getToAccId());
                AccountContext contextTo = new AccountContext(accountTo, bankTo);

                yield new TransferTransaction(dto.getValue(), contextFrom, contextTo);
            }
            default -> throw new IllegalStateException("Unexpected value: " + transactionDto);
        };
    }

    public Bank getBank(Integer id) {
        return banks.stream().filter(bank -> Objects.equals(bank.getId(), id)).findFirst().get();
    }

    public void addBank(Bank bank) {
        banks.add(bank);
    }

    /**
     * Сообщает банкам, что пора начислить коммиссию
     */
    void addComm() {
        banks.forEach(Bank::addComm);
    }

    /**
     * Сообщает банкам, что пора начислить процент
     */
    void addPercent() {
        banks.forEach(Bank::addPercent);
    }
}
