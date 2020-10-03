
module.exports = {
  tableName: 'Pelicula',
  attributes: {
    id: { 
      type: 'number', 
      autoIncrement: true,
    },
    createdAt: { 
      columnName: 'created_at',
      type: 'ref', 
      columnType: 'datetime', 
      autoCreatedAt: true,
    },
    updatedAt: { 
      columnName: 'updated_at',
      type: 'ref', 
      columnType: 'datetime',
      autoUpdatedAt: true,
    },
    nombre: {
      type: 'string',
      required: true,
    },
    precioUnitario: {
      type: 'number',
      columnType: 'FLOAT',
      columnName: 'precio_unitario',
      required: true,
    }
  },

};

