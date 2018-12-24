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
                <form class="form-horizontal" role="form">
                <#--名称-->
                    <div class="form-group">
                        <label>名称</label><input type="text" class="form-control" name="productName"
                                                value="${(productInfo.productName)!''}"/>
                    </div>
                <#--价格-->
                    <div class="form-group">
                        <label>价格</label><input type="text" class="form-control" name="productPrice"
                                                value="${(productInfo.productPrice)!''}"/>
                    </div>
                <#--库存-->
                    <div class="form-group">
                        <label>库存</label><input type="text" class="form-control" name="productStock"
                                                value="${(productInfo.productStock)!''}"/>
                    </div>
                <#--描述-->
                    <div class="form-group">
                        <label>描述</label><input type="text" class="form-control" name="productDescription"
                                                value="${(productInfo.productDescription)!''}"/>
                    </div>
                <#--图片-->
                    <div class="form-group">
                        <label>图片</label>
                        <img src="${(productInfo.productIcon)!''}" name="productIcon">
                    </div>
                <#--类目-->
                    <div class="form-group">
                        <label>类目</label>

                    </div>
                </form>
                <button type="button" class="btn btn-default btn-danger">按钮</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>