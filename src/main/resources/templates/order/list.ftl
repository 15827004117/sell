<html lang="en">
<#include "../common/head.ftl">
<body>
<div id="wrapper" class="toggled">
    <#--边栏sidebar-->
    <#include "../common/nav.ftl">
    <#--主要内容content-->
    <div id="page-content-wrapper">
        <#--表格-->
        <div class="container">
            <div class="row clearfix">
            <#-- 表格 -->
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>订单编号</th>
                            <th>买家姓名</th>
                            <th>买家手机</th>
                            <th>买家地址</th>
                            <th>订单金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                <#list orderDTOPage.content as dto>
                <tr>
                    <td>${dto.orderId}</td>
                    <td>${dto.buyerName}</td>
                    <td>${dto.buyerPhone}</td>
                    <td>${dto.buyerAddress}</td>
                    <td>${dto.orderAmount}</td>
                    <td>${dto.getOrderStatusEnum().message}</td>
                    <td>${dto.getPayStatusEnum().message}</td>
                    <td>${dto.createTime}</td>
                    <td><a href="/sell/seller/order/detail?orderId=${dto.orderId}">详情</td>
                    <td>
                        <#if dto.getOrderStatusEnum().code ==0>
                            <a href="/sell/seller/order/cancel?orderId=${dto.orderId}">取消</a>
                        </#if>
                    </td>
                </tr>
                </#list>

                        </tbody>
                    </table>
                </div>
            <#-- 分页 -->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                    <#--上一页-->
                <#if curPage lte 1>
                <li class="disabled"><a href="#">上一页</a></li>
                <#else>
                <li><a href="http://localhost:8080/sell/seller/order/list?page=${curPage - 1}&size=5">上一页</a></li>
                </#if>

                    <#--当前页-->
                <#list 1..orderDTOPage.getTotalPages() as index>
                    <#if curPage == index>
                        <li class="disabled"><a href="#">${index}</a></li>
                    <#else>
                        <li ><a href="http://localhost:8080/sell/seller/order/list?page=${index}&size=5">${index}</a></li>
                    </#if>
                </#list>

                    <#--下一页-->
                    <#if curPage gte orderDTOPage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li ><a href="http://localhost:8080/sell/seller/order/list?page=${curPage + 1}&size=5">下一页</a></li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
    <#--消息弹窗-->
    <div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    提醒
                </h4>
            </div>
            <div class="modal-body">
                您有新的订单！
            </div>
            <div class="modal-footer">
                <button type="button" onclick="javascritp:document.getElementById('notice').pause();" class="btn btn-default">关闭</button>
                <button onclick="location.reload()" type="button" class="btn btn-primary">查看新订单</button>
            </div>
        </div>
    </div>
</div>
    <#--播放音乐-->
    <audio id="notice" loop="loop">
        <source src="/sell/mp3/song.mp3" type="audio/mpeg" />
    </audio>
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<#--websocket-->
<script>
    var websocket = null;
    if('WebSocket' in window) {
        websocket = new WebSocket('ws://localhost:8080/sell/webSocket');
    }else {
        alert("该浏览器不支持WebSocket");
    }

    websocket.onopen = function (event) {
        console.log('websocket建立连接');
    }

    websocket.onclose = function (event) {
        console.log('websocket连接关闭');
    }

    websocket.onmessage = function (event) {
        console.log('收到消息:' + event.data)
        // 弹窗提醒
        $('#myModal').modal('show');
        // 播放音乐
        document.getElementById('notice').play();
    }

    websocket.onerror = function () {
        alert('websocket通信发生错误！');
    }

    window.onbeforeunload = function () {
        websocket.close();
    }

</script>
</body>
</html>