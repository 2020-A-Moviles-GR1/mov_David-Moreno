/**
 * Usuario.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {
    attributes:{
      cedula:{//nombre de atributo
          type: 'string',
          required: true,//por defecto false
          columnName:'epn_cedula',
          unique:true,//por defecto false
          minLength:10,
          maxLength:25
      },

      nombre:{
        type:'string',
        minLength:3,
        required: true //por defecto false
      },

      correo:{
        type:'string',
        isEmail:true //por defecto false
      },
      estadoCivil:{
        type:'string',
       // isInt:['Soltero','Casado','Viudo','Union Libre'],//solo estos valores
        defaultsTo:'Soltero'
      },

      password:{
        type:'string',
        regex: /^[a-zA-Z]\w{3,14}$/
      },

      pokemons:{// uno a muchos 
        collection:'pokemon',//referencia al modelo
        via:'usuario'// nombre atributo foreign key

      }


    },

};

