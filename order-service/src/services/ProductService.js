exports.getProductById = async (id) => {
  try {

    const response = await fetch(
      `http://localhost:8090/api/products/${id}`
    );

    if (!response.ok) {
      return null;
    }

    const result = await response.json();

    return result.data;

  } catch (err) {

    console.log(err);

    return null;
  }
};