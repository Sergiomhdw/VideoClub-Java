/**
 * 
 */
			var url=window.location;
			var utlseparada=url.toString().split('?');
			var nombre=utlseparada[1].split('=');
			let nick=nombre[1];
			
			
$(document).ready(function(){

		$("#bienve").html("<p>Bienvenido: "+nick+" </p>");
		
		var ele = $('#genero').val();
			ele = "";
			$.ajax({
				type: "GET",
				dataType:"html",
				url: "./ServletListar",
				
				data: $.param({
				ele:ele
				
			}),
					
				success: function(result){
					$("#lista").html(result);
				},
				error: function (){
					$("#lista").val("Error de comunicación");
				}
			});

	$("#ordenar").click(function(){
			
		var ele = $('#genero').val();		
			
			$.ajax({
				type: "GET",
				dataType:"html",
				url: "./ServletListar",
				
				data: $.param({
				ele:ele
				
			}),
				success: function(result){
					$("#lista").html(result);
				},
				error: function (){
					$("#lista").val("Error de comunicación");
				}
			});
	});

	$("#pagina").click(function(){
			document.location.href="perfil.html?name="+nick;

	});
	
});

function alquilar(id){
		debugger;
		var id = id;
		var url=window.location;
		var utlseparada=url.toString().split('?');
		var nombre=utlseparada[1].split('=');
		let nick=nombre[1];
		
		$.ajax({
				type: "GET",
				dataType:"html",
				url: "./Servletalquilar",
				
				data: $.param({
				nick:nick,
				id:id
			}),
			
				success: function(result){
					
					debugger;
					if(result=="cantidad"){
						$("#error").html("<p>Lo sentimos pero no nos quedan mas copias</p>");
					}
					else if(result == "saldo"){
						$("#error").html("<p>Lo sentimos pero no tienes saldo</p>");
					}
					else{
						$("#suce").html("<p>Alquilada con exito</p>");
						

					}
						setTimeout('document.location.reload()',1000);
					
					
				},
				error: function (){
					$("#error").val("Error de comunicación");
				}
			});
		
		
	}