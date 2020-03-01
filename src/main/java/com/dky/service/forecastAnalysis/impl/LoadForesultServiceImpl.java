package com.dky.service.forecastAnalysis.impl;

import com.dky.entity.LoadForesult;
import com.dky.mapper.LoadForesultMapper;
import com.dky.service.forecastAnalysis.LoadForesultService;
import com.dky.util.ReadProperties;
import com.dky.util.daoOperate.DeleteFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("loadForesultService")
public class LoadForesultServiceImpl implements LoadForesultService {


    @Autowired
    private LoadForesultMapper loadForesultMapper;
    @Override
    public  LoadForesult select(LoadForesult loadForesult) {
        return loadForesultMapper.select(loadForesult);
    }

    @Override
    public int insert(LoadForesult loadForesult) {
        return loadForesultMapper.insert(loadForesult);
    }

    @Override
    public int selectInt(LoadForesult loadForesult) {
        int number=0;
        LoadForesult foresult=new LoadForesult();
        foresult=loadForesultMapper.select(loadForesult);
        if (foresult!=null){
            number=1;
        }
        return number;
    }

    @Override
    public int update(String[] strings,String area,String mdate) {
        int number=0;
        Map<String,Object> map=new HashMap<>();
        map.put("area",area);
        map.put("mdate",mdate);
        LoadForesult loadForesultMap=new LoadForesult();
        loadForesultMap=loadForesultMapper.findForesultByParams(map);
        DeleteFunction deleteFunction=new DeleteFunction();
        String   strKey="{f00,f01,f02,f03,f04,f05,f06,f07,f08,f09,f10,f11,f12,f13,f14,f15,f16,f17,f18,f19,f20,f21,f22,f23,f24,f25,f26,f27,f28,f29,f30,f31,f32,f33,f34,f35,f36,f37,f38,f39,f40,f41,f42,f43,f44,f45,f46,f47,f48,f49,f50,f51,f52,f53,f54,f55,f56,f57,f58,f59,f60,f61,f62,f63,f64,f65,f66,f67,f68,f69,f70,f71,f72,f73,f74,f75,f76,f77,f78,f79,f80,f81,f82,f83,f84,f85,f86,f87,f88,f89,f90,f91,f92,f93,f94,f95,f96}";
        StringBuilder sB=new StringBuilder();
        for (int i=0;i<strings.length;i++){
            if (i==0){
                sB.append("{"+strings[i]+",");
            }else   if (i==strings.length-1){
                sB.append(strings[i]+"}");
            }else {
                sB.append(strings[i]+",");
            }

        }
        String conditions="  area="+area+" and mdate="+mdate;
        LoadForesult loadForesult=new LoadForesult();
        loadForesult.setId(new Long(1));
        loadForesult.setArea(Integer.parseInt(area));
        loadForesult.setMdate(mdate);
        loadForesult.setF00(new	 BigDecimal(strings[0]));
        loadForesult.setF01(new	 BigDecimal(strings[1]));
        loadForesult.setF02(new	 BigDecimal(strings[2]));
        loadForesult.setF03(new	 BigDecimal(strings[3]));
        loadForesult.setF04(new	 BigDecimal(strings[4]));
        loadForesult.setF05(new	 BigDecimal(strings[5]));
        loadForesult.setF06(new	 BigDecimal(strings[6]));
        loadForesult.setF07(new	 BigDecimal(strings[7]));
        loadForesult.setF08(new	 BigDecimal(strings[8]));
        loadForesult.setF09(new	 BigDecimal(strings[9]));
        loadForesult.setF10(new	 BigDecimal(strings[10]));
        loadForesult.setF11(new	 BigDecimal(strings[11]));
        loadForesult.setF12(new	 BigDecimal(strings[12]));
        loadForesult.setF13(new	 BigDecimal(strings[13]));
        loadForesult.setF14(new	 BigDecimal(strings[14]));
        loadForesult.setF15(new	 BigDecimal(strings[15]));
        loadForesult.setF16(new	 BigDecimal(strings[16]));
        loadForesult.setF17(new	 BigDecimal(strings[17]));
        loadForesult.setF18(new	 BigDecimal(strings[18]));
        loadForesult.setF19(new	 BigDecimal(strings[19]));
        loadForesult.setF20(new	 BigDecimal(strings[20]));
        loadForesult.setF21(new	 BigDecimal(strings[21]));
        loadForesult.setF22(new	 BigDecimal(strings[22]));
        loadForesult.setF23(new	 BigDecimal(strings[23]));
        loadForesult.setF24(new	 BigDecimal(strings[24]));
        loadForesult.setF25(new	 BigDecimal(strings[25]));
        loadForesult.setF26(new	 BigDecimal(strings[26]));
        loadForesult.setF27(new	 BigDecimal(strings[27]));
        loadForesult.setF28(new	 BigDecimal(strings[28]));
        loadForesult.setF29(new	 BigDecimal(strings[29]));
        loadForesult.setF30(new	 BigDecimal(strings[30]));
        loadForesult.setF31(new	 BigDecimal(strings[31]));
        loadForesult.setF32(new	 BigDecimal(strings[32]));
        loadForesult.setF33(new	 BigDecimal(strings[33]));
        loadForesult.setF34(new	 BigDecimal(strings[34]));
        loadForesult.setF35(new	 BigDecimal(strings[35]));
        loadForesult.setF36(new	 BigDecimal(strings[36]));
        loadForesult.setF37(new	 BigDecimal(strings[37]));
        loadForesult.setF38(new	 BigDecimal(strings[38]));
        loadForesult.setF39(new	 BigDecimal(strings[39]));
        loadForesult.setF40(new	 BigDecimal(strings[40]));
        loadForesult.setF41(new	 BigDecimal(strings[41]));
        loadForesult.setF42(new	 BigDecimal(strings[42]));
        loadForesult.setF43(new	 BigDecimal(strings[43]));
        loadForesult.setF44(new	 BigDecimal(strings[44]));
        loadForesult.setF45(new	 BigDecimal(strings[45]));
        loadForesult.setF46(new	 BigDecimal(strings[46]));
        loadForesult.setF47(new	 BigDecimal(strings[47]));
        loadForesult.setF48(new	 BigDecimal(strings[48]));
        loadForesult.setF49(new	 BigDecimal(strings[49]));
        loadForesult.setF50(new	 BigDecimal(strings[50]));
        loadForesult.setF51(new	 BigDecimal(strings[51]));
        loadForesult.setF52(new	 BigDecimal(strings[52]));
        loadForesult.setF53(new	 BigDecimal(strings[53]));
        loadForesult.setF54(new	 BigDecimal(strings[54]));
        loadForesult.setF55(new	 BigDecimal(strings[55]));
        loadForesult.setF56(new	 BigDecimal(strings[56]));
        loadForesult.setF57(new	 BigDecimal(strings[57]));
        loadForesult.setF58(new	 BigDecimal(strings[58]));
        loadForesult.setF59(new	 BigDecimal(strings[59]));
        loadForesult.setF60(new	 BigDecimal(strings[60]));
        loadForesult.setF61(new	 BigDecimal(strings[61]));
        loadForesult.setF62(new	 BigDecimal(strings[62]));
        loadForesult.setF63(new	 BigDecimal(strings[63]));
        loadForesult.setF64(new	 BigDecimal(strings[64]));
        loadForesult.setF65(new	 BigDecimal(strings[65]));
        loadForesult.setF66(new	 BigDecimal(strings[66]));
        loadForesult.setF67(new	 BigDecimal(strings[67]));
        loadForesult.setF68(new	 BigDecimal(strings[68]));
        loadForesult.setF69(new	 BigDecimal(strings[69]));
        loadForesult.setF70(new	 BigDecimal(strings[70]));
        loadForesult.setF71(new	 BigDecimal(strings[71]));
        loadForesult.setF72(new	 BigDecimal(strings[72]));
        loadForesult.setF73(new	 BigDecimal(strings[73]));
        loadForesult.setF74(new	 BigDecimal(strings[74]));
        loadForesult.setF75(new	 BigDecimal(strings[75]));
        loadForesult.setF76(new	 BigDecimal(strings[76]));
        loadForesult.setF77(new	 BigDecimal(strings[77]));
        loadForesult.setF78(new	 BigDecimal(strings[78]));
        loadForesult.setF79(new	 BigDecimal(strings[79]));
        loadForesult.setF80(new	 BigDecimal(strings[80]));
        loadForesult.setF81(new	 BigDecimal(strings[81]));
        loadForesult.setF82(new	 BigDecimal(strings[82]));
        loadForesult.setF83(new	 BigDecimal(strings[83]));
        loadForesult.setF84(new	 BigDecimal(strings[84]));
        loadForesult.setF85(new	 BigDecimal(strings[85]));
        loadForesult.setF86(new	 BigDecimal(strings[86]));
        loadForesult.setF87(new	 BigDecimal(strings[87]));
        loadForesult.setF88(new	 BigDecimal(strings[88]));
        loadForesult.setF89(new	 BigDecimal(strings[89]));
        loadForesult.setF90(new	 BigDecimal(strings[90]));
        loadForesult.setF91(new	 BigDecimal(strings[91]));
        loadForesult.setF92(new	 BigDecimal(strings[92]));
        loadForesult.setF93(new	 BigDecimal(strings[93]));
        loadForesult.setF94(new	 BigDecimal(strings[94]));
        loadForesult.setF95(new	 BigDecimal(strings[95]));
        loadForesult.setF96(new	 BigDecimal(strings[96]));
        loadForesult.setCurrentSchema(ReadProperties.readProperties("/db.properties","currentSchema"));

        if (loadForesultMap!=null){
         //   number=loadForesultMapper.update(loadForesult);
            try {
                number=deleteFunction.updateTable("loadfor.load_foresult",strKey,sB.toString(),conditions);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            number=loadForesultMapper.insert(loadForesult);
        }
        return number;
    }

    @Override
    public int update(LoadForesult loadForesult) {
        loadForesult.setCurrentSchema(ReadProperties.readProperties("/db.properties","currentSchema"));
        return loadForesultMapper.update(loadForesult);
    }

    @Override
    public List<LoadForesult> selectAll() {
        return loadForesultMapper.selectAll();
    }
}
