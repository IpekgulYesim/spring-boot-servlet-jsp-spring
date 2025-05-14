<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%> 
<!DOCTYPE html> 
<html> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>Welcome Page</title> 
</head> 
<body> 
  
    <form action="logout" method="get"> 
  
        <h2> 
            Hello 
            <%=request.getParameter("adsoyad")%>! 
        </h2> 
        <h3>Redirecting to Acıktım..</h3> 
  
        <br>

    </form> 
    
    <script>
        const queryString = window.location.search;
        const URLParams = new URLSearchParams(queryString);
        const name = URLParams.get("adsoyad");
        const username = URLParams.get("kullanici");
        const id = URLParams.get("id");

        sessionStorage.setItem("name", name);
        sessionStorage.setItem("username", username);
        sessionStorage.setItem("id", id);

        window.location.replace("/user-login.html");
    </script>
</body> 
</html>