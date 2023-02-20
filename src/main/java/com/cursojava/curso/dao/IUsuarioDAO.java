package com.cursojava.curso.dao;

import com.cursojava.curso.model.Usuario;

import java.util.List;

public interface IUsuarioDAO {
    List<Usuario> getusuario();

    void delete(int id);

    void postusuario(Usuario usuario);

  //  public int verificar(Usuario usuario);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}


