$('#modalConfirmacaoExclusao').on('show.bs.modal', function(event){
	                               // Quando esse evento acontecer, vai executar a funcao
	
	var botao = $(event.relatedTarget); // Trata-se do " x ", para Excluir
	
	var codigoTitulo = botao.data('codigotitulo');
	var descricaoTitulo = botao.data('desctitulo');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.attr('action');
	
	// Guardar a action original
	if($('#actionOriginal').val() == ''){
		$('#actionOriginal').val(action);
		form.attr('actionOriginal', $('#actionOriginal').val());
	}
	//
	
	if(!action.endsWith('/')){
		action += '/';
	}
	form.attr('action', action + codigoTitulo);
	
	modal.find('.modal-body span').html(' Quer excluir o titulo <strong> ' + descricaoTitulo + ' <strong> ? ');
	
});


/**
 * Everton Godoy
 * Solucao para resolver o problema do video 
 * 2.16. Implementando a exclusao
 * Foi implementada uma solucao no video
 * 2.17. Corrigindo bug exclusao
 * Mas ainda nao assisti. Essa corecao foi minha 
 * 
 * @param event
 * @returns
 */
$('#modalConfirmacaoExclusao').on('hide.bs.modal', function(event){
	
	var modal = $(this);
	var form = modal.find('form');
	form.attr('action', $('#actionOriginal').val());
	$('#actionOriginal').val('');
	
});


$(function(){
	// Procure os componentes que sao rel=tooltip e chame a funcao tooltip, ".tooltip();" de acordo com o video dele, mas nao funcionou.

	// Isso funcionou para o ToolTip
	// Seleciona os componentes pela Class glyphicon-pencil e glyphicon-remove, e aplica o Tooltip neles
	// Fonte: https://www.tutorialspoint.com/Bootstrap-tooltip-options-method
	$('.glyphicon-pencil').tooltip({title: "Editar Titulo", placement: "top"});
	$('.glyphicon-remove').tooltip({title: "Excluir Titulo", placement: "top"});
	
});