package com.popov314.autoparts.controller;

import com.popov314.autoparts.model.Employee;
import com.popov314.autoparts.model.WorkRecord;
import com.popov314.autoparts.model.reference_tables.Department;
import com.popov314.autoparts.model.reference_tables.Position;
import com.popov314.autoparts.model.reference_tables.Profession;
import com.popov314.autoparts.model.reference_tables.Qualification;
import com.popov314.autoparts.model.reference_tables.Specialty;
import com.popov314.autoparts.model.reference_tables.WorkPlace;
import com.popov314.autoparts.repository.EmployeeRepository;
import com.popov314.autoparts.repository.WorkRecordRepository;
import com.popov314.autoparts.repository.ref_tables.DepartmentRepository;
import com.popov314.autoparts.repository.ref_tables.PositionRepository;
import com.popov314.autoparts.repository.ref_tables.ProfessionRepository;
import com.popov314.autoparts.repository.ref_tables.QualificationRepository;
import com.popov314.autoparts.repository.ref_tables.SpecialtyRepository;
import com.popov314.autoparts.repository.ref_tables.WorkPlaceRepository;
import com.popov314.autoparts.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hr")
@RequiredArgsConstructor
class HRController {

  private final EmployeeRepository employeeRepository;
  private final DepartmentRepository departmentRepository;
  private final PositionRepository positionRepository;
  private final WorkRecordRepository workRecordRepository;
  private final ProfessionRepository professionRepository;
  private final QualificationRepository qualificationRepository;
  private final SpecialtyRepository specialtyRepository;
  private final WorkPlaceRepository workPlaceRepository;

  private final MenuService menuService;

  @GetMapping("/employees")
  public String employees(Model model, Authentication authentication) {
    model.addAttribute("employees", employeeRepository.findAll());
    model.addAttribute("employee", new Employee());
    model.addAttribute("perms", menuService.getPagePermissions("/hr/employees", authentication));
    return "/hr/employees";
  }

  @PostMapping("/employees/save")
  public String saveEmployee(@ModelAttribute Employee employee) {
    employeeRepository.save(employee);
    return "redirect:/hr/employees";
  }

  @DeleteMapping("/employees/delete/{id}")
  @PreAuthorize("hasRole('DIRECTOR')")
  public String deleteEmployee(@PathVariable int id) {
    employeeRepository.deleteById(id);
    return "redirect:/hr/employees";
  }

  @GetMapping("/departments")
  public String departments(Model model, Authentication authentication) {
    model.addAttribute("departments", departmentRepository.findAll());
    model.addAttribute("department", new Department());
    model.addAttribute("perms", menuService.getPagePermissions("/hr/departments", authentication));
    return "/hr/departments";
  }

  @PostMapping("/departments/save")
  public String saveDepartment(@ModelAttribute Department department) {
    departmentRepository.save(department);
    return "redirect:/hr/departments";
  }

  @DeleteMapping("/departments/delete/{id}")
  @PreAuthorize("hasRole('DIRECTOR')")
  public String deleteDepartment(@PathVariable int id) {
    departmentRepository.deleteById(id);
    return "redirect:/hr/departments";
  }

  @GetMapping("/positions")
  public String positions(Model model, Authentication authentication) {
    model.addAttribute("positions", positionRepository.findAll());
    model.addAttribute("position", new Position());
    model.addAttribute("perms", menuService.getPagePermissions("/hr/positions", authentication));
    return "/hr/positions";
  }

  @PostMapping("/positions/save")
  public String savePosition(@ModelAttribute Position position) {
    positionRepository.save(position);
    return "redirect:/hr/positions";
  }

  @DeleteMapping("/positions/delete/{id}")
  @PreAuthorize("hasRole('DIRECTOR')")
  public String deletePosition(@PathVariable int id) {
    positionRepository.deleteById(id);
    return "redirect:/hr/positions";
  }

  @GetMapping("/work_records")
  public String workRecords(Model model, Authentication authentication) {
    model.addAttribute("workRecords", workRecordRepository.findAll());
    model.addAttribute("workRecord", new WorkRecord());
    model.addAttribute("perms", menuService.getPagePermissions("/hr/work_records", authentication));
    return "/hr/work_records";
  }

  @PostMapping("/work_records/save")
  public String saveWorkRecord(@ModelAttribute WorkRecord workRecord) {
    workRecordRepository.save(workRecord);
    return "redirect:/hr/work_records";
  }

  @DeleteMapping("/work_records/delete/{id}")
  @PreAuthorize("hasRole('DIRECTOR')")
  public String deleteWorkRecord(@PathVariable int id) {
    workRecordRepository.deleteById(id);
    return "redirect:/hr/work_records";
  }


  @GetMapping("/professions")
  public String professions(Model model, Authentication authentication) {
    model.addAttribute("professions", professionRepository.findAll());
    model.addAttribute("profession", new Profession());
    model.addAttribute("perms", menuService.getPagePermissions("/hr/professions", authentication));
    return "/hr/professions";
  }

  @PostMapping("/professions/save")
  public String saveProfession(@ModelAttribute Profession profession) {
    professionRepository.save(profession);
    return "redirect:/hr/professions";
  }

  @DeleteMapping("/professions/delete/{id}")
  @PreAuthorize("hasRole('DIRECTOR')")
  public String deleteProfession(@PathVariable int id) {
    professionRepository.deleteById(id);
    return "redirect:/hr/professions";
  }

  @GetMapping("/qualifications")
  public String qualifications(Model model, Authentication authentication) {
    model.addAttribute("qualifications", qualificationRepository.findAll());
    model.addAttribute("qualification", new Qualification());
    model.addAttribute("perms", menuService.getPagePermissions("/hr/qualifications", authentication));
    return "/hr/qualifications";
  }

  @PostMapping("/qualifications/save")
  public String saveQualification(@ModelAttribute Qualification qualification) {
    qualificationRepository.save(qualification);
    return "redirect:/hr/qualifications";
  }

  @DeleteMapping("/qualifications/delete/{id}")
  @PreAuthorize("hasRole('DIRECTOR')")
  public String deleteQualification(@PathVariable int id) {
    qualificationRepository.deleteById(id);
    return "redirect:/hr/qualifications";
  }

  @GetMapping("/specialties")
  public String specialties(Model model, Authentication authentication) {
    model.addAttribute("specialties", specialtyRepository.findAll());
    model.addAttribute("specialty", new Specialty());
    model.addAttribute("perms", menuService.getPagePermissions("/hr/specialties", authentication));
    return "/hr/specialties";
  }

  @PostMapping("/specialties/save")
  public String saveSpecialty(@ModelAttribute Specialty specialty) {
    specialtyRepository.save(specialty);
    return "redirect:/hr/specialties";
  }

  @DeleteMapping("/specialties/delete/{id}")
  @PreAuthorize("hasRole('DIRECTOR')")
  public String deleteSpecialty(@PathVariable int id) {
    specialtyRepository.deleteById(id);
    return "redirect:/hr/specialties";
  }

  @GetMapping("/workplaces")
  public String workplaces(Model model, Authentication authentication) {
    model.addAttribute("workPlaces", workPlaceRepository.findAll());
    model.addAttribute("workPlace", new WorkPlace());
    model.addAttribute("perms", menuService.getPagePermissions("/hr/workplaces", authentication));
    return "/hr/workplaces";
  }

  @PostMapping("/workplaces/save")
  public String saveWorkPlace(@ModelAttribute WorkPlace workPlace) {
    workPlaceRepository.save(workPlace);
    return "redirect:/hr/workplaces";
  }

  @DeleteMapping("/workplaces/delete/{id}")
  @PreAuthorize("hasRole('DIRECTOR')")
  public String deleteWorkPlace(@PathVariable int id) {
    workPlaceRepository.deleteById(id);
    return "redirect:/hr/workplaces";
  }
}
