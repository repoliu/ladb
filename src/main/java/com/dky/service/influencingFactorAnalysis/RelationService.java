package com.dky.service.influencingFactorAnalysis;

import com.dky.entity.influ.vo.WeatherResult;

import java.text.ParseException;
import java.util.Map; /**
 * @Author: YangSL
 * @Description:
 * @Date: Create in 10:04 2018/1/17
 */
public interface RelationService {
    String findLastMdateByArea(Integer area);

    void setResultByParams(WeatherResult result, Map<String, String> params,String wenDu,String jiangYu) throws ParseException;
}
