package org.acme.service;

import jakarta.ws.rs.core.Response;
import org.acme.dto.*;

import java.util.List;

public interface VendaService {

    Response getAll();

    VendaResponseCompDTO getId(long id);

    Response insert(VendaDTO vendaDTO);

    Response insertPagamento(VendaPagamentoDTO vendaPagamentoDTO);

    Response insertComp(VendaCompDTO dto);

    Response insertItemVenda(VendaItemVendaDTO dto);

    Response insertItemVendaId(VendaItemVendaId dto);
    Response removeItemVendaId(VendaItemVendaId dto);

    Response adicionaAvaliacao(AvaliacaoProdutoVendaDTO avaliacaoProdutoVendaDTO);

    Response adicionaAvaliacaoId(AvaliacaoProdutoVendaIdDTO avaliacaoProdutoVendaIdDTO);

    Response delete(long id);
}
