package com.dky.service.influencingFactorAnalysis.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dky.entity.LoadDaytrait;
import com.dky.entity.LoadHisdata96Cluster;
import com.dky.entity.RelationSupport;
import com.dky.entity.influ.LoadWeatherCluster;
import com.dky.entity.influ.vo.ClusterData;
import com.dky.entity.influ.vo.ClusterTableData;
import com.dky.entity.influ.vo.RelationValue;
import com.dky.entity.influ.vo.WeatherInfo;
import com.dky.entity.vo.ChartsOption;
import com.dky.entity.vo.Series;
import com.dky.mapper.LoadDaytraitMapper;
import com.dky.mapper.LoadHisdata96ClusterMapper;
import com.dky.mapper.LoadHisdataMapper;
import com.dky.mapper.RelationSupportMapper;
import com.dky.mapper.influ.AutovalueMapper;
import com.dky.mapper.influ.CityforecastMapper;
import com.dky.mapper.influ.InfluCommonMapper;
import com.dky.service.influencingFactorAnalysis.WeatherAnalysisService;
import com.dky.thread.influ.DataOfNRainClusterThread;
import com.dky.thread.influ.DataOfRainClusterThread;
import com.dky.thread.influ.DataOfWeatherInfoThread;
import com.dky.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: YangSL
 * @Description: 气象因素分析
 * @Date: Create in 11:28 2018/1/5
 */
@Service
public class WeatherAnalysisServiceImpl implements WeatherAnalysisService {
    private final LoadDaytraitMapper loadDaytraitMapper;
    private final AutovalueMapper autovalueMapper;
    private final CityforecastMapper cityforecastMapper;
    private final RelationSupportMapper relationSupportMapper;
    private final LoadHisdata96ClusterMapper loadHisdata96ClusterMapper;
    private final LoadHisdataMapper loadHisdataMapper;
    private final InfluCommonMapper influCommonMapper;

    @Autowired
    public WeatherAnalysisServiceImpl(LoadDaytraitMapper loadDaytraitMapper,
                                      AutovalueMapper autovalueMapper,
                                      CityforecastMapper cityforecastMapper,
                                      RelationSupportMapper relationSupportMapper,
                                      LoadHisdata96ClusterMapper loadHisdata96ClusterMapper,
                                      LoadHisdataMapper loadHisdataMapper,
                                      InfluCommonMapper influCommonMapper) {
        this.loadDaytraitMapper = loadDaytraitMapper;
        this.autovalueMapper = autovalueMapper;
        this.cityforecastMapper = cityforecastMapper;
        this.relationSupportMapper = relationSupportMapper;
        this.loadHisdata96ClusterMapper = loadHisdata96ClusterMapper;
        this.loadHisdataMapper = loadHisdataMapper;
        this.influCommonMapper = influCommonMapper;
    }
    Logger logger = Logger.getLogger(WeatherAnalysisServiceImpl.class.getName());
    /**
     * 根据指定日期，返回最近30天记录
     *
     * @param params 参数
     * @return 返回结果集
     */
    @Override
    public List<LoadDaytrait> findListByParams(Map<String, Object> params) {
        return loadDaytraitMapper.findResentListByParams(params);
    }

    /**
     * 返回对应天气类型的24小时数据
     *
     * @param params 参数
     * @return 24小时数据
     */
    @Override
    public List<RelationValue> findValuesByParams(Map<String, Object> params) {
        String type = (String) params.get("aType");
        String aType = setAType(type);
        params.put("type", aType);
        List<RelationValue> result = new ArrayList<>();
        StringBuilder mdateH = new StringBuilder((String) params.get("mdate"));
        StringBuilder minMdateH = new StringBuilder((String) params.get("minMdate"));
        mdateH.insert(4, "-").insert(7, "-");
        minMdateH.insert(4, "-").insert(7, "-");
        params.put("mdateH", mdateH.toString());
        params.put("minMdateH", minMdateH.toString());

        //如果气象因素类型时舒适度，则需要进行计算
        if (!aType.equals("COSINESS")) {
            result = autovalueMapper.findAssignTypeListByParams(params);
        } else {
            List<WeatherInfo> tempList = autovalueMapper.findWeatherInfosByParams(params);
            for (WeatherInfo w : tempList) {
                RelationValue r = new RelationValue();
                if (w.getHumidity() == null) {
                    r.setValue(null);
                } else {
                    int humidity = w.getHumidity();
                    if (humidity > 100) {
                        w.setHumidity(humidity / 10);
                    }
                    int cosiness = WeatherCountUtil.computeCosiness(w.getAvgWp(), w.getAvgTemp() / 10, w.getHumidity());
                    r.setValue(cosiness);
                }

                r.setDayTime(DateUtils.format(w.getTime(), DateUtils.YMDH_P));
                result.add(r);
            }
        }
        return result;
    }

    /**
     * 生成综合曲线的数据
     *
     * @param daytraits    负荷数据
     * @param values       天气数据
     * @param analysisType 天气类型
     * @return 曲线图数据
     */
    @Override
    public String createLoadCharts(List<LoadDaytrait> daytraits, List<RelationValue> values, String analysisType) {
        List<ChartsOption> result = new ArrayList<>();
        List<Date> dayList = new ArrayList<>();
        for (LoadDaytrait l : daytraits) {
            Date mdate = null;
            try {
                SimpleDateFormat n = new SimpleDateFormat("yyyyMMdd");
                mdate = n.parse(l.getMdate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (!dayList.contains(mdate)) {
                dayList.add(mdate);
            }
        }
        for (RelationValue r : values) {
            Date mdate = null;
            try {
                SimpleDateFormat h = new SimpleDateFormat("yyyy-MM-dd");
                mdate = h.parse(r.getDayTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (!dayList.contains(mdate)) {
                dayList.add(mdate);
            }
        }
        Collections.sort(dayList);
        String[] dataMaxload = new String[dayList.size()];
        String[] dataAvgload = new String[dayList.size()];
        String[] dataTypevalue = new String[dayList.size()];
        String[] hengzhou = new String[dayList.size()];
        int count = 0;
        for (Date d : dayList) {
            int count0 = 0;
            int count1 = 1;
            for (LoadDaytrait l : daytraits) {
                String temp = DateUtils.format(d, DateUtils.YMDN_P);
                if (l.getMdate().equals(temp)) {
                    dataMaxload[count] = l.getMaxload().intValue() + "";
                    dataAvgload[count] = l.getAveload().intValue() + "";
                    count0++;
                }
            }
            for (RelationValue r : values) {
                String temp = DateUtils.format(d, DateUtils.YMDH_P);
                if (r.getDayTime().equals(temp)) {
                    if (r.getValue() == null) {
                        dataTypevalue[count] = null;
                    } else {
                        switch (analysisType) {
                            case "温度":
                                dataTypevalue[count] = (r.getValue() / 10) + "";
                                break;
                            case "湿度":
                                dataTypevalue[count] = r.getValue().toString();
                                break;
                            case "气压":
                                dataTypevalue[count] = r.getValue().toString();
                                break;
                            case "人体舒适度":
                                dataTypevalue[count] = r.getValue().toString();
                                break;
                            case "降雨量":
                                dataTypevalue[count] = r.getValue() / 10 + "";
                                break;
                        }
                    }
                    count1++;
                }
            }
            if (count0 == 0) {
                dataMaxload[count] = null;
                dataAvgload[count] = null;
            }
            if (count1 == 0) {
                dataTypevalue[count] = null;
            }
            hengzhou[count] = DateUtils.format(d, DateUtils.MDH_P);
            count++;
        }

        result.add(setCharts(hengzhou, "最大负荷", dataMaxload, 0));
        result.add(setCharts(hengzhou, "平均负荷", dataAvgload, 0));
        result.add(setCharts(hengzhou, analysisType, dataTypevalue, 1));
        return JSONArray.toJSONString(result, SerializerFeature.DisableCircularReferenceDetect);
    }


    /**
     * 查询气象信息数据
     *
     * @param params 参数
     * @return 气象信息
     */
    @Override
    public WeatherInfo setWeatherInfoByParams(Map<String, Object> params) {
        StringBuilder mdateH = new StringBuilder((String) params.get("mdate"));
        mdateH.insert(4, "-").insert(7, "-");
        params.put("mdateH", mdateH.toString());
        WeatherInfo weatherInfo = autovalueMapper.findOneDayWeatherByParams(params);
        if (weatherInfo != null) {
            weatherInfo.setWp(String.valueOf(weatherInfo.getMinWp()) + "-" + weatherInfo.getMaxWp() + "级风");
            weatherInfo.setMaxTemp(weatherInfo.getMaxTemp() / 10);
            weatherInfo.setMinTemp(weatherInfo.getMinTemp() / 10);
            weatherInfo.setRainfall(weatherInfo.getRainfall() / 10);
            int humidity = weatherInfo.getHumidity();
            if (humidity > 100) {
                weatherInfo.setHumidity(humidity / 10);
            }
            int cosiness = WeatherCountUtil.computeCosiness(weatherInfo.getAvgWp(), weatherInfo.getAvgTemp() / 10, weatherInfo.getHumidity());
            weatherInfo.setCosiness(cosiness);
            weatherInfo.setWeatherType(cityforecastMapper.findWeatherText24ByParams(params));
        }
        return weatherInfo;
    }

    /**
     * 获取聚类表格数据
     *
     * @param params 参数
     * @return 聚类数据
     */
    @Override
    public List<ClusterData> findDatasByParams(Map<String, Object> params) {
        params.put("type1", "temperature");
        params.put("type2", "maxload");
        params.put("type3", "aveload");
        //获取当月最低温度对负荷的支持度布尔结果（支持度大于0返回true，小于0返回false）
        boolean tempSelect = (boolean) params.get("tempSelect");

        //先进入load_weather_cluster_data进行查询，然后对其进行更新
        List<ClusterData> result =new ArrayList<>();
/*        result=  influCommonMapper.ListClusterDataByParams(params);
        if (result != null && result.size() > 0) {
            new Thread(() -> {
                getClusterData(params, tempSelect, "update");
            }).start();
            return result;
        }*/
                                                            //之前用到了，现在第三个参数没有用到
        result = getClusterData(params, tempSelect, "insert");
        return result;
    }

    //获取聚类表格数据，并根据更新表
    @Transactional
    public List<ClusterData> getClusterData(Map<String, Object> params, boolean tempSelect, String active) {
        List<ClusterData> result;//获取每日聚类数据（Sql1）
        //获取各类型天数、最小/大温度，最小/大负荷、平均负荷等数据（Sql2），同时该对象中还含有前台展示所需的属性
        result = loadDaytraitMapper.findClusterDatasByParams(params);


 List<LoadWeatherCluster> clusters = loadDaytraitMapper.findClustersByParams(params);
 //遍历Sql2结果集，目的获取每个聚类类型的表格结果
        for (ClusterData c : result) {
            c.setArea((Integer) params.get("area"));
            c.setAreaname((String) params.get("areaname"));
            c.setYear((String) params.get("year"));
            c.setFlag((String) params.get("clusterFlag"));
            //取最低/高温度赋值给变量minTemp,maxTemp（注意查看数据库中温度单位，统一转化为℃）
            int minTemp = (c.getMinTemperature().intValue()) / 10;
            int maxTemp = (c.getMaxTemperature().intValue()) / 10;
            //升温/降温一度增长负荷的和
            int sum = 0;
            //循环，起始为最低温度，终点为最高温度，每次加1，目的是获取每个温度负荷增长的结果
            for (int i = minTemp; i <= maxTemp; i++) {
                //定义每个温度的负荷增长量
                int sumload = 0;
                //计数器，计算平均结果
                int count = 0;
                //遍历Sql1结果集，目的获取温度等于循环条件i的日期负荷增长结果
                for (LoadWeatherCluster l : clusters) {
                    //判断当前记录的温度与当前循环条件i是否相等
                    if (l.getValue1().intValue() / 10 == i) {
                        //循环条件
                        boolean j = true;
                        //温差限定值
                        int z = 2;
                        while (j) {
                            //再次遍历Sql1结果集，目的获取相近日期的负荷数据，进行计算
                            for (LoadWeatherCluster w : clusters) {
                                long compResult = 0;
                                try {
                                    compResult = DateUtils.compareByYMDN(w.getMdate(), l.getMdate());
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                if (compResult > 10) {
                                    continue;
                                }
                                //温差
                                int diffVlue1;
                                //分支判断支持度结果
                                if (tempSelect) {
                                    //true时为升温，用外层循环的温度-内层循环的温度
                                    diffVlue1 = (l.getValue1().intValue() - w.getValue1().intValue()) / 10;
                                } else {
                                    //false是为降温，用内层循环的温度-外层温度
                                    diffVlue1 = (w.getValue1().intValue() - l.getValue1().intValue()) / 10;
                                }
                                //判断，温差>0,并且温差小于规定值（初始为1度），并且两个日期差值在10天以内

                                if (diffVlue1 > 0 && diffVlue1 < z) {
                                    //负荷差值
                                    int diffVlue2;
                                    //分支判断支持度结果
                                    if (tempSelect) {
                                        //true时为升温，用外层的最大负荷-内层最大负荷
                                        diffVlue2 = l.getValue2().intValue() - w.getValue2().intValue();
                                    } else {
                                        //false时为降温，用外层的平均负荷-内层平均负荷
                                        diffVlue2 = l.getValue3().intValue() - w.getValue3().intValue();
                                    }
                                    //如果负荷差大于0，则为升温/降温时增长的负荷
                                    if (diffVlue2 > 0) {
                                        //负荷除温差，得到升温/降温一度增长的负荷
                                        int diffMaxLoad = diffVlue2 / diffVlue1;
                                        //本次循环结果添加进当前温度的增长量，计数器加一，当前日期循环结束
                                        sumload += diffMaxLoad;
                                        count++;
                                        j = false;
                                        break;
                                    }
                                }
                            }
                            //如果温差大于7，则不再寻找
                            if (z > 7) {
                                j = false;
                                break;
                            }
                            z++;
                        }
                    }
                }
                //多天负荷增长值的平均值，如果计数器不为0，则当前温度有结果
                int diffMaxLoad = 0;
                if (count != 0) {
                    diffMaxLoad = sumload / count;
                }
                //将多天平均值加入当前温度的增长负荷值
                sum += diffMaxLoad;
            }
            //拼接温度曲线，存入对象
            String tem = minTemp + "-" + maxTemp;
            c.setTemperature(tem);
            //将当前类型的天数存入对象，因为一天有三个记录（temperature,maxload,aveload）有3条，所以除3
            c.setCountDays(c.getDays() / 3);
            //等到本类型的温差，为0时无温差，直接将sum结果存入对象，非0则计算平均结果存入对象
            int divide = maxTemp - minTemp;
            if (divide == 0) {
                c.setReload(sum);
            } else {
                c.setReload(sum / divide);
            }
            //将本类型平均负荷存入对象
            c.setIntAvgload(c.getAvgload().intValue());
            //这个时将支持度结果存入对象，方便前台判断
            c.setTempSelect(tempSelect);
        }

        /*
        String currentSchema = ReadProperties.readProperties("/db.properties", "currentSchema");
        params.put("schema", currentSchema);
        String sql = " select " + params.get("schema") + ".delete('" + params.get("schema") + "','load_weather_cluster_data','area = ''" + params.get("area") + "'' " +
                "and year = ''" + params.get("year") + "'' and flag = ''" + params.get("clusterFlag") + "'' and temp_select = ''" + params.get("tempSelect") + "''');";

        DeleteFunction deletePublic = new DeleteFunction();
        String tableName = "loadfor.test";
        String whereCondition = "1=1";

        logger.info("============================马上调用删除==================================================");
        try {
            deletePublic.deleteTable(tableName, whereCondition);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("============================删除方法已经走完==================================================");
        if ("insert".equals(active) && result.size() > 0) {
            influCommonMapper.insertLoadWeatherClusterDataByList(result);
        } else if ("update".equals(active)) {
            influCommonMapper.insertLoadWeatherClusterDataByList(result);
        }
            JdbcOperate jdbcOperate=new JdbcOperate();
           jdbcOperate.delete(sql);
            //influCommonMapper.deleteLoadWeatherClusterDateByList(params);
            String whereCondition="area = ''"+params.get("area")+"'' " +" and year = ''"+params.get("year")+"'' and flag = ''"+params.get("clusterFlag")+"'' and temp_select = ''"+params.get("tempSelect")+"''";
            String tableName=currentSchema+".load_weather_cluster_data";
*/

        return result;
    }

    /**
     * 获取聚类信息的具体数据
     *
     * @param params 参数，页码等信息
     * @return 表格数据
     * @throws ParseException
     */
    @Override
    public List<ClusterTableData> findClusterDatasByParams(Map<String, Object> params) throws ParseException {
        params.put("type1", "temperature");
        params.put("type2", "maxload");
        params.put("type3", "aveload");
        String clusterFlag = (String) params.get("clusterFlag");
        List<String> lowDate = null;

        //如果聚类类型时MINTEMP，则查询该年温度支持度小于0的日期
        switch (clusterFlag) {
            case "MINTEMP":
                lowDate = relationSupportMapper.findLowDateListByParams(params);
                break;
            default:
                break;
        }
        params.put("lowDate", lowDate);

        //获取聚类信息，并进行封装处理
        List<ClusterTableData> result = loadDaytraitMapper.findClusterTableDatasByParams(params);
        for (ClusterTableData c : result) {
            c.setTemp(c.getTempB().intValue() / 10);
            c.setMaxload(c.getMaxloadB().intValue());
            c.setAvgload(c.getAvgloadB().intValue());
            c.setMdate(DateUtils.format(DateUtils.parse(c.getMdateN(), DateUtils.YMDN_P), DateUtils.YMDH_P));
        }
        return result;
    }

    /**
     * 查询各类型有多少天
     *
     * @param params 参数
     * @return 天数
     */
    @Override
    public int findClusterCountByParams(Map<String, Object> params) {
        String clusterFlag = (String) params.get("clusterFlag");
        List<String> lowDate = null;
        switch (clusterFlag) {
            case "MINTEMP":
                lowDate = relationSupportMapper.findLowDateListByParams(params);
                break;
            default:
                break;
        }
        params.put("lowDate", lowDate);
        return loadDaytraitMapper.findClusterCountByParams(params) / 3;
    }

    /**
     * 查询气象因素的支持度时正相关还是负相关
     *
     * @param params 参数
     * @return 结果
     */
    @Override
    public Boolean setTempSelect(Map<String, Object> params) {
        //设置数据库查询参数，有一部分已经通过params传入
        params.put("mdateMonth", ((String) params.get("mdate")).replace("-", "").substring(0, 6));
        params.put("relationType", "最低温度");
        //调用sql查询
        BigDecimal minTemp = relationSupportMapper.findValueByParams(params);
        //逻辑判断
        if (minTemp == null) {
            return true;
        } else if (minTemp.intValue() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 查询降雨量聚类曲线图数据
     *
     * @param params 参数
     * @return 曲线图数据
     */
    @Override
    public String findRainCLusterGraphByParams(Map<String, Object> params) {
        List<LoadHisdata96Cluster> clusters = loadHisdata96ClusterMapper.findListByParams(params);
        if (clusters.size() == 0) {
            return null;
        }
        String[] hengzhou = TimePoint.timeCreate(96);
        String type = "line";
        String name = "graph";
        List<ChartsOption> result = new ArrayList<>();
        int index = 1;
        for (LoadHisdata96Cluster c : clusters) {
            String[] data = ObjToArraysUtils.getFieldValues(c);
            ChartsOption ch = ChartsUtils.createChartsOption(data, hengzhou, name + index, type);
            index++;
            result.add(ch);
        }
        return JSONArray.toJSONString(result);
    }

    /**
     * 返回极端天气时，各因素对负荷的影响综合曲线
     *
     * @param area  地区
     * @param mdate 时间
     * @return 曲线数据
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public Map<String, Object> findRainGraphsByParams(Integer area, String mdate) throws ExecutionException, InterruptedException {
        Map<String, Object> result = new HashMap<>();

        //此处时为了前端的快速相应，使用多线程进行查询不同因素的曲线数据
        FutureTask<String[]> ft1 = new FutureTask<String[]>(
                new DataOfRainClusterThread(area, mdate, 0, loadHisdata96ClusterMapper));
        FutureTask<String[]> ft2 = new FutureTask<String[]>(
                new DataOfNRainClusterThread(area, mdate, 0, loadHisdataMapper));
        FutureTask<List<List<String>>> ft3 = new FutureTask<List<List<String>>>(
                new DataOfWeatherInfoThread(area, mdate, "rain", autovalueMapper));
        FutureTask<List<List<String>>> ft4 = new FutureTask<List<List<String>>>(
                new DataOfWeatherInfoThread(area, mdate, "wp", autovalueMapper));
        FutureTask<List<List<String>>> ft5 = new FutureTask<List<List<String>>>(
                new DataOfWeatherInfoThread(area, mdate, "temperature", autovalueMapper));
        FutureTask<String[]> ft6 = new FutureTask<String[]>(
                new DataOfRainClusterThread(area, mdate, 1, loadHisdata96ClusterMapper));
        new Thread(ft1, "今日负荷").start();
        new Thread(ft2, "未降雨日负荷").start();
        new Thread(ft3, "降雨量").start();
        new Thread(ft4, "风力").start();
        new Thread(ft5, "温度").start();
        new Thread(ft6, "差值").start();
        String[] xAxis = TimePoint.timeCreate(96);
        String[] rain = ft1.get();
        if (rain == null || rain.length == 0) {
            return null;
        }
        result.put("rain", rain);
        result.put("cRain", ft6.get());
        result.put("nRain", ft2.get());
        result.put("rainValue", ft3.get());
        result.put("wpValue", ft4.get());
        result.put("tempValue", ft5.get());
        result.put("xAxis", xAxis);
        return result;
    }

    /**
     * 大雨及以上天气时，各因素和负荷差值的支持度分析
     *
     * @param area  地区id
     * @param mdate 时间
     * @return 图表数据
     */
    @Override
    public Map<String, Object> findDLoadSpotByParams(Integer area, String mdate) {
        Map<String, Object> params = new HashMap<>();
        params.put("area", area);
        params.put("mdate", mdate);
        List<RelationSupport> list = relationSupportMapper.findRainstormSupportByParams(params);
        if (list.size() > 0) {
            String[] yAxis = new String[list.size()];
            String[] data = new String[list.size()];
            int i = 0;
            for (RelationSupport r : list) {
                String type = r.getRelationType();
                yAxis[i] = type.substring(type.lastIndexOf("-") + 1, type.length());
                data[i] = r.getRelationDegree().intValue() + "";
                i++;
            }
            Map<String, Object> result = new HashMap<>();
            result.put("yAxis", yAxis);
            result.put("data", data);
            String temp = list.get(0).getRelationType();
            result.put("name", temp.substring(0, temp.lastIndexOf("-")) + "时差值支持度分析");
            return result;
        }
        return null;
    }

    //根据参数生成图表数据
    private ChartsOption setCharts(String[] hengzhou, String analysisType, String[] data, int index) {
        ChartsOption chartsOption0 = new ChartsOption();
        chartsOption0.setLegend(analysisType);
        chartsOption0.setHengZhou(hengzhou);
        Series series0 = new Series();
        series0.setType("line");
        series0.setSmooth(true);
        series0.setName(analysisType);
        series0.setShowAllSymbol(true);
        series0.setData(data);
        series0.setYAxisIndex(index);
        chartsOption0.setSeries(series0);
        return chartsOption0;
    }

    //不同类型天气的英文名称对照
    private String setAType(String type) {
        switch (type) {
            case "温度":
                return "TEMPERATURE";
            case "湿度":
                return "HUMIDITY";
            case "气压":
                return "ENPRESURE";
            case "人体舒适度":
                return "COSINESS";
            case "降雨量":
                return "RAIN";
            default:
                return "";
        }
    }

}
