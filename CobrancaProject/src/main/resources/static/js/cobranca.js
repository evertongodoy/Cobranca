$('#modalConfirmacaoExclusao').on('show.bs.modal', function(event){
	                               // Quando esse evento acontecer, vai executar a funcao
	
	var botao = $(event.relatedTarget);
	
	var codigoTitulo = botao.data('codigotitulo');
	var descricaoTitulo = botao.data('desctitulo');
	
	
	// alert(codigoTitulo);
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.attr('action');
	if(!action.endsWith('/')){
		action += '/';
	}
	form.attr('action', action + codigoTitulo);
	
	modal.find('.modal-body span').html(' Quer excluir o titulo <strong> ' + descricaoTitulo + ' <strong> ? ');
	
});

