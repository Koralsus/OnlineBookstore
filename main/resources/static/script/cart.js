document.addEventListener("DOMContentLoaded", () => {
  loadCart();
});

function loadCart() {
  let cart = JSON.parse(localStorage.getItem("cart")) || [];
  const cartContainer = document.getElementById("cart-items");
  const totalElement = document.getElementById("cart-total");

  cartContainer.innerHTML = "";

  if (cart.length === 0) {
    cartContainer.innerHTML = "<p>Your cart is empty.</p>";
    totalElement.textContent = "0.00";
    return;
  }

  let total = 0;

  cart.forEach(item => {
    total += item.price * item.quantity;

    const div = document.createElement("div");
    div.className = "card mb-3";
    div.innerHTML = `
      <div class="card-body">
        <h5>${item.title}</h5>
        <p>₱${item.price}</p>
        <div class="input-group mb-2" style="width: 150px;">
          <button class="btn btn-outline-secondary" onclick="updateQuantity(${item.bookId}, -1)">-</button>
          <input type="text" class="form-control text-center" value="${item.quantity}" readonly>
          <button class="btn btn-outline-secondary" onclick="updateQuantity(${item.bookId}, 1)">+</button>
        </div>
        <button class="btn btn-danger" onclick="removeFromCart(${item.bookId})">Remove</button>
      </div>
    `;
    cartContainer.appendChild(div);
  });

  totalElement.textContent = total.toFixed(2);
}

function updateQuantity(bookId, change) {
  let cart = JSON.parse(localStorage.getItem("cart")) || [];
  const index = cart.findIndex(item => item.bookId === bookId);

  if (index !== -1) {
    cart[index].quantity += change;
    if (cart[index].quantity <= 0) {
      cart.splice(index, 1);
    }
    localStorage.setItem("cart", JSON.stringify(cart));
    loadCart();
  }
}

function removeFromCart(bookId) {
  let cart = JSON.parse(localStorage.getItem("cart")) || [];
  cart = cart.filter(item => item.bookId !== bookId);
  localStorage.setItem("cart", JSON.stringify(cart));
  loadCart();
}

function placeOrder() {
  const userId = localStorage.getItem("userId");
  if (!userId) {
    alert("You must be logged in to place an order!");
    return;
  }

  fetch(`http://localhost:8080/api/orders/place?userId=${userId}`, {
    method: "POST"
  })
    .then(res => {
      if (!res.ok) throw new Error("Failed to place order");
      return res.json();
    })
    .then(() => {
      alert("Order placed successfully!");
      localStorage.removeItem("cart");
      window.location.href = "orders.html";
    })
    .catch(error => console.error("Error placing order:", error));
}
