package exercise.dto.users;

import exercise.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

// BEGIN
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class UsersPage {
    @NotNull
    private List<User> users;
    String term;
}
// END
