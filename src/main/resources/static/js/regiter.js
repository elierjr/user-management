$(document).ready(function() {
 
});

async function registerUsuarios ()
{
let datos = {};
datos.nombre=document.getElementById("exampleFirstName").value;
datos.apellido=document.getElementById("exampleLastName").value;
datos.email=document.getElementById("exampleInputEmail").value;
datos.password=document.getElementById("exampleInputPassword").value;
let repetirpassword =document.getElementById("exampleRepeatPassword").value;
if(datos.password !=repetirpassword)
{
alert("the password is wrong");
return;
}
const request = await fetch('api/usuarios', {
method: 'POST',
headers: {
  'Accept': 'application/json',
  'Content-Type': 'application/json'
},
body: JSON.stringify(datos)
});

    alert("The account was created with success");
    window.location.href="login.html";
}