export enum Status {
    AGUARDANDO_PAGAMENTO = 1,
    PAGAMENTO_APROVADO = 2,
    PAGAMENTO_REJEITADO = 3,
    EM_PROCESSAMENTO = 4,
    PRONTO_PARA_ENVIO = 5,
    ENVIADO = 6,
    EM_TRANSPORTE = 7,
    ENTREGUE = 8,
    CONCLUIDO = 9,
    CANCELADO = 10,
    DEVOLUCAO_SOLICITADA = 11,
    EM_DEVOLUCAO = 12,
    DEVOLVIDO = 13,
    REEMBOLSADO = 14
}

export const StatusLabel = {
    [Status.AGUARDANDO_PAGAMENTO]: 'Aguardando pagamento',
    [Status.PAGAMENTO_APROVADO]: 'Pagamento aprovado',
    [Status.PAGAMENTO_REJEITADO]: 'Pagamento rejeitado',
    [Status.EM_PROCESSAMENTO]: 'Em processamento',
    [Status.PRONTO_PARA_ENVIO]: 'Pronto para envio',
    [Status.ENVIADO]: 'Enviado',
    [Status.EM_TRANSPORTE]: 'Em transporte',
    [Status.ENTREGUE]: 'Entregue',
    [Status.CONCLUIDO]: 'Concluído',
    [Status.CANCELADO]: 'Cancelado',
    [Status.DEVOLUCAO_SOLICITADA]: 'Devolução solicitada',
    [Status.EM_DEVOLUCAO]: 'Em devolução',
    [Status.DEVOLVIDO]: 'Devolvido',
    [Status.REEMBOLSADO]: 'Reembolsado'
};
