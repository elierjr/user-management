// Call the dataTables jQuery plugin
$(document).ready(function() {
       
        cargarusuarios ();
  $('#usuarios').DataTable();
  actualizarcorreo();
});
 
function  actualizarcorreo(){
  document.getElementById("correo").outerHTML = localStorage.email;
}
async function cargarusuarios ()
{
  const request = await fetch('api/usuarios', {
    method: 'GET',
    headers: getheaders()
  });
  const usuarios = await request.json();
  
  
  let listadohtml = '';
  for(let usuario of usuarios){
    let telefono = usuario.telefono == null ? "-":usuario.telefono;
    let botoneliminar = ' <a href="#" onclick = "eliminarusuario('+usuario.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
    let usuariohtml = '<tr><td>'+usuario.id+'</td><td>' +usuario.nombre+ ' ' 
    +usuario.apellido+ ' </td><td>'+usuario.email+'</td><td>'
    +telefono+ '</td><td> '+botoneliminar+'</td></tr>' 
  
    listadohtml += usuariohtml;
  }
  console.log(content);
     

  document.querySelector("#usuarios tbody").outerHTML = listadohtml ;
}
function getheaders(){
  return {
    'Accept': 'application/json',
    'Content-Type': 'application/json',
    'Authorization':localStorage.token
  };
}

async function eliminarusuario(id) {
  if(!confirm('do you wish to delete this user? '))
  {
    return;
  }

  const request = await fetch('api/usuarios/'+id+'', {
    method: 'DELETE',
    headers: getheaders()
  });
  
  location.reload();
  
}