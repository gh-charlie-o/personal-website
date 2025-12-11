// Theme toggle functionality
(function() {
    const THEME_KEY = 'theme';
    const DARK_CLASS = 'dark';
    const LIGHT_CLASS = 'light';

    // Get the current theme from localStorage or default to dark
    function getCurrentTheme() {
        return localStorage.getItem(THEME_KEY) || 'dark';
    }

    // Set theme on the HTML element
    function setTheme(theme) {
        const html = document.documentElement;

        if (theme === 'dark') {
            html.classList.add(DARK_CLASS);
            html.classList.remove(LIGHT_CLASS);
        } else {
            html.classList.add(LIGHT_CLASS);
            html.classList.remove(DARK_CLASS);
        }

        localStorage.setItem(THEME_KEY, theme);
        updateThemeIcon(theme);
    }

    // Update the icon in the button
    function updateThemeIcon(theme) {
        const icon = document.getElementById('theme-icon');
        if (icon) {
            icon.textContent = theme === 'dark' ? 'light_mode' : 'dark_mode';
        }
    }

    // Toggle between light and dark
    function toggleTheme() {
        const currentTheme = getCurrentTheme();
        const newTheme = currentTheme === 'dark' ? 'light' : 'dark';
        setTheme(newTheme);
    }

    // Initialize theme on page load
    function initTheme() {
        const theme = getCurrentTheme();
        setTheme(theme);

        // Add event listener to toggle button
        const toggleButton = document.getElementById('theme-toggle');
        if (toggleButton) {
            toggleButton.addEventListener('click', toggleTheme);
        }
    }

    // Run initialization when DOM is ready
    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', initTheme);
    } else {
        initTheme();
    }
})();