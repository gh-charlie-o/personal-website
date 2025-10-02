/**
 * Theme Toggle Functionality
 * Handles switching between light and dark themes with localStorage persistence
 */

document.addEventListener('DOMContentLoaded', function() {
    const themeToggle = document.getElementById('theme-toggle');
    const themeIcon = document.getElementById('theme-icon');
    const htmlElement = document.documentElement;
    
    // Theme constants
    const THEMES = {
        LIGHT: 'light',
        DARK: 'dark'
    };
    
    const ICONS = {
        LIGHT: 'bi-sun-fill',
        DARK: 'bi-moon-fill'
    };
    
    // Get saved theme from localStorage or default to light
    let currentTheme = localStorage.getItem('theme') || THEMES.LIGHT;
    
    /**
     * Apply theme to the document
     * @param {string} theme - The theme to apply ('light' or 'dark')
     */
    function applyTheme(theme) {
        if (theme === THEMES.DARK) {
            htmlElement.setAttribute('data-theme', 'dark');
            themeIcon.className = ICONS.LIGHT; // Show sun icon when in dark mode
        } else {
            htmlElement.removeAttribute('data-theme');
            themeIcon.className = ICONS.DARK; // Show moon icon when in light mode
        }
        currentTheme = theme;
        localStorage.setItem('theme', theme);
    }
    
    /**
     * Toggle between light and dark themes
     */
    function toggleTheme() {
        const newTheme = currentTheme === THEMES.LIGHT ? THEMES.DARK : THEMES.LIGHT;
        applyTheme(newTheme);
        
        // Add a subtle animation effect
        themeToggle.style.transform = 'scale(0.9)';
        setTimeout(() => {
            themeToggle.style.transform = '';
        }, 150);
        
        // Optional: Log theme change for analytics/debugging
        console.log(`Theme switched to: ${newTheme}`);
    }
    
    /**
     * Check system preference for dark mode
     * @returns {string} The preferred theme based on system settings
     */
    function getSystemPreference() {
        if (window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches) {
            return THEMES.DARK;
        }
        return THEMES.LIGHT;
    }
    
    /**
     * Initialize theme based on saved preference or system preference
     */
    function initializeTheme() {
        // If no saved theme, check system preference
        if (!localStorage.getItem('theme')) {
            currentTheme = getSystemPreference();
        }
        applyTheme(currentTheme);
    }
    
    /**
     * Listen for system theme changes
     */
    function setupSystemThemeListener() {
        if (window.matchMedia) {
            const mediaQuery = window.matchMedia('(prefers-color-scheme: dark)');
            mediaQuery.addEventListener('change', function(e) {
                // Only auto-switch if user hasn't manually set a preference
                if (!localStorage.getItem('theme')) {
                    const systemTheme = e.matches ? THEMES.DARK : THEMES.LIGHT;
                    applyTheme(systemTheme);
                }
            });
        }
    }
    
    /**
     * Add keyboard support for accessibility
     */
    function setupKeyboardSupport() {
        themeToggle.addEventListener('keydown', function(e) {
            if (e.key === 'Enter' || e.key === ' ') {
                e.preventDefault();
                toggleTheme();
            }
        });
    }
    
    /**
     * Add tooltip for better UX
     */
    function setupTooltip() {
        themeToggle.setAttribute('title', 'Toggle theme');
        themeToggle.setAttribute('aria-label', 'Toggle between light and dark theme');
    }
    
    // Initialize everything
    initializeTheme();
    setupSystemThemeListener();
    setupKeyboardSupport();
    setupTooltip();
    
    // Add click event listener
    themeToggle.addEventListener('click', toggleTheme);
    
    // Add smooth transition after initial load
    setTimeout(() => {
        document.body.style.transition = 'background-color 0.3s ease, color 0.3s ease';
    }, 100);
    
    // Export functions for potential external use
    window.ThemeToggle = {
        toggle: toggleTheme,
        setTheme: applyTheme,
        getCurrentTheme: () => currentTheme,
        getSystemPreference: getSystemPreference
    };
});