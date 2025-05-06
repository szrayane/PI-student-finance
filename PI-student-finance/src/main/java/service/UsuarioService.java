package service;
import dto.UsuarioDTO;
import model.Usuario;
import java.util.List;


public interface UsuarioService {
    Usuario criarUsuario(UsuarioDTO dto);
    List<Usuario> listarUsuarios();
}
