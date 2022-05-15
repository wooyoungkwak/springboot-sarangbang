<%--
  Created by IntelliJ IDEA.
  User: zilet
  Date: 2022-03-02
  Time: 오후 7:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld" prefix="stripes" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% String contextPath = request.getContextPath(); %>

<stripes:layout-render name="/WEB-INF/views/layout/general.jsp">

    <!-- chart content -->
    <stripes:layout-component name="contents">
        <div class="container-fluid px-4">
            <h1 class="mt-4">주식 정보 - 실시간 / 상하한가 / 월별평균 </h1>
            <ol class="breadcrumb mb-4">
                <li class="breadcrumb-item"><a href="/home">Dashboard</a></li>
                <li class="breadcrumb-item active">주식 정보</li>
            </ol>
            <div class="card mb-4">
                <div class="card-body">

                    주식 정보 업체를 선택 하세요.
                    <br/>
                    ※ Chart.js is a third party plugin that is used to generate the charts in this template. The
                    charts below have been customized - for further customization options, please visit the
                    official
                    <a target="_blank" href="https://www.chartjs.org/docs/latest/">Chart.js documentation</a>
                    .
                    <br/>
                    <br/>
                    <div class="input-group">
                        <div class="input-group-text">
                            <i class="fas fa-chart-area me-1"></i>
                            기업
                        </div>
                        <select class="form-select" aria-label="Default select example" id="stockCompany">
                            <option selected>선택</option>
                            <c:forEach items="${stockCompanies}" var="stockCompany" varStatus="status">
                                <option value="${stockCompany.alias}">${stockCompany.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>

            <div class="card mb-4">
                <div class="card-header">
                    <i class="fas fa-chart-area me-1"></i>
                    Line Chart - 일일 주식가
                </div>
                <div class="card-body">
                    <canvas id="myLineChart" width="100%" height="30"></canvas>
                </div>
                <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
            </div>

            <div class="row">
                <div class="col-lg-6">
                    <div class="card mb-4">
                        <div class="card-header">
                            <i class="fas fa-chart-bar me-1"></i>
                            Bar Chart - 상한가
                        </div>
                        <div class="card-body">
                            <canvas id="myBarChartHigh" width="100%" height="50"></canvas>
                        </div>
                        <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="card mb-4">
                        <div class="card-header">
                            <i class="fas fa-chart-bar me-1"></i>
                            Bar Chart - 하한가
                        </div>
                        <div class="card-body">
                            <canvas id="myBarChartLow" width="100%" height="50"></canvas>
                        </div>
                        <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-6">
                    <div class="card mb-4">
                        <div class="card-header">
                            <i class="fas fa-chart-pie me-1"></i>
                            Pie Chart - 월별 평균 주식가
                        </div>
                        <div class="card-body">
                            <canvas id="myPieChart" width="100%" height="50"></canvas>
                        </div>
                        <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
                    </div>
                </div>
            </div>

        </div>
    </stripes:layout-component>

    <!-- javascript -->
    <stripes:layout-component name="javascript">
        <script type="application/javascript" src="<%=contextPath%>/resources/js/stock.js"></script>

        <script type="application/javascript">
            $(document).ready(function () {

                let lineChart = createLineChart("myLineChart");
                let barChartHigh = createBarChartHigh("myBarChartHigh");
                let barChartLow = createBarChartLow("myBarChartLow");
                let pieChart = createPieChart("myPieChart");

                let dateFormat = function (strDate) {
                    // log(" ******** ", (typeof strDate), strDate);

                    let formatted_date;
                    if ((typeof strDate) === 'string') {
                        let date = new Date(strDate);
                        formatted_date = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
                    } else {
                        formatted_date = strDate[0] + "-" + strDate[1] + "-" + strDate[2];
                    }
                    return formatted_date;
                };

                let monthFormat = function (strDate) {
                    let date = new Date(strDate);
                    return (date.getMonth() + 1);
                }

                let lineChartConfigure = function (alias, stockType) {

                    let data = {
                        "alias": alias,
                        "stockType": stockType
                    }

                    let resultCallback = function (results) {

                        // line chart data
                        let datas = [];
                        let totalDatas = [];

                        // bar chart data
                        let barHighDatas = [];
                        let totalBarHighDatas = [];
                        let barLowDatas = [];
                        let totalBarLowDatas = [];

                        // pie chart data
                        let pieDatas = new Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
                        let totalPieDatas = [];
                        let pieAverage = 0;

                        // line label or bar chart label
                        let labels = [];

                        // pie label
                        let pieLabels = ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'];

                        let length = results.length;

                        for (let i = 0; i < length; i++) {

                            labels.push(dateFormat(results[i].regDate));
                            datas.push(Number(results[i].price));

                            barHighDatas.push(Number(results[i].highPrice));
                            barLowDatas.push(Number(results[i].lowPrice));

                            pieAverage += Number(results[i].price);
                        }

                        pieAverage = pieAverage / 5;
                        pieDatas[2] = pieAverage;
                        totalPieDatas.push(pieDatas);

                        totalDatas.push(datas);
                        totalBarHighDatas.push(barHighDatas);
                        totalBarLowDatas.push(barLowDatas);

                        setLineDatas(lineChart, labels, totalDatas);
                        setBarHighDatas(barChartHigh, labels, totalBarHighDatas);
                        setBarLowDatas(barChartLow, labels, totalBarLowDatas);
                        setPieDatas(pieChart, pieLabels, totalPieDatas);
                    };

                    let errorCallback = function (message) {
                        alert("정보를 불러오는데 실패 하였습니다. \n ( " + message + " )");
                    };

                    let url = "/stockInfo";
                    $.ajaxJJ(url, data, resultCallback, errorCallback);

                };

                $('#stockCompany').on('change', function () {
                    lineChartConfigure(this.value, "DAY");
                });

            });
        </script>
    </stripes:layout-component>

</stripes:layout-render>