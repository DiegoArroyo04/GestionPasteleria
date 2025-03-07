// Obtener el modal
var errorModal = document.getElementById("errorModal");

// Obtener el botón de cierre del modal
var closeModal = document.querySelector(".close");

// Verificar si el modal existe en el DOM (es decir, si hay un mensaje de error)
if (errorModal) {
    // Mostrar el modal
    errorModal.style.display = "flex";

    // Cuando el usuario hace clic en el botón de cierre, cerrar el modal
    closeModal.onclick = function () {
        errorModal.style.display = "none";
    }

    // Cerrar el modal si el usuario hace clic en cualquier lugar fuera del modal
    window.onclick = function (event) {
        if (event.target == errorModal) {
            errorModal.style.display = "none";
        }
    }
}
