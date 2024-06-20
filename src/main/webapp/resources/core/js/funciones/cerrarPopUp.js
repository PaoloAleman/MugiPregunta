cerrarPopUp(document.getElementById("contenedorMensajePopUp"))
function cerrarPopUp(contenedor){
    var botonCerrarPopUp=document.getElementById("cerrarPopUp")

    botonCerrarPopUp.addEventListener("click",()=>{
        contenedor.innerHTML=""
    })

    document.addEventListener("keydown",function (event){
        if (event.key === 'Escape' || event.key === 'Esc' || event.keyCode === 27) {
            contenedor.innerHTML=""
        }
    })
}