package com.mrdevv.service.impl;

import com.mrdevv.model.dao.UsuarioDAO;
import com.mrdevv.model.entity.Rol;
import com.mrdevv.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    UsuarioDAO usuarioDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioDAO.findUsuarioByUsuario(username)
                .orElseThrow(()-> new UsernameNotFoundException("El usuario " + username + " no existe."));


        Set<GrantedAuthority> role = new HashSet<>();
        role.add(new SimpleGrantedAuthority("ROLE_".concat(usuario.getRol().getDescripcion())));

        return new User(usuario.getUsuario(),
                usuario.getPassword(),
                true,
                true,
                true,
                true,
                role);
    }
}
