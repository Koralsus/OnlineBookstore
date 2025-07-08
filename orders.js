document.addEventListener("DOMContentLoaded", () => {
  const userId = localStorage.getItem("userId");
  if (!userId) {
    alert("You must be logged in to view your orders!");
    window.location.href = "login.html";
    return;
  }

  fetch(`http://localhost:8080/api/orders/user/${userId}`)
    .then(res => {
      if (!res.ok) throw new Error("Failed to load orders");
      return res.json();
    })
    .then(orders => {
      const ordersList = document.getElementById("orders-list");
      ordersList.innerHTML = "";

      if (orders.length === 0) {
        ordersList.innerHTML = "<p>You don't have any orders yet.</p>";
        return;
      }

      orders.forEach(order => {
        const div = document.createElement("div");
        div.className = "card mb-3";
        div.innerHTML = `
          <div class="card-body">
            <h5>Order #${order.orderId}</h5>
            <p>Date: ${order.orderDate}</p>
            <p>Total: ₱${order.totalAmount.toFixed(2)}</p>
            <p>Items: ${order.orderItems.length}</p>
          </div>
        `;
        ordersList.appendChild(div);
      });
    })
    .catch(error => {
      console.error("Error loading orders:", error);
      alert("Unable to load your orders. Please try again later.");
    });
});
