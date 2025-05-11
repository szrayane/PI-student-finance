package com.example.PI_student_finance.service;
import com.example.PI_student_finance.dto.DespesaDTO;
import com.example.PI_student_finance.model.Despesa;
import java.util.List;

public interface DespesaService {
    Despesa criarDespesa(DespesaDTO dto);
    List<Despesa> listarPorUsuario(Long usuarioId);
}
