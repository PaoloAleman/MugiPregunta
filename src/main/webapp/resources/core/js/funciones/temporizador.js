let tiempoInicial = 9;

function mostrarTemporizador() {
    document.getElementById("temporizador").textContent = tiempoInicial;

    tiempoInicial--;

    if (tiempoInicial < 0) {
        clearInterval(temporizador);
    }

    const redirectTimeout = 9500;


    switch (window.location.pathname) {
        case '/duelo/versus':
            setTimeout(() => {
                window.location.href = "/duelo/resultado";
            }, redirectTimeout);
            break;
        case '/partida':
            setTimeout(() => {
                window.location.href = "/respuestaIncorrecta";
            }, redirectTimeout);
            break;
        case '/respuestaCorrecta':
            setTimeout(() => {
                window.location.href = "/partida";
            }, redirectTimeout);
            break;
    }
}
let temporizador = setInterval(mostrarTemporizador, 1000);
