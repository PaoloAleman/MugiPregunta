function redirigir(){
    var flag=document.getElementById("mensaje")
    const patron = /^-?\d+$/;
    if(patron.test(flag.value)){
        window.location.href = "/home";
    }
}