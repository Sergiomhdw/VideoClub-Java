/**
 * 
 */
$(document).ready(function(){
	$("#entrar").click(function(){
		var nick = $('#nombre').val();
		var clave = $('#pass').val();
		
		$.ajax({
			type: "POST",
			dataType:"html",
			url: "./Servletloguear",
				
			data: $.param({
				nick:nick,
				clave:clave
			}),
			success: function(result){
				if(result == "true"){
				alert("Bienvenido");
				document.location.href="pagina.html?name="+nick;
				}else{
					$("#error").html("<p>ERROR usuario incorrecto</p>");

				}
			},
			error: function (){
				alert("ERROR");
			}
		});
	});
	
		$("#btnregistro").click(function(){
		debugger;
		var nick = $('#nick').val();
		var nombre = $('#nombre').val();
		var apellido = $('#apellido').val();
		var clave = $('#clave').val();
		var clave2 = $('#clave2').val();
		var email = $('#email').val();
		var check = $('#check').is(':checked');
		
		
		if(clave == clave2){
			
			$.ajax({
			type: "POST",
			dataType:"html",
			url: "./Servletregistro",
				
			data: $.param({
				nick:nick,
				nombre:nombre,
				apellido:apellido,
				clave:clave,
				email:email,
				check:check
			}),
			success: function(result){
				if(result == "false"){
					document.location.href="pagina.html?name="+nick;
					alert("Bienvenido");
				}else{alert("Ese nombre de usuario no está disponible")}
				
			},
			error: function (){
				alert("ERROR");
			}
		});
		}else{
			alert("ERROR las contraseñas no coinciden");
		}
	});	
});