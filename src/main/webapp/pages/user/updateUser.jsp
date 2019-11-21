<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html style="width:100%;height:100%;overflow:hidden">
<head>
    <% String APP_PATH = request.getContextPath(); %>
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
<div region="center" border="false" style="padding:5px;">
    <fieldset class="fieldset_common_style">
        <form id="addUserFrom" class="easyui-form" method="post" data-options="novalidate:true">
            <table class="table_common_style">
                <tr>
                    <td class="table_common_td_label_style">用户名：</td>
                    <td class="table_common_td_txt_style">
                        <input type="hidden" name="id" value="${updateUser.id}">
                        <input value="${updateUser.userID}" class="easyui-textbox" type="text" id="userID" name="userID"
                               style="width:150px;"></input>
                        <span class="span_common_mustinput_style">*</span>
                    </td>
                    <td class="table_common_td_label_style">密码：</td>
                    <td class="table_common_td_txt_style">
                        <input  type="password" class="easyui-textbox" type="text" id="password" name="password"
                               style="width:150px;"></input>
                        <span class="span_common_mustinput_style">*</span>
                    </td>
                </tr>
                <tr>
                    <td class="table_common_td_label_style">姓名：</td>
                    <td class="table_common_td_txt_style">
                        <input value="${updateUser.userName}" class="easyui-textbox" type="text" id="userName" name="userName"
                               style="width:150px;"></input>
                        <span class="span_common_mustinput_style">*</span>
                    </td>
                    <td class="table_common_td_label_style">用户类型：</td>
                    <td class="table_common_td_txt_style">


                        <select id="userType" name="userType" class="easyui-combobox" style="width:150px;height:25px"
                                data-options="editable:true">
                            <option value="" selected>-=请选择=-</option>
                            <c:forEach var="ut" items="${userTypes}">
                                <option <c:if test="${ut.get('CodeCode') == updateUser.userType}">selected="selected"</c:if>  value="${ut.get("CodeCode")}">${ut.get("CodeValue")}</option>
                            </c:forEach>

                        </select>

                        <script type="text/javascript">

                            $(document).ready(function () {

                                $('#userType').combobox({

                                    onChange: function (newValue, oldValue) {

                                        onUserTypeChange();

                                    }

                                });


                            });

                        </script>

                        <span class="span_common_mustinput_style">*</span>
                    </td>
                </tr>
                <tr>
                    <td class="table_common_td_label_style">单位名称：</td>
                    <td class="table_common_td_txt_style">


                        <select id="companyCode" name="companyCode" class="easyui-combobox"
                                style="width:150px;height:25px" data-options="editable:true">
                            <option value="" selected>-=请选择=-</option>

                            <c:forEach items="${compnays}" var="cp">
                              <option  <c:if test="${cp.companyCode == updateUser.companyCode}">selected="selected"</c:if> value="${cp.companyCode}">${cp.companyName}</option>
                            </c:forEach>
                        </select>

                        <input type="hidden" id="companyCode_companyName" name="companyName" value="">

                        <script type="text/javascript">

                            $(document).ready(function () {

                                $('#companyCode_companyName').val($('#companyCode option:selected').text());

                                $('#companyCode').combobox({

                                    onChange: function (newValue, oldValue) {

                                        var ops = document.getElementById('companyCode').options;

                                        for (var i = 0; i < ops.length; i++) {

                                            if (ops[i].value == newValue) {

                                                $('#companyCode_companyName').val(ops[i].text);

                                                break;

                                            }

                                        }

                                        return false;
                                        ;

                                    }

                                });


                                $('#companyCode').combobox('textbox').bind('focus', function () {
                                    var value = $('#companyCode').combobox('getValue');
                                    var opts = $('#companyCode').combobox('getData');
                                    if (value != '') {
                                        var findFlag = false;
                                        for (var i = 0; i < opts.length; i++) {
                                            if (opts[i].value == value) {
                                                findFlag = true;
                                                break;
                                            }
                                        }
                                        if (!findFlag) {
                                            value = '';
                                            $('#companyCode').combobox('setValue', '');
                                        }
                                    }
                                    if (value == '') {
                                        $('#companyCode').combobox('setText', '');
                                    }
                                });
                                $('#companyCode').combobox('textbox').bind('blur', function () {
                                    var value = $('#companyCode').combobox('getValue');
                                    if (value == '') {
                                        $('#companyCode').combobox('setText', '-=请选择=-');
                                    }
                                });
                            });

                        </script>

                    </td>
                    <td class="table_common_td_label_style">联系方式：</td>
                    <td class="table_common_td_txt_style">
                        <input value="${updateUser.tel}" class="easyui-textbox" type="text" id="tel" name="tel" style="width:150px;"></input>
                        <span class="span_common_mustinput_style">*</span>
                    </td>
                </tr>
                <tr>
                    <td class="table_common_td_label_style">邮箱：</td>
                    <td class="table_common_td_txt_style">
                        <input value="${updateUser.email}" class="easyui-textbox" type="text" id="email" name="email" style="width:150px;"></input>
                        <span class="span_common_mustinput_style">*</span>
                    </td>
                    <td class="table_common_td_label_style">角色：
                    </td>
                    <td class="table_common_td_txt_style">
                        <input class="easyui-combobox" id="role" name="role" style="width:150px"
                               data-options='valueField:"roleCode",textField:"text",data:${jsonDate},multiple:true,panelHeight:"auto"'
                               editable="false">


                        <span class="span_common_mustinput_style">*</span>
                    </td>
                </tr>
                <tr>
                    <td class="table_common_td_label_comment_style">备注：</td>
                    <td colspan="3" class="table_common_td_txt_style">
                        <textarea  class="easyui-textbox" rows="2" id="remark" name="remark"
                                  style="width:420px;height:60px" data-options="multiline:true">${updateUser.remark}</textarea>
                    </td>
                </tr>
                <tr height="30">
                    <td colspan="4" align="center">
                        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="formCheck()"
                           data-options="iconCls:'icon-save'">修改</a>
                        <a href="javascript:void(0)" style="margin-left:50px;" class="easyui-linkbutton"
                           style="margin-left:15px;" onclick="closeDialog()" data-options="iconCls:'icon-cancel'">取消</a>
                    </td>
                </tr>
            </table>
        </form>
    </fieldset>
</div>
<script type="text/javascript">
    function onUserTypeChange() {
        var userType = $('#userType').combobox('getValue');
        if (userType == '02') {
            $("#companyCode").combobox("enable");
        } else {
            $("#companyCode").combobox("disable");
        }
    }

    function formCheck() {

        var userID = $.trim($("#userID").val());
        var password = $.trim($("#password").val());
        var userName = $.trim($("#userName").val());
        var userType = $('#userType').combobox('getValue');
        var companyCode = $('#companyCode').combobox('getValue');
        var email = $.trim($("#email").val());
        var tel = $.trim($("#tel").val());
        var role = $('#role').combobox('getValue');

        if (userID == '') {
            $.messager.alert('警告', '用户名不能为空。', 'warning');
            return false;
        }
        if (password == '') {
            $.messager.alert('警告', '密码不能为空。', 'warning');
            return false;
        } else if (password.length < 6) {
            $.messager.alert('警告', '密码至少6位。', 'warning');
            return false;
        }
        if (userName == '') {
            $.messager.alert('警告', '姓名不能为空。', 'warning');
            return false;
        }
        if (userType == '') {
            $.messager.alert('警告', '请选择用户类型。', 'warning');
            return false;
        }
        if (userType == '02' && companyCode == '') {
            $.messager.alert('警告', '请选择企业。', 'warning');
            return false;
        }
        if (userType != '02') {
            $('#companyCode').combobox('setValue', '');
        }
        if (tel == '') {
            $.messager.alert('警告', '联系方式不能为空。', 'warning');
            return false;
        }
        if (!isTel(tel)) {
            $.messager.alert('警告', '请填写正确的联系方式。', 'warning');
            return false;
        }
        if (!isEmail(email)) {
            $.messager.alert('警告', '请填写正确的邮箱。', 'warning');
            return false;
        }
        if (role == '') {
            $.messager.alert('警告', '至少选择一个角色。', 'warning');
            return false;
        }
        var a = $('#addUserFrom').toObject();
        a.role = $('#role').combobox('getValues').toString();
        showLoading();
        Public.ajaxPost('<%=APP_PATH %>/user/updateUser', JSON.stringify(a), function (e) {
            hideLoading();
            if (200 == e.status) {
                $.messager.alert('提示', '操作成功。', 'info');
                $('#addDialog').window('close');
                $('#userform').submit();
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