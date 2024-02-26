<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title> My Login Page </title>
</head>
<body>
  <h2>welcome to login page  !!</h2>
  <h2>your TODOS</h2>
  <tr>
  <table>
  <thead>
  <tr>
  <th>id</th>

  <th>Description</th>

  <th>target date</th>

  <th>Is Done</th>

  </tr>
  </thead>

 <tbody>
 <c:forEach items="${todos}" var="todo">
 <tr>
   <td>${todo.id}</td>
   <td>${todo.username}</td>
   <td>${todo.description}</td>
   <td>${todo.targetDate}</td>
   <td>${todo.done}</td>
 </tr>
 </c:forEach>
 </tbody>

  </table>
</body>
</html>
