package egovframework.example.sample.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("statMapper")
public interface StatMapper {
	
	// 해당 지역의 평점+후기 높은 TOP 5 병원을 조회한다.
	List<?> statisticsRate(@Param("province") String province, @Param("city") String city) throws Exception;

	// 해당 지역의 재방문율 높은 TOP 5 병원을 조회한다.
	List<?> statisticsRetention(@Param("province") String province, @Param("city") String city) throws Exception;
}