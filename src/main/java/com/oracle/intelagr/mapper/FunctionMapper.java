package com.oracle.intelagr.mapper;

import com.oracle.intelagr.entity.Function;

import java.util.List;

public interface FunctionMapper {


    /**
     * 所有一级菜单代码 SELECT DISTINCT moduleCode FROM m_function
     * @return
     */
   List<String> getAllFunctionModuleCode();

    /**
     * 根据一级菜单代码取得二级菜单
     * @return
     */
   List<Function> getFunctionByModuleCode(String moduleCode);


    /**
     * select FunctionCode from m_rolefunctionmap where DeleteFlag = 'N' and RoleCode = #{roleCode}
     */
    List<Function> getRoleFuctions(String roleCode);

}
