package com.example.PI_student_finance.service;
import com.example.PI_student_finance.dto.UsuarioDTO;
import com.example.PI_student_finance.model.Usuario;
import java.util.List;


public interface UsuarioService {
    Usuario criarUsuario(UsuarioDTO dto);
    List<Usuario> listarUsuarios();
}
