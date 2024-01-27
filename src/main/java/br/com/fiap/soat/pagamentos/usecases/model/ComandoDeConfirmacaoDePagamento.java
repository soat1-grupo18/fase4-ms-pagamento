package br.com.fiap.soat.pagamentos.usecases.model;

import java.util.UUID;

public class ComandoDeConfirmacaoDePagamento {
    private final String action;
    private final String pagamentoId;

    public ComandoDeConfirmacaoDePagamento(String action, String pagamentoId) {
        this.action = action;
        this.pagamentoId = pagamentoId;
    }

    public String getAction() {
        return action;
    }

    public String getPagamentoId() {
        return pagamentoId;
    }
}
