/**
 * Pokemon.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {
  attributes: {
	  nombre:{
		  type: 'string'
		  },
		  
	 	usuario:{ // many to one (nombre FK) mismo nombre que la relacion
		  model: 'usuario',
		  required: true
		  //required: true//(es opcional)
		},


		/*batalla:{
		model: 'batalla',
		required: true
		}*/
	},
};

