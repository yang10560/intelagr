package com.oracle.intelagr.mapper;

import com.oracle.intelagr.entity.ServialNum;

import java.util.List;



public interface ServialNumMapper {
	public List<ServialNum> select();
	public ServialNum selectById(int id);
	public void update(ServialNum servialNum);
}
