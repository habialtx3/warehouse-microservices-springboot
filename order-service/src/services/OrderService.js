const { Order, OrderItem } = require("../models");

const productService = require("./productService");


exports.createOrder = async (data) => {

  let totalPrice = 0;

  const validatedItems = [];

  // checking if it's valid product
  if (data.items && data.items.length > 0) {
    for (const item of data.items) {

      const product = await productService.getProductById(item.product_id);
      if (!product) {
        throw new Error(`Product ${item.product_id} is not found`);
      }

      const subtotal =
        Number(product.price) * item.quantity;

      totalPrice += subtotal;

      validatedItems.push(
        {
          product_id: product.id,
          quantity: item.quantity,
          price_at_purchase: product.price
        }
      )
    }
  }

  // create order
  const order = await Order.create({
    order_number: data.order_number,
    customer_id: data.customer_id,
    total_price: totalPrice,
    status: data.status,
  });

  // create order items
  for (const item of validatedItems) {
    await OrderItem.create({
      order_id: order.id,
      product_id: item.product_id,
      quantity: item.quantity,
      price_at_purchase: item.price_at_purchase,
    })
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