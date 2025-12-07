import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import MainLayout from './layouts/MainLayout';
import HomePage from './pages/HomePage';
import EmployeesPage from './pages/EmployeesPage';

function App() {
  return (
      <BrowserRouter>
        <Routes>
          {/* Оборачиваем страницы в MainLayout */}
          <Route path="/" element={<MainLayout />}>
            <Route index element={<HomePage />} />
            <Route path="employees" element={<EmployeesPage />} />
            <Route path="documents" element={<h2>Здесь будут документы</h2>} />
          </Route>

          {/* Отдельный роут для Логина (без меню) */}
          <Route path="/login" element={<h2>Страница входа</h2>} />
        </Routes>
      </BrowserRouter>
  );
}

export default App;