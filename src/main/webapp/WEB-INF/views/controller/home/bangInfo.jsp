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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<% String contextPath = request.getContextPath(); %>

<stripes:layout-render name="/WEB-INF/views/layout/general.jsp">

    <!-- table content -->
    <stripes:layout-component name="contents">
        <div class="container-fluid px-4">
            <h1 class="mt-4">부동산 정보</h1>
            <ol class="breadcrumb mb-4">
                <li class="breadcrumb-item"><a href="home">Dashboard</a></li>
                <li class="breadcrumb-item active">부동산 정보</li>
            </ol>
            <div class="card mb-4">
                <div class="card-body">
                    광주 광역시 부동산 정보
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
                    <table id="datatablesSimple" class="banginfo">
                        <thead>
                            <tr>
                                <th><img src="<%=contextPath%>/resources/assets/img/bookmark-dark.png" style="width: 22px; height: 22px;"/></th>
                                <th>index</th>
                                <th>위치</th>
                                <th>주소</th>
                                <th>거래</th>
                                <th>방수</th>
                                <th>욕실수</th>
                                <th>층수</th>
                                <th>대지</th>
                                <th>연면적</th>
                                <th>난방</th>
                                <th>방향</th>
                                <th>가격</th>
                                <th>사용일</th>
                                <th>부동산</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${bangInfos != null}">
                                <c:forEach items="${bangInfos}" var="bangInfo" varStatus="status">
                                    <tr>
                                        <td>
                                            <a href="#" id="index${bangInfo.bangInfoSeq}"
                                               onclick="favorites(${bangInfo.bangInfoSeq})"
                                               isFavorites="${bangInfo.isFavorites}">
                                                <c:choose>
                                                    <c:when test="${bangInfo.isFavorites == true}">
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
                                        <td><c:out value="${bangInfo.location}"></c:out></td>
                                        <td><c:out value="${bangInfo.address}"></c:out></td>
                                        <td><c:out value="${bangInfo.deal}"></c:out></td>
                                        <td><c:out value="${bangInfo.room}"></c:out></td>
                                        <td><c:out value="${bangInfo.floor}"></c:out></td>
                                        <td><c:out value="${bangInfo.place / 100}"></c:out>평</td>
                                        <td><c:out value="${bangInfo.ground / 100}"></c:out>평</td>
                                        <td><c:out value="${bangInfo.bathRoom}"></c:out></td>
                                        <td><c:out value="${bangInfo.heating}"></c:out></td>
                                        <td><c:out value="${bangInfo.direction}"></c:out></td>
                                        <td><fmt:formatNumber value="${bangInfo.price / 10000}"
                                                              pattern="#,###"></fmt:formatNumber>만원
                                        </td>
                                        <td>
                                            <fmt:parseDate value="${bangInfo.registerDate}" pattern="yyyy-MM-dd'T'HH:mm"
                                                           var="registerDate"></fmt:parseDate>
                                            <fmt:formatDate pattern="yyyy-MM-dd"
                                                            value="${registerDate}"></fmt:formatDate>
                                        </td>
                                        <td><c:out value="${bangInfo.estateAgency.name}"></c:out></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>
                        <tfoot>
                            <tr>
                                <th><img src="<%=contextPath%>/resources/assets/img/bookmark-dark.png"
                                         style="width: 22px; height: 22px;"/></th>
                                <th>index</th>
                                <th>위치</th>
                                <th>주소</th>
                                <th>거래</th>
                                <th>방수</th>
                                <th>욕실수</th>
                                <th>층수</th>
                                <th>대지</th>
                                <th>연면적</th>
                                <th>난방</th>
                                <th>방향</th>
                                <th>가격</th>
                                <th>사용일</th>
                                <th>부동산</th>
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
            let favorites = function (bangInfoSeq) {
                let contextPath = $.getContextPath();

                let id = 'index' + bangInfoSeq;
                let node = document.getElementById(id);
                let isFavorites = node.getAttribute('isFavorites');

                if (isFavorites === 'false') {
                    node.children[0].setAttribute('src', contextPath + '/resources/assets/img/bookmark-dark.png');
                    node.setAttribute('isFavorites', 'true');

                    $.ajaxASync('/bangInfo/isFavorites', {
                        'bangInfoSeq': bangInfoSeq,
                        'isFavorites': true
                    });
                } else {
                    node.children[0].setAttribute('src', contextPath + '/resources/assets/img/bookmark-light.png');
                    node.setAttribute('isFavorites', 'false');

                    $.ajaxASync('/bangInfo/isFavorites', {
                        'bangInfoSeq': bangInfoSeq,
                        'isFavorites': false
                    });
                }

            };

            // $(document).ready(function (){
            //     $('#datatablesSimple tbody').on('click', function (){
            //         log($(this).html());
            //     });
            // });
        </script>
    </stripes:layout-component>

</stripes:layout-render>