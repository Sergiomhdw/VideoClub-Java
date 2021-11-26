
			var url=window.location;
			var utlseparada=url.toString().split('?');
			var nombre=utlseparada[1].split('=');
			let nick=nombre[1];
			
$(document).ready(function(){
	
			$.ajax({
				type: "GET",
				dataType:"html",
				url: "./ServletListarmias",
				
				data: $.param({
				nick:nick
			}),
				success: function(result){
					$("#mispeliculas").html(result);
				},
				error: function (){
					$("#mispeliculas").val("Error de comunicación");
				}
			});
			
			
	$("#atras").click(function(){
		document.location.href="pagina.html?name="+nick;
	});
	
	$("#salir").click(function(){
		document.location.href="index.html";
	});
	
	$("#modificar").click(function(){
				
		var nombre = $('#nombre').val();
		var apellido = $('#apellido').val();
		var clave = $('#clave').val();
		var clave2 = $('#clave').val();
		var email = $('#email').val();
		var check = $('#check').is(':checked');
		
		if(clave == clave2){
			$.ajax({
			type: "POST",
			dataType:"html",
			url: "./ServletModificar",
				
			data: $.param({
				nick:nick,
				nombre:nombre,
				apellido:apellido,
				clave:clave,
				email:email,
				check:check
			}),
			success: function(result){
				if(result == "true"){
					$("#suce").html("<p>Modificado existosamente</p>");


				}else{$("#error").html("<p>Error</p>");}

				setTimeout('document.location.reload()',1000);

			},
			error: function (){
				alert("ERROR");
			}
			});
		}
	});
	
	$("#eliminar").click(function(){
		alert("Una vez hecho no hay vuelta atras");
		var pass = prompt("Introduce su clave para verificar que es usted");
	
		$.ajax({
			type: "POST",
			dataType:"html",
			url: "./Servleteliminar",
				
			data: $.param({
				nick:nick,
				pass:pass
			}),
			success: function(result){
				if(result == "true"){
					$("#suce").html("<p>Usuario eliminado</p>");
					document.location.href="index.html";
				
				}else{$("#error").html("<p>ERROR en la contraseña</p>");}

				setTimeout('document.location.reload()',1000);

			},
			error: function (){
				alert("ERROR");
			}
		});
	});
	
	$("#saldo").click(function(){
		var cantidad = $('#cantidad').val();
		
		$.ajax({
			type: "POST",
			dataType:"html",
			url: "./ServleSaldo",
				
			data: $.param({
				nick:nick,
				cantidad:cantidad
			}),
			success: function(result){
				if(result == "true"){
				$("#suce").html("<p>Gracias por su deposito</p>");

				}else{
					$("#error").html("<p>Ups algo salió mal</p>");
				}

				setTimeout('document.location.reload()',1000);

			},
			error: function (){
				$("#error").html("<p>Ups algo salió mal</p>");
			}
		});
	});
	
});