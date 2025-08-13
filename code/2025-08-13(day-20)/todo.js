document.getElementById("addBtn").addEventListener("click", function() {
    const taskInput = document.getElementById("taskInput");
    const taskText = taskInput.value.trim();
    if (taskText === "") return;

    const li = document.createElement("li");
    li.textContent = taskText;

    const delBtn = document.createElement("button");
    delBtn.textContent = "Delete";
    delBtn.className = "deleteBtn";
    delBtn.onclick = function() {
        li.remove();
    };

    li.appendChild(delBtn);
    document.getElementById("taskList").appendChild(li);
    taskInput.value = "";
});