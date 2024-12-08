package com.br.csi.gda.service;

import com.br.csi.gda.model.usuario.Login;
import com.br.csi.gda.model.usuario.LoginRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {
    private final LoginRepository repository;
    public AutenticacaoService(LoginRepository repository){
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException{
        Login usuario = this.repository.findByLogin(login);
        if(usuario == null){
            throw new UsernameNotFoundException("Usuário ou senha incorretos");
        }
        else{
            UserDetails user = User.withUsername(usuario.getLogin()).password(usuario.getSenha()).authorities(usuario.getPermissao()).build();
            return user;
        }
    }

}
