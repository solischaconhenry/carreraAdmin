var repository = require('../dataAccess/repository.js');


exports.getEventos = function(callback) {
	var params = {
		query: {},
		collection: 'eventos'
	};
	repository.getCollection(params, function(data){
	        callback(data);
	});
};

exports.getEventoPorId = function(idEvento, callback) {
	var params = {
		query: {_id: idEvento},
		collection: 'eventos'
	};
	repository.getDocument(params, function(data){
	        callback(data);
	});
};
exports.nuevoEvento = function(doc, callback) {
	var params = {
		query: doc,
		collection: 'eventos'
	};
	repository.addDocument(params, function(res) {
		callback(res);
	});
}

exports.editarEvento = function(idEvento, doc, callback) {
	var params = {
		query: {_id: idEvento},
		updateQuery: {$set: doc},
		collection: 'eventos'
	};
	repository.updateDocument(params, function(res) {
		callback(res);
	});
}

exports.eliminarEvento = function(idEvento, callback) {
	var params = {
		query: {_id: idEvento},
		collection: 'eventos'
	};
	repository.deleteDocument(params, function(res) {
		callback(res);
	});
}
