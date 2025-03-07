document.addEventListener("DOMContentLoaded", function () {
    const carrito = document.querySelector(".carrito");

    // RECORRER PRODUCTOS
    document.querySelectorAll(".productos").forEach((producto) => {
        const btnMas = producto.querySelector(".mas");
        const btnMenos = producto.querySelector(".menos");
        const cantidadTexto = producto.querySelector(".cantidadProducto");
        const hiddenCantidad = producto.querySelector(".hiddenCantidad");
        const formulario = producto.querySelector("form");

        let cantidad = 1;

        // AUMENTAR CANTIDAD PRODUCTO
        btnMas.addEventListener("click", function (e) {
            e.preventDefault();
            cantidad++;
            cantidadTexto.textContent = cantidad;
            hiddenCantidad.value = cantidad;
        });

        // DISMINUIR CANTIDAD PRODUCTO
        btnMenos.addEventListener("click", function (e) {
            e.preventDefault();
            if (cantidad > 1) {
                cantidad--;
                cantidadTexto.textContent = cantidad;
                hiddenCantidad.value = cantidad;
            }
        });

        // EVENTO CUANDO SE AÑADE AL CARRITO
        formulario.addEventListener("submit", function (e) {
            e.preventDefault();

            // ANIMACION DE VUELO
            let clon = producto.querySelector(".productoImagen").cloneNode(true);
            clon.style.position = "fixed";
            clon.style.width = "50px";
            clon.style.height = "50px";
            clon.style.borderRadius = "50%";
            clon.style.transition = "transform 1s ease-in-out, opacity 1s ease-in-out";
            clon.style.zIndex = "1000";
            document.body.appendChild(clon);



            // OBTENER POSICIÓN INICIAL DEL PRODUCTO
            let posicionProducto = producto.querySelector(".productoImagen").getBoundingClientRect();
            let scrollX = window.scrollX || document.documentElement.scrollLeft;
            let scrollY = window.scrollY || document.documentElement.scrollTop;

            clon.style.left = `${posicionProducto.left + scrollX}px`;
            clon.style.top = `${posicionProducto.top + scrollY}px`;



            //MOVIMIENTO HACIA EL CARRITO 
            setTimeout(() => {
                let posicionCarrito = carrito.getBoundingClientRect();
                clon.style.transform = `translate(${posicionCarrito.left - posicionProducto.left}px, ${posicionCarrito.top - posicionProducto.top}px) scale(0.2)`;
                clon.style.opacity = "0";
            }, 100);

            // Eliminar clon después de la animación
            setTimeout(() => {
                clon.remove();
                carrito.classList.add("rebote");
                setTimeout(() => carrito.classList.remove("rebote"), 400);
            }, 1000);

            // Enviar formulario después de la animación
            setTimeout(() => formulario.submit(), 1300);
        });
    });
});
