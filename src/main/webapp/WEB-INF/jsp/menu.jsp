<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<ul id="menu">
	<li><a href="">Groupstage</a>
		<ul class="sub-menu">
			<c:forEach var="group" items="${comunio.groups}">
				<li><a href="showView/group/${group.groupName}">Gruppe ${group.groupName }</a></li>
			</c:forEach>
		</ul></li>
	<li><a href="#">Playoffs</a></li>
	<li><a href="#">Rules</a></li>
	<li><a href="#">Admin</a>
</ul>