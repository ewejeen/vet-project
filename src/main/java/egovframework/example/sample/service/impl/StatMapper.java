/*
 * Copyright 2011 MOPAS(Ministry of Public Administration and Security).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.example.sample.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.VetVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("statMapper")
public interface StatMapper {
	
	// 평점 좋은 병원
	List<?> statisticsRate(@Param("province") String province, @Param("city") String city) throws Exception;

	// 재방문율 높은 병원
	List<?> statisticsRetention(@Param("province") String province, @Param("city") String city) throws Exception;
}