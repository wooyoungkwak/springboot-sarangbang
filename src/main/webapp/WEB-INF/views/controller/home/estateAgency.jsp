<%--
  Created by IntelliJ IDEA.
  User: zilet
  Date: 2022-03-02
  Time: 오후 7:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld" prefix="stripes" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String contextPath = request.getContextPath(); %>

<stripes:layout-render name="/WEB-INF/views/layout/general.jsp">

    <!-- table content -->
    <stripes:layout-component name="contents">
        <div class="container-fluid px-4">
            <h1 class="mt-4">Tables</h1>
            <ol class="breadcrumb mb-4">
                <li class="breadcrumb-item"><a href="home">Dashboard</a></li>
                <li class="breadcrumb-item active">Tables</li>
            </ol>
            <div class="card mb-4">
                <div class="card-body">
                    광주 광역시 부동산 중계소 정보
                    <br />
                    <br />
                    <i class="fa fa-play"></i> 사랑방 싸이트를 참조한 정보입니다.
                </div>
            </div>
            <div class="card mb-4">
                <div class="card-header">
                    <i class="fas fa-table me-1"></i>
                    DataTable Example
                </div>
                <div class="card-body">
                    <table id="datatablesSimple">
                        <thead>
                            <tr>
                                <th><img src="<%=contextPath%>/resources/assets/img/bookmark-dark.png"
                                         style="width: 22px; height: 22px;"/></th>
                                <th>index</th>
                                <th>주소</th>
                                <th>전화</th>
                                <th>이름</th>
                                <th>담당자</th>
                                <th>담당자 전화</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${estateAgencies != null}">
                                <c:forEach items="${estateAgencies}" var="estateAgency" varStatus="status">
                                    <tr>
                                        <td>
                                            <a href="#" id="index${estateAgency.estateAgencySeq}"
                                               onclick="favorites(${estateAgency.estateAgencySeq})"
                                               isFavorites="${estateAgency.isFavorites}">
                                                <c:choose>
                                                    <c:when test="${estateAgency.isFavorites == true}">
                                                        <img src="<%=contextPath%>/resources/assets/img/bookmark-dark.png"
                                                             style="width: 22px; height: 22px;"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img src="<%=contextPath%>/resources/assets/img/bookmark-light.png"
                                                             style="width: 22px; height: 22px;"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </a>
                                        </td>
                                        <td><c:out value="${status.index +1 }"></c:out></td>
                                        <td><c:out value="${estateAgency.address}"></c:out></td>
                                        <td><c:out value="${estateAgency.phone}"></c:out></td>
                                        <td><c:out value="${estateAgency.name}"></c:out></td>
                                        <td><c:out value="${estateAgency.agencyName}"></c:out></td>
                                        <td><c:out value="${estateAgency.agencyPhone}"></c:out></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>
                        <tfoot>
                            <tr>
                                <th><img src="<%=contextPath%>/resources/assets/img/bookmark-dark.png"
                                         style="width: 22px; height: 22px;"/></th>
                                <th>index</th>
                                <th>주소</th>
                                <th>전화</th>
                                <th>이름</th>
                                <th>담당자</th>
                                <th>담당자 전화</th>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>
    </stripes:layout-component>

    <!-- javascript -->
    <stripes:layout-component name="javascript">
        <script type="application/javascript">
            let favorites = function (estateAgencySeq) {
                let contextPath = $.getContextPath();

                let id = 'index' + estateAgencySeq;
                let node = document.getElementById(id);
                let isFavorites = node.getAttribute('isFavorites');

                if (isFavorites === 'false') {
                    node.children[0].setAttribute('src', contextPath + '/resources/assets/img/bookmark-dark.png');
                    node.setAttribute('isFavorites', 'true');

                    $.ajaxASync('/estateAgency/isFavorites', {
                        'estateAgencySeq': estateAgencySeq,
                        'isFavorites': true
                    });
                } else {
                    node.children[0].setAttribute('src', contextPath + '/resources/assets/img/bookmark-light.png');
                    node.setAttribute('isFavorites', 'false');

                    $.ajaxASync('/estateAgency/isFavorites', {
                        'estateAgencySeq': estateAgencySeq,
                        'isFavorites': false
                    });
                }

            };
        </script>
    </stripes:layout-component>

</stripes:layout-render>