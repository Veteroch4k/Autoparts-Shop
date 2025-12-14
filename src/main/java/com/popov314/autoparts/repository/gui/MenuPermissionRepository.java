package com.popov314.autoparts.repository.gui;

import com.popov314.autoparts.model.gui.MenuDto;
import com.popov314.autoparts.model.gui.MenuPermission;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MenuPermissionRepository extends JpaRepository<MenuPermission, Integer> {

  // Найти права по Имени Роли и URL пункта меню
  @Query("SELECT mp FROM MenuPermission mp " +
      "JOIN mp.menuItem mi " +
      "WHERE mp.roleName = :roleName AND mi.urlPath = :url")
  Optional<MenuPermission> findByRoleAndUrl(@Param("roleName") String roleName,
      @Param("url") String url);
}
