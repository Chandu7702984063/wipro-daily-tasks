<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Employee</title>
    <style>
        /* Reset some basic styles */
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f0f2f5;
            color: #333;
            padding: 20px;
        }

        h2 {
            text-align: center;
            margin-bottom: 30px;
            color: #1f4e79;
        }

        .card {
            background: #fff;
            border-radius: 12px;
            box-shadow: 0 6px 20px rgba(0,0,0,0.1);
            padding: 30px;
            max-width: 720px;
            margin: 0 auto;
            transition: transform 0.3s ease;
        }

        .card:hover {
            transform: translateY(-5px);
        }

        form div {
            margin-bottom: 20px;
        }

        label {
            font-weight: 500;
            display: block;
            margin-bottom: 8px;
            color: #555;
        }

        input[type="text"],
        select {
            width: 100%;
            padding: 10px 14px;
            border-radius: 6px;
            border: 1px solid #ccc;
            font-size: 16px;
            transition: border 0.3s;
        }

        input[type="text"]:focus,
        select:focus {
            border-color: #1f4e79;
            outline: none;
        }

        .address-inputs {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(140px, 1fr));
            gap: 12px;
        }

        button[type="submit"] {
            background-color: #1f4e79;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 16px;
            transition: background 0.3s;
        }

        button[type="submit"]:hover {
            background-color: #3a70a5;
        }

        a {
            margin-left: 15px;
            text-decoration: none;
            color: #1f4e79;
            font-weight: 500;
            transition: color 0.3s;
        }

        a:hover {
            color: #3a70a5;
        }

        @media (max-width: 500px) {
            .card {
                padding: 20px;
            }

            .address-inputs {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>
<h2>Edit Employee</h2>

<div class="card">
    <form action="../update/${emp.id}" method="post">
        <div>
            <label>Name:
                <input type="text" name="name" value="${emp.name}" required>
            </label>
        </div>
        <div>
            <label>Type:
                <select name="type">
                    <option value="regular" <c:if test="${emp.type == 'regular'}">selected</c:if>>Regular</option>
                    <option value="contract" <c:if test="${emp.type == 'contract'}">selected</c:if>>Contract</option>
                </select>
            </label>
        </div>
        <div>
            <label>Department:
                <input type="text" name="dept"
                       value="<c:out value='${emp.department != null ? emp.department.name : ""}'/>">
            </label>
        </div>
        <div>
            <strong>Address</strong>
            <div class="address-inputs">
                <input type="text" name="street" placeholder="Street"
                       value="<c:out value='${emp.address != null ? emp.address.street : ""}'/>">
                <input type="text" name="city" placeholder="City"
                       value="<c:out value='${emp.address != null ? emp.address.city : ""}'/>">
                <input type="text" name="state" placeholder="State"
                       value="<c:out value='${emp.address != null ? emp.address.state : ""}'/>">
                <input type="text" name="zip" placeholder="Zip"
                       value="<c:out value='${emp.address != null ? emp.address.zip : ""}'/>">
            </div>
        </div>
        <div>
            <button type="submit">Save</button>
            <a href="../">Cancel</a>
        </div>
    </form>
</div>
</body>
</html>
