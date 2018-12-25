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
            <#-- 表单 -->
                <form class="form-horizontal" method="post" action="/sell/seller/category/save">
                <#--名称-->
                    <div class="form-group">
                        <label>名称</label><input type="text" class="form-control" name="categoryName"
                                                value="${(productCategory.categoryName)!''}"/>
                    </div>
                <#--类型-->
                    <div class="form-group">
                        <label>类型</label><input type="text" class="form-control" name="categoryType"
                                                value="${(productCategory.categoryType)!''}"/>
                    </div>
                    <input hidden type="text" name="categoryId" value="${(productCategory.categoryId)!''}">
                    <button type="submit" class="btn btn-default btn-danger">提交</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>