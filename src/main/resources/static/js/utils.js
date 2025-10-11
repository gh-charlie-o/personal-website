function timer(isoDate) {
    return {
        startDate: null,
        displayText: 'Calculando...', // Initial display text

        init() {
            // Parse the ISO date string passed from Thymeleaf
            this.startDate = new Date(isoDate);

            // Update the text immediately on load
            this.updateText();

            // Then update it every second
            setInterval(() => {
                this.updateText();
            }, 1000);
        },

        updateText() {
            this.displayText = this.formatTimeSince(this.startDate);
        },

        formatTimeSince(initialDate) {
            const now = new Date();
            let totalSeconds = Math.floor((now - initialDate) / 1000);

            let years = Math.floor(totalSeconds / 31536000);
            totalSeconds %= 31536000;
            let months = Math.floor(totalSeconds / 2592000);
            totalSeconds %= 2592000;
            let days = Math.floor(totalSeconds / 86400);
            totalSeconds %= 86400;
            let hours = Math.floor(totalSeconds / 3600);
            totalSeconds %= 3600;
            let minutes = Math.floor(totalSeconds / 60);
            let seconds = totalSeconds % 60;

            const parts = [];
            if (years > 0) parts.push(`${years} ${years === 1 ? 'año' : 'años'}`);
            if (months > 0) parts.push(`${months} ${months === 1 ? 'mes' : 'meses'}`);
            if (days > 0) parts.push(`${days} ${days === 1 ? 'día' : 'días'}`);
            if (hours > 0) parts.push(`${hours} ${hours === 1 ? 'hora' : 'horas'}`);
            if (minutes > 0 || parts.length > 0) {
                const formattedMinutes = minutes < 10 ? '0' + minutes : minutes;
                parts.push(`${formattedMinutes} ${minutes === 1 ? 'minuto' : 'minutos'}`);
            }
            const formattedSeconds = seconds < 10 ? '0' + seconds : seconds;
            parts.push(`${formattedSeconds} ${seconds === 1 ? 'segundo' : 'segundos'}`);

            let result = "";
            if (parts.length > 1) {
                result += parts.slice(0, -1).join(', ') + ' y ' + parts.slice(-1);
            } else {
                result += parts[0];
            }

            return result;
        }
    }
}