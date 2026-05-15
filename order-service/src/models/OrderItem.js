const { DataTypes } = require("sequelize");
const sequelize = require("../config/database");

const OrderItem = sequelize.define("OrderItem", {
  id: {
    type: DataTypes.UUID,
    defaultValue: DataTypes.UUIDV4,
    primaryKey: true,
  },

  product_id: {
    type: DataTypes.STRING,
    allowNull: false,
  },

  quantity: {
    type: DataTypes.INTEGER,
    allowNull: false,
  },

  price_at_purchase: {
    type: DataTypes.DECIMAL(12, 2),
    allowNull: false,
  },
});

module.exports = OrderItem;