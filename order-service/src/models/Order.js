const { DataTypes } = require("sequelize");
const sequelize = require("../config/database");

const Order = sequelize.define("Order", {
  id: {
    type: DataTypes.UUID,
    defaultValue: DataTypes.UUIDV4,
    primaryKey: true,
  },

  order_number: {
    type: DataTypes.STRING,
    allowNull: false,
  },

  customer_id: {
    type: DataTypes.STRING,
    allowNull: false,
  },

  total_price: {
    type: DataTypes.DECIMAL(12, 2),
    allowNull: false,
  },

  status: {
    type: DataTypes.STRING,
    defaultValue: "PENDING",
  },
});

module.exports = Order;