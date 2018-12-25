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
                            <th>类目编号</th>
                            <th>类目姓名</th>
                            <th>类型</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="1">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#list categoryList as cate>
                            <tr>
                                <td>${cate.categoryId}</td>
                                <td>${cate.categoryName}</td>
                                <td>${cate.categoryType}</td>
                                <td>${cate.createTime}</td>
                                <td>${cate.updateTime}</td>
                                <td><a href="/sell/seller/category/index?categoryId=${cate.categoryId}">修改</td>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>