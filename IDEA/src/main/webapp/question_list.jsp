<%-- This jsp file is used for loadding question list of 'Q&A' page. --%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>

<%
    request.setCharacterEncoding("utf-8");
    if (request.getParameter("classid") == null)
        response.sendRedirect("index.jsp");
    String classid = request.getParameter("classid");
%>

<div class="d-flex flex-column align-items-stretch flex-shrink-0 bg-white">
    <div class="d-flex flex-shrink-0 p-3 link-dark text-decoration-none border-bottom">
        <span class="fs-5 fw-semibold flex-grow-1">Questions</span>
        <button id="ask_btn" class="btn btn-primary" type="submit">Ask</button>
        <script>
            document.getElementById("ask_btn").addEventListener("click", function () {
                $('#main').load('edit_question.jsp?classid=<%=classid%>');
            })
        </script>
    </div>
    <div class="list-group list-group-flush border-bottom scrollarea">
        <a href="#" class="list-group-item list-group-item-action py-2 lh-tight" style="height: 100px;">
            <div class="row">
                <strong class="mb-1 col-10 text-truncate">What is 'Let's Discuss'? padding padding
                    padding
                    padding padding padding padding padding </strong>
                <small class="col-2 p-0">6/19/21</small>
            </div>
            <div class="row overflow-hidden" style="height: 60px;">
                <div class="col-10 mb-2 small text-wrap text-truncate">Some placeholder content in a
                    paragraph below the heading and
                    date. padding padding padding padding padding padding padding padding padding
                    padding
                    padding padding padding padding
                </div>
                <div class="col-2 p-0" style="height: 60px;">
                    <!-- if question -->
                    <i class="fa fa-question-circle fa-lg" aria-hidden="true"></i>
                    <!-- if documentation -->
                    <!-- <i class="fa fa-file-text fa-lg" aria-hidden="true"></i> -->
                    <span class="badge badge-secondary">5</span>
                    <div>
                        <span class="badge badge-success p-1" data-toggle="tooltip"
                            title="Good Question">GQ</span>
                        <span class="badge badge-danger p-1" data-toggle="tooltip"
                            title="Hot Question">HQ</span>
                        <span class="badge badge-warning p-1" data-toggle="tooltip"
                            title="Instructor Answer">IA</span>
                        <span class="badge badge-info p-1" data-toggle="tooltip"
                            title="Instructor Question">IQ</span>
                    </div>
                </div>
            </div>
        </a>
        <a href="#" class="list-group-item list-group-item-action py-2 lh-tight" style="height: 100px;">
            <div class="row">
                <strong class="mb-1 col-10 text-truncate">What is 'Let's Discuss'? padding padding
                    padding
                    padding padding padding padding padding </strong>
                <small class="col-2 p-0">6/19/21</small>
            </div>
            <div class="row overflow-hidden" style="height: 60px;">
                <div class="col-10 mb-2 small text-wrap text-truncate">Some placeholder content in a
                    paragraph below the heading and
                    date. padding padding padding padding padding padding padding padding padding
                    padding
                    padding padding padding padding
                </div>
                <div class="col-2 p-0" style="height: 60px;">
                    <!-- if question -->
                    <i class="fa fa-question-circle fa-lg" aria-hidden="true"></i>
                    <!-- if documentation -->
                    <!-- <i class="fa fa-file-text fa-lg" aria-hidden="true"></i> -->
                    <span class="badge badge-secondary">5</span>
                    <div>
                        <span class="badge badge-success p-1" data-toggle="tooltip"
                            title="Good Question">GQ</span>
                        <span class="badge badge-danger p-1" data-toggle="tooltip"
                            title="Hot Question">HQ</span>
                    </div>
                </div>
            </div>
        </a>
    </div>
</div>