package service;
import dto.DespesaDTO;
import model.Despesa;
import java.util.List;

public interface DespesaService {
    Despesa criarDespesa(DespesaDTO dto);
    List<Despesa> listarPorUsuario(Long usuarioId);
}
