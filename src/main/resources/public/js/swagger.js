// Fichero de configuración de la documentación de Swagger.

window.onload = function() {
	const ui = SwaggerUIBundle({
		
		// Contenido de la página.
		url: '/yaml/similarproducts-api.yaml',
		
		// Contenedor de la página para Swagger.
		dom_id: '#swagger-container',
		
		// Apariencia de Swagger.
		deepLinking: true,
		presets: [
			SwaggerUIBundle.presets.apis,
			SwaggerUIStandalonePreset
		],
		plugins: [
			SwaggerUIBundle.plugins.DownloadUrl
		],
		layout: "StandaloneLayout",
		validatorUrl: 'none'
	});
	window.ui = ui;
};
