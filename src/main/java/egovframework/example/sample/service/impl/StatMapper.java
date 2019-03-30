package egovframework.example.sample.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("statMapper")
public interface StatMapper {
	
	// 평점 좋은 병원
	List<?> statisticsRate(@Param("province") String province, @Param("city") String city) throws Exception;

	// 재방문율 높은 병원
	List<?> statisticsRetention(@Param("province") String province, @Param("city") String city) throws Exception;
}