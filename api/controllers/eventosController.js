var eventosService = require('../businessLogic/eventosService.js'); 

exports.getEventos = function(eRequest, eResponse) {
  eventosService.getEventos(function(data){
        eResponse.send(data);
    });
};

exports.getEventoPorId = function(eRequest, eResponse) {
  eventosService.getEventoPorId(eRequest.params.id, function(data){
        eResponse.send(data);
    });
};

exports.nuevoEvento = function(eRequest, eResponse) {
  eventosService.nuevoEvento(eRequest.body, function(data){
        eResponse.send(data);
    });
};

exports.editarEvento = function(eRequest, eResponse) {
  eventosService.editarEvento(eRequest.params.idEvento, eRequest.body, function(data){
        eResponse.send(data);
    });
};

exports.eliminarEvento = function(eRequest, eResponse) {
  eventosService.eliminarEvento(eRequest.params.idEvento, function(data){
        eResponse.send(data);
    });
};

exports.nuevoContenido = function(eRequest, eResponse) {
  eventosService.nuevoContenido(eRequest.body, function(data){
        eResponse.send(data);
    });
};

exports.eliminarContenido = function(eRequest, eResponse) {
  eventosService.eliminarContenido(eRequest.params, function(data){
        eResponse.send(data);
    });
};

exports.getContenidoEvento = function(eRequest, eResponse) {
  eventosService.getContenidoEvento(eRequest.params, function(data){
        eResponse.send(data);
    });
};


eventosService.nuevoEvento({nombre: 'nombre actividad', descripcion: 'descripcion evento'}, function(data){
      console.log('nuevoEvento: console.log(data);');
      console.log(data);
      eventosService.getEventos(function(data){
          console.log('getEventos: console.log(data);');
          console.log(data);
      });
      /*eventosService.editarEvento(eRequest.params.idEvento, eRequest.body, function(data){
          eResponse.send(data);
      });*/
    });


eventosService.getEventoPorId('573383e2cd3c468f2ad3ea23', function(data){
    console.log('getEventoPorId: console.log(data);');
    console.log(data);
});

eventosService.eliminarEvento('573383e2cd3c468f2ad3ea23', function(data){
    console.log('eliminarEvento: console.log(data);');
    console.log(data);
});

/*
eventosService.editarEvento('573383e2cd3c468f2ad3ea23', {nombre: 'actividad a borrar ------------>'}, function(data){
      console.log('\n\n-----------------------------\n')
      console.log('editarEvento: console.log(data);');
      console.log(data);
      console.log('\n-----------------------------\n\n')
    });*/