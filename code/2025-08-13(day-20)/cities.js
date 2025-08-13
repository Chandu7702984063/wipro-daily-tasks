const cities = ["Pune", "Delhi", "Chennai", "Kolkata", "Mumbai", "Hyderabad", "Bangalore"];

document.getElementById("loadBtn").addEventListener("click", function() {
    const dropdown = document.getElementById("cityDropdown");
    dropdown.innerHTML = "";
    cities.slice().sort().forEach(city => {
        const option = document.createElement("option");
        option.value = city;
        option.textContent = city;
        dropdown.appendChild(option);
    });
});