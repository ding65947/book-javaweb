<%--
  Created by IntelliJ IDEA.
  User: ding65947
  Date: 2022/9/7
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--分页条的开始--%>
<div id="page_nav">
    <%--大于首页才显示--%>
    <c:if test="${requestScope.page.pageNo>1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>

    <%--页码输出的开始--%>
    <c:choose>
        <c:when test="${requestScope.page.pageTotal<=5}">
            <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
                <c:if test="${i==requestScope.page.pageNo}">
                    【${i}】
                </c:if>
                <c:if test="${i!=requestScope.page.pageNo}">
                    <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                </c:if>

            </c:forEach>
        </c:when>

        <c:when test="${requestScope.page.pageTotal>5}">
            <c:choose>
                <%--当前页码为前面3个：1，2，3的情况 页码范围是1-5--%>
                <c:when test="${requestScope.page.pageNo<=3}">
                    <c:forEach begin="1" end="5" var="i">
                        <c:if test="${i==requestScope.page.pageNo}">
                            【${i}】
                        </c:if>
                        <c:if test="${i!=requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                        </c:if>

                    </c:forEach>
                </c:when>

                <c:when test="${requestScope.page.pageNo>=requestScope.page.pageTotal-3}">
                    <%--当前页码是最后3个，8，9，10，页码范围是：总页码减4-总页码--%>
                    <c:forEach begin="${requestScope.page.pageTotal-4}" end="${requestScope.page.pageTotal}" var="i">
                        <c:if test="${i==requestScope.page.pageNo}">
                            【${i}】
                        </c:if>
                        <c:if test="${i!=requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:when>


                <c:otherwise>
                    <c:forEach begin="${requestScope.page.pageNo-2}" end="${requestScope.page.pageNo+2}" var="i">
                        <c:if test="${i==requestScope.page.pageNo}">
                            【${i}】
                        </c:if>
                        <c:if test="${i!=requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:otherwise>

            </c:choose>
        </c:when>
    </c:choose>
    <%--页码输出的结束--%>

    <%--如果是最后一页，不显示下一页--%>
    <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">
    <script type="text/javascript">
        $(function () {
            //跳到指定的页码
            $("#searchPageBtn").click(function () {
                var pageNo=$("#pn_input").val();

                //javascript中提供了一个location地址栏对象
                //它有一个属性叫href.它可以获取浏览器地址栏中的地址
                //href属性可读可写
                location.href="${pageScope.basePath}${requestScope.page.url}&pageNo="+pageNo;
            })

        })
    </script>
</div>
<%--分页条的结束--%>
