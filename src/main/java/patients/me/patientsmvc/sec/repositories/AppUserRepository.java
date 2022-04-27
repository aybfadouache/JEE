package patients.me.patientsmvc.sec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import patients.me.patientsmvc.sec.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    AppUser findByUsername(String username);
}
