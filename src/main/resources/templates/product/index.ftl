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
                        <label>名称</label><input type="text" class="form-control" value="${productInfo.get().productName}"/>
                    </div>
                    <#---->
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="inputPassword3"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <div class="checkbox">
                                <label><input type="checkbox"/>Remember me</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Sign in</button>
                        </div>
                    </div>
                </form>
                <button type="button" class="btn btn-default btn-danger">按钮</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>