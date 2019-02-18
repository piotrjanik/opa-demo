package pj.sample.api.v1;


import lombok.Data;
import pj.sample.domains.users.UserProfile;
import pj.sample.domains.users.UserProfileState;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
class UserProfileV1 {
    @NotNull
    String username;
    String name;
    @Email
    String email;
    List<String> roles;
    @NotNull
    String state;

    UserProfileV1(@NotNull UserProfile user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.state = user.getState().toString();
    }

    UserProfile toDomain() {
        return UserProfile.builder()
                .name(this.name)
                .email(this.email)
                .state(UserProfileState.valueOf(this.state))
                .build();

    }
}
