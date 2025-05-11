package com.example.PI_student_finance.serviceimpl;
import com.example.PI_student_finance.dto.DespesaDTO;
import com.example.PI_student_finance.model.Despesa;
import com.example.PI_student_finance.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.PI_student_finance.repository.DespesaRepository;
import com.example.PI_student_finance.repository.UsuarioRepository;
import com.example.PI_student_finance.service.DespesaService;
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
