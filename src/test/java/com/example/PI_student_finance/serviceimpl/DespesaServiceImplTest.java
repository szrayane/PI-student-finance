package com.example.PI_student_finance.serviceimpl;

import com.example.PI_student_finance.dto.DespesaDTO;
import com.example.PI_student_finance.model.Despesa;
import com.example.PI_student_finance.model.Usuario;
import com.example.PI_student_finance.repository.DespesaRepository;
import com.example.PI_student_finance.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class DespesaServiceImplTest {

    @Mock
    private DespesaRepository despesaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    private DespesaServiceImpl despesaService;
    private Usuario usuario;
    private DespesaDTO despesaDTO;
    private Despesa despesa;
    private Validator validator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        despesaService = new DespesaServiceImpl(despesaRepository, usuarioRepository);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Teste Usuario");

        despesaDTO = new DespesaDTO(
            "Almoço",
            new BigDecimal("50.0"),
            "Alimentação",
            1L,
            LocalDate.now()
        );

        despesa = new Despesa(
            null,
            despesaDTO.descricao(),
            despesaDTO.valor(),
            despesaDTO.categoria(),
            usuario,
            despesaDTO.data()
        );
    }

    @Test
    void testCriarDespesa() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(despesaRepository.save(any(Despesa.class))).thenReturn(despesa);

        Despesa resultado = despesaService.criarDespesa(despesaDTO);

        assertNotNull(resultado);
        assertEquals(despesaDTO.descricao(), resultado.getDescricao());
        assertEquals(despesaDTO.valor(), resultado.getValor());
    }

    @Test
    void testUsuarioNaoEncontrado() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            despesaService.criarDespesa(despesaDTO);
        });
    }

    @Test
    void testListarDespesas() {
        List<Despesa> despesas = Arrays.asList(despesa);
        when(despesaRepository.findByUsuarioId(1L)).thenReturn(despesas);

        List<Despesa> resultado = despesaService.listarPorUsuario(1L);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    void testListaVazia() {
        when(despesaRepository.findByUsuarioId(1L)).thenReturn(List.of());

        List<Despesa> resultado = despesaService.listarPorUsuario(1L);

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    void testDespesaComDescricaoNula() {
        DespesaDTO dtoInvalido = new DespesaDTO(
            null,
            new BigDecimal("50.0"),
            "Alimentação",
            1L,
            LocalDate.now()
        );

        Set<jakarta.validation.ConstraintViolation<DespesaDTO>> violations = validator.validate(dtoInvalido);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("descricao")));
    }

    @Test
    void testDespesaComValorNulo() {
        DespesaDTO dtoInvalido = new DespesaDTO(
            "Almoço",
            null,
            "Alimentação",
            1L,
            LocalDate.now()
        );

        Set<jakarta.validation.ConstraintViolation<DespesaDTO>> violations = validator.validate(dtoInvalido);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("valor")));
    }

    @Test
    void testDespesaComCategoriaNula() {
        DespesaDTO dtoInvalido = new DespesaDTO(
            "Almoço",
            new BigDecimal("50.0"),
            null,
            1L,
            LocalDate.now()
        );

        Set<jakarta.validation.ConstraintViolation<DespesaDTO>> violations = validator.validate(dtoInvalido);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("categoria")));
    }

    @Test
    void testDespesaComDataNula() {
        DespesaDTO dtoInvalido = new DespesaDTO(
            "Almoço",
            new BigDecimal("50.0"),
            "Alimentação",
            1L,
            null
        );

        Set<jakarta.validation.ConstraintViolation<DespesaDTO>> violations = validator.validate(dtoInvalido);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("data")));
    }

    @Test
    void testDespesaComDataNoPassado() {
        DespesaDTO dtoInvalido = new DespesaDTO(
            "Almoço",
            new BigDecimal("50.0"),
            "Alimentação",
            1L,
            LocalDate.now().minusDays(1)
        );

        // Aqui assumimos que a data no passado é válida, pois não há validação específica para isso
        Set<jakarta.validation.ConstraintViolation<DespesaDTO>> violations = validator.validate(dtoInvalido);
        assertTrue(violations.isEmpty());
    }
} 