<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/app/css/todo/massages.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/app/css/todo/styles.css">
<title>Todo List</title>

</head>
<body>
	<h1>Todo List</h1>
	<div id="todoForm">
		<t:messagesPanel />

		<form:form action="${pageContext.request.contextPath}/todo/createTodo"
			method="post" modelAttribute="todoForm">
			<form:input path="todoTitle" />
			<form:errors path="todoTitle" />
			<form:button>Create Todo</form:button>
		</form:form>
	</div>
	<hr />
	<div id="todoList">
		<ul>
			<c:forEach items="${todos}" var="todo">
				<li><c:choose>
						<c:when test="${todo.finished}">
							<span class="strike"> ${f:h(todo.todoTitle)} </span>
						</c:when>
						<c:otherwise>
                            ${f:h(todo.todoTitle)}
                            <form:form
								action="${pageContext.request.contextPath}/todo/finishTodo"
								method="post" modelAttribute="todoForm" cssClass="inline">
								<form:hidden path="todoId" value="${f:h(todo.todoId)}" />
								<form:button>Finish</form:button>
							</form:form>
							<form:form
								action="${pageContext.request.contextPath}/todo/deleteTodo"
								method="post" modelAttribute="todoForm" cssClass="inline">
								<form:hidden path="todoId" value="${f:h(todo.todoId)}" />
								<form:button>Delete</form:button>
							</form:form>
						</c:otherwise>
					</c:choose></li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>