<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<ul id="menu">
	<li><a href="">Groupstage</a>
		<ul class="sub-menu">
			<c:forEach var="group" items="${groups}">
				<li><a href="${group.groupName}">Gruppe ${group.groupName}</a></li>
			</c:forEach>
		</ul></li>
	<li><a href="#">Playoffs</a></li>
	<li><a href="#">Rules</a></li>
	<li><a href="#">Admin</a>
</ul>