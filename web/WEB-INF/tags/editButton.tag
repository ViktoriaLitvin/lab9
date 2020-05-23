<%@ tag pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="ad" required="true" rtexprvalue="true"
	type="bsu.rfe.java.group6.lab9.Litvinenko.metodichka.entity.Ad"%>

<%-- Кнопка редактирования показывается только если:
1) пользователь аутентифицирован (authUser!=null);
2) передано текущее объявление (ad!=null);
3) id автора объявленния и id аутентифицированного пользователя
совпадают --%>

<c:if test="${sessionScope.authUser!=null && ad!=null && ad.authorId==sessionScope.authUser.id}">
	<div style="float: left; margin: 10px; margin-top: 20px; padding: 5px 0px; border: 1px solid black; background-color: #ccc; width: 150px; text-align: center">
		<a href="<c:url value="/updateAd.jsp">
		<c:param name="id" value="${ad.id}" />
		</c:url>">Редактировать</a>
	</div>
</c:if>