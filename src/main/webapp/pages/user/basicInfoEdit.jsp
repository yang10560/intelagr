<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html style="width:100%;height:100%;overflow:hidden">
<head>
    <% String APP_PATH = request.getContextPath(); %>
    <meta http-equiv="Content-Type" content="text/html; charset=gbk">
    <title>用户管理-五常优质水稻溯源监管平台</title>
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
<div region="center" border="false" style="padding:0 10px;">
    <form id="fm" name="fm" class="easyui-form" method="post" data-options="novalidate:true">
        <table cellpadding="8" style="text-align:center;" align="center">
            <tr>
                <td height="20px;">&nbsp;</td>
            </tr>
            <tr>
                <td width="30%" align="right">用户姓名:</td>
                <td>
                    <input class="easyui-textbox" type="text" id="userName" name="userName" style="width:200px;"
                           value="${user.userName}"></input>
                    <input type="hidden" name="id" style="width:200px;" value="${user.id}"></input>
                </td>
            </tr>
            <tr>
                <td width="30%" align="right">联系方式:</td>
                <td>
                    <input class="easyui-textbox" type="text" id="tel" name="tel" style="width:200px;"
                           value="${user.tel}"></input>
                </td>
            </tr>
            <tr>
                <td width="30%" align="right">电子邮件:</td>
                <td>
                    <input class="easyui-textbox" type="text" id="email" name="email" style="width:200px;"
                           value="${user.email}"></input>
                </td>
            </tr>
            <tr height="60">
                <td colspan="2" align="center">
                    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="save()"
                       data-options="iconCls:'icon-save'">保存</a>
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
    function save() {
        if ($('#userName').val() == '') {
            $.messager.alert('警告', '用户姓名不能为空！', 'warning');
            return false;
        }
        if ($('#tel').val() == '') {
            $.messager.alert('警告', '联系方式不能为空！', 'warning');
            return false;
        }
        if (!isTel($("#tel").val())) {
            $.messager.alert('警告', '请填写正确的电话号码或者手机号！', 'warning');
            return false;
        }
        if ($('#email').val() == '') {
            $.messager.alert('警告', '电子邮件不能为空！', 'warning');
            return false;
        }
        if (!isEmail($("#email").val())) {
            $.messager.alert('警告', '请填写正确的电子邮件！', 'warning');
            return false;
        }
        var a = $('#fm').toObject();
        Public.ajaxPost('<%=APP_PATH %>/user/baseInfoUpdate', JSON.stringify(a), function (e) {
            if (200 == e.status) {
                $.messager.alert('提示', e.msg, 'info');
            } else {
                $.messager.alert('错误', '操作失败:' + e.msg, 'error');
            }
        });
    }
</script>
</body>
</html>