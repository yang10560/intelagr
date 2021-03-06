<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html style="width:100%;height:100%;overflow:hidden">
<head>
    <title>添加角色-五常优质水稻溯源监管平台</title>
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
    <fieldset class="fieldset_common_style">
        <form id="addroleform" action="<%=APP_PATH %>/role/updateRole" class="easyui-form" method="post"
              data-options="novalidate:true">
            <table class="table_common_style">
                <tr>
                    <input type="hidden" name="id" value="${updateRole.id}">
                    <input type="hidden" name="oldRoleCode" value="${updateRole.roleCode}">
                    <td class="table_common_td_label_style">角色编号:</td>
                    <td class="table_common_td_txt_style">
                        <input value="${updateRole.roleCode}" class="easyui-textbox" type="text" id="roleCode" name="roleCode"
                               style="width:200px;"></input>
                        <span class="span_common_mustinput_style">*</span>
                    </td>
                </tr>
                <tr>
                    <td class="table_common_td_label_style">角色名:</td>
                    <td class="table_common_td_txt_style">
                        <input value="${updateRole.roleName}" class="easyui-textbox" type="text" id="roleName" name="roleName"
                               style="width:200px;"></input>
                        <span class="span_common_mustinput_style">*</span>
                    </td>
                </tr>
                <tr>
                    <td class="table_common_td_label_comment_style">备注:</td>
                    <td class="table_common_td_txt_style">
                        <textarea class="easyui-textbox" rows="2" id="remark" name="remark"
                                  style="width:200px;height:60px" data-options="multiline:true">${updateRole.remark}</textarea>
                    </td>
                </tr>
                <tr height="50">
                    <td colspan="2" align="center">
                        <a href="#" class="easyui-linkbutton" onclick="addForm_check()"
                           data-options="iconCls:'icon-save'">修改</a>
                        <a href="#" class="easyui-linkbutton" style="margin-left:40px;" onclick="closeDialog()"
                           data-options="iconCls:'icon-cancel'">取消</a>
                    </td>
                </tr>
            </table>
        </form>
    </fieldset>
</div>
<script type="text/javascript">
    function addForm_check() {
        var roleCode = $.trim($("#roleCode").val());
        var roleName = $.trim($("#roleName").val());

        if (roleCode == '') {
            $.messager.alert('警告', '角色编号不能为空。', 'warning');
            return false;
        }
        if (roleName == '') {
            $.messager.alert('警告', '角色名不能为空。', 'warning');
            return false;
        }
        var a = $('#addroleform').toObject();
        showLoading();
        Public.ajaxPost('updateRole', JSON.stringify(a), function (e) {
            hideLoading();
            if (200 == e.status) {
                $.messager.alert('提示', '操作成功。', 'info');
                $('#addDialog').window('close');
                $('#roleform').submit();
            } else {
                parent.parent.Public.tips({
                    type: 1,
                    content: "失败！" + e.msg
                });
            }
        });
    }

    function closeDialog() {
        $('#addDialog').dialog('close');
    }
</script>
</body>
</html>