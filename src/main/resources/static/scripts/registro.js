// Mostrar modal de Términos y Condiciones
document.getElementById('terminosCondiciones').onclick = function () {
    modalTerminosCondiciones.style.display = "flex";
}
var cerrarTerminosCondiciones = document.getElementById('cerrarTerminosCondiciones');

// Cerrar modal de Términos y Condiciones
cerrarTerminosCondiciones.onclick = function () {
    modalTerminosCondiciones.style.display = "none";
}

// Cerrar modal si el usuario hace clic fuera del modal
window.onclick = function (event) {

    if (event.target == modalTerminosCondiciones) {
        modalTerminosCondiciones.style.display = "none";
    }
}

//CERRAR MODAL
document.getElementById('botonAceptarTerminos').onclick = function () {
    modalTerminosCondiciones.style.display = "none";
};


//AL CARGAR LA PAGINA ANIMAR MODAL
document.getElementById("containerRegistro").classList.add("animarModal");


//MOSTRAR MODAL DE ERROR SI EXISTE
document.getElementById("modalError").style.display = "flex";

// Cerrar modal si el usuario hace clic fuera del modal
window.onclick = function (event) {

    if (event.target == document.getElementById("modalError")) {
        document.getElementById("modalError").style.display = "none";
    }
}

var cerrarModalError = document.getElementById("cerrarModalError");

// Cerrar modal de Términos y Condiciones
cerrarModalError.onclick = function () {
    document.getElementById("modalError").style.display = "none";
}

var aceptarError = document.getElementById("botonAceptarError");

// Cerrar modal de Términos y Condiciones
aceptarError.onclick = function () {
    document.getElementById("modalError").style.display = "none";
}

