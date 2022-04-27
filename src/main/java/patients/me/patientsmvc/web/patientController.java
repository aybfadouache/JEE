package patients.me.patientsmvc.web;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import patients.me.patientsmvc.entities.Patient;
import patients.me.patientsmvc.repositories.PatientRepos;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class patientController {
    private PatientRepos patientRepos;

    @GetMapping(path = "/user/index")
    public String patients(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "5") int size,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword){
        Page<Patient> pagePatients=patientRepos.findByNomContains(keyword,PageRequest.of(page,size));
        model.addAttribute("listPatients", pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword", keyword);
        return "patients";
    }
    @GetMapping("/admin/delete")
    public String delete(Long id, String keyword, int page)
    {
        patientRepos.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/")
    public String home() {

        return "home";
    }

    @GetMapping("/user/patients")
    @ResponseBody
    public List<Patient> listPatients() {

        return patientRepos.findAll();
    }

    @GetMapping("/admin/formPatients")
    public String formPatients(Model model)
    {
        model.addAttribute("patient", new Patient());
        return "formPatients";
    }

    @PostMapping("/admin/save")
    public String save(Model model, @Valid Patient patient, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword)
    {
        if(bindingResult.hasErrors())
            return "formPatients";
        patientRepos.save(patient);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;

    }

    @GetMapping("/admin/editPatient")
    public String editPatient(Model model, Long id, String keyword, int page)
    {
        Patient patient=patientRepos.findById(id).orElse(null);
        if (patient == null) throw new RuntimeException("Patient introuvable");
        model.addAttribute("patient", patient);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editPatient";
    }

}
