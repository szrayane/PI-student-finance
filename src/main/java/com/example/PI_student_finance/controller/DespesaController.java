package com.example.PI_student_finance.controller;
import jakarta.validation.Valid;
import com.example.PI_student_finance.model.Despesa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.PI_student_finance.repository.DespesaRepository;
import java.util.List;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

    @Autowired
    private DespesaRepository despesaRepository;

    @PostMapping
    public ResponseEntity<Despesa> criar(@RequestBody @Valid Despesa despesa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(despesaRepository.save(despesa));
    }

    @GetMapping("/usuario/{id}")
    public List<Despesa> listarPorUsuario(@PathVariable Long id) {
        return despesaRepository.findByUsuarioId(id);
    }
}
