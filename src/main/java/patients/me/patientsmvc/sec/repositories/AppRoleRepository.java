package patients.me.patientsmvc.sec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import patients.me.patientsmvc.sec.entities.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByRoleName(String roleName);
}
