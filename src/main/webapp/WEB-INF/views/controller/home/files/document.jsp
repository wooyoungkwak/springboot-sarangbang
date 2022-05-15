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

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<% String contextPath = request.getContextPath(); %>

<stripes:layout-render name="/WEB-INF/views/layout/general.jsp">

    <!-- dashboard content -->
    <stripes:layout-component name="contents">
        <div class="container-fluid px-4">
            <h1 class="mt-4">Document</h1>
            <ol class="breadcrumb mb-4">
                <li class="breadcrumb-item"><a href="home">Dashboard</a></li>
                <li class="breadcrumb-item"><a href="#">files</a></li>
                <li class="breadcrumb-item active">Document</li>
            </ol>
            <div class="card mb-4">
                <div class="card-body">
                    일반 문서 파일 관리 하는 곳 입니다.
                </div>
            </div>

            <div class="card mb-4">
                <div class="card-header">
                    <i class="fas fa-folder"></i>
                    file upload / download
                </div>

                <div class="card-body">
                    <form class="uploadForm">
                        <div class="row justify-content-start mb-2">
                            <div class="col-8">
                                <div class="input-group">
                                    <span class="input-group-text" id="basic-addon3">파일 설명</span>
                                    <input type="text" class="form-control" id="description" aria-describedby="basic-addon3">
                                </div>
                            </div>
                            <div class="col-4">
                                <div class="input-group">
                                    <input type="file" class="form-control" id="fileUpload" aria-describedby="inputGroupFileAddon04" aria-label="Upload" name="uploadFile">
                                    <button class="btn btn-primary" type="button" id="btnFileUpload">Upload</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>

                <ul class="list-group list-group-flush"></ul>

                <div class="card-body">
                    <table id="documentDataTable">
                        <thead>
                            <tr>
                                <th scope="col" data-sortable="false">#</th>
                                <th scope="col">설명</th>
                                <th scope="col">파일명</th>
                                <th scope="col">등록일</th>
                                <th scope="col">삭제일</th>
                                <th scope="col">다운로드</th>
                            </tr>
                        </thead>
                        <tbody id="tbody">
                            <c:forEach items="${fileInfos}" var="fileInfo" varStatus="status">
                                <tr>
                                    <th scope="row">${status.count}<input type="hidden" value="${fileInfo.fileInfoSeq}"/></th>
                                    <td>${fileInfo.description}</td>
                                    <td>${fileInfo.fileName}</td>
                                    <td>
                                        <fmt:parseDate value="${fileInfo.regDt}" pattern="yyyy-MM-dd'T'HH:mm" var="registerDate"></fmt:parseDate>
                                        <fmt:formatDate pattern="yyyy-MM-dd" value="${registerDate}"></fmt:formatDate>
                                    </td>
<%--                                    <td>${fileInfo.regDt}</td>--%>
                                    <td>${fileInfo.delDt}</td>
                                    <td>
                                        <button id="download${status.count}" class="fileDownload btn btn-primary" onclick="downloadFunc(${status.count});">Download</button>
                                        <button id="delete${status.count}" class="fileDelete btn btn-secondary" onclick="deleteFunc(${status.count});">Delete</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>

            </div>
        </div>
    </stripes:layout-component>

    <!-- javascript -->
    <stripes:layout-component name="javascript">
        <script type="text/javascript">

            const documentDataTable = document.getElementById('documentDataTable');
            const sDatatable = new simpleDatatables.DataTable(documentDataTable);

            // yyyy-MM-dd
            let changeDate = function (dateArr) {
                let yyyy = dateArr[0];
                let MM = dateArr[1];
                let dd = dateArr[2];

                if (MM < 10) MM = '0' + MM;
                if (dd < 10) dd = '0' + dd;

                return  yyyy + "-" + MM + '-' + dd;
            }

            let trAdd = function (fileInfo){
                let node = document.getElementById('tbody');
                let trLength = node.children.length;
                let count = trLength + 1;

                console.log(JSON.stringify(fileInfo));
                console.log(fileInfo.regDt);

                let col1 = count + `<input type="hidden" value="` + fileInfo.fileInfoSeq + `"/>`;
                let col2 = fileInfo.description;
                let col3 = fileInfo.fileName;
                let col4 = changeDate(fileInfo.regDt);
                let col5 = fileInfo.delDt
                let col6 = `<button id="download` + count +`" class="fileDownload btn btn-primary" onclick="downloadFunc(` + fileInfo.fileInfoSeq + `);">Download</button>` +
                           `<button id="delete`+ count  + `" class="fileDelete btn btn-secondary" onclick="deleteFunc(` + fileInfo.fileInfoSeq + `);">Delete</button>`;
                let data= [col1, col2, col3, col4, col5, col6];

                sDatatable.rows().add(data);
            }

            let downloadFunc = function (count) {
                let _contextPath = $.getContextPath();
                let id = "download" + count;
                let node = document.getElementById(id);
                let trNode = node.parentElement.parentElement;

                let _fileName = trNode.children[2].innerHTML;

                let parameter = {
                    fileType: "file",
                    fileName: _fileName,
                }
                location.href = _contextPath + '/fileDownload?' + $.param(parameter);
            };

            let deleteFunc = function (count) {
                let _contextPath = $.getContextPath();

                let id = "delete" + count;
                let node = document.getElementById(id);
                let trNode = node.parentElement.parentElement;
                let _fileInfoSeq = trNode.children[0].children[0].getAttribute('value');

                let result = $.fileDelete({
                    contextPath: _contextPath,
                    data: JSON.stringify({fileInfoSeq: _fileInfoSeq}),
                    alarm: _alarm
                });

                if (result) {
                    deleteTr(trNode);
                }
            };

            let deleteTr = function (trNode) {
                if (trNode == undefined) return;
                trNode.remove();
            }

            let _alarm = function (errThrown) {
                alert(errThrown);
            }

            $(document).ready(function () {
                let _contextPath = $.getContextPath();

                $('#btnFileUpload').on('click', function (fileInfo) {
                    let _description = $('#description').val();
                    $('.uploadForm').fileUpload({
                        contextPath: _contextPath + "/fileUpload",
                        description: _description,
                        addTr: trAdd
                    });
                });
            });
        </script>
    </stripes:layout-component>

</stripes:layout-render>