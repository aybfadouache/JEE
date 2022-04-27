package patients.me.patientsmvc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import patients.me.patientsmvc.entities.Patient;

public interface PatientRepos extends JpaRepository<Patient, Long> {
    Page<Patient> findByNomContains(String kw , Pageable pageable);
}
