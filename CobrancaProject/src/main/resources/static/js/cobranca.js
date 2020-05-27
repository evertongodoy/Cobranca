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
	$('.glyphicon-check').tooltip({title: "Receber Titulo", placement: "top"});
	
	// Tratamento do Currency/Maskmoney
	// 1. Encontre todos os elementos que tenham a classe eg-moeda -> $('.eg-moeda'), e chame a funcao maskMoney();, podem ser passadas opcoes
	$('.eg-moeda').maskMoney({decimal: ',' , thousands: '.' , allowZero: true, });
	
	// Toda vez que carregar a pagina, serao selecionados os elementos que possuierem essa classe, no caso: eg-atualizar-status
	// Toda vez que clicar nesse link, vai executar uma funcao, que recebe o parametro EVENT do click "Evento do Clique"
	$('.eg-atualizar-status').on('click', function (event) {

		// Nao fazer o comportamento padrao do clique do link
		event.preventDefault();
		
		// Pegar o Botao currentTarget porque esta vindo de um LINK.
		var btnReceber = $(event.currentTarget);
		
		// Vai pegar a URL pelo botao
		var urlRecebimento = btnReceber.attr('href');
		
		console.log('urlRecebimento', urlRecebimento);

	});
	
	
});


/*
 MaskMoney

***************************************************************
 Nesse exemplo foi utilizado CSS para fazer a selecao
***************************************************************
https://github.com/plentz/jquery-maskmoney ,  baixar em "dist"

Exemplo:

 Para um input:

   <input type="text" id="currency" />


 a hora que a pagina for carregada,
   $(function() ) {
      . . . 
   })

 Voce seleciona o elemento
   . . .
      $('#currency')
   . . . 

 e chama a funcao maskMoney
  . . .
     $('...').maskMoney();
 . . . 

 Tem algumas opcoes que podem ser vistas aqui:

- https://github.com/plentz/jquery-maskmoney#options
*/