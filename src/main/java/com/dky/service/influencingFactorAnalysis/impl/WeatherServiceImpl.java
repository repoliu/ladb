package com.dky.service.influencingFactorAnalysis.impl;

import com.alibaba.fastjson.JSONArray;
import com.dky.entity.*;
import com.dky.entity.influ.*;
import com.dky.entity.vo.ChartsOption;
import com.dky.entity.influ.vo.WeatherResult;
import com.dky.mapper.*;
import com.dky.mapper.influ.*;
import com.dky.mybaties.ResultMapHandler;
import com.dky.service.influencingFactorAnalysis.WeatherService;
import com.dky.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * 气象因素分析得Service类，主要是生成气象因素分析所需要得曲线数据
 */
@Service("weatherService")
public class WeatherServiceImpl implements WeatherService {
    private final ForHourWeatherMapper forHourWeatherMapper;
    private final HisHourWeatherMapper hisHourWeatherMapper;
    private final RelationSupportMapper relationSupportMapper;
    private final ForWeatherMapper forWeatherMapper;
    private final WeatherInfoMapper weatherInfoMapper;
    private final DbareaMapper dbareaMapper;
    private final AutovalueMapper autovalueMapper;
    private final QstationMapper qstationMapper;
    private final CityforecastMapper cityforecastMapper;

    @Autowired
    public WeatherServiceImpl(ForHourWeatherMapper forHourWeatherMapper,
                              HisHourWeatherMapper hisHourWeatherMapper,
                              RelationSupportMapper relationSupportMapper,
                              ForWeatherMapper forWeatherMapper,
                              WeatherInfoMapper weatherInfoMapper,
                              DbareaMapper dbareaMapper,
                              AutovalueMapper autovalueMapper,
                              QstationMapper qstationMapper,
                              CityforecastMapper cityforecastMapper) {
        this.forHourWeatherMapper = forHourWeatherMapper;
        this.hisHourWeatherMapper = hisHourWeatherMapper;
        this.relationSupportMapper = relationSupportMapper;
        this.forWeatherMapper = forWeatherMapper;
        this.weatherInfoMapper = weatherInfoMapper;
        this.dbareaMapper = dbareaMapper;
        this.autovalueMapper = autovalueMapper;
        this.qstationMapper = qstationMapper;
        this.cityforecastMapper = cityforecastMapper;
    }

    /**
     * 从数据库中获得最新一条记录得地区和时间标识，封装成WeatherResult返回
     *
     * @return 返回含有地区和时间标识得实体类
     */
    public WeatherResult findAreaAndMdate() {
        WeatherResult wr = forHourWeatherMapper.findAreaAndMdate();
        if (wr == null) {
            wr = hisHourWeatherMapper.findAreaAndMdate();
        }
        return wr;
    }

    /**
     * 通过地区标识，在数据库中找到存在得最大时间和最小时间
     *
     * @param area 地区的唯一标识
     * @return 返回含有最大时间和最小时间的实体类
     */
    public WeatherResult findExtremeMdateByArea(Integer area) {
        return forHourWeatherMapper.findExtremeMdateByArea(area);
    }

    /**
     * 返回温度曲线的数据
     *
     * @param param 数据库参数，含有地区标识和时间，通过方法添加温度标识
     * @return 返回温度曲线的数据
     */
    public List<ChartsOption> findTemperatureListByKey(Map<String, Object> param) {
        String type = "temperature";
        param.put("type", type);
        String name = "温度";
        return returnList(param, name);
    }

    /**
     * 返回降雨量的数据
     *
     * @param param 数据库参数，含有地区标识和时间，通过方法添加降雨量标识
     * @return 返回降雨量曲线的数据
     */
    public List<ChartsOption> findRainfallListByKey(Map<String, Object> param) {
        String type = "rainfall";
        param.put("type", type);
        String name = "降雨量";
        return returnList(param, name);
    }

    /**
     * 返回支持度的数据
     *
     * @param param 数据库参数，含有地区标识和时间
     * @return 返回支持度的数据
     */
    public List<ChartsOption> findSupportListByKey(Map<String, Object> param) {
        List<RelationSupport> list = relationSupportMapper.findSupportListByKey(param);
        List<ChartsOption> result = new ArrayList<>();
        if (list != null && list.size() != 0) {
            if (param.get("areaname") == null){
                String areaname = list.get(0).getAreaname();
                param.put("areaname",areaname);
            }
            String[] data = new String[list.size()];
            String[] hengZhou = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                data[i] = list.get(i).getRelationDegree().intValue()+"";
                hengZhou[i] = list.get(i).getRelationType();
            }
            ChartsOption c1 = ChartsUtils.createChartsOption(data, hengZhou, "支持度", "bar");
            result.add(c1);
        } else {
            if (param.get("areaname") == null){
                String areaname = dbareaMapper.findAreaname((Integer) param.get("area"));
                param.put("areaname",areaname);
            }
            return null;
        }
        return result;
    }

    /**
     * 通过参数查找天气状况的参数
     *
     * @param param 数据库参数，含有地区标识和时间
     * @return 返回不同的天气数据
     */
    public ForWeather findForWeatherByKey(Map<String, Object> param) {
        return forWeatherMapper.findForWeatherByKey(param);
    }

    /**
     * 查找导入记录
     *
     * @return 返回数据库中最新一天的导入记录
     */
    public List<WeatherInfo> findImportRecord() throws ParseException {
        Date date = weatherInfoMapper.findLastVerTime();
        if (date == null) {
            return null;
        }
        Map<String, String> condition = TimePoint.timeSelectForWeatherRecord(date);
        List<WeatherInfo> result = weatherInfoMapper.findImportListByVerTime(condition);
        for (WeatherInfo w : result) {
            w.setVerTime(DateUtils.format(w.getTime(),DateUtils.YMDHMSH_P));
        }
        return result;
    }

    /**
     * 通过area找到areaname的方法
     * @param area 地区得唯一标识
     * @return areaname
     */
    @Override
    public String findAreaname(Integer area) {
        return dbareaMapper.findAreaname(area);
    }

    /**
     * 向result中设置rList（降雨量图），sList（支持度图），tList（温度图）的方法
     * @param mdate 传回的日期
     * @param areaname 地区的中文名字
     * @param result 气象因素返回界面的结果
     * @throws ParseException 转换异常，基本不会出现
     */
    @Override
    public void findResultChartsOption(String mdate, String areaname, WeatherResult result) throws ParseException {
        Date startDate = DateUtils.parse(mdate,DateUtils.YMDH_P);
        Date endDate = new Date(startDate.getTime() + 86400000L);
        String stcd = qstationMapper.findStcdByAreaname(areaname);
        Map<String, Object> params = new HashMap<>();
        params.put("startDate", DateUtils.format(startDate,DateUtils.YMDHMSH_P));
        params.put("endDate", DateUtils.format(endDate,DateUtils.YMDHMSH_P));
        params.put("stcd", stcd);
        params.put("mdate", mdate.replace("-", ""));
        params.put("areaname", areaname);
        params.put("mdateMonth",mdate.replace("-", "").substring(0,6));
        params.put("relationType","最低温度");
        BigDecimal minTemp = relationSupportMapper.findValueByParams(params);
        if (minTemp == null){
            result.setTempSelect(true);
        } else if (minTemp.intValue() > 0){
            result.setTempSelect(true);
        } else {
            result.setTempSelect(false);
        }
        List<ChartsOption> sList = findSupportListByKey(params);
        if (sList == null){
            result.setSList(null);
        } else {
            result.setSList(JSONArray.toJSONString(sList));
        }
        setT_R_ForWeather(result, params);

    }

    /**
     * 通过area查询area代表的地区，数据库里最新的记录，时间倒序
     * @param area 地区的唯一标识
     * @return 最新的日期
     */
    @Override
    public Date findMdateByArea(Integer area) {
        return autovalueMapper.findMaxMdateByArea(area);
    }

    @Override
    public String findGraphDataByParams(Map<String, Object> params) {
        ResultMapHandler handler = new ResultMapHandler();
        autovalueMapper.findValueMapByParams(params,handler);
        Map<Date,Integer> map = handler.getMappedResults();
        if (map.size()==0){
            return null;
        }
        Iterator<Map.Entry<Date,Integer>> iterator = map.entrySet().iterator();
        String[] data = new String[map.size()];
        String[] hengzhou = new String[map.size()];
        String name = null;
        String type = "line";
        int div = 1;
        String weatherType = (String)params.get("type");
        switch (weatherType){
            case "temperature":
                name = "24小时温度曲线";
                div = 10;
                break;
            case "rain":
                name = "24小时降雨量曲线";
                div = 10;
                break;
            case "wp":
                name = "24小时风力等级曲线";
                break;
            case "humidity":
                name = "24小时湿度曲线";
                break;
            default:
                break;
        }
        int index = 0;
        while (iterator.hasNext()){
            Map.Entry<Date,Integer> entry = iterator.next();
            Date d = entry.getKey();
            Integer i = entry.getValue();
            if(weatherType.equals("humidity")&&i>100){
                data[index] = i/10 +"";
            } else {
                data[index] = i / div + "";
            }
            hengzhou[index] = DateUtils.format(d,DateUtils.HM_P);
            index++;
        }
        List<ChartsOption> result = new ArrayList<>();
        result.add(ChartsUtils.createChartsOption(data,hengzhou,name,type));
        return JSONArray.toJSONString(result);
    }

    /**
     * 生成对应的温度/降雨量曲线图数据
     *
     * @param param 包含地区、时间、类型
     * @param name  生成图例的名字
     * @return 返回对应曲线图数据
     */
    private List<ChartsOption> returnList(Map<String, Object> param, String name) {
        List<ChartsOption> result = new ArrayList<>();
        ForHourWeather forHourWeather = forHourWeatherMapper.findForHourWeatherByKey(param);
        if (forHourWeather != null) {
            String[] d1 = ObjToArraysUtils.getFieldValues(forHourWeather);
            String[] hengZhou = createHengZhou(d1.length);
            ChartsOption c1 = ChartsUtils.createChartsOption(d1, hengZhou, "预测" + name, "line");
            result.add(c1);
        }
        HisHourWeather hisHourWeather = hisHourWeatherMapper.findHisHourWeatherByKey(param);
        if (hisHourWeather != null) {
            String[] d1 = ObjToArraysUtils.getFieldValues(hisHourWeather);
            String[] hengZhou = createHengZhou(d1.length);
            String[] d2 = ObjToArraysUtils.getFieldValues(hisHourWeather);
            ChartsOption c2 = ChartsUtils.createChartsOption(d2, hengZhou, "实际" + name, "line");
            result.add(c2);
        }
        return result;
    }

    /**
     * 生成图表横轴
     *
     * @param length 横轴的长度标识
     * @return 返回横轴
     */
    private String[] createHengZhou(int length) {
        String[] result = new String[length];
        for (int i = 0; i < length; i++) {
            if (i < 10) {
                result[i] = "0" + i + ":00";
            } else {
                result[i] = i + ":00";
            }
        }
        return result;
    }

    /**
     * 获取到结果集，将其转换后封装进result里
     * @param result 气象因素的返回结果
     * @param params 查询所用的参数
     */
    private void setT_R_ForWeather(WeatherResult result, Map<String, Object> params) {
        List<Autovalue> autovalues = autovalueMapper.findListByTimeAndStcd(params);
        if (autovalues.size() == 0) {
            return;
        }
        List<Date> dateList = new ArrayList<>();
        List<String> timeList = new ArrayList<>();
        List<String> tempList = new ArrayList<>();
        List<String> rainList = new ArrayList<>();
        int maxTemperature = -99, minTemperature = 99, totalWd = 0, totalTemperature = 0, maxWp = 0, minWp = 20, totalHumidity = 0, count = 0,totalRain = 0;
        String weatherType = cityforecastMapper.findWeatherText24ByTimeAndStcd(params);
        for (Autovalue autovalue : autovalues) {
            Date tem = autovalue.getTime();
            if (!dateList.contains(tem)) {
                dateList.add(tem);
                timeList.add(DateUtils.format(tem,DateUtils.HM_P));
                int temperature = (autovalue.getTemperature() / 10);
                if (temperature > maxTemperature) {
                    maxTemperature = temperature;
                } else if (temperature < minTemperature) {
                    minTemperature = temperature;
                }
                int wp = autovalue.getWp();
                if (wp > maxWp) {
                    maxWp = wp;
                } else if (wp < minWp) {
                    minWp = wp;
                }
                totalRain += autovalue.getRain();
                tempList.add(temperature + "");
                rainList.add(autovalue.getRain()/10 + "");
                totalHumidity += (autovalue.getHumidity() / 10);
                totalWd += autovalue.getWp();
                totalTemperature += (autovalue.getTemperature()/10);
                count++;
            }
        }
        int size = timeList.size();
        String[] hengZhou = new String[size];
        String[] tempArray = new String[size];
        String[] rainArray = new String[size];
        for (int i = 0; i < size; i++) {
            hengZhou[i] = timeList.get(i);
            tempArray[i] = tempList.get(i);
            rainArray[i] = rainList.get(i);
            if (i == size - 1) {
                if (hengZhou[i].equals("00:00")) {
                    hengZhou[i] = "24:00";
                }
            }
        }
        List<ChartsOption> tList = new ArrayList<>();
        List<ChartsOption> rList = new ArrayList<>();
        tList.add(ChartsUtils.createChartsOption(tempArray, hengZhou, "24小时温度曲线", "line"));
        rList.add(ChartsUtils.createChartsOption(rainArray, hengZhou, "24小时降雨量曲线", "line"));
        result.setTList(JSONArray.toJSONString(tList));
        result.setRList(JSONArray.toJSONString(rList));
        int wd = totalWd / count;
        int humidity = totalHumidity / count;
        int temperature = totalTemperature/ count;
        int cosiness = WeatherCountUtil.computeCosiness(wd,temperature,humidity);
        /*double ws = WeatherCountUtil.sumWsByWp(wd);*/
       /* double cos=(1.818*temperature+ 18.18)*(0.88 + 0.002*humidity/100)+(temperature- 32) / (45 -temperature)- 3.2*ws+ 18.2;*/
        ForWeather forWeather = new ForWeather();
        /*int cosi= (int)cos;
        int cosiness = WeatherCountUtil.sumCosiness(cosi);*/
        forWeather.setRainfall(totalRain/10+"mm");
        forWeather.setCosiness(cosiness+"");
        forWeather.setHumidity(new BigDecimal(humidity));
        forWeather.setMaxTemperature(new BigDecimal(maxTemperature));
        forWeather.setMinTemperature(new BigDecimal(minTemperature));
        forWeather.setWeatherType(weatherType);
        StringBuilder sb = new StringBuilder();
        if (minWp < maxWp) {
            sb.append(minWp).append("-").append(maxWp).append("级风");
        } else {
            sb.append(minWp).append("级风");
        }
        forWeather.setWp(sb.toString());
        result.setForWeather(forWeather);

    }
}
