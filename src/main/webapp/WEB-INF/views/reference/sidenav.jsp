<%--
  Created by IntelliJ IDEA.
  User: zilet
  Date: 2022-04-27
  Time: 오후 2:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
        <div class="sb-sidenav-menu">
            <div class="nav">
                <div class="sb-sidenav-menu-heading">Core</div>
                <a class="nav-link" href="/home">
                    <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                    Dashboard
                </a>

                <%-- nav-환경 --%>
                <div class="sb-sidenav-menu-heading">환경</div>

                <%-- nav-theme --%>
                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                    <div class="sb-nav-link-icon"><i class="fas fa-brush"></i></div>
                    Theme
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="collapseLayouts" aria-label="envOne" data-bs-parent="#sidenavAccordion">
                    <nav class="sb-sidenav-menu-nested nav">
                        <a class="nav-link" href="#" id="dark">
                            <div class="sb-nav-link-icon"><i class="fas fa-fill-drip"></i></div>
                            Dark Nav
                        </a>
                        <a class="nav-link" href="#" id="light">
                            <div class="sb-nav-link-icon"><i class="fas fa-fill-drip"></i></div>
                            Light Nav
                        </a>
                    </nav>
                </div>


                <%-- FILE --%>
                <div class="sb-sidenav-menu-heading">FILES</div>
                <a class="nav-link" href="/files/document">
                    <div class="sb-nav-link-icon"><i class="fas fa-folder-open"></i></div>
                    Document
                </a>
                <a class="nav-link" href="/files/businesscard">
                    <div class="sb-nav-link-icon"><i class="fas fa-address-card"></i></div>
                    Business Card
                </a>

                <%-- nav-프로젝트 --%>
                <div class="sb-sidenav-menu-heading">프로젝트</div>
                <a class="nav-link" href="/tpms">
                    <div class="sb-nav-link-icon"><i class="fas fa-industry"></i></div>
                    TPMS
                </a>
                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseMusic" aria-expanded="false" aria-controls="collapseMusic">
                    <div class="sb-nav-link-icon"><i class="fas fa-music"></i></div>
                    Music 프로젝트
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="collapseMusic" aria-label="projOne">
                    <nav class="sb-sidenav-menu-nested nav">
                        <a class="nav-link" href="/music/classic" id="classic">
                            <div class="sb-nav-link-icon"><i class="fa fa-file-audio"></i></div>
                            Classic
                        </a>
                        <a class="nav-link" href="/music/pop" id="pop">
                            <div class="sb-nav-link-icon"><i class="fa fa-file-audio"></i></div>
                            POP
                        </a>
                    </nav>
                </div>
                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseMobile" aria-expanded="false" aria-controls="collapseMobile">
                    <div class="sb-nav-link-icon"><i class="fas fa-mobile-alt me-1"></i></div>
                    Mobile 프로젝트
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="collapseMobile" aria-label="projTwo" data-bs-parent="#sidenavAccordion">
                    <nav class="sb-sidenav-menu-nested nav">
                        <a class="nav-link" href="#" id="ios">
                            <div class="sb-nav-link-icon"><i class="fa fa-apple-alt"></i></div>
                            IOS
<%--                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>--%>
                        </a>
                        <a class="nav-link" href="#" id="android" data-bs-toggle="collapse" data-bs-target="#collapseAndroid" aria-expanded="false" aria-controls="collapseAndroid">
                            <div class="sb-nav-link-icon"><i class="fa fa-robot"></i></div>
                            Android
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="collapseAndroid" aria-label="androidOne">
                            <nav class="sb-sidenav-menu-nested nav">
                                <a class="nav-link" href="/mobile/hybrid" id="hybrid">
                                    <div class="sb-nav-link-icon"><i class="fa fa-file"></i></div>
                                    Hybrid
                                </a>
                                <a class="nav-link" href="/mobile/fragment" id="fragment">
                                    <div class="sb-nav-link-icon"><i class="fa fa-file"></i></div>
                                    Fragment
                                </a>
                            </nav>
                        </div>
                    </nav>
                </div>

                <%-- nav-정보 --%>
                <div class="sb-sidenav-menu-heading">정보</div>
                <a class="nav-link" href="/stock">
                    <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>
                    주식 정보
                </a>
                <a class="nav-link" href="/bangInfo">
                    <div class="sb-nav-link-icon"><i class="fas fa-house-user"></i></div>
                    부동산 정보
                </a>
                <a class="nav-link" href="/estateAgency">
                    <div class="sb-nav-link-icon"><i class="fas fa-address-card"></i></div>
                    부동산 중계소 정보
                </a>
            </div>
        </div>

        <div class="sb-sidenav-footer">
            <div class="small">Logged in as:</div>
            ${username}
        </div>

    </nav>
</div>

