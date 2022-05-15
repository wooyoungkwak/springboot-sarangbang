<%--
  Created by IntelliJ IDEA.
  User: zilet
  Date: 2022-04-28
  Time: 오후 3:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld" prefix="stripes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<% String contextPath = request.getContextPath(); %>

<stripes:layout-render name="/WEB-INF/views/layout/general.jsp">

    <!-- dashboard content -->
    <stripes:layout-component name="contents">
        <div class="container-fluid px-4">
            <h1 class="mt-4">Business Card</h1>
            <ol class="breadcrumb mb-4">
                <li class="breadcrumb-item"><a href="home">Dashboard</a></li>
                <li class="breadcrumb-item"><a href="#">files</a></li>
                <li class="breadcrumb-item active">Business Card</li>
            </ol>
            <div class="card mb-4">
                <div class="card-body">
                    명함 관리 하는 곳 입니다.
                </div>
            </div>
        </div>
    </stripes:layout-component>

    <!-- javascript -->
    <stripes:layout-component name="javascript">
        <script type="text/javascript">
            $(document).ready(function () {

            });
        </script>
    </stripes:layout-component>

</stripes:layout-render>