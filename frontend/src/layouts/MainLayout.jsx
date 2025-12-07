import React from 'react';
import { Outlet, useNavigate } from 'react-router-dom';
import {
  AppBar, Toolbar, Typography, Drawer, List, ListItem,
  ListItemButton, ListItemIcon, ListItemText, Box, CssBaseline
} from '@mui/material';
import PeopleIcon from '@mui/icons-material/People';
import DashboardIcon from '@mui/icons-material/Dashboard';
import DescriptionIcon from '@mui/icons-material/Description';
import LogoutIcon from '@mui/icons-material/Logout';

const drawerWidth = 240;

const MainLayout = () => {
  const navigate = useNavigate();

  // Пункты меню (потом мы будем фильтровать их по ролям!)
  const menuItems = [
    { text: 'Главная', icon: <DashboardIcon />, path: '/' },
    { text: 'Сотрудники', icon: <PeopleIcon />, path: '/employees' }, // См.
    { text: 'Документы', icon: <DescriptionIcon />, path: '/documents' }, // См.
  ];

  return (
      <Box sx={{ display: 'flex' }}>
        <CssBaseline />

        {/* Верхняя шапка (AppBar) */}
        <AppBar position="fixed" sx={{ zIndex: (theme) => theme.zIndex.drawer + 1 }}>
          <Toolbar>
            <Typography variant="h6" noWrap component="div">
              АИС Отдел кадров (Магазин Автозапчастей)
            </Typography>
          </Toolbar>
        </AppBar>

        {/* Боковое меню (Drawer) */}
        <Drawer
            variant="permanent"
            sx={{
              width: drawerWidth,
              flexShrink: 0,
              [`& .MuiDrawer-paper`]: { width: drawerWidth, boxSizing: 'border-box' },
            }}
        >
          <Toolbar /> {/* Пустое место под шапкой */}
          <Box sx={{ overflow: 'auto' }}>
            <List>
              {menuItems.map((item) => (
                  <ListItem key={item.text} disablePadding>
                    <ListItemButton onClick={() => navigate(item.path)}>
                      <ListItemIcon>{item.icon}</ListItemIcon>
                      <ListItemText primary={item.text} />
                    </ListItemButton>
                  </ListItem>
              ))}
            </List>

            {/* Кнопка выхода */}
            <List style={{ marginTop: 'auto' }}>
              <ListItem disablePadding>
                <ListItemButton>
                  <ListItemIcon><LogoutIcon /></ListItemIcon>
                  <ListItemText primary="Выход" />
                </ListItemButton>
              </ListItem>
            </List>
          </Box>
        </Drawer>

        {/* Основной контент страницы */}
        <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
          <Toolbar /> {/* Отступ сверху, чтобы контент не залез под шапку */}
          <Outlet /> {/* Сюда будут подставляться страницы (React Router) */}
        </Box>
      </Box>
  );
};

export default MainLayout;