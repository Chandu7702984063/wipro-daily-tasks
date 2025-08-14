const userIdInput = document.getElementById("userId");
const passwordInput = document.getElementById("password");
const statusEl = document.getElementById("status");

const loginBtn = document.getElementById("loginBtn");
const logoutBtn = document.getElementById("logoutBtn");

window.onload = () => {
  const storedUserId = localStorage.getItem("userId");
  if (storedUserId) {
    statusEl.textContent = `Logged in as: ${storedUserId}`;
  } else {
    statusEl.textContent = "Not logged in";
  }
};
loginBtn.addEventListener("click", () => {
  const userId = userIdInput.value.trim();
  const password = passwordInput.value.trim();

  if (userId && password) {
    localStorage.setItem("userId", userId);
    statusEl.textContent = `Logged in as: ${userId}`;
    userIdInput.value = "";
    passwordInput.value = "";
  } else {
    alert("Please enter both User ID and Password");
  }
});
logoutBtn.addEventListener("click", () => {
  localStorage.removeItem("userId");
  statusEl.textContent = "Not logged in";
});
