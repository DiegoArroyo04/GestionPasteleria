

//ENVIAR EL CORREO ELECTRONICO
const formulario = document.getElementById("pedidoForm");

// Función que se ejecutará al enviar el formulario
const onSubmit = function (event) {
    event.preventDefault();

    //SERVICIOS DE EMAIL JS 
    const serviceID = 'service_q0kb6d1';
    const templateID = 'template_nz6cv6n';


    emailjs.sendForm(serviceID, templateID, formulario)
        .then(() => {
            formulario.submit();
        }, (err) => {
            formulario.submit();
        });
};


formulario.addEventListener('submit', onSubmit);

