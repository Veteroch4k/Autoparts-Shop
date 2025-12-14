package com.popov314.autoparts.model.gui;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "menu_permissions")
@Data
public class MenuPermission {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "role_name", nullable = false)
  private String roleName;

  // Связь с пунктом меню
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "menu_item_id", nullable = false)
  private MenuItem menuItem;

  // Флаги доступа
  @Column(name = "can_read")
  private boolean canRead = false;

  @Column(name = "can_write")
  private boolean canWrite = false;

  @Column(name = "can_edit")
  private boolean canEdit = false;

  @Column(name = "can_delete")
  private boolean canDelete = false;
}