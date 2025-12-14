package com.popov314.autoparts.service;

import com.popov314.autoparts.model.gui.MenuDto;
import com.popov314.autoparts.model.gui.MenuPermission;
import com.popov314.autoparts.repository.gui.MenuPermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuService {

  private final MenuPermissionRepository permissionRepo;

  public MenuDto getPagePermissions(String currentUrl, Authentication authentication) {

    // 1. Получаем роль текущего пользователя
    String userRole = authentication.getAuthorities().stream()
        .findFirst()
        .get()
        .getAuthority();

    // 2. Ищем права в базе
    MenuPermission perm = permissionRepo.findByRoleAndUrl(userRole, currentUrl)
        .orElse(null);

    // 3. Заполняем DTO
    MenuDto dto = new MenuDto();
    if (perm != null) {
      dto.setCanWrite(perm.isCanWrite());
      dto.setCanEdit(perm.isCanEdit());
      dto.setCanDelete(perm.isCanDelete());
    } else {
      // Если прав не нашли — запрещаем всё по умолчанию (безопасность!)
      dto.setCanRead(false);
      dto.setCanWrite(false);
      dto.setCanEdit(false);
      dto.setCanDelete(false);
    }
    return dto;
  }
}
