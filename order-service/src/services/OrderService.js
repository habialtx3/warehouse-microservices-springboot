const { Order, OrderItem } = require("../models");

exports.createOrder = async (data) => {
  const order = await Order.create({
    order_number: data.order_number,
    customer_id: data.customer_id,
    total_price: data.total_price,
    status: data.status,
  });

  if (data.items && data.items.length > 0) {
    for (const item of data.items) {
      await OrderItem.create({
        order_id: order.id,
        product_id: item.product_id,
        quantity: item.quantity,
        price_at_purchase: item.price_at_purchase,
      });
    }
  }

  return order;
};

exports.getAllOrders = async () => {
  return await Order.findAll({
    include: ["items"],
  });
};

exports.getOrderById = async (id) => {
  return await Order.findByPk(id, {
    include: ["items"],
  });
};

exports.updateOrder = async (id, data) => {
  const order = await Order.findByPk(id);

  if (!order) return null;

  await order.update(data);

  return order;
};

exports.deleteOrder = async (id) => {
  const order = await Order.findByPk(id);

  if (!order) return null;

  await OrderItem.destroy({
    where: { order_id: id },
  });

  await order.destroy();

  return true;
};