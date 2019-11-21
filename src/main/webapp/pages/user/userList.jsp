<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html style="width:100%;height:100%;overflow:hidden">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户管理-五常优质水稻溯源监管平台</title>
    <% String APP_PATH = request.getContextPath(); %>
    <link rel="stylesheet" type="text/css" href="<%=APP_PATH %>/style/index.css">
    <link rel="stylesheet" type="text/css" href="<%=APP_PATH %>/js/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=APP_PATH %>/js/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=APP_PATH %>/js/themes/color.css">
    <script type="text/javascript" src="<%=APP_PATH %>/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=APP_PATH %>/js/common.js"></script>
    <script type="text/javascript" src="<%=APP_PATH %>/js/form2js.js"></script>
    <script type="text/javascript" src="<%=APP_PATH %>/js/json2.js"></script>
    <script type="text/javascript" src="<%=APP_PATH %>/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=APP_PATH %>/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=APP_PATH %>/js/ajaxfileupload.js"></script>
</head>
<body class="easyui-layout">
<div region="center" border="false" style="padding:5px;">
    <fieldset id="queryBlock" class="fieldset_common_style">
        <form id="userform" name="userform" method="post" action="<%=APP_PATH %>/user/list">
            <table class="table_common_style">
                <tr>
                    <td class="table_common_td_label_query_style">用户名：</td>
                    <td class="table_common_td_txt_query_style">
                        <input name="userID" value="${queryUserMap.get('userId')}" class="easyui-textbox" style="width:200px;height:25px;">
                        <input type='hidden' id="pageTotal" name="pageTotal" value="${users.total}"/>
                        <input type="hidden" id="page" name="page" value="${users.pageNum}">
                        <input type="hidden" id="pageSize" name="pageSize" value="${users.pageSize}">
                    </td>
                    <td class="table_common_td_label_query_style">用户姓名：</td>
                    <td class="table_common_td_txt_query_style">
                        <input name="userName" value="${queryUserMap.get('userName')}" class="easyui-textbox" style="width:200px;height:25px">
                    </td>
                </tr>
                <tr>
                    <td class="table_common_td_label_query_style">用户类型：</td>
                    <td class="table_common_td_txt_query_style">


                        <select id="type" name="type" class="easyui-combobox" style="width:200px;height:25px"
                                data-options="editable:true">
                            <option value="" selected>-=请选择=-</option>
                            <c:forEach var="ut" items="${userTypes}">
                                <option <c:if test="${ut.get('CodeCode')==queryUserMap.get('type')}">selected="selected"</c:if> value="${ut.get("CodeCode")}">${ut.get("CodeValue")}</option>
                            </c:forEach>


                        </select>

                        <script type="text/javascript">

                            $(document).ready(function () {

                                $('#type').combobox({

                                    onChange: function (newValue, oldValue) {

                                        return false;

                                    }

                                });


                            });

                        </script>

                    </td>
                    <td colspan="2" align="right" valign="bottom">
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="onSubmit()">
                            <span class="l-btn-left"><span
                                    class="l-btn-text icon-search l-btn-icon-left">查询</span></span></a>
                    </td>
                </tr>
            </table>
        </form>
    </fieldset>
    <fieldset id="toolBlock" class="fieldset_common_style">
        <table>
            <tr>
                <td>
                    <a href="#" class="easyui-linkbutton" onclick="detail();">
							<span class="l-btn-left">
								<span class="l-btn-text icon-view l-btn-icon-left">查看</span>
							</span>
                    </a>
                    <a href="#" class="easyui-linkbutton" onclick="addUser();">
							<span class="l-btn-left">
								<span class="l-btn-text icon-add l-btn-icon-left">添加</span>
							</span>
                    </a>
                    <a href="#" class="easyui-linkbutton" onclick="editUser();">
							<span class="l-btn-left">
								<span class="l-btn-text icon-edit l-btn-icon-left">修改</span>
							</span>
                    </a>
                    <a href="#" class="easyui-linkbutton" onclick="deleteUser();">
							<span class="l-btn-left">
								<span class="l-btn-text icon-remove l-btn-icon-left">删除</span>
							</span>
                    </a>
                    <a href="#" class="easyui-linkbutton" onclick="resetPassword();">
							<span class="l-btn-left">
								<span class="l-btn-text icon-lock l-btn-icon-left">重置密码</span>
							</span>
                    </a>
                    <a href="#" class="easyui-linkbutton" onclick="setLoginStatus('01');">
							<span class="l-btn-left">
								<span class="l-btn-text icon-redo l-btn-icon-left">启用</span>
							</span>
                    </a>
                    <a href="#" class="easyui-linkbutton" onclick="setLoginStatus('02');">
							<span class="l-btn-left">
								<span class="l-btn-text icon-cancel l-btn-icon-left">禁用</span>
							</span>
                    </a>
                </td>
            </tr>
        </table>
    </fieldset>
    <table id="data" class="easyui-datagrid" striped="true" checkbox="true" singleSelect="true"
           pageSize="${users.pageSize}"
           pageNumber="${users.pageNum}">
        <thead>
        <tr>
            <th field="id" width="150" checkbox="true" align="center"></th>
            <th field="userId" width="150" align="center">用户名</th>
            <th field="realName" width="150" align="center">姓名</th>
            <th field="userType" width="150" align="center">用户类型</th>
            <th field="companyCode" width="300" align="center">单位名称</th>
            <th field="tel" width="200" align="center">手机</th>
            <th field="email" width="200" align="center">邮箱</th>
            <th field="loginStatus" width="150" align="center">状态</th>
            <th field="remark" width="150" align="center">备注</th>
        </tr>
        </thead>
        <tbody>


        <c:forEach items="${users.list}" var="us">

            <tr>
                <td height="30" align="center" nowrap>${us.id}</td>
                <td height="30" align="center" nowrap>${us.userID}</td>
                <td height="30" align="center" nowrap>${us.userName}</td>
                <td height="30" align="center" nowrap><span>${us.userType}</span></td>
                <td height="30" align="center" nowrap>${us.companyName}</td>
                <td height="30" align="center" nowrap>${us.tel}</td>
                <td height="30" align="center" nowrap>${us.email}</td>
                <td height="30" align="center" nowrap><span>${us.loginStatus}</span></td>
                <td height="30" align="center" nowrap>${us.remark}</td>
            </tr>

        </c:forEach>

        </tbody>
    </table>
</div>
<div id="addDialog"></div>
<script type="text/javascript">
    var winHeight = $(window).height();
    var queryBlockHeight = $("#queryBlock").height();
    var toolBlock = $("#toolBlock").height();

    $(document).ready(function () {
        $("#data").datagrid({
            height: winHeight - queryBlockHeight - toolBlock - 45,
            pagination: true,
            rownumbers: true,
            fitColumns: false,
            //fit: true,
            pageList: [5, 10, 15, 20, 25],
            pagePosition: "top"
        });

        var pagger = $('#data').datagrid('getPager');
        $(pagger).pagination({
            total: $("#pageTotal").val(),
            pageNumber: parseInt($("#page").val()),
            showPageList: true,
            showRefresh: false,
            onSelectPage: function (pageNumber, pageSize) {
                $('#page').val(pageNumber);
                $('#pageSize').val(pageSize);
                onSubmit();
            }
        });
    });

    function addUser() {
        $('#addDialog').dialog({
            title: '添加新用户',
            width: 580,
            height: 310,
            closed: false,
            cache: false,
            href: '<%=APP_PATH%>/user/toAddUserPage',
            modal: true
        });
    }

    function editUser() {
        var rows = $('#data').datagrid('getSelections');
        var length = rows.length;
        if (length == 0) {
            $.messager.alert('警告', '至少选择一个用户。', 'warning');
            return false;
        }
        if (length > 1) {
            $.messager.alert('警告', '只能选择一个用户。', 'warning');
            return false;
        }
        var id = rows[0].id;
        if (rows[0].userId == 'admin') {
            $.messager.alert('警告', '管理员账号不能修改。', 'warning');
            return false;
        }
        $('#addDialog').dialog({
            title: '修改用户信息',
            width: 580,
            height: 310,
            closed: false,
            cache: false,
            href: '<%=APP_PATH %>/user/toUpdateUserPage?id=' + id,
            modal: true
        });
    }

    function resetPassword() {
        var rows = $('#data').datagrid('getSelections');
        var length = rows.length;
        if (length == 0) {
            $.messager.alert('警告', '至少选择一个用户。', 'warning');
            return false;
        }
        if (length > 1) {
            $.messager.alert('警告', '只能选择一个用户。', 'warning');
            return false;
        }
        var id = rows[0].id;
        $('#addDialog').dialog({
            title: '重置密码',
            width: 320,
            height: 200,
            closed: false,
            cache: false,
            href: '<%=APP_PATH%>/user/editPass?id=' + id + "",
            modal: true
        });
    }

    function detail() {
        var rows = $('#data').datagrid('getSelections');
        var length = rows.length;
        if (length == 0) {
            $.messager.alert('警告', '至少选择一个用户。', 'warning');
            return false;
        }
        if (length > 1) {
            $.messager.alert('警告', '只能选择一个用户。', 'warning');
            return false;
        }
        var id = rows[0].id;
        $('#addDialog').dialog({
            title: '查看用户信息',
            width: 550,
            height: 290,
            closed: false,
            cache: false,
            href: '<%=APP_PATH %>/user/detail?id=' + id,
            modal: true
        });
    }

    function deleteUser() {
        var ids = [];
        var rows = $('#data').datagrid('getSelections');
        var length = rows.length;
        for (var i = 0; i < rows.length; i++) ids.push(rows[i].id);
        if (ids.length == 0) {
            $.messager.alert('警告', '至少选择一个用户。', 'warning');
            return false;
        }
        if (rows[0].userId == 'admin') {
            $.messager.alert('警告', '管理员账号不能删。', 'warning');
            return false;
        }
        $.messager.confirm("确认", "您确认删除选定的记录吗？", function (deleteAction) {
            if (deleteAction) {
                showLoading();

                $.get('<%=APP_PATH%>/user/delete', "ids=" + ids, function (e) {
                    hideLoading();
                    if (200 == e.status) {
                        $.messager.alert('提示', '操作成功。', 'info');
                        $('#userform').submit();
                    } else
                        $.messager.alert('错误', e.msg, 'error');
                });
            }
        });
    }

    function setLoginStatus(loginStatus) {
        var rows = $('#data').datagrid('getSelections');
        var length = rows.length;
        if (length == 0) {
            $.messager.alert('警告', '至少选择一个用户。', 'warning');
            return false;
        }
        if (length > 1) {
            $.messager.alert('警告', '管理员账号不能删。', 'warning');
            return false;
        }
        if (rows[0].userId == 'admin') {
            $.messager.alert('警告', '管理员账号不支持此操作。', 'warning');
            return false;
        }
        var id = rows[0].id;
        showLoading();
        Public.ajaxGet('<%=APP_PATH %>/user/setLoginStatus', {loginStatus: loginStatus, id: id}, function (e) {
            hideLoading();
            if (200 == e.status) {
                $.messager.alert('提示', '操作成功。', 'info');
                $('#userform').submit();
            } else
                $.messager.alert('错误', e.msg, 'error');
        });
    }

    function onSubmit() {
        showLoading();
        $('#userform').submit();
        hideLoading();
    }
</script>
</body>
</html>