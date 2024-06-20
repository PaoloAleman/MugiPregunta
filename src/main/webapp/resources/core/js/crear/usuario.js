var crearUsuario=document.getElementById("crearUsuario")
var contenedorMensajePopUp=document.getElementById("contenedorMensajePopUp")
crearUsuario.addEventListener("click",()=>{

    var data={
        nombre : document.getElementById("nombre").value,
        fechaNacimiento : document.getElementById("fechaNacimiento").value,
        idSexo : document.getElementById("idSexo").value,
        idCiudad : document.getElementById("idCiudad").value,
        username : document.getElementById("username").value,
        password : document.getElementById("password").value,
        repetirPassword : document.getElementById("repetir_password").value,
        mail : document.getElementById("mail").value,
        img : document.getElementById("img_profile").value,
    }

    $.ajax({
        url: '/registro',
        method: 'POST',
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function(response) {
            contenedorMensajePopUp.innerHTML=response;
            cerrarPopUp(contenedorMensajePopUp)
            setTimeout(redirigir,3000);
        },
        error: function(error) {
            console.error("Error al enviar la solicitud:", error);
        }
    });
})
