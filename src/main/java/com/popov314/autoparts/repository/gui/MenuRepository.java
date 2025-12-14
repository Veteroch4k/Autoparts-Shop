package com.popov314.autoparts.repository.gui;

import com.popov314.autoparts.model.gui.MenuItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MenuRepository extends JpaRepository<MenuItem, Integer> {

  @Query("""
        SELECT DISTINCT m
        FROM MenuItem m
        JOIN MenuPermission p ON p.menuItem = m
        LEFT JOIN FETCH m.children c
        LEFT JOIN MenuPermission pc ON pc.menuItem = c
        WHERE p.roleName = :roleName
          AND p.canRead = true
          AND m.parent IS NULL
          AND (c IS NULL OR (pc.roleName = :roleName AND pc.canRead = true))
        ORDER BY m.sortOrder
    """)
  List<MenuItem> findRootMenuByRole(@Param("roleName") String roleName);

}
