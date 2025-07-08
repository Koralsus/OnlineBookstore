document.getElementById("registerForm").addEventListener("submit", (e) => {
  e.preventDefault();

  const username = document.getElementById("reg-username").value.trim();
  const email = document.getElementById("reg-email").value.trim();
  const password = document.getElementById("reg-password").value;
  const firstName = document.getElementById("reg-firstname").value.trim();
  const lastName = document.getElementById("reg-lastname").value.trim();

  // Basic simple validation
  if (!username || !email || !password || !firstName || !lastName) {
    alert("Please fill in all fields.");
    return;
  }

  fetch("http://localhost:8080/api/users/register", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ username, email, password, firstName, lastName }),
  })
    .then((res) => {
      if (!res.ok) throw new Error("Registration failed. Please check your details.");
      return res.json();
    })
    .then(() => {
      alert("Registration successful! Please log in.");
      window.location.href = "login.html";
    })
    .catch((err) => {
      console.error("Error during registration:", err);
      alert(err.message);
    });
});
