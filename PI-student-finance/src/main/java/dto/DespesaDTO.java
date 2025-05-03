package dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DespesaDTO(
        @NotBlank String descricao,
        @NotNull BigDecimal valor,
        @NotBlank String categoria,
        @NotNull Long usuarioId,
        @NotNull LocalDate data
) {}
