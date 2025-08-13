function validateEmail(email) {
    // Simple email regex
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
}

function showMsg(id, msg) {
    document.getElementById(id).textContent = msg;
}

document.getElementById("email").addEventListener("blur", function() {
    const email = this.value.trim();
    if (!email) {
        showMsg("emailMsg", "Email cannot be empty.");
    } else if (!validateEmail(email)) {
        showMsg("emailMsg", "Invalid email format.");
    } else {
        showMsg("emailMsg", "");
    }
});

document.getElementById("password").addEventListener("blur", function() {
    const password = this.value.trim();
    if (!password) {
        showMsg("passwordMsg", "Password cannot be empty.");
    } else {
        showMsg("passwordMsg", "");
    }
});

document.getElementById("loginBtn").addEventListener("click", function() {
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();

    let valid = true;

    if (!email) {
        showMsg("emailMsg", "Email cannot be empty.");
        valid = false;
    } else if (!validateEmail(email)) {
        showMsg("emailMsg", "Invalid email format.");
        valid = false;
    } else {
        showMsg("emailMsg", "");
    }

    if (!password) {
        showMsg("passwordMsg", "Password cannot be empty.");
        valid = false;
    } else {
        showMsg("passwordMsg", "");
    }

    if (valid) {
        alert("Login successful!");
    }
});