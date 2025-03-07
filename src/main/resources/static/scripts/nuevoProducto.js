
let ingredienteIndex = 1;


//CREACION DINAMICA DE INGREDIENTES
document.getElementById('aniadirIngrediente').addEventListener('click', function () {

    //CONTENEDORES INGREDIENTES
    const container = document.getElementById('ingredientesContainer');
    const nuevoIngrediente = document.createElement('div');
    nuevoIngrediente.classList.add('ingrediente-item');


    //CREAR LABEL
    const ingredienteLabel = document.createElement('label');
    ingredienteLabel.textContent = 'Ingrediente:';
    nuevoIngrediente.appendChild(ingredienteLabel);

    //CREAR SELECT
    const ingredienteSelect = document.createElement('select');
    ingredienteSelect.name = `ingredientes[${ingredienteIndex}].nombre`;
    ingredienteSelect.classList.add('input');
    ingredienteSelect.required = true;

    // CREAR OPCIONES SELECT
    const defaultOption = document.createElement('option');
    defaultOption.value = '';
    defaultOption.textContent = 'Selecciona un ingrediente';
    defaultOption.disabled = true;
    defaultOption.selected = true;
    ingredienteSelect.appendChild(defaultOption);

    //RECORRER LISTA DE INGREDIENTES EXISTENTES CREANDO OPCIONES
    ingredientesExistentes.forEach(ingrediente => {
        const option = document.createElement('option');
        option.value = ingrediente.nombre;
        option.textContent = ingrediente.nombre;
        ingredienteSelect.appendChild(option);
    });

    nuevoIngrediente.appendChild(ingredienteSelect);

    //CANTIDAD INGREDIENTE

    //LABEL
    const cantidadLabel = document.createElement('label');
    cantidadLabel.textContent = 'Cantidad:';
    nuevoIngrediente.appendChild(cantidadLabel);

    //INPUT
    const cantidadInput = document.createElement('input');
    cantidadInput.type = 'number';
    cantidadInput.name = `ingredientes[${ingredienteIndex}].cantidad`;
    cantidadInput.classList.add('input');
    cantidadInput.placeholder = 'Cantidad necesaria';
    cantidadInput.required = true;
    nuevoIngrediente.appendChild(cantidadInput);

    container.appendChild(nuevoIngrediente);

    //AUMENTAR INDICE PARA QUE EL PROXIMO INGREDIENTE VAYA EN LA PROXIMA POSICION DE LA LISTA DE INGREDIENTESs
    ingredienteIndex++;

    // Añadir evento al nuevo select
    ingredienteSelect.addEventListener('change', actualizarOpciones);

    // Actualizar opciones inmediatamente al añadir el nuevo select
    actualizarOpciones();
});

//FUNCION PARA ACTUALIZAR OPCIONES DE LOS SELECTS
function actualizarOpciones() {
    const selects = document.querySelectorAll('#ingredientesContainer select');

    // OBTENER VALORES SELECCIONADOS QUE NO ESTAN VACIOS
    const seleccionados = new Set([...selects].map(select => select.value).filter(value => value !== ''));

    selects.forEach(select => {
        const opciones = select.querySelectorAll('option');

        //DE LA LISTA DE SELECTS RECORRER OPCIONES Y DESHABILITAR LAS QUE YA ESTAN SELECCIONADAS
        opciones.forEach(option => {
            if (option.value !== '' && seleccionados.has(option.value) && option.value !== select.value) {
                option.disabled = true;
            } else {
                option.disabled = false;
            }
        });

    });
}

// EVENTO PARA EL PRIMER SELECT
document.querySelectorAll('#ingredientesContainer select').forEach(select => {
    select.addEventListener('change', actualizarOpciones);
});