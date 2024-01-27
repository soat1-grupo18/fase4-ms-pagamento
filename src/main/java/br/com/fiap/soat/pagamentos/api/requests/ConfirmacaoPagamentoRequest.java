package br.com.fiap.soat.pagamentos.api.requests;

import br.com.fiap.soat.pagamentos.usecases.model.ComandoDeConfirmacaoDePagamento;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public class ConfirmacaoPagamentoRequest {

    private String id;
    private String live_mode;
    private String type;
    private String date_created;
    private String user_id;
    private String api_version;
    @Schema(name = "Ação a ser processada. Somente a confirmação de pagamento é aceita no momento", example = "payment.created")
    private String action;
    @Schema(name = "Id de pagamento registrado no checkout de pedido")
    private ConfirmacaoPagamentoDataRequest data;

    public ComandoDeConfirmacaoDePagamento toDomain() {
        return new ComandoDeConfirmacaoDePagamento(this.getAction(), (this.getData().getId()));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLive_mode() {
        return live_mode;
    }

    public void setLive_mode(String live_mode) {
        this.live_mode = live_mode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getApi_version() {
        return api_version;
    }

    public void setApi_version(String api_version) {
        this.api_version = api_version;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ConfirmacaoPagamentoDataRequest getData() {
        return data;
    }

    public void setData(ConfirmacaoPagamentoDataRequest data) {
        this.data = data;
    }
}
