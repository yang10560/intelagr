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
</head>
<body class="easyui-layout">
<div region="center" border="false" style="padding:5px;">
    <form id="editUserFrom" class="easyui-form" method="post" data-options="novalidate:true">
        <fieldset class="fieldset_common_style">
            <table class="table_common_style">
                <tr>
                    <td class="table_common_td_label_style">用户名:</td>
                    <td class="table_common_td_txt_style">
                        <input class="easyui-textbox" type="text" name="userID" style="width:200px;" value="${resetUser.userID}"
                               readonly></input>
                        <input type="hidden" name="id" style="width:200px;" value="${resetUser.id}"></input>
                    </td>
                </tr>
                <tr>
                    <td class="table_common_td_label_style">密码:</td>
                    <td class="table_common_td_txt_style">
                        <input class="easyui-textbox" type="password" name="password" style="width:200px;"
                               value=""></input>
                        <span class="span_common_mustinput_style">*</span>
                    </td>
                </tr>
                <tr height="50">
                    <td colspan="2" align="center">
                        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="formCheck()"
                           data-options="iconCls:'icon-save'">保存</a>
                        <a href="javascript:void(0)" class="easyui-linkbutton" style="margin-left:30px;"
                           onclick="closeDialog()" data-options="iconCls:'icon-cancel'">取消</a>
                    </td>
                </tr>
            </table>
        </fieldset>
    </form>
</div>
<script type="text/javascript">
    function formCheck() {
        var a = $('#editUserFrom').toObject();
        if (a.password == '') {
            $.messager.alert('警告', '密码不能为空。', 'warning');
            return false;
        } else if (a.password.length < 6) {
            $.messager.alert('警告', '密码至少6位。', 'warning');
            return false;
        }
        Public.ajaxPost('resetPass', JSON.stringify(a), function (e) {
            if (200 == e.status) {
                $.messager.alert('提示', '操作成功。', 'info');
                closeDialog();
            }
        });
    }

    function closeDialog() {
        $('#addDialog').dialog('close');
    }

    function form_check() {
        $('#userform').submit();
    }
</script>
</body>
</html>