<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="form" action="/portal/home" method="post" novalidate>
    <div class="form-group row">
        <div class="col-sm-10">
            <label for="chooseCode">Podaj kod
                <c:out value="${data == 'fund' ? ' funduszu inwestycyjnego, którego ': ' waluty, której '}"/>
                analizy chcesz dokonać:
            </label>
            <input type="text" class="form-control" name="code" id="chooseCode"
                   value="<c:out value="${sessionScope.code}"/>" required>
            <div class="invalid-feedback">
                Proszę podać kod <c:out value="${data == 'fund' ? 'funduszu inwestycyjnego': 'waluty'}"/>.
            </div>
        </div>
    </div>
    <jsp:include page="form-step-nav.jsp"/>
</form>