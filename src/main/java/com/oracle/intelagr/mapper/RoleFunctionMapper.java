package com.oracle.intelagr.mapper;

import java.util.Map;

public interface RoleFunctionMapper {


    /**
     * 更新roldCode
     * @param parms
     * @return
     */
    boolean updateRoleCodeByOldRoleCode(Map<String,Object> parms);
}
