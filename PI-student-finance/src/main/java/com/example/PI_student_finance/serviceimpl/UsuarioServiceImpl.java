package com.example.PI_student_finance.serviceimpl;
import com.example.PI_student_finance.dto.UsuarioDTO;
import com.example.PI_student_finance.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.PI_student_finance.repository.UsuarioRepository;
import com.example.PI_student_finance.service.UsuarioService;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario criarUsuario(UsuarioDTO dto) {
        Usuario usuario = new Usuario(null, dto.nome(), dto.email(), dto.senha());
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
}
