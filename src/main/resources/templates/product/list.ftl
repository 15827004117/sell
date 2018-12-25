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
                            <th>商品编号</th>
                            <th>名称</th>
                            <th>图片</th>
                            <th>单价</th>
                            <th>库存</th>
                            <th>描述</th>
                            <th>类目</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                <#list productInfoPage.content as dto>
                <tr>
                    <td>${dto.productId}</td>
                    <td>${dto.productName}</td>
                    <td><img src="${dto.productIcon}" height="100" width="100"></td>
                    <td>${dto.productPrice}</td>
                    <td>${dto.productStock}</td>
                    <td>${dto.productDescription}</td>
                    <td>${dto.getProductSatatsEnum().message}</td>
                    <td>${dto.createTime}</td>
                    <td>${(dto.updateTime)!''}</td>
                    <td><a href="/sell/seller/product/index?orderId=${dto.productId}">修改</td>
                    <td><#if dto.productStatus == 0 >
                            <a href="/sell/seller/product/off_sale?productId=${dto.productId}">下架</a>
                        <#elseif dto.productStatus == 1 >
                            <a href="/sell/seller/product/on_sale?productId=${dto.productId}">上架</a>
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
                <li><a href="http://localhost:8080/sell/seller/product/list?page=${curPage - 1}&size=5">上一页</a></li>
                </#if>

                    <#--当前页-->
                <#list 1..productInfoPage.getTotalPages() as index>
                    <#if curPage == index>
                        <li class="disabled"><a href="#">${index}</a></li>
                    <#else>
                        <li ><a href="http://localhost:8080/sell/seller/product/list?page=${index}&size=5">${index}</a></li>
                    </#if>
                </#list>

                    <#--下一页-->
                    <#if curPage gte productInfoPage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li ><a href="http://localhost:8080/sell/seller/product/list?page=${curPage + 1}&size=5">下一页</a></li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>