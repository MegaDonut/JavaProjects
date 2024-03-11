package bank;

import Exceptions.UnexpectedDtoException;
import bank.account.*;
import bank.account.accountContext.AccountContext;
import bank.account.dto.DebitAccountDto;
import bank.account.dto.DepositAccountDto;
import bank.transaction.AddTransaction;
import bank.transaction.TakeTransaction;
import bank.transaction.Transactionable;
import bank.transaction.TransferTransaction;
import bank.transaction.dto.AddTransactionDto;
import bank.transaction.dto.TakeTransactionDto;
import bank.transaction.dto.TransactionDto;
import bank.transaction.dto.TransferTransactionDto;
import lombok.Builder;
import lombok.Data;
import user.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * Банк
 * Выполняет операции снятия, пополнения, переводов в пределах этого же банка.
 * Может провести рассылку сообщений своим подписчикам.
 * Добавляет комиссию и процент на соответствующие счета.
 * Сообщает счетам, о пройденном времени (для вычисления процента)
 */
@Data
@Builder
public class Bank {
    private Integer id;
    private Double comm;
    /**
     * Функция для вычисления процента депозитного счета
     */
    private Function<Double, Double> depositFunc;
    private Double percent;
    private Double limit;

    private final List<BankAccountable> accounts = new LinkedList<>();
    private final List<User> subscribers = new LinkedList<>();

    public void addAccount(BankAccountable account) {
        accounts.add(account);
    }

    /**
     * Создание транзакций для пополнения, снятия, переводов между счетами внути текущего банка
     *
     * @param transactionDto - объект, хранящий в себе информацию для создания транзакции
     */
    public Transactionable createTrans(TransactionDto transactionDto) {
        return switch (transactionDto) {
            case AddTransactionDto dto -> {
                BankAccountable account = getAccount(dto.getAccId());
                yield new AddTransaction(dto.getValue(), new AccountContext(account, this));
            }
            case TakeTransactionDto dto -> {
                BankAccountable account1 = getAccount(dto.getAccId());
                yield new TakeTransaction(dto.getValue(), new AccountContext(account1, this));
            }
            case TransferTransactionDto dto -> {
                BankAccountable accountFrom = getAccount(dto.getFromBankId());
                AccountContext contextFrom = new AccountContext(accountFrom, this);

                BankAccountable accountTo = getAccount(dto.getToBankId());
                AccountContext contextTo = new AccountContext(accountTo, this);

                yield new TransferTransaction(dto.getValue(), contextFrom, contextTo);
            }
            default -> throw new IllegalStateException("Unexpected value: " + transactionDto);
        };
    }

    public BankAccountable getAccount(Integer id) {
        return accounts.stream().filter(acc -> Objects.equals(acc.watchId(), id)).findFirst().get();
    }

    /**
     * Начислить комиссию
     */
    public void addComm() {
        accounts.forEach(acc -> {
            if (acc instanceof Comissionable) ((Comissionable) acc).chargeCommission(comm);
        });
    }

    /**
     * Начислить процент
     */
    public void addPercent() {
        accounts.forEach(acc -> {
            if (acc instanceof Chargable) ((Chargable) acc).chargeInterest();
        });
    }

    /**
     * Добавить подписчика
     */
    public void subscribe(User user) {
        subscribers.add(user);
    }

    /**
     * Разослать подписчикам сообщение
     */
    public void sendMessage(String message) {
        subscribers.forEach(u -> u.receive(message));
    }
    /**
     * Скипнуть день
     */
    public void tick() {
        accounts.forEach(acc ->
        {
            switch (acc) {
                case DebitAccount ac:
                    try {
                        ac.tick(DebitAccountDto.builder().percent(percent).build());
                    } catch (UnexpectedDtoException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case DepositAccount ac:
                    try {
                        ac.tick((DepositAccountDto.builder().depositPercentFunc(depositFunc).build()));
                    } catch (UnexpectedDtoException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + acc);
            }
        });
    }
}
