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
                <div class="col-md-12 column">
                <#--订单信息-->
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>订单编号</th>
                            <th>订单总金额</th>
                        </tr>
                        </thead>

                        <tbody>
                        <tr>
                            <td>${orderDTO.orderId}</td>
                            <td>${orderDTO.orderAmount}</td>
                        </tr>
                        </tbody>
                    </table>

                <#--订单详情数据-->
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>商品编号</th>
                            <th>商品名称</th>
                            <th>商品单价</th>
                            <th>商品数量</th>
                            <th>商品总额</th>
                        </tr>
                        </thead>

                        <tbody>
                <#list orderDTO.orderDetailList as detail>
                <tr>
                    <td>${detail.productId}</td>
                    <td>${detail.productName}</td>
                    <td>${detail.productPrice}</td>
                    <td>${detail.productQuantity}</td>
                    <td>${detail.productQuantity * detail.productPrice}</td>
                </tr>
                </#list>
                        </tbody>
                    </table>

                <#--完结、取消订单-->
                    <div class="container">
                        <div class="row clearfix">
                            <div class="col-md-12 column">
                        <#if orderDTO.getOrderStatusEnum().code == 0>
                            <a href="/sell/seller/order/finish?orderId=${orderDTO.orderId}" type="button"
                               class="btn btn-default btn-primary">完结订单</a>
                            <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}" type="button"
                               class="btn btn-default btn-danger">取消订单</a>
                        </#if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>