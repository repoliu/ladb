package com.dky.entity;

import com.dky.util.StringUtil;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

/**
 * Created by Henry on 2016/7/29.
 * 实体基类，用于存放共有的属性。
 */
public class BasicBean implements Serializable {

    private String caseid;

    private List<String> dates;

    private Vector<Double> vector96 = null;

    private Double h01;
    private Double h02;
    private Double h03;
    private Double h04;
    private Double h05;
    private Double h06;
    private Double h07;
    private Double h08;
    private Double h09;
    private Double h10;

    private Double h11;
    private Double h12;
    private Double h13;
    private Double h14;
    private Double h15;
    private Double h16;
    private Double h17;
    private Double h18;
    private Double h19;
    private Double h20;

    private Double h21;
    private Double h22;
    private Double h23;
    private Double h24;
    private Double h25;
    private Double h26;
    private Double h27;
    private Double h28;
    private Double h29;
    private Double h30;

    private Double h31;
    private Double h32;
    private Double h33;
    private Double h34;
    private Double h35;
    private Double h36;
    private Double h37;
    private Double h38;
    private Double h39;
    private Double h40;

    private Double h41;
    private Double h42;
    private Double h43;
    private Double h44;
    private Double h45;
    private Double h46;
    private Double h47;
    private Double h48;
    private Double h49;
    private Double h50;

    private Double h51;
    private Double h52;
    private Double h53;
    private Double h54;
    private Double h55;
    private Double h56;
    private Double h57;
    private Double h58;
    private Double h59;
    private Double h60;

    private Double h61;
    private Double h62;
    private Double h63;
    private Double h64;
    private Double h65;
    private Double h66;
    private Double h67;
    private Double h68;
    private Double h69;
    private Double h70;

    private Double h71;
    private Double h72;
    private Double h73;
    private Double h74;
    private Double h75;
    private Double h76;
    private Double h77;
    private Double h78;
    private Double h79;
    private Double h80;

    private Double h81;
    private Double h82;
    private Double h83;
    private Double h84;
    private Double h85;
    private Double h86;
    private Double h87;
    private Double h88;
    private Double h89;
    private Double h90;

    private Double h91;
    private Double h92;
    private Double h93;
    private Double h94;
    private Double h95;
    private Double h96;


    private Double f00;
    private Double f01;
    private Double f02;
    private Double f03;
    private Double f04;
    private Double f05;
    private Double f06;
    private Double f07;
    private Double f08;
    private Double f09;
    private Double f10;

    private Double f11;
    private Double f12;
    private Double f13;
    private Double f14;
    private Double f15;
    private Double f16;
    private Double f17;
    private Double f18;
    private Double f19;
    private Double f20;

    private Double f21;
    private Double f22;
    private Double f23;
    private Double f24;
    private Double f25;
    private Double f26;
    private Double f27;
    private Double f28;
    private Double f29;
    private Double f30;

    private Double f31;
    private Double f32;
    private Double f33;
    private Double f34;
    private Double f35;
    private Double f36;
    private Double f37;
    private Double f38;
    private Double f39;
    private Double f40;

    private Double f41;
    private Double f42;
    private Double f43;
    private Double f44;
    private Double f45;
    private Double f46;
    private Double f47;
    private Double f48;
    private Double f49;
    private Double f50;

    private Double f51;
    private Double f52;
    private Double f53;
    private Double f54;
    private Double f55;
    private Double f56;
    private Double f57;
    private Double f58;
    private Double f59;
    private Double f60;

    private Double f61;
    private Double f62;
    private Double f63;
    private Double f64;
    private Double f65;
    private Double f66;
    private Double f67;
    private Double f68;
    private Double f69;
    private Double f70;

    private Double f71;
    private Double f72;
    private Double f73;
    private Double f74;
    private Double f75;
    private Double f76;
    private Double f77;
    private Double f78;
    private Double f79;
    private Double f80;

    private Double f81;
    private Double f82;
    private Double f83;
    private Double f84;
    private Double f85;
    private Double f86;
    private Double f87;
    private Double f88;
    private Double f89;
    private Double f90;

    private Double f91;
    private Double f92;
    private Double f93;
    private Double f94;
    private Double f95;


    private Double f96;
    private Double f97;
    private Double f98;
    private Double f99;
    private Double f100;
    private Double f101;
    private Double f102;
    private Double f103;
    private Double f104;
    private Double f105;

    private Double f106;
    private Double f107;
    private Double f108;
    private Double f109;
    private Double f110;
    private Double f111;
    private Double f112;
    private Double f113;
    private Double f114;
    private Double f115;
    private Double f116;
    private Double f117;
    private Double f118;
    private Double f119;

    private Double f120;
    private Double f121;
    private Double f122;
    private Double f123;
    private Double f124;
    private Double f125;
    private Double f126;
    private Double f127;
    private Double f128;
    private Double f129;

    private Double f130;
    private Double f131;
    private Double f132;
    private Double f133;
    private Double f134;
    private Double f135;
    private Double f136;
    private Double f137;
    private Double f138;
    private Double f139;

    private Double f140;
    private Double f141;
    private Double f142;
    private Double f143;
    private Double f144;
    private Double f145;
    private Double f146;
    private Double f147;
    private Double f148;
    private Double f149;

    private Double f150;
    private Double f151;
    private Double f152;
    private Double f153;
    private Double f154;
    private Double f155;
    private Double f156;
    private Double f157;
    private Double f158;
    private Double f159;

    private Double f160;
    private Double f161;
    private Double f162;
    private Double f163;
    private Double f164;
    private Double f165;
    private Double f166;
    private Double f167;
    private Double f168;
    private Double f169;

    private Double f170;
    private Double f171;
    private Double f172;
    private Double f173;
    private Double f174;
    private Double f175;
    private Double f176;
    private Double f177;
    private Double f178;
    private Double f179;

    private Double f180;
    private Double f181;
    private Double f182;
    private Double f183;
    private Double f184;
    private Double f185;
    private Double f186;
    private Double f187;
    private Double f188;
    private Double f189;

    private Double f190;
    private Double f191;
    private Double f192;
    private Double f193;
    private Double f194;
    private Double f195;
    private Double f196;
    private Double f197;
    private Double f198;
    private Double f199;

    private Double f200;
    private Double f201;
    private Double f202;
    private Double f203;
    private Double f204;
    private Double f205;

    private Double f206;
    private Double f207;
    private Double f208;
    private Double f209;
    private Double f210;
    private Double f211;
    private Double f212;
    private Double f213;
    private Double f214;
    private Double f215;
    private Double f216;
    private Double f217;
    private Double f218;
    private Double f219;

    private Double f220;
    private Double f221;
    private Double f222;
    private Double f223;
    private Double f224;
    private Double f225;
    private Double f226;
    private Double f227;
    private Double f228;
    private Double f229;

    private Double f230;
    private Double f231;
    private Double f232;
    private Double f233;
    private Double f234;
    private Double f235;
    private Double f236;
    private Double f237;
    private Double f238;
    private Double f239;

    private Double f240;
    private Double f241;
    private Double f242;
    private Double f243;
    private Double f244;
    private Double f245;
    private Double f246;
    private Double f247;
    private Double f248;
    private Double f249;

    private Double f250;
    private Double f251;
    private Double f252;
    private Double f253;
    private Double f254;
    private Double f255;
    private Double f256;
    private Double f257;
    private Double f258;
    private Double f259;

    private Double f260;
    private Double f261;
    private Double f262;
    private Double f263;
    private Double f264;
    private Double f265;
    private Double f266;
    private Double f267;
    private Double f268;
    private Double f269;

    private Double f270;
    private Double f271;
    private Double f272;
    private Double f273;
    private Double f274;
    private Double f275;
    private Double f276;
    private Double f277;
    private Double f278;
    private Double f279;

    private Double f280;
    private Double f281;
    private Double f282;
    private Double f283;
    private Double f284;
    private Double f285;
    private Double f286;
    private Double f287;
    private Double f288;

    private Double l01;
    private Double l02;
    private Double l03;
    private Double l04;
    private Double l05;
    private Double l06;
    private Double l07;
    private Double l08;
    private Double l09;
    private Double l10;

    private Double l11;
    private Double l12;
    private Double l13;
    private Double l14;
    private Double l15;
    private Double l16;
    private Double l17;
    private Double l18;
    private Double l19;
    private Double l20;

    private Double l21;
    private Double l22;
    private Double l23;
    private Double l24;
    private Double l25;
    private Double l26;
    private Double l27;
    private Double l28;
    private Double l29;
    private Double l30;

    private Double l31;
    private Double l32;
    private Double l33;
    private Double l34;
    private Double l35;
    private Double l36;
    private Double l37;
    private Double l38;
    private Double l39;
    private Double l40;

    private Double l41;
    private Double l42;
    private Double l43;
    private Double l44;
    private Double l45;
    private Double l46;
    private Double l47;
    private Double l48;
    private Double l49;
    private Double l50;

    private Double l51;
    private Double l52;
    private Double l53;
    private Double l54;
    private Double l55;
    private Double l56;
    private Double l57;
    private Double l58;
    private Double l59;
    private Double l60;

    private Double l61;
    private Double l62;
    private Double l63;
    private Double l64;
    private Double l65;
    private Double l66;
    private Double l67;
    private Double l68;
    private Double l69;
    private Double l70;

    private Double l71;
    private Double l72;
    private Double l73;
    private Double l74;
    private Double l75;
    private Double l76;
    private Double l77;
    private Double l78;
    private Double l79;
    private Double l80;

    private Double l81;
    private Double l82;
    private Double l83;
    private Double l84;
    private Double l85;
    private Double l86;
    private Double l87;
    private Double l88;
    private Double l89;
    private Double l90;

    private Double l91;
    private Double l92;
    private Double l93;
    private Double l94;
    private Double l95;
    private Double l96;

    public String getCaseid() {
        return caseid;
    }


//    @JsonFormat(pattern = "HH:mm")
//    public List<Date> getDates() {
//        dates = new ArrayList<Date>();
//        Calendar calendar = Calendar.getInstance();
//        for (int i=1;i<=96;i++) {
//            String[] hm = StringUtil.getNXTime(i).split(":");
//            calendar.set(Integer.parseInt(caseid.substring(0, 4)), (Integer.parseInt(caseid.substring(4, 6)) - 1), Integer.parseInt(caseid.substring(6, 8)), Integer.parseInt(hm[0]), Integer.parseInt(hm[1]));
////            JsonMapper.toJsonString(calendar.getTime());
//            dates.add(calendar.getTime());
//        }
//        return dates;
//    }

    public List<String> getDates() {
        dates = new ArrayList<String>();
        for (int i=1;i<=96;i++) {
            dates.add(StringUtil.getNXTime(i));
        }
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    public void setCaseid(String caseid) {
        this.caseid = caseid;
    }

    public Double getH01() {
        return h01;
    }

    public void setH01(Double h01) {
        this.h01 = h01;
    }

    public Double getH02() {
        return h02;
    }

    public void setH02(Double h02) {
        this.h02 = h02;
    }

    public Double getH03() {
        return h03;
    }

    public void setH03(Double h03) {
        this.h03 = h03;
    }

    public Double getH04() {
        return h04;
    }

    public void setH04(Double h04) {
        this.h04 = h04;
    }

    public Double getH05() {
        return h05;
    }

    public void setH05(Double h05) {
        this.h05 = h05;
    }

    public Double getH06() {
        return h06;
    }

    public void setH06(Double h06) {
        this.h06 = h06;
    }

    public Double getH07() {
        return h07;
    }

    public void setH07(Double h07) {
        this.h07 = h07;
    }

    public Double getH08() {
        return h08;
    }

    public void setH08(Double h08) {
        this.h08 = h08;
    }

    public Double getH09() {
        return h09;
    }

    public void setH09(Double h09) {
        this.h09 = h09;
    }

    public Double getH10() {
        return h10;
    }

    public void setH10(Double h10) {
        this.h10 = h10;
    }

    public Double getH11() {
        return h11;
    }

    public void setH11(Double h11) {
        this.h11 = h11;
    }

    public Double getH12() {
        return h12;
    }

    public void setH12(Double h12) {
        this.h12 = h12;
    }

    public Double getH13() {
        return h13;
    }

    public void setH13(Double h13) {
        this.h13 = h13;
    }

    public Double getH14() {
        return h14;
    }

    public void setH14(Double h14) {
        this.h14 = h14;
    }

    public Double getH15() {
        return h15;
    }

    public void setH15(Double h15) {
        this.h15 = h15;
    }

    public Double getH16() {
        return h16;
    }

    public void setH16(Double h16) {
        this.h16 = h16;
    }

    public Double getH17() {
        return h17;
    }

    public void setH17(Double h17) {
        this.h17 = h17;
    }

    public Double getH18() {
        return h18;
    }

    public void setH18(Double h18) {
        this.h18 = h18;
    }

    public Double getH19() {
        return h19;
    }

    public void setH19(Double h19) {
        this.h19 = h19;
    }

    public Double getH20() {
        return h20;
    }

    public void setH20(Double h20) {
        this.h20 = h20;
    }

    public Double getH21() {
        return h21;
    }

    public void setH21(Double h21) {
        this.h21 = h21;
    }

    public Double getH22() {
        return h22;
    }

    public void setH22(Double h22) {
        this.h22 = h22;
    }

    public Double getH23() {
        return h23;
    }

    public void setH23(Double h23) {
        this.h23 = h23;
    }

    public Double getH24() {
        return h24;
    }

    public void setH24(Double h24) {
        this.h24 = h24;
    }

    public Double getH25() {
        return h25;
    }

    public void setH25(Double h25) {
        this.h25 = h25;
    }

    public Double getH26() {
        return h26;
    }

    public void setH26(Double h26) {
        this.h26 = h26;
    }

    public Double getH27() {
        return h27;
    }

    public void setH27(Double h27) {
        this.h27 = h27;
    }

    public Double getH28() {
        return h28;
    }

    public void setH28(Double h28) {
        this.h28 = h28;
    }

    public Double getH29() {
        return h29;
    }

    public void setH29(Double h29) {
        this.h29 = h29;
    }

    public Double getH30() {
        return h30;
    }

    public void setH30(Double h30) {
        this.h30 = h30;
    }

    public Double getH31() {
        return h31;
    }

    public void setH31(Double h31) {
        this.h31 = h31;
    }

    public Double getH32() {
        return h32;
    }

    public void setH32(Double h32) {
        this.h32 = h32;
    }

    public Double getH33() {
        return h33;
    }

    public void setH33(Double h33) {
        this.h33 = h33;
    }

    public Double getH34() {
        return h34;
    }

    public void setH34(Double h34) {
        this.h34 = h34;
    }

    public Double getH35() {
        return h35;
    }

    public void setH35(Double h35) {
        this.h35 = h35;
    }

    public Double getH36() {
        return h36;
    }

    public void setH36(Double h36) {
        this.h36 = h36;
    }

    public Double getH37() {
        return h37;
    }

    public void setH37(Double h37) {
        this.h37 = h37;
    }

    public Double getH38() {
        return h38;
    }

    public void setH38(Double h38) {
        this.h38 = h38;
    }

    public Double getH39() {
        return h39;
    }

    public void setH39(Double h39) {
        this.h39 = h39;
    }

    public Double getH40() {
        return h40;
    }

    public void setH40(Double h40) {
        this.h40 = h40;
    }

    public Double getH41() {
        return h41;
    }

    public void setH41(Double h41) {
        this.h41 = h41;
    }

    public Double getH42() {
        return h42;
    }

    public void setH42(Double h42) {
        this.h42 = h42;
    }

    public Double getH43() {
        return h43;
    }

    public void setH43(Double h43) {
        this.h43 = h43;
    }

    public Double getH44() {
        return h44;
    }

    public void setH44(Double h44) {
        this.h44 = h44;
    }

    public Double getH45() {
        return h45;
    }

    public void setH45(Double h45) {
        this.h45 = h45;
    }

    public Double getH46() {
        return h46;
    }

    public void setH46(Double h46) {
        this.h46 = h46;
    }

    public Double getH47() {
        return h47;
    }

    public void setH47(Double h47) {
        this.h47 = h47;
    }

    public Double getH48() {
        return h48;
    }

    public void setH48(Double h48) {
        this.h48 = h48;
    }

    public Double getH49() {
        return h49;
    }

    public void setH49(Double h49) {
        this.h49 = h49;
    }

    public Double getH50() {
        return h50;
    }

    public void setH50(Double h50) {
        this.h50 = h50;
    }

    public Double getH51() {
        return h51;
    }

    public void setH51(Double h51) {
        this.h51 = h51;
    }

    public Double getH52() {
        return h52;
    }

    public void setH52(Double h52) {
        this.h52 = h52;
    }

    public Double getH53() {
        return h53;
    }

    public void setH53(Double h53) {
        this.h53 = h53;
    }

    public Double getH54() {
        return h54;
    }

    public void setH54(Double h54) {
        this.h54 = h54;
    }

    public Double getH55() {
        return h55;
    }

    public void setH55(Double h55) {
        this.h55 = h55;
    }

    public Double getH56() {
        return h56;
    }

    public void setH56(Double h56) {
        this.h56 = h56;
    }

    public Double getH57() {
        return h57;
    }

    public void setH57(Double h57) {
        this.h57 = h57;
    }

    public Double getH58() {
        return h58;
    }

    public void setH58(Double h58) {
        this.h58 = h58;
    }

    public Double getH59() {
        return h59;
    }

    public void setH59(Double h59) {
        this.h59 = h59;
    }

    public Double getH60() {
        return h60;
    }

    public void setH60(Double h60) {
        this.h60 = h60;
    }

    public Double getH61() {
        return h61;
    }

    public void setH61(Double h61) {
        this.h61 = h61;
    }

    public Double getH62() {
        return h62;
    }

    public void setH62(Double h62) {
        this.h62 = h62;
    }

    public Double getH63() {
        return h63;
    }

    public void setH63(Double h63) {
        this.h63 = h63;
    }

    public Double getH64() {
        return h64;
    }

    public void setH64(Double h64) {
        this.h64 = h64;
    }

    public Double getH65() {
        return h65;
    }

    public void setH65(Double h65) {
        this.h65 = h65;
    }

    public Double getH66() {
        return h66;
    }

    public void setH66(Double h66) {
        this.h66 = h66;
    }

    public Double getH67() {
        return h67;
    }

    public void setH67(Double h67) {
        this.h67 = h67;
    }

    public Double getH68() {
        return h68;
    }

    public void setH68(Double h68) {
        this.h68 = h68;
    }

    public Double getH69() {
        return h69;
    }

    public void setH69(Double h69) {
        this.h69 = h69;
    }

    public Double getH70() {
        return h70;
    }

    public void setH70(Double h70) {
        this.h70 = h70;
    }

    public Double getH71() {
        return h71;
    }

    public void setH71(Double h71) {
        this.h71 = h71;
    }

    public Double getH72() {
        return h72;
    }

    public void setH72(Double h72) {
        this.h72 = h72;
    }

    public Double getH73() {
        return h73;
    }

    public void setH73(Double h73) {
        this.h73 = h73;
    }

    public Double getH74() {
        return h74;
    }

    public void setH74(Double h74) {
        this.h74 = h74;
    }

    public Double getH75() {
        return h75;
    }

    public void setH75(Double h75) {
        this.h75 = h75;
    }

    public Double getH76() {
        return h76;
    }

    public void setH76(Double h76) {
        this.h76 = h76;
    }

    public Double getH77() {
        return h77;
    }

    public void setH77(Double h77) {
        this.h77 = h77;
    }

    public Double getH78() {
        return h78;
    }

    public void setH78(Double h78) {
        this.h78 = h78;
    }

    public Double getH79() {
        return h79;
    }

    public void setH79(Double h79) {
        this.h79 = h79;
    }

    public Double getH80() {
        return h80;
    }

    public void setH80(Double h80) {
        this.h80 = h80;
    }

    public Double getH81() {
        return h81;
    }

    public void setH81(Double h81) {
        this.h81 = h81;
    }

    public Double getH82() {
        return h82;
    }

    public void setH82(Double h82) {
        this.h82 = h82;
    }

    public Double getH83() {
        return h83;
    }

    public void setH83(Double h83) {
        this.h83 = h83;
    }

    public Double getH84() {
        return h84;
    }

    public void setH84(Double h84) {
        this.h84 = h84;
    }

    public Double getH85() {
        return h85;
    }

    public void setH85(Double h85) {
        this.h85 = h85;
    }

    public Double getH86() {
        return h86;
    }

    public void setH86(Double h86) {
        this.h86 = h86;
    }

    public Double getH87() {
        return h87;
    }

    public void setH87(Double h87) {
        this.h87 = h87;
    }

    public Double getH88() {
        return h88;
    }

    public void setH88(Double h88) {
        this.h88 = h88;
    }

    public Double getH89() {
        return h89;
    }

    public void setH89(Double h89) {
        this.h89 = h89;
    }

    public Double getH90() {
        return h90;
    }

    public void setH90(Double h90) {
        this.h90 = h90;
    }

    public Double getH91() {
        return h91;
    }

    public void setH91(Double h91) {
        this.h91 = h91;
    }

    public Double getH92() {
        return h92;
    }

    public void setH92(Double h92) {
        this.h92 = h92;
    }

    public Double getH93() {
        return h93;
    }

    public void setH93(Double h93) {
        this.h93 = h93;
    }

    public Double getH94() {
        return h94;
    }

    public void setH94(Double h94) {
        this.h94 = h94;
    }

    public Double getH95() {
        return h95;
    }

    public void setH95(Double h95) {
        this.h95 = h95;
    }

    public Double getH96() {
        return h96;
    }

    public void setH96(Double h96) {
        this.h96 = h96;
    }

    public Double getF00() {
        return f00;
    }

    public void setF00(Double f00) {
        this.f00 = f00;
    }

    public Double getF01() {
        return f01;
    }

    public void setF01(Double f01) {
        this.f01 = f01;
    }

    public Double getF02() {
        return f02;
    }

    public void setF02(Double f02) {
        this.f02 = f02;
    }

    public Double getF03() {
        return f03;
    }

    public void setF03(Double f03) {
        this.f03 = f03;
    }

    public Double getF04() {
        return f04;
    }

    public void setF04(Double f04) {
        this.f04 = f04;
    }

    public Double getF05() {
        return f05;
    }

    public void setF05(Double f05) {
        this.f05 = f05;
    }

    public Double getF06() {
        return f06;
    }

    public void setF06(Double f06) {
        this.f06 = f06;
    }

    public Double getF07() {
        return f07;
    }

    public void setF07(Double f07) {
        this.f07 = f07;
    }

    public Double getF08() {
        return f08;
    }

    public void setF08(Double f08) {
        this.f08 = f08;
    }

    public Double getF09() {
        return f09;
    }

    public void setF09(Double f09) {
        this.f09 = f09;
    }

    public Double getF10() {
        return f10;
    }

    public void setF10(Double f10) {
        this.f10 = f10;
    }

    public Double getF11() {
        return f11;
    }

    public void setF11(Double f11) {
        this.f11 = f11;
    }

    public Double getF12() {
        return f12;
    }

    public void setF12(Double f12) {
        this.f12 = f12;
    }

    public Double getF13() {
        return f13;
    }

    public void setF13(Double f13) {
        this.f13 = f13;
    }

    public Double getF14() {
        return f14;
    }

    public void setF14(Double f14) {
        this.f14 = f14;
    }

    public Double getF15() {
        return f15;
    }

    public void setF15(Double f15) {
        this.f15 = f15;
    }

    public Double getF16() {
        return f16;
    }

    public void setF16(Double f16) {
        this.f16 = f16;
    }

    public Double getF17() {
        return f17;
    }

    public void setF17(Double f17) {
        this.f17 = f17;
    }

    public Double getF18() {
        return f18;
    }

    public void setF18(Double f18) {
        this.f18 = f18;
    }

    public Double getF19() {
        return f19;
    }

    public void setF19(Double f19) {
        this.f19 = f19;
    }

    public Double getF20() {
        return f20;
    }

    public void setF20(Double f20) {
        this.f20 = f20;
    }

    public Double getF21() {
        return f21;
    }

    public void setF21(Double f21) {
        this.f21 = f21;
    }

    public Double getF22() {
        return f22;
    }

    public void setF22(Double f22) {
        this.f22 = f22;
    }

    public Double getF23() {
        return f23;
    }

    public void setF23(Double f23) {
        this.f23 = f23;
    }

    public Double getF24() {
        return f24;
    }

    public void setF24(Double f24) {
        this.f24 = f24;
    }

    public Double getF25() {
        return f25;
    }

    public void setF25(Double f25) {
        this.f25 = f25;
    }

    public Double getF26() {
        return f26;
    }

    public void setF26(Double f26) {
        this.f26 = f26;
    }

    public Double getF27() {
        return f27;
    }

    public void setF27(Double f27) {
        this.f27 = f27;
    }

    public Double getF28() {
        return f28;
    }

    public void setF28(Double f28) {
        this.f28 = f28;
    }

    public Double getF29() {
        return f29;
    }

    public void setF29(Double f29) {
        this.f29 = f29;
    }

    public Double getF30() {
        return f30;
    }

    public void setF30(Double f30) {
        this.f30 = f30;
    }

    public Double getF31() {
        return f31;
    }

    public void setF31(Double f31) {
        this.f31 = f31;
    }

    public Double getF32() {
        return f32;
    }

    public void setF32(Double f32) {
        this.f32 = f32;
    }

    public Double getF33() {
        return f33;
    }

    public void setF33(Double f33) {
        this.f33 = f33;
    }

    public Double getF34() {
        return f34;
    }

    public void setF34(Double f34) {
        this.f34 = f34;
    }

    public Double getF35() {
        return f35;
    }

    public void setF35(Double f35) {
        this.f35 = f35;
    }

    public Double getF36() {
        return f36;
    }

    public void setF36(Double f36) {
        this.f36 = f36;
    }

    public Double getF37() {
        return f37;
    }

    public void setF37(Double f37) {
        this.f37 = f37;
    }

    public Double getF38() {
        return f38;
    }

    public void setF38(Double f38) {
        this.f38 = f38;
    }

    public Double getF39() {
        return f39;
    }

    public void setF39(Double f39) {
        this.f39 = f39;
    }

    public Double getF40() {
        return f40;
    }

    public void setF40(Double f40) {
        this.f40 = f40;
    }

    public Double getF41() {
        return f41;
    }

    public void setF41(Double f41) {
        this.f41 = f41;
    }

    public Double getF42() {
        return f42;
    }

    public void setF42(Double f42) {
        this.f42 = f42;
    }

    public Double getF43() {
        return f43;
    }

    public void setF43(Double f43) {
        this.f43 = f43;
    }

    public Double getF44() {
        return f44;
    }

    public void setF44(Double f44) {
        this.f44 = f44;
    }

    public Double getF45() {
        return f45;
    }

    public void setF45(Double f45) {
        this.f45 = f45;
    }

    public Double getF46() {
        return f46;
    }

    public void setF46(Double f46) {
        this.f46 = f46;
    }

    public Double getF47() {
        return f47;
    }

    public void setF47(Double f47) {
        this.f47 = f47;
    }

    public Double getF48() {
        return f48;
    }

    public void setF48(Double f48) {
        this.f48 = f48;
    }

    public Double getF49() {
        return f49;
    }

    public void setF49(Double f49) {
        this.f49 = f49;
    }

    public Double getF50() {
        return f50;
    }

    public void setF50(Double f50) {
        this.f50 = f50;
    }

    public Double getF51() {
        return f51;
    }

    public void setF51(Double f51) {
        this.f51 = f51;
    }

    public Double getF52() {
        return f52;
    }

    public void setF52(Double f52) {
        this.f52 = f52;
    }

    public Double getF53() {
        return f53;
    }

    public void setF53(Double f53) {
        this.f53 = f53;
    }

    public Double getF54() {
        return f54;
    }

    public void setF54(Double f54) {
        this.f54 = f54;
    }

    public Double getF55() {
        return f55;
    }

    public void setF55(Double f55) {
        this.f55 = f55;
    }

    public Double getF56() {
        return f56;
    }

    public void setF56(Double f56) {
        this.f56 = f56;
    }

    public Double getF57() {
        return f57;
    }

    public void setF57(Double f57) {
        this.f57 = f57;
    }

    public Double getF58() {
        return f58;
    }

    public void setF58(Double f58) {
        this.f58 = f58;
    }

    public Double getF59() {
        return f59;
    }

    public void setF59(Double f59) {
        this.f59 = f59;
    }

    public Double getF60() {
        return f60;
    }

    public void setF60(Double f60) {
        this.f60 = f60;
    }

    public Double getF61() {
        return f61;
    }

    public void setF61(Double f61) {
        this.f61 = f61;
    }

    public Double getF62() {
        return f62;
    }

    public void setF62(Double f62) {
        this.f62 = f62;
    }

    public Double getF63() {
        return f63;
    }

    public void setF63(Double f63) {
        this.f63 = f63;
    }

    public Double getF64() {
        return f64;
    }

    public void setF64(Double f64) {
        this.f64 = f64;
    }

    public Double getF65() {
        return f65;
    }

    public void setF65(Double f65) {
        this.f65 = f65;
    }

    public Double getF66() {
        return f66;
    }

    public void setF66(Double f66) {
        this.f66 = f66;
    }

    public Double getF67() {
        return f67;
    }

    public void setF67(Double f67) {
        this.f67 = f67;
    }

    public Double getF68() {
        return f68;
    }

    public void setF68(Double f68) {
        this.f68 = f68;
    }

    public Double getF69() {
        return f69;
    }

    public void setF69(Double f69) {
        this.f69 = f69;
    }

    public Double getF70() {
        return f70;
    }

    public void setF70(Double f70) {
        this.f70 = f70;
    }

    public Double getF71() {
        return f71;
    }

    public void setF71(Double f71) {
        this.f71 = f71;
    }

    public Double getF72() {
        return f72;
    }

    public void setF72(Double f72) {
        this.f72 = f72;
    }

    public Double getF73() {
        return f73;
    }

    public void setF73(Double f73) {
        this.f73 = f73;
    }

    public Double getF74() {
        return f74;
    }

    public void setF74(Double f74) {
        this.f74 = f74;
    }

    public Double getF75() {
        return f75;
    }

    public void setF75(Double f75) {
        this.f75 = f75;
    }

    public Double getF76() {
        return f76;
    }

    public void setF76(Double f76) {
        this.f76 = f76;
    }

    public Double getF77() {
        return f77;
    }

    public void setF77(Double f77) {
        this.f77 = f77;
    }

    public Double getF78() {
        return f78;
    }

    public void setF78(Double f78) {
        this.f78 = f78;
    }

    public Double getF79() {
        return f79;
    }

    public void setF79(Double f79) {
        this.f79 = f79;
    }

    public Double getF80() {
        return f80;
    }

    public void setF80(Double f80) {
        this.f80 = f80;
    }

    public Double getF81() {
        return f81;
    }

    public void setF81(Double f81) {
        this.f81 = f81;
    }

    public Double getF82() {
        return f82;
    }

    public void setF82(Double f82) {
        this.f82 = f82;
    }

    public Double getF83() {
        return f83;
    }

    public void setF83(Double f83) {
        this.f83 = f83;
    }

    public Double getF84() {
        return f84;
    }

    public void setF84(Double f84) {
        this.f84 = f84;
    }

    public Double getF85() {
        return f85;
    }

    public void setF85(Double f85) {
        this.f85 = f85;
    }

    public Double getF86() {
        return f86;
    }

    public void setF86(Double f86) {
        this.f86 = f86;
    }

    public Double getF87() {
        return f87;
    }

    public void setF87(Double f87) {
        this.f87 = f87;
    }

    public Double getF88() {
        return f88;
    }

    public void setF88(Double f88) {
        this.f88 = f88;
    }

    public Double getF89() {
        return f89;
    }

    public void setF89(Double f89) {
        this.f89 = f89;
    }

    public Double getF90() {
        return f90;
    }

    public void setF90(Double f90) {
        this.f90 = f90;
    }

    public Double getF91() {
        return f91;
    }

    public void setF91(Double f91) {
        this.f91 = f91;
    }

    public Double getF92() {
        return f92;
    }

    public void setF92(Double f92) {
        this.f92 = f92;
    }

    public Double getF93() {
        return f93;
    }

    public void setF93(Double f93) {
        this.f93 = f93;
    }

    public Double getF94() {
        return f94;
    }

    public void setF94(Double f94) {
        this.f94 = f94;
    }

    public Double getF95() {
        return f95;
    }

    public void setF95(Double f95) {
        this.f95 = f95;
    }

    public Double getF96() {
        return f96;
    }

    public void setF96(Double f96) {
        this.f96 = f96;
    }

    public Double getF97() {
        return f97;
    }

    public void setF97(Double f97) {
        this.f97 = f97;
    }

    public Double getF98() {
        return f98;
    }

    public void setF98(Double f98) {
        this.f98 = f98;
    }

    public Double getF99() {
        return f99;
    }

    public void setF99(Double f99) {
        this.f99 = f99;
    }

    public Double getF100() {
        return f100;
    }

    public void setF100(Double f100) {
        this.f100 = f100;
    }

    public Double getF101() {
        return f101;
    }

    public void setF101(Double f101) {
        this.f101 = f101;
    }

    public Double getF102() {
        return f102;
    }

    public void setF102(Double f102) {
        this.f102 = f102;
    }

    public Double getF103() {
        return f103;
    }

    public void setF103(Double f103) {
        this.f103 = f103;
    }

    public Double getF104() {
        return f104;
    }

    public void setF104(Double f104) {
        this.f104 = f104;
    }

    public Double getF105() {
        return f105;
    }

    public void setF105(Double f105) {
        this.f105 = f105;
    }

    public Double getF106() {
        return f106;
    }

    public void setF106(Double f106) {
        this.f106 = f106;
    }

    public Double getF107() {
        return f107;
    }

    public void setF107(Double f107) {
        this.f107 = f107;
    }

    public Double getF108() {
        return f108;
    }

    public void setF108(Double f108) {
        this.f108 = f108;
    }

    public Double getF109() {
        return f109;
    }

    public void setF109(Double f109) {
        this.f109 = f109;
    }

    public Double getF110() {
        return f110;
    }

    public void setF110(Double f110) {
        this.f110 = f110;
    }

    public Double getF111() {
        return f111;
    }

    public void setF111(Double f111) {
        this.f111 = f111;
    }

    public Double getF112() {
        return f112;
    }

    public void setF112(Double f112) {
        this.f112 = f112;
    }

    public Double getF113() {
        return f113;
    }

    public void setF113(Double f113) {
        this.f113 = f113;
    }

    public Double getF114() {
        return f114;
    }

    public void setF114(Double f114) {
        this.f114 = f114;
    }

    public Double getF115() {
        return f115;
    }

    public void setF115(Double f115) {
        this.f115 = f115;
    }

    public Double getF116() {
        return f116;
    }

    public void setF116(Double f116) {
        this.f116 = f116;
    }

    public Double getF117() {
        return f117;
    }

    public void setF117(Double f117) {
        this.f117 = f117;
    }

    public Double getF118() {
        return f118;
    }

    public void setF118(Double f118) {
        this.f118 = f118;
    }

    public Double getF119() {
        return f119;
    }

    public void setF119(Double f119) {
        this.f119 = f119;
    }

    public Double getF120() {
        return f120;
    }

    public void setF120(Double f120) {
        this.f120 = f120;
    }

    public Double getF121() {
        return f121;
    }

    public void setF121(Double f121) {
        this.f121 = f121;
    }

    public Double getF122() {
        return f122;
    }

    public void setF122(Double f122) {
        this.f122 = f122;
    }

    public Double getF123() {
        return f123;
    }

    public void setF123(Double f123) {
        this.f123 = f123;
    }

    public Double getF124() {
        return f124;
    }

    public void setF124(Double f124) {
        this.f124 = f124;
    }

    public Double getF125() {
        return f125;
    }

    public void setF125(Double f125) {
        this.f125 = f125;
    }

    public Double getF126() {
        return f126;
    }

    public void setF126(Double f126) {
        this.f126 = f126;
    }

    public Double getF127() {
        return f127;
    }

    public void setF127(Double f127) {
        this.f127 = f127;
    }

    public Double getF128() {
        return f128;
    }

    public void setF128(Double f128) {
        this.f128 = f128;
    }

    public Double getF129() {
        return f129;
    }

    public void setF129(Double f129) {
        this.f129 = f129;
    }

    public Double getF130() {
        return f130;
    }

    public void setF130(Double f130) {
        this.f130 = f130;
    }

    public Double getF131() {
        return f131;
    }

    public void setF131(Double f131) {
        this.f131 = f131;
    }

    public Double getF132() {
        return f132;
    }

    public void setF132(Double f132) {
        this.f132 = f132;
    }

    public Double getF133() {
        return f133;
    }

    public void setF133(Double f133) {
        this.f133 = f133;
    }

    public Double getF134() {
        return f134;
    }

    public void setF134(Double f134) {
        this.f134 = f134;
    }

    public Double getF135() {
        return f135;
    }

    public void setF135(Double f135) {
        this.f135 = f135;
    }

    public Double getF136() {
        return f136;
    }

    public void setF136(Double f136) {
        this.f136 = f136;
    }

    public Double getF137() {
        return f137;
    }

    public void setF137(Double f137) {
        this.f137 = f137;
    }

    public Double getF138() {
        return f138;
    }

    public void setF138(Double f138) {
        this.f138 = f138;
    }

    public Double getF139() {
        return f139;
    }

    public void setF139(Double f139) {
        this.f139 = f139;
    }

    public Double getF140() {
        return f140;
    }

    public void setF140(Double f140) {
        this.f140 = f140;
    }

    public Double getF141() {
        return f141;
    }

    public void setF141(Double f141) {
        this.f141 = f141;
    }

    public Double getF142() {
        return f142;
    }

    public void setF142(Double f142) {
        this.f142 = f142;
    }

    public Double getF143() {
        return f143;
    }

    public void setF143(Double f143) {
        this.f143 = f143;
    }

    public Double getF144() {
        return f144;
    }

    public void setF144(Double f144) {
        this.f144 = f144;
    }

    public Double getF145() {
        return f145;
    }

    public void setF145(Double f145) {
        this.f145 = f145;
    }

    public Double getF146() {
        return f146;
    }

    public void setF146(Double f146) {
        this.f146 = f146;
    }

    public Double getF147() {
        return f147;
    }

    public void setF147(Double f147) {
        this.f147 = f147;
    }

    public Double getF148() {
        return f148;
    }

    public void setF148(Double f148) {
        this.f148 = f148;
    }

    public Double getF149() {
        return f149;
    }

    public void setF149(Double f149) {
        this.f149 = f149;
    }

    public Double getF150() {
        return f150;
    }

    public void setF150(Double f150) {
        this.f150 = f150;
    }

    public Double getF151() {
        return f151;
    }

    public void setF151(Double f151) {
        this.f151 = f151;
    }

    public Double getF152() {
        return f152;
    }

    public void setF152(Double f152) {
        this.f152 = f152;
    }

    public Double getF153() {
        return f153;
    }

    public void setF153(Double f153) {
        this.f153 = f153;
    }

    public Double getF154() {
        return f154;
    }

    public void setF154(Double f154) {
        this.f154 = f154;
    }

    public Double getF155() {
        return f155;
    }

    public void setF155(Double f155) {
        this.f155 = f155;
    }

    public Double getF156() {
        return f156;
    }

    public void setF156(Double f156) {
        this.f156 = f156;
    }

    public Double getF157() {
        return f157;
    }

    public void setF157(Double f157) {
        this.f157 = f157;
    }

    public Double getF158() {
        return f158;
    }

    public void setF158(Double f158) {
        this.f158 = f158;
    }

    public Double getF159() {
        return f159;
    }

    public void setF159(Double f159) {
        this.f159 = f159;
    }

    public Double getF160() {
        return f160;
    }

    public void setF160(Double f160) {
        this.f160 = f160;
    }

    public Double getF161() {
        return f161;
    }

    public void setF161(Double f161) {
        this.f161 = f161;
    }

    public Double getF162() {
        return f162;
    }

    public void setF162(Double f162) {
        this.f162 = f162;
    }

    public Double getF163() {
        return f163;
    }

    public void setF163(Double f163) {
        this.f163 = f163;
    }

    public Double getF164() {
        return f164;
    }

    public void setF164(Double f164) {
        this.f164 = f164;
    }

    public Double getF165() {
        return f165;
    }

    public void setF165(Double f165) {
        this.f165 = f165;
    }

    public Double getF166() {
        return f166;
    }

    public void setF166(Double f166) {
        this.f166 = f166;
    }

    public Double getF167() {
        return f167;
    }

    public void setF167(Double f167) {
        this.f167 = f167;
    }

    public Double getF168() {
        return f168;
    }

    public void setF168(Double f168) {
        this.f168 = f168;
    }

    public Double getF169() {
        return f169;
    }

    public void setF169(Double f169) {
        this.f169 = f169;
    }

    public Double getF170() {
        return f170;
    }

    public void setF170(Double f170) {
        this.f170 = f170;
    }

    public Double getF171() {
        return f171;
    }

    public void setF171(Double f171) {
        this.f171 = f171;
    }

    public Double getF172() {
        return f172;
    }

    public void setF172(Double f172) {
        this.f172 = f172;
    }

    public Double getF173() {
        return f173;
    }

    public void setF173(Double f173) {
        this.f173 = f173;
    }

    public Double getF174() {
        return f174;
    }

    public void setF174(Double f174) {
        this.f174 = f174;
    }

    public Double getF175() {
        return f175;
    }

    public void setF175(Double f175) {
        this.f175 = f175;
    }

    public Double getF176() {
        return f176;
    }

    public void setF176(Double f176) {
        this.f176 = f176;
    }

    public Double getF177() {
        return f177;
    }

    public void setF177(Double f177) {
        this.f177 = f177;
    }

    public Double getF178() {
        return f178;
    }

    public void setF178(Double f178) {
        this.f178 = f178;
    }

    public Double getF179() {
        return f179;
    }

    public void setF179(Double f179) {
        this.f179 = f179;
    }

    public Double getF180() {
        return f180;
    }

    public void setF180(Double f180) {
        this.f180 = f180;
    }

    public Double getF181() {
        return f181;
    }

    public void setF181(Double f181) {
        this.f181 = f181;
    }

    public Double getF182() {
        return f182;
    }

    public void setF182(Double f182) {
        this.f182 = f182;
    }

    public Double getF183() {
        return f183;
    }

    public void setF183(Double f183) {
        this.f183 = f183;
    }

    public Double getF184() {
        return f184;
    }

    public void setF184(Double f184) {
        this.f184 = f184;
    }

    public Double getF185() {
        return f185;
    }

    public void setF185(Double f185) {
        this.f185 = f185;
    }

    public Double getF186() {
        return f186;
    }

    public void setF186(Double f186) {
        this.f186 = f186;
    }

    public Double getF187() {
        return f187;
    }

    public void setF187(Double f187) {
        this.f187 = f187;
    }

    public Double getF188() {
        return f188;
    }

    public void setF188(Double f188) {
        this.f188 = f188;
    }

    public Double getF189() {
        return f189;
    }

    public void setF189(Double f189) {
        this.f189 = f189;
    }

    public Double getF190() {
        return f190;
    }

    public void setF190(Double f190) {
        this.f190 = f190;
    }

    public Double getF191() {
        return f191;
    }

    public void setF191(Double f191) {
        this.f191 = f191;
    }

    public Double getF192() {
        return f192;
    }

    public void setF192(Double f192) {
        this.f192 = f192;
    }

    public Double getF193() {
        return f193;
    }

    public void setF193(Double f193) {
        this.f193 = f193;
    }

    public Double getF194() {
        return f194;
    }

    public void setF194(Double f194) {
        this.f194 = f194;
    }

    public Double getF195() {
        return f195;
    }

    public void setF195(Double f195) {
        this.f195 = f195;
    }

    public Double getF196() {
        return f196;
    }

    public void setF196(Double f196) {
        this.f196 = f196;
    }

    public Double getF197() {
        return f197;
    }

    public void setF197(Double f197) {
        this.f197 = f197;
    }

    public Double getF198() {
        return f198;
    }

    public void setF198(Double f198) {
        this.f198 = f198;
    }

    public Double getF199() {
        return f199;
    }

    public void setF199(Double f199) {
        this.f199 = f199;
    }

    public Double getF200() {
        return f200;
    }

    public void setF200(Double f200) {
        this.f200 = f200;
    }

    public Double getF201() {
        return f201;
    }

    public void setF201(Double f201) {
        this.f201 = f201;
    }

    public Double getF202() {
        return f202;
    }

    public void setF202(Double f202) {
        this.f202 = f202;
    }

    public Double getF203() {
        return f203;
    }

    public void setF203(Double f203) {
        this.f203 = f203;
    }

    public Double getF204() {
        return f204;
    }

    public void setF204(Double f204) {
        this.f204 = f204;
    }

    public Double getF205() {
        return f205;
    }

    public void setF205(Double f205) {
        this.f205 = f205;
    }

    public Double getF206() {
        return f206;
    }

    public void setF206(Double f206) {
        this.f206 = f206;
    }

    public Double getF207() {
        return f207;
    }

    public void setF207(Double f207) {
        this.f207 = f207;
    }

    public Double getF208() {
        return f208;
    }

    public void setF208(Double f208) {
        this.f208 = f208;
    }

    public Double getF209() {
        return f209;
    }

    public void setF209(Double f209) {
        this.f209 = f209;
    }

    public Double getF210() {
        return f210;
    }

    public void setF210(Double f210) {
        this.f210 = f210;
    }

    public Double getF211() {
        return f211;
    }

    public void setF211(Double f211) {
        this.f211 = f211;
    }

    public Double getF212() {
        return f212;
    }

    public void setF212(Double f212) {
        this.f212 = f212;
    }

    public Double getF213() {
        return f213;
    }

    public void setF213(Double f213) {
        this.f213 = f213;
    }

    public Double getF214() {
        return f214;
    }

    public void setF214(Double f214) {
        this.f214 = f214;
    }

    public Double getF215() {
        return f215;
    }

    public void setF215(Double f215) {
        this.f215 = f215;
    }

    public Double getF216() {
        return f216;
    }

    public void setF216(Double f216) {
        this.f216 = f216;
    }

    public Double getF217() {
        return f217;
    }

    public void setF217(Double f217) {
        this.f217 = f217;
    }

    public Double getF218() {
        return f218;
    }

    public void setF218(Double f218) {
        this.f218 = f218;
    }

    public Double getF219() {
        return f219;
    }

    public void setF219(Double f219) {
        this.f219 = f219;
    }

    public Double getF220() {
        return f220;
    }

    public void setF220(Double f220) {
        this.f220 = f220;
    }

    public Double getF221() {
        return f221;
    }

    public void setF221(Double f221) {
        this.f221 = f221;
    }

    public Double getF222() {
        return f222;
    }

    public void setF222(Double f222) {
        this.f222 = f222;
    }

    public Double getF223() {
        return f223;
    }

    public void setF223(Double f223) {
        this.f223 = f223;
    }

    public Double getF224() {
        return f224;
    }

    public void setF224(Double f224) {
        this.f224 = f224;
    }

    public Double getF225() {
        return f225;
    }

    public void setF225(Double f225) {
        this.f225 = f225;
    }

    public Double getF226() {
        return f226;
    }

    public void setF226(Double f226) {
        this.f226 = f226;
    }

    public Double getF227() {
        return f227;
    }

    public void setF227(Double f227) {
        this.f227 = f227;
    }

    public Double getF228() {
        return f228;
    }

    public void setF228(Double f228) {
        this.f228 = f228;
    }

    public Double getF229() {
        return f229;
    }

    public void setF229(Double f229) {
        this.f229 = f229;
    }

    public Double getF230() {
        return f230;
    }

    public void setF230(Double f230) {
        this.f230 = f230;
    }

    public Double getF231() {
        return f231;
    }

    public void setF231(Double f231) {
        this.f231 = f231;
    }

    public Double getF232() {
        return f232;
    }

    public void setF232(Double f232) {
        this.f232 = f232;
    }

    public Double getF233() {
        return f233;
    }

    public void setF233(Double f233) {
        this.f233 = f233;
    }

    public Double getF234() {
        return f234;
    }

    public void setF234(Double f234) {
        this.f234 = f234;
    }

    public Double getF235() {
        return f235;
    }

    public void setF235(Double f235) {
        this.f235 = f235;
    }

    public Double getF236() {
        return f236;
    }

    public void setF236(Double f236) {
        this.f236 = f236;
    }

    public Double getF237() {
        return f237;
    }

    public void setF237(Double f237) {
        this.f237 = f237;
    }

    public Double getF238() {
        return f238;
    }

    public void setF238(Double f238) {
        this.f238 = f238;
    }

    public Double getF239() {
        return f239;
    }

    public void setF239(Double f239) {
        this.f239 = f239;
    }

    public Double getF240() {
        return f240;
    }

    public void setF240(Double f240) {
        this.f240 = f240;
    }

    public Double getF241() {
        return f241;
    }

    public void setF241(Double f241) {
        this.f241 = f241;
    }

    public Double getF242() {
        return f242;
    }

    public void setF242(Double f242) {
        this.f242 = f242;
    }

    public Double getF243() {
        return f243;
    }

    public void setF243(Double f243) {
        this.f243 = f243;
    }

    public Double getF244() {
        return f244;
    }

    public void setF244(Double f244) {
        this.f244 = f244;
    }

    public Double getF245() {
        return f245;
    }

    public void setF245(Double f245) {
        this.f245 = f245;
    }

    public Double getF246() {
        return f246;
    }

    public void setF246(Double f246) {
        this.f246 = f246;
    }

    public Double getF247() {
        return f247;
    }

    public void setF247(Double f247) {
        this.f247 = f247;
    }

    public Double getF248() {
        return f248;
    }

    public void setF248(Double f248) {
        this.f248 = f248;
    }

    public Double getF249() {
        return f249;
    }

    public void setF249(Double f249) {
        this.f249 = f249;
    }

    public Double getF250() {
        return f250;
    }

    public void setF250(Double f250) {
        this.f250 = f250;
    }

    public Double getF251() {
        return f251;
    }

    public void setF251(Double f251) {
        this.f251 = f251;
    }

    public Double getF252() {
        return f252;
    }

    public void setF252(Double f252) {
        this.f252 = f252;
    }

    public Double getF253() {
        return f253;
    }

    public void setF253(Double f253) {
        this.f253 = f253;
    }

    public Double getF254() {
        return f254;
    }

    public void setF254(Double f254) {
        this.f254 = f254;
    }

    public Double getF255() {
        return f255;
    }

    public void setF255(Double f255) {
        this.f255 = f255;
    }

    public Double getF256() {
        return f256;
    }

    public void setF256(Double f256) {
        this.f256 = f256;
    }

    public Double getF257() {
        return f257;
    }

    public void setF257(Double f257) {
        this.f257 = f257;
    }

    public Double getF258() {
        return f258;
    }

    public void setF258(Double f258) {
        this.f258 = f258;
    }

    public Double getF259() {
        return f259;
    }

    public void setF259(Double f259) {
        this.f259 = f259;
    }

    public Double getF260() {
        return f260;
    }

    public void setF260(Double f260) {
        this.f260 = f260;
    }

    public Double getF261() {
        return f261;
    }

    public void setF261(Double f261) {
        this.f261 = f261;
    }

    public Double getF262() {
        return f262;
    }

    public void setF262(Double f262) {
        this.f262 = f262;
    }

    public Double getF263() {
        return f263;
    }

    public void setF263(Double f263) {
        this.f263 = f263;
    }

    public Double getF264() {
        return f264;
    }

    public void setF264(Double f264) {
        this.f264 = f264;
    }

    public Double getF265() {
        return f265;
    }

    public void setF265(Double f265) {
        this.f265 = f265;
    }

    public Double getF266() {
        return f266;
    }

    public void setF266(Double f266) {
        this.f266 = f266;
    }

    public Double getF267() {
        return f267;
    }

    public void setF267(Double f267) {
        this.f267 = f267;
    }

    public Double getF268() {
        return f268;
    }

    public void setF268(Double f268) {
        this.f268 = f268;
    }

    public Double getF269() {
        return f269;
    }

    public void setF269(Double f269) {
        this.f269 = f269;
    }

    public Double getF270() {
        return f270;
    }

    public void setF270(Double f270) {
        this.f270 = f270;
    }

    public Double getF271() {
        return f271;
    }

    public void setF271(Double f271) {
        this.f271 = f271;
    }

    public Double getF272() {
        return f272;
    }

    public void setF272(Double f272) {
        this.f272 = f272;
    }

    public Double getF273() {
        return f273;
    }

    public void setF273(Double f273) {
        this.f273 = f273;
    }

    public Double getF274() {
        return f274;
    }

    public void setF274(Double f274) {
        this.f274 = f274;
    }

    public Double getF275() {
        return f275;
    }

    public void setF275(Double f275) {
        this.f275 = f275;
    }

    public Double getF276() {
        return f276;
    }

    public void setF276(Double f276) {
        this.f276 = f276;
    }

    public Double getF277() {
        return f277;
    }

    public void setF277(Double f277) {
        this.f277 = f277;
    }

    public Double getF278() {
        return f278;
    }

    public void setF278(Double f278) {
        this.f278 = f278;
    }

    public Double getF279() {
        return f279;
    }

    public void setF279(Double f279) {
        this.f279 = f279;
    }

    public Double getF280() {
        return f280;
    }

    public void setF280(Double f280) {
        this.f280 = f280;
    }

    public Double getF281() {
        return f281;
    }

    public void setF281(Double f281) {
        this.f281 = f281;
    }

    public Double getF282() {
        return f282;
    }

    public void setF282(Double f282) {
        this.f282 = f282;
    }

    public Double getF283() {
        return f283;
    }

    public void setF283(Double f283) {
        this.f283 = f283;
    }

    public Double getF284() {
        return f284;
    }

    public void setF284(Double f284) {
        this.f284 = f284;
    }

    public Double getF285() {
        return f285;
    }

    public void setF285(Double f285) {
        this.f285 = f285;
    }

    public Double getF286() {
        return f286;
    }

    public void setF286(Double f286) {
        this.f286 = f286;
    }

    public Double getF287() {
        return f287;
    }

    public void setF287(Double f287) {
        this.f287 = f287;
    }

    public Double getF288() {
        return f288;
    }

    public void setF288(Double f288) {
        this.f288 = f288;
    }

    /**
     * 获取96点值
     * @return
     */
    public Vector<Double> getVectorH96() {
        vector96 = new Vector<Double>();
        if(h01!=null && h48!=null && h96!=null){
            vector96.add(h01);
            vector96.add(h02);
            vector96.add(h03);
            vector96.add(h04);
            vector96.add(h05);
            vector96.add(h06);
            vector96.add(h07);
            vector96.add(h08);
            vector96.add(h09);
            vector96.add(h10);

            vector96.add(h11);
            vector96.add(h12);
            vector96.add(h13);
            vector96.add(h14);
            vector96.add(h15);
            vector96.add(h16);
            vector96.add(h17);
            vector96.add(h18);
            vector96.add(h19);
            vector96.add(h20);

            vector96.add(h21);
            vector96.add(h22);
            vector96.add(h23);
            vector96.add(h24);
            vector96.add(h25);
            vector96.add(h26);
            vector96.add(h27);
            vector96.add(h28);
            vector96.add(h29);
            vector96.add(h30);

            vector96.add(h31);
            vector96.add(h32);
            vector96.add(h33);
            vector96.add(h34);
            vector96.add(h35);
            vector96.add(h36);
            vector96.add(h37);
            vector96.add(h38);
            vector96.add(h39);
            vector96.add(h40);

            vector96.add(h41);
            vector96.add(h42);
            vector96.add(h43);
            vector96.add(h44);
            vector96.add(h45);
            vector96.add(h46);
            vector96.add(h47);
            vector96.add(h48);
            vector96.add(h49);
            vector96.add(h50);

            vector96.add(h51);
            vector96.add(h52);
            vector96.add(h53);
            vector96.add(h54);
            vector96.add(h55);
            vector96.add(h56);
            vector96.add(h57);
            vector96.add(h58);
            vector96.add(h59);
            vector96.add(h60);

            vector96.add(h61);
            vector96.add(h62);
            vector96.add(h63);
            vector96.add(h64);
            vector96.add(h65);
            vector96.add(h66);
            vector96.add(h67);
            vector96.add(h68);
            vector96.add(h69);
            vector96.add(h70);

            vector96.add(h71);
            vector96.add(h72);
            vector96.add(h73);
            vector96.add(h74);
            vector96.add(h75);
            vector96.add(h76);
            vector96.add(h77);
            vector96.add(h78);
            vector96.add(h79);
            vector96.add(h80);

            vector96.add(h81);
            vector96.add(h82);
            vector96.add(h83);
            vector96.add(h84);
            vector96.add(h85);
            vector96.add(h86);
            vector96.add(h87);
            vector96.add(h88);
            vector96.add(h89);
            vector96.add(h90);

            vector96.add(h91);
            vector96.add(h92);
            vector96.add(h93);
            vector96.add(h94);
            vector96.add(h95);
            vector96.add(h96);
        }else{
            for(int i=0;i<96;i++){
                vector96.add(0.0);
            }
        }
        return vector96;
    }

    public Double getL01() {
        return l01;
    }

    public void setL01(Double l01) {
        this.l01 = l01;
    }

    public Double getL02() {
        return l02;
    }

    public void setL02(Double l02) {
        this.l02 = l02;
    }

    public Double getL03() {
        return l03;
    }

    public void setL03(Double l03) {
        this.l03 = l03;
    }

    public Double getL04() {
        return l04;
    }

    public void setL04(Double l04) {
        this.l04 = l04;
    }

    public Double getL05() {
        return l05;
    }

    public void setL05(Double l05) {
        this.l05 = l05;
    }

    public Double getL06() {
        return l06;
    }

    public void setL06(Double l06) {
        this.l06 = l06;
    }

    public Double getL07() {
        return l07;
    }

    public void setL07(Double l07) {
        this.l07 = l07;
    }

    public Double getL08() {
        return l08;
    }

    public void setL08(Double l08) {
        this.l08 = l08;
    }

    public Double getL09() {
        return l09;
    }

    public void setL09(Double l09) {
        this.l09 = l09;
    }

    public Double getL10() {
        return l10;
    }

    public void setL10(Double l10) {
        this.l10 = l10;
    }

    public Double getL11() {
        return l11;
    }

    public void setL11(Double l11) {
        this.l11 = l11;
    }

    public Double getL12() {
        return l12;
    }

    public void setL12(Double l12) {
        this.l12 = l12;
    }

    public Double getL13() {
        return l13;
    }

    public void setL13(Double l13) {
        this.l13 = l13;
    }

    public Double getL14() {
        return l14;
    }

    public void setL14(Double l14) {
        this.l14 = l14;
    }

    public Double getL15() {
        return l15;
    }

    public void setL15(Double l15) {
        this.l15 = l15;
    }

    public Double getL16() {
        return l16;
    }

    public void setL16(Double l16) {
        this.l16 = l16;
    }

    public Double getL17() {
        return l17;
    }

    public void setL17(Double l17) {
        this.l17 = l17;
    }

    public Double getL18() {
        return l18;
    }

    public void setL18(Double l18) {
        this.l18 = l18;
    }

    public Double getL19() {
        return l19;
    }

    public void setL19(Double l19) {
        this.l19 = l19;
    }

    public Double getL20() {
        return l20;
    }

    public void setL20(Double l20) {
        this.l20 = l20;
    }

    public Double getL21() {
        return l21;
    }

    public void setL21(Double l21) {
        this.l21 = l21;
    }

    public Double getL22() {
        return l22;
    }

    public void setL22(Double l22) {
        this.l22 = l22;
    }

    public Double getL23() {
        return l23;
    }

    public void setL23(Double l23) {
        this.l23 = l23;
    }

    public Double getL24() {
        return l24;
    }

    public void setL24(Double l24) {
        this.l24 = l24;
    }

    public Double getL25() {
        return l25;
    }

    public void setL25(Double l25) {
        this.l25 = l25;
    }

    public Double getL26() {
        return l26;
    }

    public void setL26(Double l26) {
        this.l26 = l26;
    }

    public Double getL27() {
        return l27;
    }

    public void setL27(Double l27) {
        this.l27 = l27;
    }

    public Double getL28() {
        return l28;
    }

    public void setL28(Double l28) {
        this.l28 = l28;
    }

    public Double getL29() {
        return l29;
    }

    public void setL29(Double l29) {
        this.l29 = l29;
    }

    public Double getL30() {
        return l30;
    }

    public void setL30(Double l30) {
        this.l30 = l30;
    }

    public Double getL31() {
        return l31;
    }

    public void setL31(Double l31) {
        this.l31 = l31;
    }

    public Double getL32() {
        return l32;
    }

    public void setL32(Double l32) {
        this.l32 = l32;
    }

    public Double getL33() {
        return l33;
    }

    public void setL33(Double l33) {
        this.l33 = l33;
    }

    public Double getL34() {
        return l34;
    }

    public void setL34(Double l34) {
        this.l34 = l34;
    }

    public Double getL35() {
        return l35;
    }

    public void setL35(Double l35) {
        this.l35 = l35;
    }

    public Double getL36() {
        return l36;
    }

    public void setL36(Double l36) {
        this.l36 = l36;
    }

    public Double getL37() {
        return l37;
    }

    public void setL37(Double l37) {
        this.l37 = l37;
    }

    public Double getL38() {
        return l38;
    }

    public void setL38(Double l38) {
        this.l38 = l38;
    }

    public Double getL39() {
        return l39;
    }

    public void setL39(Double l39) {
        this.l39 = l39;
    }

    public Double getL40() {
        return l40;
    }

    public void setL40(Double l40) {
        this.l40 = l40;
    }

    public Double getL41() {
        return l41;
    }

    public void setL41(Double l41) {
        this.l41 = l41;
    }

    public Double getL42() {
        return l42;
    }

    public void setL42(Double l42) {
        this.l42 = l42;
    }

    public Double getL43() {
        return l43;
    }

    public void setL43(Double l43) {
        this.l43 = l43;
    }

    public Double getL44() {
        return l44;
    }

    public void setL44(Double l44) {
        this.l44 = l44;
    }

    public Double getL45() {
        return l45;
    }

    public void setL45(Double l45) {
        this.l45 = l45;
    }

    public Double getL46() {
        return l46;
    }

    public void setL46(Double l46) {
        this.l46 = l46;
    }

    public Double getL47() {
        return l47;
    }

    public void setL47(Double l47) {
        this.l47 = l47;
    }

    public Double getL48() {
        return l48;
    }

    public void setL48(Double l48) {
        this.l48 = l48;
    }

    public Double getL49() {
        return l49;
    }

    public void setL49(Double l49) {
        this.l49 = l49;
    }

    public Double getL50() {
        return l50;
    }

    public void setL50(Double l50) {
        this.l50 = l50;
    }

    public Double getL51() {
        return l51;
    }

    public void setL51(Double l51) {
        this.l51 = l51;
    }

    public Double getL52() {
        return l52;
    }

    public void setL52(Double l52) {
        this.l52 = l52;
    }

    public Double getL53() {
        return l53;
    }

    public void setL53(Double l53) {
        this.l53 = l53;
    }

    public Double getL54() {
        return l54;
    }

    public void setL54(Double l54) {
        this.l54 = l54;
    }

    public Double getL55() {
        return l55;
    }

    public void setL55(Double l55) {
        this.l55 = l55;
    }

    public Double getL56() {
        return l56;
    }

    public void setL56(Double l56) {
        this.l56 = l56;
    }

    public Double getL57() {
        return l57;
    }

    public void setL57(Double l57) {
        this.l57 = l57;
    }

    public Double getL58() {
        return l58;
    }

    public void setL58(Double l58) {
        this.l58 = l58;
    }

    public Double getL59() {
        return l59;
    }

    public void setL59(Double l59) {
        this.l59 = l59;
    }

    public Double getL60() {
        return l60;
    }

    public void setL60(Double l60) {
        this.l60 = l60;
    }

    public Double getL61() {
        return l61;
    }

    public void setL61(Double l61) {
        this.l61 = l61;
    }

    public Double getL62() {
        return l62;
    }

    public void setL62(Double l62) {
        this.l62 = l62;
    }

    public Double getL63() {
        return l63;
    }

    public void setL63(Double l63) {
        this.l63 = l63;
    }

    public Double getL64() {
        return l64;
    }

    public void setL64(Double l64) {
        this.l64 = l64;
    }

    public Double getL65() {
        return l65;
    }

    public void setL65(Double l65) {
        this.l65 = l65;
    }

    public Double getL66() {
        return l66;
    }

    public void setL66(Double l66) {
        this.l66 = l66;
    }

    public Double getL67() {
        return l67;
    }

    public void setL67(Double l67) {
        this.l67 = l67;
    }

    public Double getL68() {
        return l68;
    }

    public void setL68(Double l68) {
        this.l68 = l68;
    }

    public Double getL69() {
        return l69;
    }

    public void setL69(Double l69) {
        this.l69 = l69;
    }

    public Double getL70() {
        return l70;
    }

    public void setL70(Double l70) {
        this.l70 = l70;
    }

    public Double getL71() {
        return l71;
    }

    public void setL71(Double l71) {
        this.l71 = l71;
    }

    public Double getL72() {
        return l72;
    }

    public void setL72(Double l72) {
        this.l72 = l72;
    }

    public Double getL73() {
        return l73;
    }

    public void setL73(Double l73) {
        this.l73 = l73;
    }

    public Double getL74() {
        return l74;
    }

    public void setL74(Double l74) {
        this.l74 = l74;
    }

    public Double getL75() {
        return l75;
    }

    public void setL75(Double l75) {
        this.l75 = l75;
    }

    public Double getL76() {
        return l76;
    }

    public void setL76(Double l76) {
        this.l76 = l76;
    }

    public Double getL77() {
        return l77;
    }

    public void setL77(Double l77) {
        this.l77 = l77;
    }

    public Double getL78() {
        return l78;
    }

    public void setL78(Double l78) {
        this.l78 = l78;
    }

    public Double getL79() {
        return l79;
    }

    public void setL79(Double l79) {
        this.l79 = l79;
    }

    public Double getL80() {
        return l80;
    }

    public void setL80(Double l80) {
        this.l80 = l80;
    }

    public Double getL81() {
        return l81;
    }

    public void setL81(Double l81) {
        this.l81 = l81;
    }

    public Double getL82() {
        return l82;
    }

    public void setL82(Double l82) {
        this.l82 = l82;
    }

    public Double getL83() {
        return l83;
    }

    public void setL83(Double l83) {
        this.l83 = l83;
    }

    public Double getL84() {
        return l84;
    }

    public void setL84(Double l84) {
        this.l84 = l84;
    }

    public Double getL85() {
        return l85;
    }

    public void setL85(Double l85) {
        this.l85 = l85;
    }

    public Double getL86() {
        return l86;
    }

    public void setL86(Double l86) {
        this.l86 = l86;
    }

    public Double getL87() {
        return l87;
    }

    public void setL87(Double l87) {
        this.l87 = l87;
    }

    public Double getL88() {
        return l88;
    }

    public void setL88(Double l88) {
        this.l88 = l88;
    }

    public Double getL89() {
        return l89;
    }

    public void setL89(Double l89) {
        this.l89 = l89;
    }

    public Double getL90() {
        return l90;
    }

    public void setL90(Double l90) {
        this.l90 = l90;
    }

    public Double getL91() {
        return l91;
    }

    public void setL91(Double l91) {
        this.l91 = l91;
    }

    public Double getL92() {
        return l92;
    }

    public void setL92(Double l92) {
        this.l92 = l92;
    }

    public Double getL93() {
        return l93;
    }

    public void setL93(Double l93) {
        this.l93 = l93;
    }

    public Double getL94() {
        return l94;
    }

    public void setL94(Double l94) {
        this.l94 = l94;
    }

    public Double getL95() {
        return l95;
    }

    public void setL95(Double l95) {
        this.l95 = l95;
    }

    public Double getL96() {
        return l96;
    }

    public void setL96(Double l96) {
        this.l96 = l96;
    }


    public void setVectorH96(Vector<Double> vector96) {
        this.vector96 = vector96;
        setH01(vector96.get(0));
        setH02(vector96.get(1));
        setH03(vector96.get(2));
        setH04(vector96.get(3));
        setH05(vector96.get(4));
        setH06(vector96.get(5));
        setH07(vector96.get(6));
        setH08(vector96.get(7));
        setH09(vector96.get(8));
        setH10(vector96.get(9));

        setH11(vector96.get(10));
        setH12(vector96.get(11));
        setH13(vector96.get(12));
        setH14(vector96.get(13));
        setH15(vector96.get(14));
        setH16(vector96.get(15));
        setH17(vector96.get(16));
        setH18(vector96.get(17));
        setH19(vector96.get(18));
        setH20(vector96.get(19));

        setH21(vector96.get(20));
        setH22(vector96.get(21));
        setH23(vector96.get(22));
        setH24(vector96.get(23));
        setH25(vector96.get(24));
        setH26(vector96.get(25));
        setH27(vector96.get(26));
        setH28(vector96.get(27));
        setH29(vector96.get(28));
        setH30(vector96.get(29));

        setH31(vector96.get(30));
        setH32(vector96.get(31));
        setH33(vector96.get(32));
        setH34(vector96.get(33));
        setH35(vector96.get(34));
        setH36(vector96.get(35));
        setH37(vector96.get(36));
        setH38(vector96.get(37));
        setH39(vector96.get(38));
        setH40(vector96.get(39));

        setH41(vector96.get(40));
        setH42(vector96.get(41));
        setH43(vector96.get(42));
        setH44(vector96.get(43));
        setH45(vector96.get(44));
        setH46(vector96.get(45));
        setH47(vector96.get(46));
        setH48(vector96.get(47));
        setH49(vector96.get(48));
        setH50(vector96.get(49));

        setH51(vector96.get(50));
        setH52(vector96.get(51));
        setH53(vector96.get(52));
        setH54(vector96.get(53));
        setH55(vector96.get(54));
        setH56(vector96.get(55));
        setH57(vector96.get(56));
        setH58(vector96.get(57));
        setH59(vector96.get(58));
        setH60(vector96.get(59));

        setH61(vector96.get(60));
        setH62(vector96.get(61));
        setH63(vector96.get(62));
        setH64(vector96.get(63));
        setH65(vector96.get(64));
        setH66(vector96.get(65));
        setH67(vector96.get(66));
        setH68(vector96.get(67));
        setH69(vector96.get(68));
        setH70(vector96.get(69));

        setH71(vector96.get(70));
        setH72(vector96.get(71));
        setH73(vector96.get(72));
        setH74(vector96.get(73));
        setH75(vector96.get(74));
        setH76(vector96.get(75));
        setH77(vector96.get(76));
        setH78(vector96.get(77));
        setH79(vector96.get(78));
        setH80(vector96.get(79));

        setH81(vector96.get(80));
        setH82(vector96.get(81));
        setH83(vector96.get(82));
        setH84(vector96.get(83));
        setH85(vector96.get(84));
        setH86(vector96.get(85));
        setH87(vector96.get(86));
        setH88(vector96.get(87));
        setH89(vector96.get(88));
        setH90(vector96.get(89));

        setH91(vector96.get(90));
        setH92(vector96.get(91));
        setH93(vector96.get(92));
        setH94(vector96.get(93));
        setH95(vector96.get(94));
        setH96(vector96.get(95));


    }

    public void setVectorf288change96(Vector<Double> vectorf288change96) {
        this.vectorf288change96 = vectorf288change96;
        setF00(vectorf288change96.get(0));
        setF03(vectorf288change96.get(3));
        setF06(vectorf288change96.get(6));
        setF09(vectorf288change96.get(9));
        setF12(vectorf288change96.get(12));
        setF15(vectorf288change96.get(15));
        setF18(vectorf288change96.get(18));
        setF21(vectorf288change96.get(21));
        setF24(vectorf288change96.get(24));
        setF27(vectorf288change96.get(27));
        setF30(vectorf288change96.get(30));
        setF33(vectorf288change96.get(33));
        setF36(vectorf288change96.get(36));
        setF39(vectorf288change96.get(39));
        setF42(vectorf288change96.get(42));
        setF45(vectorf288change96.get(45));
        setF48(vectorf288change96.get(48));
        setF51(vectorf288change96.get(51));
        setF54(vectorf288change96.get(54));
        setF57(vectorf288change96.get(57));
        setF60(vectorf288change96.get(60));
        setF63(vectorf288change96.get(63));
        setF66(vectorf288change96.get(66));
        setF69(vectorf288change96.get(69));
        setF72(vectorf288change96.get(72));
        setF75(vectorf288change96.get(75));
        setF78(vectorf288change96.get(78));
        setF81(vectorf288change96.get(81));
        setF84(vectorf288change96.get(84));
        setF87(vectorf288change96.get(87));
        setF90(vectorf288change96.get(90));
        setF93(vectorf288change96.get(93));
        setF96(vectorf288change96.get(96));
        setF99(vectorf288change96.get(99));
        setF102(vectorf288change96.get(102));
        setF105(vectorf288change96.get(105));
        setF108(vectorf288change96.get(108));
        setF111(vectorf288change96.get(111));
        setF114(vectorf288change96.get(114));
        setF117(vectorf288change96.get(117));
        setF120(vectorf288change96.get(120));
        setF123(vectorf288change96.get(123));
        setF126(vectorf288change96.get(126));
        setF129(vectorf288change96.get(129));
        setF132(vectorf288change96.get(132));
        setF135(vectorf288change96.get(135));
        setF138(vectorf288change96.get(138));
        setF141(vectorf288change96.get(141));
        setF144(vectorf288change96.get(144));
        setF147(vectorf288change96.get(147));
        setF150(vectorf288change96.get(150));
        setF153(vectorf288change96.get(153));
        setF156(vectorf288change96.get(156));
        setF159(vectorf288change96.get(159));
        setF162(vectorf288change96.get(162));
        setF165(vectorf288change96.get(165));
        setF168(vectorf288change96.get(168));
        setF171(vectorf288change96.get(171));
        setF174(vectorf288change96.get(174));
        setF177(vectorf288change96.get(177));
        setF180(vectorf288change96.get(180));
        setF183(vectorf288change96.get(183));
        setF186(vectorf288change96.get(186));
        setF189(vectorf288change96.get(189));
        setF192(vectorf288change96.get(192));
        setF195(vectorf288change96.get(195));
        setF198(vectorf288change96.get(198));
        setF201(vectorf288change96.get(201));
        setF204(vectorf288change96.get(204));
        setF207(vectorf288change96.get(207));
        setF210(vectorf288change96.get(210));
        setF213(vectorf288change96.get(213));
        setF216(vectorf288change96.get(216));
        setF219(vectorf288change96.get(219));
        setF222(vectorf288change96.get(222));
        setF225(vectorf288change96.get(225));
        setF228(vectorf288change96.get(228));
        setF231(vectorf288change96.get(231));
        setF234(vectorf288change96.get(234));
        setF237(vectorf288change96.get(237));
        setF240(vectorf288change96.get(240));
        setF243(vectorf288change96.get(243));
        setF246(vectorf288change96.get(246));
        setF249(vectorf288change96.get(249));
        setF252(vectorf288change96.get(252));
        setF255(vectorf288change96.get(255));
        setF258(vectorf288change96.get(258));
        setF261(vectorf288change96.get(261));
        setF264(vectorf288change96.get(264));
        setF267(vectorf288change96.get(267));
        setF270(vectorf288change96.get(270));
        setF273(vectorf288change96.get(273));
        setF276(vectorf288change96.get(276));
        setF279(vectorf288change96.get(279));
        setF282(vectorf288change96.get(282));
        setF285(vectorf288change96.get(285));
        setF288(vectorf288change96.get(288));

    }

    private Vector<Double> vectorf288change96 = null;
    public Vector<Double> getVectorf288change96() {
        vectorf288change96 = new Vector<Double>();
        if(f01!=null && f48!=null && f288!=null){
            vectorf288change96.add(f00);
            vectorf288change96.add(f03);
            vectorf288change96.add(f06);
            vectorf288change96.add(f09);

            vectorf288change96.add(f12);
            vectorf288change96.add(f15);
            vectorf288change96.add(f18);

            vectorf288change96.add(f21);
            vectorf288change96.add(f24);
            vectorf288change96.add(f27);
            vectorf288change96.add(f30);

            vectorf288change96.add(f33);
            vectorf288change96.add(f36);
            vectorf288change96.add(f39);

            vectorf288change96.add(f42);
            vectorf288change96.add(f45);
            vectorf288change96.add(f48);

            vectorf288change96.add(f51);
            vectorf288change96.add(f54);
            vectorf288change96.add(f57);
            vectorf288change96.add(f60);

            vectorf288change96.add(f63);
            vectorf288change96.add(f66);
            vectorf288change96.add(f69);
            vectorf288change96.add(f72);
            vectorf288change96.add(f75);
            vectorf288change96.add(f78);

            vectorf288change96.add(f81);
            vectorf288change96.add(f84);
            vectorf288change96.add(f87);
            vectorf288change96.add(f90);

            vectorf288change96.add(f93);
            vectorf288change96.add(f96);
            vectorf288change96.add(f99);

            vectorf288change96.add(f102);
            vectorf288change96.add(f105);
            vectorf288change96.add(f108);

            vectorf288change96.add(f111);
            vectorf288change96.add(f114);
            vectorf288change96.add(f117);
            vectorf288change96.add(f120);
            vectorf288change96.add(f123);
            vectorf288change96.add(f126);

            vectorf288change96.add(f129);
            vectorf288change96.add(f132);
            vectorf288change96.add(f135);
            vectorf288change96.add(f138);

            vectorf288change96.add(f141);
            vectorf288change96.add(f144);
            vectorf288change96.add(f147);
            vectorf288change96.add(f150);

            vectorf288change96.add(f153);
            vectorf288change96.add(f156);
            vectorf288change96.add(f159);

            vectorf288change96.add(f162);
            vectorf288change96.add(f165);
            vectorf288change96.add(f168);

            vectorf288change96.add(f171);
            vectorf288change96.add(f174);
            vectorf288change96.add(f177);
            vectorf288change96.add(f180);

            vectorf288change96.add(f183);
            vectorf288change96.add(f186);
            vectorf288change96.add(f189);
            vectorf288change96.add(f192);
            vectorf288change96.add(f195);
            vectorf288change96.add(f198);

            vectorf288change96.add(f201);
            vectorf288change96.add(f204);
            vectorf288change96.add(f207);
            vectorf288change96.add(f210);

            vectorf288change96.add(f213);
            vectorf288change96.add(f216);
            vectorf288change96.add(f219);
            vectorf288change96.add(f222);

            vectorf288change96.add(f225);
            vectorf288change96.add(f228);
            vectorf288change96.add(f231);
            vectorf288change96.add(f234);
            vectorf288change96.add(f237);
            vectorf288change96.add(f240);

            vectorf288change96.add(f243);
            vectorf288change96.add(f246);
            vectorf288change96.add(f249);
            vectorf288change96.add(f252);
            vectorf288change96.add(f255);
            vectorf288change96.add(f258);

            vectorf288change96.add(f261);
            vectorf288change96.add(f264);
            vectorf288change96.add(f267);
            vectorf288change96.add(f270);

            vectorf288change96.add(f273);
            vectorf288change96.add(f276);
            vectorf288change96.add(f279);

            vectorf288change96.add(f282);
            vectorf288change96.add(f285);
            vectorf288change96.add(f288);

        }else{
            for (int i=0;i<97;i++){
                vectorf288change96.add(0.0);
            }
        }
        return vectorf288change96;
    }

    private Vector<Double> vectorf288 = null;

    public Vector<Double> getVectorf288() {
        vectorf288 = new Vector<Double>();
        if(f01!=null && f48!=null && f288!=null){
            vectorf288.add(f00);
            vectorf288.add(f01);
            vectorf288.add(f02);
            vectorf288.add(f03);
            vectorf288.add(f04);
            vectorf288.add(f05);
            vectorf288.add(f06);
            vectorf288.add(f07);
            vectorf288.add(f08);
            vectorf288.add(f09);
            vectorf288.add(f10);

            vectorf288.add(f11);
            vectorf288.add(f12);
            vectorf288.add(f13);
            vectorf288.add(f14);
            vectorf288.add(f15);
            vectorf288.add(f16);
            vectorf288.add(f17);
            vectorf288.add(f18);
            vectorf288.add(f19);
            vectorf288.add(f20);

            vectorf288.add(f21);
            vectorf288.add(f22);
            vectorf288.add(f23);
            vectorf288.add(f24);
            vectorf288.add(f25);
            vectorf288.add(f26);
            vectorf288.add(f27);
            vectorf288.add(f28);
            vectorf288.add(f29);
            vectorf288.add(f30);

            vectorf288.add(f31);
            vectorf288.add(f32);
            vectorf288.add(f33);
            vectorf288.add(f34);
            vectorf288.add(f35);
            vectorf288.add(f36);
            vectorf288.add(f37);
            vectorf288.add(f38);
            vectorf288.add(f39);
            vectorf288.add(f40);

            vectorf288.add(f41);
            vectorf288.add(f42);
            vectorf288.add(f43);
            vectorf288.add(f44);
            vectorf288.add(f45);
            vectorf288.add(f46);
            vectorf288.add(f47);
            vectorf288.add(f48);
            vectorf288.add(f49);
            vectorf288.add(f50);

            vectorf288.add(f51);
            vectorf288.add(f52);
            vectorf288.add(f53);
            vectorf288.add(f54);
            vectorf288.add(f55);
            vectorf288.add(f56);
            vectorf288.add(f57);
            vectorf288.add(f58);
            vectorf288.add(f59);
            vectorf288.add(f60);

            vectorf288.add(f61);
            vectorf288.add(f62);
            vectorf288.add(f63);
            vectorf288.add(f64);
            vectorf288.add(f65);
            vectorf288.add(f66);
            vectorf288.add(f67);
            vectorf288.add(f68);
            vectorf288.add(f69);
            vectorf288.add(f70);

            vectorf288.add(f71);
            vectorf288.add(f72);
            vectorf288.add(f73);
            vectorf288.add(f74);
            vectorf288.add(f75);
            vectorf288.add(f76);
            vectorf288.add(f77);
            vectorf288.add(f78);
            vectorf288.add(f79);
            vectorf288.add(f80);

            vectorf288.add(f81);
            vectorf288.add(f82);
            vectorf288.add(f83);
            vectorf288.add(f84);
            vectorf288.add(f85);
            vectorf288.add(f86);
            vectorf288.add(f87);
            vectorf288.add(f88);
            vectorf288.add(f89);
            vectorf288.add(f90);

            vectorf288.add(f91);
            vectorf288.add(f92);
            vectorf288.add(f93);
            vectorf288.add(f94);
            vectorf288.add(f95);
            vectorf288.add(f96);
            vectorf288.add(f97);
            vectorf288.add(f98);
            vectorf288.add(f99);

            vectorf288.add(f100);
            vectorf288.add(f101);
            vectorf288.add(f102);
            vectorf288.add(f103);
            vectorf288.add(f104);
            vectorf288.add(f105);
            vectorf288.add(f106);
            vectorf288.add(f107);
            vectorf288.add(f108);
            vectorf288.add(f109);
            vectorf288.add(f110);

            vectorf288.add(f111);
            vectorf288.add(f112);
            vectorf288.add(f113);
            vectorf288.add(f114);
            vectorf288.add(f115);
            vectorf288.add(f116);
            vectorf288.add(f117);
            vectorf288.add(f118);
            vectorf288.add(f119);
            vectorf288.add(f120);

            vectorf288.add(f121);
            vectorf288.add(f122);
            vectorf288.add(f123);
            vectorf288.add(f124);
            vectorf288.add(f125);
            vectorf288.add(f126);
            vectorf288.add(f127);
            vectorf288.add(f128);
            vectorf288.add(f129);
            vectorf288.add(f130);

            vectorf288.add(f131);
            vectorf288.add(f132);
            vectorf288.add(f133);
            vectorf288.add(f134);
            vectorf288.add(f135);
            vectorf288.add(f136);
            vectorf288.add(f137);
            vectorf288.add(f138);
            vectorf288.add(f139);
            vectorf288.add(f140);

            vectorf288.add(f141);
            vectorf288.add(f142);
            vectorf288.add(f143);
            vectorf288.add(f144);
            vectorf288.add(f145);
            vectorf288.add(f146);
            vectorf288.add(f147);
            vectorf288.add(f148);
            vectorf288.add(f149);
            vectorf288.add(f150);

            vectorf288.add(f151);
            vectorf288.add(f152);
            vectorf288.add(f153);
            vectorf288.add(f154);
            vectorf288.add(f155);
            vectorf288.add(f156);
            vectorf288.add(f157);
            vectorf288.add(f158);
            vectorf288.add(f159);
            vectorf288.add(f160);

            vectorf288.add(f161);
            vectorf288.add(f162);
            vectorf288.add(f163);
            vectorf288.add(f164);
            vectorf288.add(f165);
            vectorf288.add(f166);
            vectorf288.add(f167);
            vectorf288.add(f168);
            vectorf288.add(f169);
            vectorf288.add(f170);

            vectorf288.add(f171);
            vectorf288.add(f172);
            vectorf288.add(f173);
            vectorf288.add(f174);
            vectorf288.add(f175);
            vectorf288.add(f176);
            vectorf288.add(f177);
            vectorf288.add(f178);
            vectorf288.add(f179);
            vectorf288.add(f180);

            vectorf288.add(f181);
            vectorf288.add(f182);
            vectorf288.add(f183);
            vectorf288.add(f184);
            vectorf288.add(f185);
            vectorf288.add(f186);
            vectorf288.add(f187);
            vectorf288.add(f188);
            vectorf288.add(f189);
            vectorf288.add(f190);

            vectorf288.add(f191);
            vectorf288.add(f192);
            vectorf288.add(f193);
            vectorf288.add(f194);
            vectorf288.add(f195);
            vectorf288.add(f196);
            vectorf288.add(f197);
            vectorf288.add(f198);
            vectorf288.add(f199);

            vectorf288.add(f200);
            vectorf288.add(f201);
            vectorf288.add(f202);
            vectorf288.add(f203);
            vectorf288.add(f204);
            vectorf288.add(f205);
            vectorf288.add(f206);
            vectorf288.add(f207);
            vectorf288.add(f208);
            vectorf288.add(f209);
            vectorf288.add(f210);

            vectorf288.add(f211);
            vectorf288.add(f212);
            vectorf288.add(f213);
            vectorf288.add(f214);
            vectorf288.add(f215);
            vectorf288.add(f216);
            vectorf288.add(f217);
            vectorf288.add(f218);
            vectorf288.add(f219);
            vectorf288.add(f220);

            vectorf288.add(f221);
            vectorf288.add(f222);
            vectorf288.add(f223);
            vectorf288.add(f224);
            vectorf288.add(f225);
            vectorf288.add(f226);
            vectorf288.add(f227);
            vectorf288.add(f228);
            vectorf288.add(f229);
            vectorf288.add(f230);

            vectorf288.add(f231);
            vectorf288.add(f232);
            vectorf288.add(f233);
            vectorf288.add(f234);
            vectorf288.add(f235);
            vectorf288.add(f236);
            vectorf288.add(f237);
            vectorf288.add(f238);
            vectorf288.add(f239);
            vectorf288.add(f240);

            vectorf288.add(f241);
            vectorf288.add(f242);
            vectorf288.add(f243);
            vectorf288.add(f244);
            vectorf288.add(f245);
            vectorf288.add(f246);
            vectorf288.add(f247);
            vectorf288.add(f248);
            vectorf288.add(f249);
            vectorf288.add(f250);

            vectorf288.add(f251);
            vectorf288.add(f252);
            vectorf288.add(f253);
            vectorf288.add(f254);
            vectorf288.add(f255);
            vectorf288.add(f256);
            vectorf288.add(f257);
            vectorf288.add(f258);
            vectorf288.add(f259);
            vectorf288.add(f260);

            vectorf288.add(f261);
            vectorf288.add(f262);
            vectorf288.add(f263);
            vectorf288.add(f264);
            vectorf288.add(f265);
            vectorf288.add(f266);
            vectorf288.add(f267);
            vectorf288.add(f268);
            vectorf288.add(f269);
            vectorf288.add(f270);

            vectorf288.add(f271);
            vectorf288.add(f272);
            vectorf288.add(f273);
            vectorf288.add(f274);
            vectorf288.add(f275);
            vectorf288.add(f276);
            vectorf288.add(f277);
            vectorf288.add(f278);
            vectorf288.add(f279);
            vectorf288.add(f280);

            vectorf288.add(f281);
            vectorf288.add(f282);
            vectorf288.add(f283);
            vectorf288.add(f284);
            vectorf288.add(f285);
            vectorf288.add(f286);
            vectorf288.add(f287);
            vectorf288.add(f288);

        }else{
            for (int i=0;i<289;i++){
                vectorf288.add(0.0);
            }
        }
        return vectorf288;
    }

    public void setVectorf288(Vector<Double> vectorf288) {
        this.vectorf288 = vectorf288;

        setF00(vectorf288.get(0));
        setF01(vectorf288.get(1));
        setF02(vectorf288.get(2));
        setF03(vectorf288.get(3));
        setF04(vectorf288.get(4));
        setF05(vectorf288.get(5));
        setF06(vectorf288.get(6));
        setF07(vectorf288.get(7));
        setF08(vectorf288.get(8));
        setF09(vectorf288.get(9));

        setF10(vectorf288.get(10));
        setF11(vectorf288.get(11));
        setF12(vectorf288.get(12));
        setF13(vectorf288.get(13));
        setF14(vectorf288.get(14));
        setF15(vectorf288.get(15));
        setF16(vectorf288.get(16));
        setF17(vectorf288.get(17));
        setF18(vectorf288.get(18));
        setF19(vectorf288.get(19));

        setF20(vectorf288.get(20));
        setF21(vectorf288.get(21));
        setF22(vectorf288.get(22));
        setF23(vectorf288.get(23));
        setF24(vectorf288.get(24));
        setF25(vectorf288.get(25));
        setF26(vectorf288.get(26));
        setF27(vectorf288.get(27));
        setF28(vectorf288.get(28));
        setF29(vectorf288.get(29));

        setF30(vectorf288.get(30));
        setF31(vectorf288.get(31));
        setF32(vectorf288.get(32));
        setF33(vectorf288.get(33));
        setF34(vectorf288.get(34));
        setF35(vectorf288.get(35));
        setF36(vectorf288.get(36));
        setF37(vectorf288.get(37));
        setF38(vectorf288.get(38));
        setF39(vectorf288.get(39));

        setF40(vectorf288.get(40));
        setF41(vectorf288.get(41));
        setF42(vectorf288.get(42));
        setF43(vectorf288.get(43));
        setF44(vectorf288.get(44));
        setF45(vectorf288.get(45));
        setF46(vectorf288.get(46));
        setF47(vectorf288.get(47));
        setF48(vectorf288.get(48));
        setF49(vectorf288.get(49));

        setF50(vectorf288.get(50));
        setF51(vectorf288.get(51));
        setF52(vectorf288.get(52));
        setF53(vectorf288.get(53));
        setF54(vectorf288.get(54));
        setF55(vectorf288.get(55));
        setF56(vectorf288.get(56));
        setF57(vectorf288.get(57));
        setF58(vectorf288.get(58));
        setF59(vectorf288.get(59));

        setF60(vectorf288.get(60));
        setF61(vectorf288.get(61));
        setF62(vectorf288.get(62));
        setF63(vectorf288.get(63));
        setF64(vectorf288.get(64));
        setF65(vectorf288.get(65));
        setF66(vectorf288.get(66));
        setF67(vectorf288.get(67));
        setF68(vectorf288.get(68));
        setF69(vectorf288.get(69));

        setF70(vectorf288.get(70));
        setF71(vectorf288.get(71));
        setF72(vectorf288.get(72));
        setF73(vectorf288.get(73));
        setF74(vectorf288.get(74));
        setF75(vectorf288.get(75));
        setF76(vectorf288.get(76));
        setF77(vectorf288.get(77));
        setF78(vectorf288.get(78));
        setF79(vectorf288.get(79));

        setF80(vectorf288.get(80));
        setF81(vectorf288.get(81));
        setF82(vectorf288.get(82));
        setF83(vectorf288.get(83));
        setF84(vectorf288.get(84));
        setF85(vectorf288.get(85));
        setF86(vectorf288.get(86));
        setF87(vectorf288.get(87));
        setF88(vectorf288.get(88));
        setF89(vectorf288.get(89));

        setF90(vectorf288.get(90));
        setF91(vectorf288.get(91));
        setF92(vectorf288.get(92));
        setF93(vectorf288.get(93));
        setF94(vectorf288.get(94));
        setF95(vectorf288.get(95));
        setF96(vectorf288.get(96));
        setF97(vectorf288.get(97));
        setF98(vectorf288.get(98));
        setF99(vectorf288.get(99));

        setF100(vectorf288.get(100));
        setF101(vectorf288.get(101));
        setF102(vectorf288.get(102));
        setF103(vectorf288.get(103));
        setF104(vectorf288.get(104));
        setF105(vectorf288.get(105));
        setF106(vectorf288.get(106));
        setF107(vectorf288.get(107));
        setF108(vectorf288.get(108));
        setF109(vectorf288.get(109));

        setF110(vectorf288.get(110));
        setF111(vectorf288.get(111));
        setF112(vectorf288.get(112));
        setF113(vectorf288.get(113));
        setF114(vectorf288.get(114));
        setF115(vectorf288.get(115));
        setF116(vectorf288.get(116));
        setF117(vectorf288.get(117));
        setF118(vectorf288.get(118));
        setF119(vectorf288.get(119));

        setF120(vectorf288.get(120));
        setF121(vectorf288.get(121));
        setF122(vectorf288.get(122));
        setF123(vectorf288.get(123));
        setF124(vectorf288.get(124));
        setF125(vectorf288.get(125));
        setF126(vectorf288.get(126));
        setF127(vectorf288.get(127));
        setF128(vectorf288.get(128));
        setF129(vectorf288.get(129));

        setF130(vectorf288.get(130));
        setF131(vectorf288.get(131));
        setF132(vectorf288.get(132));
        setF133(vectorf288.get(133));
        setF134(vectorf288.get(134));
        setF135(vectorf288.get(135));
        setF136(vectorf288.get(136));
        setF137(vectorf288.get(137));
        setF138(vectorf288.get(138));
        setF139(vectorf288.get(139));

        setF140(vectorf288.get(140));
        setF141(vectorf288.get(141));
        setF142(vectorf288.get(142));
        setF143(vectorf288.get(143));
        setF144(vectorf288.get(144));
        setF145(vectorf288.get(145));
        setF146(vectorf288.get(146));
        setF147(vectorf288.get(147));
        setF148(vectorf288.get(148));
        setF149(vectorf288.get(149));

        setF150(vectorf288.get(150));
        setF151(vectorf288.get(151));
        setF152(vectorf288.get(152));
        setF153(vectorf288.get(153));
        setF154(vectorf288.get(154));
        setF155(vectorf288.get(155));
        setF156(vectorf288.get(156));
        setF157(vectorf288.get(157));
        setF158(vectorf288.get(158));
        setF159(vectorf288.get(159));

        setF160(vectorf288.get(160));
        setF161(vectorf288.get(161));
        setF162(vectorf288.get(162));
        setF163(vectorf288.get(163));
        setF164(vectorf288.get(164));
        setF165(vectorf288.get(165));
        setF166(vectorf288.get(166));
        setF167(vectorf288.get(167));
        setF168(vectorf288.get(168));
        setF169(vectorf288.get(169));

        setF170(vectorf288.get(170));
        setF171(vectorf288.get(171));
        setF172(vectorf288.get(172));
        setF173(vectorf288.get(173));
        setF174(vectorf288.get(174));
        setF175(vectorf288.get(175));
        setF176(vectorf288.get(176));
        setF177(vectorf288.get(177));
        setF178(vectorf288.get(178));
        setF179(vectorf288.get(179));

        setF180(vectorf288.get(180));
        setF181(vectorf288.get(181));
        setF182(vectorf288.get(182));
        setF183(vectorf288.get(183));
        setF184(vectorf288.get(184));
        setF185(vectorf288.get(185));
        setF186(vectorf288.get(186));
        setF187(vectorf288.get(187));
        setF188(vectorf288.get(188));
        setF189(vectorf288.get(189));

        setF190(vectorf288.get(190));
        setF191(vectorf288.get(191));
        setF192(vectorf288.get(192));
        setF193(vectorf288.get(193));
        setF194(vectorf288.get(194));
        setF195(vectorf288.get(195));
        setF196(vectorf288.get(196));
        setF197(vectorf288.get(197));
        setF198(vectorf288.get(198));
        setF199(vectorf288.get(199));

        setF200(vectorf288.get(200));
        setF201(vectorf288.get(201));
        setF202(vectorf288.get(202));
        setF203(vectorf288.get(203));
        setF204(vectorf288.get(204));
        setF205(vectorf288.get(205));
        setF206(vectorf288.get(206));
        setF207(vectorf288.get(207));
        setF208(vectorf288.get(208));
        setF209(vectorf288.get(209));

        setF210(vectorf288.get(210));
        setF211(vectorf288.get(211));
        setF212(vectorf288.get(212));
        setF213(vectorf288.get(213));
        setF214(vectorf288.get(214));
        setF215(vectorf288.get(215));
        setF216(vectorf288.get(216));
        setF217(vectorf288.get(217));
        setF218(vectorf288.get(218));
        setF219(vectorf288.get(219));



        setF220(vectorf288.get(220));
        setF221(vectorf288.get(221));
        setF222(vectorf288.get(222));
        setF223(vectorf288.get(223));
        setF224(vectorf288.get(224));
        setF225(vectorf288.get(225));
        setF226(vectorf288.get(226));
        setF227(vectorf288.get(227));
        setF228(vectorf288.get(228));
        setF229(vectorf288.get(229));

        setF230(vectorf288.get(230));
        setF231(vectorf288.get(231));
        setF232(vectorf288.get(232));
        setF233(vectorf288.get(233));
        setF234(vectorf288.get(234));
        setF235(vectorf288.get(235));
        setF236(vectorf288.get(236));
        setF237(vectorf288.get(237));
        setF238(vectorf288.get(238));
        setF239(vectorf288.get(239));

        setF240(vectorf288.get(240));
        setF241(vectorf288.get(241));
        setF242(vectorf288.get(242));
        setF243(vectorf288.get(243));
        setF244(vectorf288.get(244));
        setF245(vectorf288.get(245));
        setF246(vectorf288.get(246));
        setF247(vectorf288.get(247));
        setF248(vectorf288.get(248));
        setF249(vectorf288.get(249));

        setF250(vectorf288.get(250));
        setF251(vectorf288.get(251));
        setF252(vectorf288.get(252));
        setF253(vectorf288.get(253));
        setF254(vectorf288.get(254));
        setF255(vectorf288.get(255));
        setF256(vectorf288.get(256));
        setF257(vectorf288.get(257));
        setF258(vectorf288.get(258));
        setF259(vectorf288.get(259));

        setF260(vectorf288.get(260));
        setF261(vectorf288.get(261));
        setF262(vectorf288.get(262));
        setF263(vectorf288.get(263));
        setF264(vectorf288.get(264));
        setF265(vectorf288.get(265));
        setF266(vectorf288.get(266));
        setF267(vectorf288.get(267));
        setF268(vectorf288.get(268));
        setF269(vectorf288.get(269));

        setF270(vectorf288.get(270));
        setF271(vectorf288.get(271));
        setF272(vectorf288.get(272));
        setF273(vectorf288.get(273));
        setF274(vectorf288.get(274));
        setF275(vectorf288.get(275));
        setF276(vectorf288.get(276));
        setF277(vectorf288.get(277));
        setF278(vectorf288.get(278));
        setF279(vectorf288.get(279));

        setF280(vectorf288.get(280));
        setF281(vectorf288.get(281));
        setF282(vectorf288.get(282));
        setF283(vectorf288.get(283));
        setF284(vectorf288.get(284));
        setF285(vectorf288.get(285));
        setF286(vectorf288.get(286));
        setF287(vectorf288.get(287));
        setF288(vectorf288.get(288));

    }

    private Vector<Double> vectorV96 = null;


    private Double v01;
    private Double v02;
    private Double v03;
    private Double v04;
    private Double v05;
    private Double v06;
    private Double v07;
    private Double v08;
    private Double v09;
    private Double v10;

    private Double v11;
    private Double v12;
    private Double v13;
    private Double v14;
    private Double v15;
    private Double v16;
    private Double v17;
    private Double v18;
    private Double v19;
    private Double v20;

    private Double v21;
    private Double v22;
    private Double v23;
    private Double v24;
    private Double v25;
    private Double v26;
    private Double v27;
    private Double v28;
    private Double v29;
    private Double v30;

    private Double v31;
    private Double v32;
    private Double v33;
    private Double v34;
    private Double v35;
    private Double v36;
    private Double v37;
    private Double v38;
    private Double v39;
    private Double v40;

    private Double v41;
    private Double v42;
    private Double v43;
    private Double v44;
    private Double v45;
    private Double v46;
    private Double v47;
    private Double v48;
    private Double v49;
    private Double v50;

    private Double v51;
    private Double v52;
    private Double v53;
    private Double v54;
    private Double v55;
    private Double v56;
    private Double v57;
    private Double v58;
    private Double v59;
    private Double v60;

    private Double v61;
    private Double v62;
    private Double v63;
    private Double v64;
    private Double v65;
    private Double v66;
    private Double v67;
    private Double v68;
    private Double v69;
    private Double v70;

    private Double v71;
    private Double v72;
    private Double v73;
    private Double v74;
    private Double v75;
    private Double v76;
    private Double v77;
    private Double v78;
    private Double v79;
    private Double v80;

    private Double v81;
    private Double v82;
    private Double v83;
    private Double v84;
    private Double v85;
    private Double v86;
    private Double v87;
    private Double v88;
    private Double v89;
    private Double v90;

    private Double v91;
    private Double v92;
    private Double v93;
    private Double v94;
    private Double v95;
    private Double v96;

    public Double getV01() {
        return v01;
    }

    public void setV01(Double v01) {
        this.v01 = v01;
    }

    public Double getV02() {
        return v02;
    }

    public void setV02(Double v02) {
        this.v02 = v02;
    }

    public Double getV03() {
        return v03;
    }

    public void setV03(Double v03) {
        this.v03 = v03;
    }

    public Double getV04() {
        return v04;
    }

    public void setV04(Double v04) {
        this.v04 = v04;
    }

    public Double getV05() {
        return v05;
    }

    public void setV05(Double v05) {
        this.v05 = v05;
    }

    public Double getV06() {
        return v06;
    }

    public void setV06(Double v06) {
        this.v06 = v06;
    }

    public Double getV07() {
        return v07;
    }

    public void setV07(Double v07) {
        this.v07 = v07;
    }

    public Double getV08() {
        return v08;
    }

    public void setV08(Double v08) {
        this.v08 = v08;
    }

    public Double getV09() {
        return v09;
    }

    public void setV09(Double v09) {
        this.v09 = v09;
    }

    public Double getV10() {
        return v10;
    }

    public void setV10(Double v10) {
        this.v10 = v10;
    }

    public Double getV11() {
        return v11;
    }

    public void setV11(Double v11) {
        this.v11 = v11;
    }

    public Double getV12() {
        return v12;
    }

    public void setV12(Double v12) {
        this.v12 = v12;
    }

    public Double getV13() {
        return v13;
    }

    public void setV13(Double v13) {
        this.v13 = v13;
    }

    public Double getV14() {
        return v14;
    }

    public void setV14(Double v14) {
        this.v14 = v14;
    }

    public Double getV15() {
        return v15;
    }

    public void setV15(Double v15) {
        this.v15 = v15;
    }

    public Double getV16() {
        return v16;
    }

    public void setV16(Double v16) {
        this.v16 = v16;
    }

    public Double getV17() {
        return v17;
    }

    public void setV17(Double v17) {
        this.v17 = v17;
    }

    public Double getV18() {
        return v18;
    }

    public void setV18(Double v18) {
        this.v18 = v18;
    }

    public Double getV19() {
        return v19;
    }

    public void setV19(Double v19) {
        this.v19 = v19;
    }

    public Double getV20() {
        return v20;
    }

    public void setV20(Double v20) {
        this.v20 = v20;
    }

    public Double getV21() {
        return v21;
    }

    public void setV21(Double v21) {
        this.v21 = v21;
    }

    public Double getV22() {
        return v22;
    }

    public void setV22(Double v22) {
        this.v22 = v22;
    }

    public Double getV23() {
        return v23;
    }

    public void setV23(Double v23) {
        this.v23 = v23;
    }

    public Double getV24() {
        return v24;
    }

    public void setV24(Double v24) {
        this.v24 = v24;
    }

    public Double getV25() {
        return v25;
    }

    public void setV25(Double v25) {
        this.v25 = v25;
    }

    public Double getV26() {
        return v26;
    }

    public void setV26(Double v26) {
        this.v26 = v26;
    }

    public Double getV27() {
        return v27;
    }

    public void setV27(Double v27) {
        this.v27 = v27;
    }

    public Double getV28() {
        return v28;
    }

    public void setV28(Double v28) {
        this.v28 = v28;
    }

    public Double getV29() {
        return v29;
    }

    public void setV29(Double v29) {
        this.v29 = v29;
    }

    public Double getV30() {
        return v30;
    }

    public void setV30(Double v30) {
        this.v30 = v30;
    }

    public Double getV31() {
        return v31;
    }

    public void setV31(Double v31) {
        this.v31 = v31;
    }

    public Double getV32() {
        return v32;
    }

    public void setV32(Double v32) {
        this.v32 = v32;
    }

    public Double getV33() {
        return v33;
    }

    public void setV33(Double v33) {
        this.v33 = v33;
    }

    public Double getV34() {
        return v34;
    }

    public void setV34(Double v34) {
        this.v34 = v34;
    }

    public Double getV35() {
        return v35;
    }

    public void setV35(Double v35) {
        this.v35 = v35;
    }

    public Double getV36() {
        return v36;
    }

    public void setV36(Double v36) {
        this.v36 = v36;
    }

    public Double getV37() {
        return v37;
    }

    public void setV37(Double v37) {
        this.v37 = v37;
    }

    public Double getV38() {
        return v38;
    }

    public void setV38(Double v38) {
        this.v38 = v38;
    }

    public Double getV39() {
        return v39;
    }

    public void setV39(Double v39) {
        this.v39 = v39;
    }

    public Double getV40() {
        return v40;
    }

    public void setV40(Double v40) {
        this.v40 = v40;
    }

    public Double getV41() {
        return v41;
    }

    public void setV41(Double v41) {
        this.v41 = v41;
    }

    public Double getV42() {
        return v42;
    }

    public void setV42(Double v42) {
        this.v42 = v42;
    }

    public Double getV43() {
        return v43;
    }

    public void setV43(Double v43) {
        this.v43 = v43;
    }

    public Double getV44() {
        return v44;
    }

    public void setV44(Double v44) {
        this.v44 = v44;
    }

    public Double getV45() {
        return v45;
    }

    public void setV45(Double v45) {
        this.v45 = v45;
    }

    public Double getV46() {
        return v46;
    }

    public void setV46(Double v46) {
        this.v46 = v46;
    }

    public Double getV47() {
        return v47;
    }

    public void setV47(Double v47) {
        this.v47 = v47;
    }

    public Double getV48() {
        return v48;
    }

    public void setV48(Double v48) {
        this.v48 = v48;
    }

    public Double getV49() {
        return v49;
    }

    public void setV49(Double v49) {
        this.v49 = v49;
    }

    public Double getV50() {
        return v50;
    }

    public void setV50(Double v50) {
        this.v50 = v50;
    }

    public Double getV51() {
        return v51;
    }

    public void setV51(Double v51) {
        this.v51 = v51;
    }

    public Double getV52() {
        return v52;
    }

    public void setV52(Double v52) {
        this.v52 = v52;
    }

    public Double getV53() {
        return v53;
    }

    public void setV53(Double v53) {
        this.v53 = v53;
    }

    public Double getV54() {
        return v54;
    }

    public void setV54(Double v54) {
        this.v54 = v54;
    }

    public Double getV55() {
        return v55;
    }

    public void setV55(Double v55) {
        this.v55 = v55;
    }

    public Double getV56() {
        return v56;
    }

    public void setV56(Double v56) {
        this.v56 = v56;
    }

    public Double getV57() {
        return v57;
    }

    public void setV57(Double v57) {
        this.v57 = v57;
    }

    public Double getV58() {
        return v58;
    }

    public void setV58(Double v58) {
        this.v58 = v58;
    }

    public Double getV59() {
        return v59;
    }

    public void setV59(Double v59) {
        this.v59 = v59;
    }

    public Double getV60() {
        return v60;
    }

    public void setV60(Double v60) {
        this.v60 = v60;
    }

    public Double getV61() {
        return v61;
    }

    public void setV61(Double v61) {
        this.v61 = v61;
    }

    public Double getV62() {
        return v62;
    }

    public void setV62(Double v62) {
        this.v62 = v62;
    }

    public Double getV63() {
        return v63;
    }

    public void setV63(Double v63) {
        this.v63 = v63;
    }

    public Double getV64() {
        return v64;
    }

    public void setV64(Double v64) {
        this.v64 = v64;
    }

    public Double getV65() {
        return v65;
    }

    public void setV65(Double v65) {
        this.v65 = v65;
    }

    public Double getV66() {
        return v66;
    }

    public void setV66(Double v66) {
        this.v66 = v66;
    }

    public Double getV67() {
        return v67;
    }

    public void setV67(Double v67) {
        this.v67 = v67;
    }

    public Double getV68() {
        return v68;
    }

    public void setV68(Double v68) {
        this.v68 = v68;
    }

    public Double getV69() {
        return v69;
    }

    public void setV69(Double v69) {
        this.v69 = v69;
    }

    public Double getV70() {
        return v70;
    }

    public void setV70(Double v70) {
        this.v70 = v70;
    }

    public Double getV71() {
        return v71;
    }

    public void setV71(Double v71) {
        this.v71 = v71;
    }

    public Double getV72() {
        return v72;
    }

    public void setV72(Double v72) {
        this.v72 = v72;
    }

    public Double getV73() {
        return v73;
    }

    public void setV73(Double v73) {
        this.v73 = v73;
    }

    public Double getV74() {
        return v74;
    }

    public void setV74(Double v74) {
        this.v74 = v74;
    }

    public Double getV75() {
        return v75;
    }

    public void setV75(Double v75) {
        this.v75 = v75;
    }

    public Double getV76() {
        return v76;
    }

    public void setV76(Double v76) {
        this.v76 = v76;
    }

    public Double getV77() {
        return v77;
    }

    public void setV77(Double v77) {
        this.v77 = v77;
    }

    public Double getV78() {
        return v78;
    }

    public void setV78(Double v78) {
        this.v78 = v78;
    }

    public Double getV79() {
        return v79;
    }

    public void setV79(Double v79) {
        this.v79 = v79;
    }

    public Double getV80() {
        return v80;
    }

    public void setV80(Double v80) {
        this.v80 = v80;
    }

    public Double getV81() {
        return v81;
    }

    public void setV81(Double v81) {
        this.v81 = v81;
    }

    public Double getV82() {
        return v82;
    }

    public void setV82(Double v82) {
        this.v82 = v82;
    }

    public Double getV83() {
        return v83;
    }

    public void setV83(Double v83) {
        this.v83 = v83;
    }

    public Double getV84() {
        return v84;
    }

    public void setV84(Double v84) {
        this.v84 = v84;
    }

    public Double getV85() {
        return v85;
    }

    public void setV85(Double v85) {
        this.v85 = v85;
    }

    public Double getV86() {
        return v86;
    }

    public void setV86(Double v86) {
        this.v86 = v86;
    }

    public Double getV87() {
        return v87;
    }

    public void setV87(Double v87) {
        this.v87 = v87;
    }

    public Double getV88() {
        return v88;
    }

    public void setV88(Double v88) {
        this.v88 = v88;
    }

    public Double getV89() {
        return v89;
    }

    public void setV89(Double v89) {
        this.v89 = v89;
    }

    public Double getV90() {
        return v90;
    }

    public void setV90(Double v90) {
        this.v90 = v90;
    }

    public Double getV91() {
        return v91;
    }

    public void setV91(Double v91) {
        this.v91 = v91;
    }

    public Double getV92() {
        return v92;
    }

    public void setV92(Double v92) {
        this.v92 = v92;
    }

    public Double getV93() {
        return v93;
    }

    public void setV93(Double v93) {
        this.v93 = v93;
    }

    public Double getV94() {
        return v94;
    }

    public void setV94(Double v94) {
        this.v94 = v94;
    }

    public Double getV95() {
        return v95;
    }

    public void setV95(Double v95) {
        this.v95 = v95;
    }

    public Double getV96() {
        return v96;
    }

    public void setV96(Double v96) {
        this.v96 = v96;
    }

    /**
     * 获取96点值
     * @return
     */
    public Vector<Double> getVectorV96() {
        vectorV96 = new Vector<Double>();
        if(v01!=null && v48!=null && v96!=null){
            vectorV96.add(v01);
            vectorV96.add(v02);
            vectorV96.add(v03);
            vectorV96.add(v04);
            vectorV96.add(v05);
            vectorV96.add(v06);
            vectorV96.add(v07);
            vectorV96.add(v08);
            vectorV96.add(v09);
            vectorV96.add(v10);

            vectorV96.add(v11);
            vectorV96.add(v12);
            vectorV96.add(v13);
            vectorV96.add(v14);
            vectorV96.add(v15);
            vectorV96.add(v16);
            vectorV96.add(v17);
            vectorV96.add(v18);
            vectorV96.add(v19);
            vectorV96.add(v20);

            vectorV96.add(v21);
            vectorV96.add(v22);
            vectorV96.add(v23);
            vectorV96.add(v24);
            vectorV96.add(v25);
            vectorV96.add(v26);
            vectorV96.add(v27);
            vectorV96.add(v28);
            vectorV96.add(v29);
            vectorV96.add(v30);

            vectorV96.add(v41);
            vectorV96.add(v42);
            vectorV96.add(v43);
            vectorV96.add(v44);
            vectorV96.add(v45);
            vectorV96.add(v46);
            vectorV96.add(v47);
            vectorV96.add(v48);
            vectorV96.add(v49);
            vectorV96.add(v50);

            vectorV96.add(v51);
            vectorV96.add(v52);
            vectorV96.add(v53);
            vectorV96.add(v54);
            vectorV96.add(v55);
            vectorV96.add(v56);
            vectorV96.add(v57);
            vectorV96.add(v58);
            vectorV96.add(v59);
            vectorV96.add(v60);

            vectorV96.add(v61);
            vectorV96.add(v62);
            vectorV96.add(v63);
            vectorV96.add(v64);
            vectorV96.add(v65);
            vectorV96.add(v66);
            vectorV96.add(v67);
            vectorV96.add(v68);
            vectorV96.add(v69);
            vectorV96.add(v70);

            vectorV96.add(v71);
            vectorV96.add(v72);
            vectorV96.add(v73);
            vectorV96.add(v74);
            vectorV96.add(v75);
            vectorV96.add(v76);
            vectorV96.add(v77);
            vectorV96.add(v78);
            vectorV96.add(v79);
            vectorV96.add(v80);

            vectorV96.add(v81);
            vectorV96.add(v82);
            vectorV96.add(v83);
            vectorV96.add(v84);
            vectorV96.add(v85);
            vectorV96.add(v86);
            vectorV96.add(v87);
            vectorV96.add(v88);
            vectorV96.add(v89);
            vectorV96.add(v90);

            vectorV96.add(v91);
            vectorV96.add(v92);
            vectorV96.add(v93);
            vectorV96.add(v94);
            vectorV96.add(v95);
            vectorV96.add(v96);
        }else{
            for (int i=0;i<96;i++){
                vectorV96.add(0.0);
            }
        }
        return vectorV96;
    }

    public void setVectorV96(Vector<Double> vectorV96) {
        this.vectorV96 = vectorV96;
        setV01(vectorV96.get(0));
        setV02(vectorV96.get(1));
        setV03(vectorV96.get(2));
        setV04(vectorV96.get(3));
        setV05(vectorV96.get(4));
        setV06(vectorV96.get(5));
        setV07(vectorV96.get(6));
        setV08(vectorV96.get(7));
        setV09(vectorV96.get(8));
        setV10(vectorV96.get(9));

        setV11(vectorV96.get(10));
        setV12(vectorV96.get(11));
        setV13(vectorV96.get(12));
        setV14(vectorV96.get(13));
        setV15(vectorV96.get(14));
        setV16(vectorV96.get(15));
        setV17(vectorV96.get(16));
        setV18(vectorV96.get(17));
        setV19(vectorV96.get(18));
        setV20(vectorV96.get(19));

        setV21(vectorV96.get(20));
        setV22(vectorV96.get(21));
        setV23(vectorV96.get(22));
        setV24(vectorV96.get(23));
        setV25(vectorV96.get(24));
        setV26(vectorV96.get(25));
        setV27(vectorV96.get(26));
        setV28(vectorV96.get(27));
        setV29(vectorV96.get(28));
        setV30(vectorV96.get(29));

        setV31(vectorV96.get(30));
        setV32(vectorV96.get(31));
        setV33(vectorV96.get(32));
        setV34(vectorV96.get(33));
        setV35(vectorV96.get(34));
        setV36(vectorV96.get(35));
        setV37(vectorV96.get(36));
        setV38(vectorV96.get(37));
        setV39(vectorV96.get(38));
        setV40(vectorV96.get(39));

        setV41(vectorV96.get(40));
        setV42(vectorV96.get(41));
        setV43(vectorV96.get(42));
        setV44(vectorV96.get(43));
        setV45(vectorV96.get(44));
        setV46(vectorV96.get(45));
        setV47(vectorV96.get(46));
        setV48(vectorV96.get(47));
        setV49(vectorV96.get(48));
        setV50(vectorV96.get(49));

        setV51(vectorV96.get(50));
        setV52(vectorV96.get(51));
        setV53(vectorV96.get(52));
        setV54(vectorV96.get(53));
        setV55(vectorV96.get(54));
        setV56(vectorV96.get(55));
        setV57(vectorV96.get(56));
        setV58(vectorV96.get(57));
        setV59(vectorV96.get(58));
        setV60(vectorV96.get(59));

        setV61(vectorV96.get(60));
        setV62(vectorV96.get(61));
        setV63(vectorV96.get(62));
        setV64(vectorV96.get(63));
        setV65(vectorV96.get(64));
        setV66(vectorV96.get(65));
        setV67(vectorV96.get(66));
        setV68(vectorV96.get(67));
        setV69(vectorV96.get(68));
        setV70(vectorV96.get(69));

        setV71(vectorV96.get(70));
        setV72(vectorV96.get(71));
        setV73(vectorV96.get(72));
        setV74(vectorV96.get(73));
        setV75(vectorV96.get(74));
        setV76(vectorV96.get(75));
        setV77(vectorV96.get(76));
        setV78(vectorV96.get(77));
        setV79(vectorV96.get(78));
        setV80(vectorV96.get(79));

        setV81(vectorV96.get(80));
        setV82(vectorV96.get(81));
        setV83(vectorV96.get(82));
        setV84(vectorV96.get(83));
        setV85(vectorV96.get(84));
        setV86(vectorV96.get(85));
        setV87(vectorV96.get(86));
        setV88(vectorV96.get(87));
        setV89(vectorV96.get(88));
        setV90(vectorV96.get(89));

        setV91(vectorV96.get(90));
        setV92(vectorV96.get(91));
        setV93(vectorV96.get(92));
        setV94(vectorV96.get(93));
        setV95(vectorV96.get(94));
        setV96(vectorV96.get(95));

    }

    public static Vector<Double> getVectorF288(BasicBean bean){
        Vector<Double> unitVector = new Vector<Double>();
        Field[] fields = BasicBean.class.getDeclaredFields();
        for (Field field : fields) {
            if(field.getName().startsWith("f")){
                field.setAccessible(true);
                try {
                    Field lineField = BasicBean.class.getDeclaredField(field.getName());
                    lineField.setAccessible(true);
                    Double hh = (Double)lineField.get(bean);
                    unitVector.add(hh);
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return unitVector;

    }

    /**
     * 返回96点值
     * @param bean
     * @return
     */
    public static Vector<Double> getVectorF96(BasicBean bean){
        Vector<Double> unitVector = new Vector<Double>();
        Field[] fields = BasicBean.class.getDeclaredFields();
        for (Field field : fields) {
            if(field.getName().startsWith("f")){
                field.setAccessible(true);
                try {
                    Field lineField = BasicBean.class.getDeclaredField(field.getName());
                    lineField.setAccessible(true);
                    Double hh = (Double)lineField.get(bean);
                    unitVector.add(hh);
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return unitVector;

    }


    public static Vector<Double> getVectorL96(BasicBean bean){
        Vector<Double> unitVector = new Vector<Double>();
        Field[] fields = BasicBean.class.getDeclaredFields();
        for (Field field : fields) {
            if(field.getName().startsWith("l")){
                field.setAccessible(true);
                try {
                    Field lineField = BasicBean.class.getDeclaredField(field.getName());
                    lineField.setAccessible(true);
                    Double hh = (Double)lineField.get(bean);
                    unitVector.add(hh);
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return unitVector;

    }

    public static void main(String[] args) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        for (int i=1;i<=96;i++) {
            String[] hm = StringUtil.getNXTime(i).split(":");
            String caseid = "20160101";
            calendar.set(Integer.parseInt(caseid.substring(0, 4)), (Integer.parseInt(caseid.substring(4, 6)) - 1), Integer.parseInt(caseid.substring(6, 8)), Integer.parseInt(hm[0]), Integer.parseInt(hm[1]));
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(calendar.getTime()));
        }
//
    }
}
