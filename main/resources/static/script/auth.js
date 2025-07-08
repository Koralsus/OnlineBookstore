function logout() {
  fetch('/logout', {
    method: 'POST',
    credentials: 'include'
  }).then(() => {
    alert("You have been logged out!");
    window.location.href = "index.html";
  });
}

document.addEventListener("DOMContentLoaded", () => {
  const loginLink = document.getElementById("loginLink");
  const registerItem = document.getElementById("registerItem");
  const logoutLink = document.getElementById("logoutLink");
  const userDropdown = document.getElementById("userDropdown");
  const userDropdownLink = document.getElementById("userDropdownLink");

  fetch("/api/user/me")
    .then(res => {
      if (res.status === 200) return res.json();
      else throw new Error("Not logged in");
    })
    .then(user => {
      if (loginLink) loginLink.style.display = "none";
      if (registerItem) registerItem.style.display = "none";
      if (logoutLink) logoutLink.style.display = "none";
      if (userDropdown) {
        userDropdown.style.display = "block";
        userDropdownLink.textContent = `${user.username} ▼`;
      }
    })
    .catch(() => {
      if (loginLink) loginLink.style.display = "block";
      if (registerItem) registerItem.style.display = "block";
      if (logoutLink) logoutLink.style.display = "none";
      if (userDropdown) userDropdown.style.display = "none";
    });
});
