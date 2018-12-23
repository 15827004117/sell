<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>卖家订单列表</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <#-- 表格 -->
        <div class="col-md-12 column">
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>订单ID</th>
                    <th>姓名</th>
                    <th>手机号</th>
                    <th>地址</th>
                    <th>金额</th>
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
                    <td>详情</td>
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
                        <li ><a href="http://localhost:8080/sell/seller/order/list?page=${curPage - 1}&size=5">下一页</a></li>
                    </#if>
            </ul>
        </div>
    </div>
</div>
</body>
</html>