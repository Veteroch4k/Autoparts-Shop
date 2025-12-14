package com.popov314.autoparts.model;

import com.popov314.autoparts.model.reference_tables.Department;
import com.popov314.autoparts.model.reference_tables.Position;
import com.popov314.autoparts.model.reference_tables.Profession;
import com.popov314.autoparts.model.reference_tables.Qualification;
import com.popov314.autoparts.model.reference_tables.Specialty;
import com.popov314.autoparts.model.reference_tables.WorkPlace;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "work_records")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkRecord {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "employee_id")
  private Employee employee;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "work_place_id")
  private WorkPlace workPlace;

  private ActionType action;

  @Column(name = "action_reason", nullable = false, length = 25)
  @NotBlank(message = "Причина должна быть изложена")
  @Size(max = 25, message = "Причина должна быть изложена максимально кратко, не превышая длину в {max} символов")
  private String actionReason;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "position_id")
  private Position position;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "department_id")
  private Department department;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "profession_id")
  private Profession profession;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "specialty_id")
  private Specialty specialty;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "qualification_id")
  private Qualification qualification;

  @Column(name = "hiring_date", nullable = false)
  @NotNull
  private LocalDate hiringDate;

  @Column(name = "firing_date", nullable = false)
  @NotNull
  private LocalDate firingDate;

  @Column(name = "transfer_date", nullable = false)
  @NotNull
  private LocalDate transferDate;

  @Column(name = "reason_of_leaving")
  private String reasonOfLeaving;


}



