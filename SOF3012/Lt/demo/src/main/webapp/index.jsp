<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<p>hello my sug is <%= 9 + 9 %> </p>

<%--scriptlets--%>
<%
    for(int a = 0; a < 5; a++) {
        out.println(a);
        out.println("<br>");
    }
%>

<%--JSP declarations--%>
<%!
    String sex(String makeitsus) {
        return makeitsus;
    }
%>

<p>GAGAGAGAGGAGAGA <%= com.lmao.demo.sex.getSus("AAAAAAAAAAAAAAA") %></p>

<p>Hello sus <%= sex("gay") %></p>


<form action="gayServlet">
    TCCTL: <input type="text" name="tcctl"> <br>
    LN: <input type="text" name="ln"><br>
    <select>
        <option>gay</option>
        <option>cc</option>
        <option>ccccd</option>
        <option>tccc</option>
    </select> <br>

    <input type="checkbox" name="Fargay" value="1">Text 1
    <input type="checkbox" name="Fargay" value="2">Text 2
    <input type="checkbox" name="Fargay" value="3">Text 3
    <input type="checkbox" name="Fargay" value="4">Text 4

    <br>

    <input type="submit" value="Submit">
</form>
</body>
</html>