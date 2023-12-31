<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@include file="../navBar.jsp"%>
    <title>게시글 목록</title>
</head>
<body>
<div class="container">
    <h2>게시글 목록</h2>
    <table class="board_list">
        <colgroup>
            <col width="15%"/>
            <col width="*"/>
            <col width="15%"/>
            <col width="20%"/>
        </colgroup>
        <thead>
        <tr>
            <th scope="col">글번호</th>
            <th scope="col">제목</th>
            <th scope="col">조회수</th>
            <th scope="col">작성일</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${not empty list}">
                <c:forEach items="${list}" var="listItem">
                    <tr>
                        <td>${listItem.boardIdx}</td>
                        <td class="title">
                            <a href="/board/openBoardDetail.do?boardIdx=${listItem.boardIdx}&nowPage=${pageDto.nowPage}">
                                ${listItem.title}
                            </a>
                        </td>
                        <td>${listItem.hitCnt}</td>
                        <td>${listItem.createdDatetime}</td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="4">조회된 결과가 없습니다.</td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
    <a href="/board/openBoardWrite.do" class="btn">글 쓰기</a>
    <div class="page">
        <a href="/board/openBoardList.do?nowPage=1"> << </a>
        <a href="/board/openBoardList.do?nowPage=${pageDto.prePage}"> < </a>
        <c:forEach begin="${pageDto.startPage}" end="${pageDto.endPage}" step="1" var="page">
            <a href="/board/openBoardList.do?nowPage=${page}"
               class="<c:if test="${page eq pageDto.nowPage}">active</c:if>">${page}</a>
        </c:forEach>
        <a href="/board/openBoardList.do?nowPage=${pageDto.nextPage}"> > </a>
        <a href="/board/openBoardList.do?nowPage=${pageDto.totalPage}"> >> </a>
    </div>

</div>
<script>
    window.onload = function () {
        let trList = document.querySelector("tbody").querySelectorAll("tr")

        for (item of trList) {
            const aTag = item.querySelector("a");
            item.addEventListener("click", function (e) {
                aTag.click();
            });
        }
        ;
    }

</script>
</body>
</html>