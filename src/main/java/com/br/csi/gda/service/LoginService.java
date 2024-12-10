package com.br.csi.gda.service;

import com.br.csi.gda.model.usuario.DadosUsuario;
import com.br.csi.gda.model.usuario.Login;
import com.br.csi.gda.model.usuario.LoginRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    private final LoginRepository repository;
    public LoginService (LoginRepository repository){
        this.repository = repository;
    }

    public void cadastrar(Login usuario){
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        this.repository.save(usuario);
    }

    public DadosUsuario findUsuario(Long id){
        Login usuario = this.repository.getReferenceById(id);
        return new DadosUsuario(usuario);
    }

    public List<DadosUsuario> findAllUsuarios(){
        return this.repository.findAll().stream().map(DadosUsuario::new).toList();
    }
}
