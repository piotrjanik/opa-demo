package pj.sample.domains.users;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserProfilesRepository extends CrudRepository<UserProfile, String> {
}
