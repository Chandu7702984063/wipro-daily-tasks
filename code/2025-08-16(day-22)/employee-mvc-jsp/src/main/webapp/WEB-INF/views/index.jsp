<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Employees</title>
    <style>
        /* Reset */
        * { box-sizing: border-box; margin: 0; padding: 0; }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #edf2f7;
            color: #333;
            padding: 20px;
        }

        h2 {
            text-align: center;
            margin-bottom: 30px;
            color: #1f4e79;
        }

        /* Cards */
        .card {
            background: #fff;
            border-radius: 12px;
            padding: 25px;
            margin-bottom: 25px;
            box-shadow: 0 8px 20px rgba(0,0,0,0.1);
            transition: transform 0.3s;
        }
        .card:hover { transform: translateY(-5px); }

        h3 { margin-bottom: 20px; color: #1f4e79; }

        /* Forms */
        input[type="text"], select {
            width: 100%;
            padding: 10px 12px;
            margin: 5px 0 15px 0;
            border-radius: 6px;
            border: 1px solid #ccc;
            font-size: 15px;
            transition: border 0.3s;
        }
        input[type="text"]:focus, select:focus { border-color: #1f4e79; outline: none; }

        button {
            background: linear-gradient(90deg, #1f4e79, #3a70a5);
            color: #fff;
            border: none;
            padding: 12px 20px;
            border-radius: 8px;
            cursor: pointer;
            font-size: 15px;
            transition: opacity 0.3s;
        }
        button:hover { opacity: 0.9; }

        a {
            text-decoration: none;
            color: #1f4e79;
            font-weight: 500;
            transition: color 0.3s;
        }
        a:hover { color: #3a70a5; }

        /* Search Form */
        .card form { display: flex; flex-wrap: wrap; gap: 10px; align-items: center; }
        .card form input[type="text"] { flex: 1 1 200px; }

        /* Table */
        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
            box-shadow: 0 6px 20px rgba(0,0,0,0.05);
        }
        th, td {
            padding: 12px 15px;
            text-align: left;
        }
        th {
            background: #1f4e79;
            color: white;
            position: sticky;
            top: 0;
        }
        tbody tr:nth-child(even) { background: #f9f9f9; }
        tbody tr:hover { background: #eaf1fc; }

        /* Type Badges */
        .badge {
            display: inline-block;
            padding: 4px 10px;
            border-radius: 12px;
            font-size: 13px;
            font-weight: 600;
            color: white;
        }
        .badge-regular { background: #28a745; } /* Green */
        .badge-contract { background: #ffc107; color: #333; } /* Yellow */

        /* Address Inputs Inline */
        .address-inputs {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(140px, 1fr));
            gap: 12px;
        }

        /* Responsive */
        @media (max-width: 768px) {
            .card form { flex-direction: column; }
            table, thead, tbody, th, td, tr { display: block; }
            tr { margin-bottom: 15px; border-bottom: 1px solid #ccc; }
            td {
                text-align: right;
                padding-left: 50%;
                position: relative;
            }
            td::before {
                content: attr(data-label);
                position: absolute;
                left: 15px;
                width: 45%;
                font-weight: bold;
                text-align: left;
            }
            th { display: none; }
        }
    </style>
</head>
<body>
<h2>Employee Directory</h2>

<div class="card">
    <form method="get" action="">
        <input type="text" name="q" placeholder="Search by name" value="${param.q}" />
        <button type="submit">Search</button>
        <a href="./">Reset</a>
    </form>
</div>

<div class="card">
    <h3>Add Employee</h3>
    <form action="add" method="post">
        <label>Name: <input type="text" name="name" required /></label>
        <label>Type:
            <select name="type">
                <option value="regular">Regular</option>
                <option value="contract">Contract</option>
            </select>
        </label>
        <label>Department: <input type="text" name="dept" placeholder="e.g. Engineering" /></label>
        <div>
            <strong>Address</strong>
            <div class="address-inputs">
                <input type="text" name="street" placeholder="Street" />
                <input type="text" name="city" placeholder="City" />
                <input type="text" name="state" placeholder="State" />
                <input type="text" name="zip" placeholder="Zip" />
            </div>
        </div>
        <button type="submit">Create</button>
    </form>
</div>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Type</th>
            <th>Department</th>
            <th>City</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${employees}" var="e">
            <tr>
                <td data-label="ID">${e.id}</td>
                <td data-label="Name">${e.name}</td>
                <td data-label="Type">
                    <span class="badge <c:out value='${e.type=="regular" ? "badge-regular":"badge-contract"}'/>">
                        ${e.type}
                    </span>
                </td>
                <td data-label="Department"><c:out value="${e.department != null ? e.department.name : ''}" /></td>
                <td data-label="City"><c:out value="${e.address != null ? e.address.city : ''}" /></td>
                <td data-label="Actions">
                    <a href="edit/${e.id}">Edit</a> &nbsp;|&nbsp;
                    <a href="delete/${e.id}" onclick="return confirm('Delete employee ${e.name}?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>
