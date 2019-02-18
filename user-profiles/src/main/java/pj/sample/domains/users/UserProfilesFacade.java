package pj.sample.domains.users;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfilesFacade {
    private UserProfilesRepository userProfilesRepository;

    public UserProfilesFacade(UserProfilesRepository userProfilesRepository) {
        this.userProfilesRepository = userProfilesRepository;
    }

    @Secured("hasRole('ADMIN') or #username == principal.name")
    public Optional<UserProfile> getByUsername(String username) {
        return userProfilesRepository.findById(username);
    }

    @Secured("hasRole('ADMIN')")
    public void update(UserProfile user) {
        userProfilesRepository.save(user);
    }

    @Secured("hasRole('ADMIN') or #username == principal.name")
    public void delete(String username) {
        userProfilesRepository.findById(username);
    }
}
