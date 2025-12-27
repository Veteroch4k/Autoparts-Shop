const appSettings = {
  // Запуск при открытии любой страницы
  init: function() {
    // 1. Достаем настройки из памяти браузера
    const theme = localStorage.getItem('theme') || 'light';
    const size = localStorage.getItem('size') || '1';
    const font = localStorage.getItem('font') || 'modern';

    // 2. Применяем их к тегу body
    document.body.setAttribute('data-theme', theme);
    document.body.setAttribute('data-size', size);
    document.body.setAttribute('data-font', font);

    // 3. Синхронизируем контролы (если мы на странице настроек)
    this.syncControls(theme, size, font);
  },

  // --- МЕТОДЫ, ВЫЗЫВАЕМЫЕ ИЗ HTML ---

  setTheme: function(mode) {
    document.body.setAttribute('data-theme', mode);
    localStorage.setItem('theme', mode);
  },

  setFontSize: function(level) {
    document.body.setAttribute('data-size', level);
    localStorage.setItem('size', level);
  },

  setFontFamily: function(type) {
    document.body.setAttribute('data-font', type);
    localStorage.setItem('font', type);
  },

  // Функция чтобы галочки соответствовали реальности
  syncControls: function(theme, size, font) {
    // Ставим нужную радио-кнопку Темы
    const themeRadio = document.querySelector(`input[name="theme"][value="${theme}"]`);
    if (themeRadio) themeRadio.checked = true;

    // Ставим ползунок Размера
    const sizeRange = document.getElementById('fontSizeRange');
    if (sizeRange) sizeRange.value = size;

    // Ставим радио-кнопку Шрифта
    const fontRadio = document.querySelector(`input[name="fontFamily"][value="${font}"]`);
    if (fontRadio) fontRadio.checked = true;
  }
};

// Запускаем скрипт сразу как загрузится страница
document.addEventListener("DOMContentLoaded", () => appSettings.init());