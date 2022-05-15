<%--
  Created by IntelliJ IDEA.
  User: zilet
  Date: 2022-03-02
  Time: 오후 7:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld" prefix="stripes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.young.sarangbang.model.dto.login.domain.DtoUser" %>
<%@ page import="com.young.sarangbang.model.vo.home.domain.VoStockInfo" %>
<%@ page import="java.util.List" %>
<% String contextPath = request.getContextPath(); %>

<stripes:layout-render name="/WEB-INF/views/layout/general.jsp">

    <!-- dashboard content -->
    <stripes:layout-component name="contents">
        <div class="container-fluid px-4">
            <h1 class="mt-4">Dashboard</h1>
            <ol class="breadcrumb mb-4">
                <li class="breadcrumb-item active">Dashboard</li>
            </ol>

            <div class="row">
                <div class="col-4">
                    <div class="card mb-4">
                        <div class="card-header">
                            <i class="fas fa-play-circle me-1"></i>
                            TPMS 프로젝트
                        </div>
                        <div class="card-body">
                            <div id="carouselExampleControls" class="carousel slide mb-4" data-bs-ride="carousel">
                                <div class="carousel-inner">
                                    <div class="carousel-item active">
                                        <img src="<%=contextPath%>/resources/assets/img/tpms1.png" class="d-block w-100" alt="...">
                                    </div>
                                    <div class="carousel-item">
                                        <img src="<%=contextPath%>/resources/assets/img/tpms2.png" class="d-block w-100" alt="...">
                                    </div>
                                    <div class="carousel-item">
                                        <img src="<%=contextPath%>/resources/assets/img/tpms3.png" class="d-block w-100" alt="...">
                                    </div>
                                </div>
                                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Previous</span>
                                </button>
                                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Next</span>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-4">
                    <div class="card mb-4">
                        <div class="card-header">
                            <i class="fas fa-play-circle me-1"></i>
                            음악 프로젝트
                        </div>
                        <div class="card-body">

                        </div>
                    </div>

                </div>
                <div class="col-4">
                    <div class="card mb-4">
                        <div class="card-header">
                            <i class="fas fa-play-circle me-1"></i>
                            모바일 프로젝트
                        </div>
                        <div class="card-body">

                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-xl-6">
                    <div class="card mb-4">
                        <div class="card-header">
                            <i class="fas fa-chart-area me-1"></i>
                            NC 소프트웨어 - 일일 주식가
                        </div>
                        <div class="card-body">
                            <canvas id="myLineChart" width="100%" height="40"></canvas>
                        </div>
                    </div>
                </div>
                <div class="col-xl-6">
                    <div class="card mb-4">
                        <div class="card-header">
                            <i class="fas fa-chart-bar me-1"></i>
                            NC 소프트웨어 - 상한가
                        </div>
                        <div class="card-body">
                            <canvas id="myBarChartHigh" width="100%" height="40"></canvas>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card mb-4">
                <div class="card-header">
                    <i class="fas fa-table me-1"></i>
                    부동산 및 부동산 중계소 정보
                </div>

                <div class="card-body">

                </div>

                <div class="card-body">
                    <table id="datatablesSimple">
                        <thead>
                            <tr>
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
                        <tfoot>
                            <tr>
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
                        <tbody>
                            <c:if test="${bangInfos != null}">
                                <c:forEach items="${bangInfos}" var="bangInfo" varStatus="status">
                                    <tr>
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
                                            <%--                                                <td><c:out value="${bangInfo.registerDate}"></c:out></td>--%>
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
                    </table>
                </div>
                <div class="card-body">
                    <table id="datatablesSimple2">
                        <thead>
                            <tr>
                                <th>index</th>
                                <th>주소</th>
                                <th>전화</th>
                                <th>이름</th>
                                <th>담당자</th>
                                <th>담당자 전화</th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <th>index</th>
                                <th>주소</th>
                                <th>전화</th>
                                <th>이름</th>
                                <th>담당자</th>
                                <th>담당자 전화</th>
                            </tr>
                        </tfoot>
                        <tbody>
                            <c:if test="${estateAgencies != null}">
                                <c:forEach items="${estateAgencies}" var="estateAgency" varStatus="status">
                                    <tr>
                                        <td><c:out value="${status.index +1}"></c:out></td>
                                        <td><c:out value="${estateAgency.address}"></c:out></td>
                                        <td><c:out value="${estateAgency.phone}"></c:out></td>
                                        <td><c:out value="${estateAgency.name}"></c:out></td>
                                        <td><c:out value="${estateAgency.agencyName}"></c:out></td>
                                        <td><c:out value="${estateAgency.agencyPhone}"></c:out></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </stripes:layout-component>

    <!-- javascript -->
    <stripes:layout-component name="javascript">
        <script type="application/javascript" src="<%=contextPath%>/resources/js/stock.js"></script>

        <script type="text/javascript">

            $(document).ready(function () {

                /* 객체 표현식 예제  */
                // 1. controller -> JSP -> javascript 데이터 객체
                <% DtoUser user = (DtoUser)request.getAttribute("user"); %>
                let userSeq = "<%=user.getUserSeq()%>";
                // log(" ****** 객체 표현식 예제 **** ");
                // log(` useqSeq = ` + userSeq);

                // 2. controller -> javascript 데이터 객체
                //   ※ jackson library 를 이용한 전달 방법을 찾아보자 ......
                <%--log(" \$\{user.userSeq\} =  " + "${user.userSeq}");--%>
                <%--log(" \$\{user.username\} = " + "${user.username}");--%>


                /* graph 예제 */
                let lineChart = createLineChart("myLineChart");
                let barChartHigh = createBarChartHigh("myBarChartHigh");

                let dateFormat = function (strDate) {
                    let date = new Date(strDate);
                    let formatted_date = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
                    return formatted_date;
                };

                let datas = [];
                let totalDatas = [];

                let barHighDatas = [];
                let totalBarHighDatas = [];

                let labels = [];

                <c:forEach items="${voStockInfos}" var="voStockInfo" varStatus="index">
                labels.push(dateFormat("${voStockInfo.regDate}"));
                datas.push(Number("${voStockInfo.price}"));
                barHighDatas.push(Number("${voStockInfo.highPrice}"));
                </c:forEach>

                totalDatas.push(datas);
                totalBarHighDatas.push(barHighDatas);

                setLineDatas(lineChart, labels, totalDatas);
                setBarHighDatas(barChartHigh, labels, totalBarHighDatas);
            });


        </script>
    </stripes:layout-component>

</stripes:layout-render>