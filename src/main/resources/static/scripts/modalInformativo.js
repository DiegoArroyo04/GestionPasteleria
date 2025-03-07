document.addEventListener("DOMContentLoaded", function () {
    // Obtener ambos modales
    var modales = document.querySelectorAll(".modal");

    // Mostrar cada modal si está presente en el DOM
    modales.forEach(function (modal) {
        modal.classList.add("mostrar");

        // Cerrar el modal al hacer clic fuera de él
        modal.addEventListener("click", function (event) {
            if (event.target === modal) {
                cerrarModal(modal.id);
            }
        });
    });
});

// Función para cerrar un modal específico
function cerrarModal(modalId) {
    var modal = document.getElementById(modalId);
    if (modal) {
        modal.classList.remove("mostrar");
    }
}

