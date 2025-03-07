
//GENERAR FACTURA CAPTURANDO EL CONTENEDOR DEL PEDIDO
document.getElementById("generarFactura").addEventListener("click", function () {
    const element = document.querySelector(".resumenContainer");
    const botones = document.querySelectorAll(".pedidoResumen a");

    //OCULTAR BOTONES PARA LA FOTO
    botones.forEach(boton => boton.style.display = "none");

    html2pdf()
        .set({
            margin: 10,
            filename: 'Resumen_Pedido_Dulce_De_Siempre.pdf',
            image: { type: 'jpeg', quality: 0.98 },
            html2canvas: { scale: 2 },
            jsPDF: { unit: 'mm', format: 'a4', orientation: 'portrait' }
        })
        .from(element)
        .save()
        .then(() => {
            //UNA VEZ GENERADO EL PDF LOS BOTONES VUELVEN A SU SITIO
            botones.forEach(boton => boton.style.display = "inline-block");
        });
});
