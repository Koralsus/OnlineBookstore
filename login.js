document.addEventListener("DOMContentLoaded", () => {
  const form = document.querySelector("form");
  form.addEventListener("submit", (e) => {
    e.preventDefault();

    const username = document.getElementById("login-username").value.trim();
    const password = document.getElementById("login-password").value;

    if (!username || !password) {
      alert("Please enter both username/email and password.");
      return;
    }

    const formData = new URLSearchParams();
    formData.append("username", username);
    formData.append("password", password);

    fetch("/login", {
      method: "POST",
      headers: { "Content-Type": "application/x-www-form-urlencoded" },
      body: formData,
      credentials: "include",
    })
      .then((res) => {
        if (res.redirected) {
          window.location.href = res.url;
        } else if (res.ok) {
          window.location.href = "index.html";
        } else {
          throw new Error("Login failed. Please check your credentials.");
        }
      })
      .catch((err) => {
        console.error("Error during login:", err);
        alert(err.message);
      });
  });
});
