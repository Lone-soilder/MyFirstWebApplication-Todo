<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<link href = "webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel ="stylesheet">
<title> List todo page </title>
</head>
<body>
  <div class="container">
      <h2>welcome to login page  !!</h2>
      <h2>your TODOS</h2>
      <tr>
      <table class="table">
      <thead>
      <tr>


      <th>User Name </th>

      <th>Description</th>

      <th>target date</th>

      <th>Is Done</th>

      <th> </th>

      <th> </th>

      <th> </th>

      </tr>
      </thead>

     <tbody>
     <c:forEach items="${todos}" var="todo">
     <tr>

       <td>${todo.username}</td>
       <td>${todo.description}</td>
       <td>${todo.targetDate}</td>
       <td>${todo.done}</td>
       <td><a href="delete-todo?id=${todo.id} " class ="btn btn-warning">Delete </a></td>
       <td><a href="update-todo?id=${todo.id} " class ="btn btn-success">Update </a></td>
     </tr>
     </c:forEach>
     </tbody>

      </table>

      <a href="add-todo" class="btn btn-success">Add-todo </a>
</div>
  <script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
  <script src="webjars/jquery/3.1.6/js/jquery.min.js"></script>

</body>
</html>
