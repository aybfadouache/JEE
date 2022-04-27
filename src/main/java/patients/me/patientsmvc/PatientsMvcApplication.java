package patients.me.patientsmvc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import patients.me.patientsmvc.entities.Patient;
import patients.me.patientsmvc.repositories.PatientRepos;
import patients.me.patientsmvc.sec.services.SecurityService;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(PatientsMvcApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //@Bean
    CommandLineRunner commandLineRunner(PatientRepos patientRepos) {
        return args -> {
            patientRepos.save
                    (new Patient(null,"Ayoub",new Date(), false, 12));
            patientRepos.save
                    (new Patient(null,"Mohammed",new Date(), true, 42));
            patientRepos.save
                    (new Patient(null,"Kenza",new Date(), true, 58));
            patientRepos.save
                    (new Patient(null,"Zakaria",new Date(), false, 80));

            patientRepos.findAll().forEach(p->{System.out.println(p.getNom());
            });

        };
    }
    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("mohamed","1234","1234");
            securityService.saveNewUser("safia","1234","1234");
            securityService.saveNewUser("ayoub","1234","1234");

            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","");

            securityService.addRoleToUser("ayoub","ADMIN");
            securityService.addRoleToUser("ayoub","USER");
            securityService.addRoleToUser("safia","USER");
            securityService.addRoleToUser("mohamed","USER");
        };
    }

}
