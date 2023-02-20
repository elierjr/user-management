package com.cursojava.curso.dao;

import com.cursojava.curso.model.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UsuarioDaoImp implements IUsuarioDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Usuario> getusuario() {
        String query = "FROM Usuario" ;
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void delete(int id) {
        Usuario usuario = entityManager.find(Usuario.class , id);
        entityManager.remove(usuario);
    }

    @Override
    public void postusuario(Usuario usuario) {
        entityManager.merge(usuario);
    }

   /* @Override
    public int verificar(Usuario usuario) {
        int resul=-1;
        String query = "FROM Usuario WHERE email =:email " ;
        List<Usuario> list = entityManager.createQuery(query)
                .setParameter("email",usuario.getEmail())
                .getResultList();
        if(!list.isEmpty()){
            String passwordhash = list.get(0).getPassword();
            Argon2 argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
             resul= argon.verify(passwordhash,usuario.getPassword())?usuario.getId():-1;
        }
        return resul;
    }*/

    @Override
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        String query = "FROM Usuario WHERE email = :email";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();

        if (lista.isEmpty()) {
            return null;
        }

        String passwordHashed = lista.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(passwordHashed, usuario.getPassword())) {
            return lista.get(0);
        }
        return null;
    }
}
