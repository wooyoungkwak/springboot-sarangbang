<%--
  Created by IntelliJ IDEA.
  User: zilet
  Date: 2022-04-28
  Time: 오후 3:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld" prefix="stripes" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<% String contextPath = request.getContextPath(); %>

<stripes:layout-render name="/WEB-INF/views/layout/general.jsp">

    <!-- table content -->
    <stripes:layout-component name="contents">
        <div class="container-fluid px-4">
            <h1 class="mt-4">Fragment</h1>
            <ol class="breadcrumb mb-4">
                <li class="breadcrumb-item"><a href="/home">Dashboard</a></li>
                <li class="breadcrumb-item"><a href="#">Mobile 프로젝트</a></li>
                <li class="breadcrumb-item"><a href="#">Android</a></li>
                <li class="breadcrumb-item active">Fragment</li>
            </ol>
            <div class="card mb-4">
                <div class="card-body">
                    Android App 에서 Fragment 를 이용한 프로젝트입니다.
                </div>
            </div>
        </div>
    </stripes:layout-component>

    <!-- javascript -->
    <stripes:layout-component name="javascript">
        <script type="application/javascript">
            $(document).ready(function (){

            });
        </script>
    </stripes:layout-component>

</stripes:layout-render>