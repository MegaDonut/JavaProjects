package user;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.LinkedList;
import java.util.List;

/**
 * Пользователь, хранящий в себе данные.
 */
@Data
@Builder
public class User {

    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    private String address;
    private Integer passport;

    private final List<String> messages = new LinkedList<>();

    /**
     * Метод, позволяющий получать сообщения от банка
     */
    public void receive(String message) {
        messages.add(message);
    }
}
