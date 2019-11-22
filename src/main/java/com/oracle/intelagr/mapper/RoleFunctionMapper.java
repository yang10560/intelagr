package com.oracle.intelagr.mapper;

import java.util.List;
import java.util.Map;

public interface RoleFunctionMapper {


    /**
     * 更新roldCode
     * @param parms
     * @return
     */
    boolean updateRoleCodeByOldRoleCode(Map<String,Object> parms);


    /**
     * 根据roleCode取functionCode
     * @return
     */
    List<String> getFunctionCodeByRoleCode(String roleCode);
}
