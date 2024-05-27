package br.projeto.petshop.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status {
    AGUARDANDO_PAGAMENTO(1, "Aguardando pagamento"),
    PAGAMENTO_APROVADO(2, "Pagamento aprovado"),
    PAGAMENTO_REJEITADO(3, "Pagamento rejeitado"),
    EM_PROCESSAMENTO(4, "Em processamento"),
    PRONTO_PARA_ENVIO(5, "Pronto para envio"),
    ENVIADO(6, "Enviado"),
    EM_TRANSPORTE(7, "Em transporte"),
    ENTREGUE(8, "Entregue"),
    CONCLUIDO(9, "Concluido"),
    CANCELADO(10, "Cancelado"),
    DEVOLUCAO_SOLICITADA(11, "Devolução solicitada"),
    EM_DEVOLUCAO(12, "Em devolução"),
    DEVOLVIDO(13, "Devolvido"),
    REEMBOLSADO(14, "Reembolsado");

    private final Integer id;
    private final String label;
    private Status(Integer id, String label) {
        this.id = id;
        this.label = label;
    }
    public Integer getId() {
        return id;
    }
    public String getLabel() {
        return label;
    }

    public static Status valueOf(Integer id) throws IllegalArgumentException {
        if (id == null) {
            return null;
        }
        for (Status status : Status.values()) {
            if (status.getId().equals(id)) {
                return status;
            }
        }
        throw new IllegalArgumentException("ID invalido para Status: " + id);
    }

    
}
