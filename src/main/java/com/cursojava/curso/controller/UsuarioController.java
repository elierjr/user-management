package com.cursojava.curso.controller;

import com.cursojava.curso.dao.IUsuarioDAO;
import com.cursojava.curso.model.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {
    @Autowired
    private IUsuarioDAO usuarioDAO;
    @Autowired
    private JWTUtil jwtUtil;
   @RequestMapping(value = "api/usuario/{id}")
    public Usuario getUsuario(@PathVariable int id)
    {
        Usuario user = new Usuario(id,"pablo","perez","fulano@hola.com","53103596","1234");
        return user;
    }

    @RequestMapping(value = "api/usuarios")
    public List<Usuario> getUsuarios(@RequestHeader (value = "Authorization") String token)
    {
        if(!verificartoken(token)){return null;}
        return usuarioDAO.getusuario();
    }
     private boolean verificartoken(String token)
     {
         String id =jwtUtil.getKey(token);
         return id != null;
     }


    @RequestMapping(value = "api/usuarios" , method = RequestMethod.POST)
    public void postUsuarios(@RequestBody Usuario usuario)
    {
        Argon2 argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon.hash(1,1024,1,usuario.getPassword());
        usuario.setPassword(hash);
       usuarioDAO.postusuario(usuario);
    }



    @RequestMapping(value = "api/usuarios/{id}" , method = RequestMethod.DELETE)
    public void delete(@RequestHeader (value = "Authorization") String token,@PathVariable int id )
    {
        if(!verificartoken(token)){return;}
        usuarioDAO.delete(id);

    }



}
