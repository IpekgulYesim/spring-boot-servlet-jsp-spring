<%@ page import="jrout.tutorial.springbootservletjsp.model.Kullanici" %>

<!DOCTYPE html>
<html>
<head>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>

<h2>HTML Table</h2>
<%
    Kullanici kullanici = (Kullanici)request.getAttribute("kullanici");
%>
<table>
    <tr>
        <th>Kullanıcı ID</th>
        <th>Kullanıcı Adı</th>
        <th>Adı</th>
        <th>Soyadı</th>
        <th>Email</th>
    </tr>
    <tr>
        <td>${kullanici.idKULLANICI}</td>
        <td><%=kullanici.getKULLANICI_ADI()%></td>
        <td><%=kullanici.getADI()%></td>
        <td><%=kullanici.getSOYADI()%></td>
        <td><%=kullanici.getEMAIL()%></td>
    </tr>
</table>

</body>
</html>

