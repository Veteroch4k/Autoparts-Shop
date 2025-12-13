package com.popov314.autoparts.model.gui;

import java.util.List;
import lombok.Data;

@Data
public class MenuDto {
  private Long id;
  private String title;
  private String url;
  private List<MenuDto> children;

  // Права текущего пользователя на этот пункт
  private boolean canWrite;
  private boolean canEdit;
  private boolean canDelete;
}