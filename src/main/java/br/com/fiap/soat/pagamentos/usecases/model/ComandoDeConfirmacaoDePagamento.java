package br.com.fiap.soat.pagamentos.usecases.model;

import java.util.UUID;

public class ComandoDeConfirmacaoDePagamento {
    private final String action;
    private final UUID pagamentoId;

    public ComandoDeConfirmacaoDePagamento(String action, UUID pagamentoId) {
        this.action = action;
        this.pagamentoId = pagamentoId;
    }

    public String getAction() {
        return action;
    }

    public UUID getPagamentoId() {
        return pagamentoId;
    }
}
