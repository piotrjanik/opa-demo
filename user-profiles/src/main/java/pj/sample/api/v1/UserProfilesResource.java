package pj.sample.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import pj.sample.domains.users.UserProfilesFacade;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/user-profiles")
public class UserProfilesResource {

    @Autowired
    UserProfilesFacade userProfilesFacade;


    @GetMapping("/me")
    public UserProfileV1 getMe(@AuthenticationPrincipal JwtAuthenticationToken principal) {
        return userProfilesFacade.getByUsername(principal.getName()).map(UserProfileV1::new).orElse(null);
    }

    @GetMapping("/{username}")
    public UserProfileV1 getByName(@PathVariable String username) {
        return userProfilesFacade.getByUsername(username).map(UserProfileV1::new).orElse(null);
    }

    @PutMapping("/{username}")
    public void update(@PathVariable String username, @RequestBody @Valid UserProfileV1 user) {
        Assert.isTrue(user.getUsername().equals(username), "Different username in path and body");
        userProfilesFacade.update(user.toDomain());
    }

    @DeleteMapping("/{username}")
    public void delete(@PathVariable String username) {
        userProfilesFacade.delete(username);
    }


}
