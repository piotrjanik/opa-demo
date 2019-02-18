package pj.sample.domains.users;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Builder
@Table(name = "user_profiles")
public class UserProfile {
    @Id
    @Email
    String email;
    @NotNull
    String name;
    @NotNull
    @Enumerated(EnumType.STRING)
    UserProfileState state;
    String comment;
}
