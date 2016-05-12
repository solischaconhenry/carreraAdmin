var usuariosService = require('../businessLogic/usuariosService.js'); 

exports.bloquearUsuario = function(uRequest, uResponse) {
  usuariosService.bloquearUsuario(uRequest.body, function(data){
        uResponse.send(data);
    });
};

usuariosService.bloquearUsuario({idUsuario: '573396123eeb7b0cdd843c9a', bloqueado: 0}, function(data){
        console.log('usuariosService.bloquearUsuario: console.log(data);');
        console.log(data);
    });

exports.hacerAdmin= function(uRequest, uResponse) {
  usuariosService.hacerAdmin(uRequest.body, function(data){
        uResponse.send(data);
    });
};

usuariosService.hacerAdmin({idUsuario: '573396123eeb7b0cdd843c9a', admin: 1}, function(data){
        console.log('usuariosService.bloquearUsuario: console.log(data);');
        console.log(data);
    });