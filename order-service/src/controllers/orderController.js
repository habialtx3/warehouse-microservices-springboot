const orderService = require("../services/OrderService");

exports.create = async (req, res) => {
  try {
    const order = await orderService.createOrder(req.body);

    res.status(201).json({
      message: "Order created",
      data: order,
    });
  } catch (err) {
    res.status(400).json({
      message: err.message,
    });
  }
};

exports.findAll = async (req, res) => {
  const orders = await orderService.getAllOrders();

  res.json({
    data: orders,
  });
};

exports.findById = async (req, res) => {
  const order = await orderService.getOrderById(req.params.id);

  if (!order) {
    return res.status(404).json({
      message: "Order not found",
    });
  }

  res.json({
    data: order,
  });
};

exports.update = async (req, res) => {
  const order = await orderService.updateOrder(
    req.params.id,
    req.body
  );

  if (!order) {
    return res.status(404).json({
      message: "Order not found",
    });
  }

  res.json({
    message: "Order updated",
    data: order,
  });
};

exports.remove = async (req, res) => {
  const deleted = await orderService.deleteOrder(
    req.params.id
  );

  if (!deleted) {
    return res.status(404).json({
      message: "Order not found",
    });
  }

  res.json({
    message: "Order deleted",
  });
};