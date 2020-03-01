
//时间格式化
//调用时采用如下语句即可
//new Date().Format("yyyy-MM-dd hh:mm:ss")
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

function convertDateFromString(dateString) {
    var arr = dateString.split(" ");
    var day = arr[0].split("-");
    var date = new Date(day[0],day[1]-1,day[2]);
    return date;
}


var toDay = new Date().Format("yyyyMMdd");
var to_Day = new Date().Format("yyyy-MM-dd");