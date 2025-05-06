package serviceimpl;
import dto.DespesaDTO;
import model.Despesa;
import model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.DespesaRepository;
import repository.UsuarioRepository;
import service.DespesaService;
import java.util.List;

@Service
public class DespesaServiceImpl implements DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Despesa criarDespesa(DespesaDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        Despesa despesa = new Despesa(null, dto.descricao(), dto.valor(), dto.categoria(), usuario, dto.data());
        return despesaRepository.save(despesa);
    }

    @Override
    public List<Despesa> listarPorUsuario(Long usuarioId) {
        return despesaRepository.findByUsuarioId(usuarioId);
    }
}
