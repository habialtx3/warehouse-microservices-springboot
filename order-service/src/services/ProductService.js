const baseUrl = process.env.SPRINGBOOT_URL

exports.getProductById = async (id) => {
  try {

    const response = await fetch(
      `${baseUrl}/api/products/${id}`
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