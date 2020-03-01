package com.dky.controller.generatrixAnalysis.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dky.util.Encryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by larry on 2014/11/17.
 */
public class BaseController {

    protected Logger log = LoggerFactory.getLogger(getClass());
    protected final static  Logger logger = LoggerFactory.getLogger(BaseController.class);
    private static int chunkSize = 64;



    /**
     * 保存上传的图片
     * @param request
     * @return
     */
    public Map<String, String> saveAndGetPics(HttpServletRequest request) {
        if (request instanceof MultipartHttpServletRequest) {
            Map<String, String> ret = new HashMap<String, String>();
            try {
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();

            } catch (Exception e) {
                logger.error("save pic error", e);
            }
            return ret;
        }
        return null;
    }

    /**
     * int 转换成 byte 数组，做了特殊处理只返回三个字节，不通用
     *
     * @param i
     * @return
     */
    private static byte[] intToByteArray(int i) {
        byte[] result = new byte[3];
//        result[0] = (byte)((i >> 24) & 0xFF);
        result[0] = (byte) ((i >> 16) & 0xFF);
        result[1] = (byte) ((i >> 8) & 0xFF);
        result[2] = (byte) (i & 0xFF);
        return result;
    }

    public String getPayload(HttpServletRequest request) {
        InputStream in = null;
        try {
            if (request instanceof MultipartHttpServletRequest) {
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                return request.getParameter("payload");
            } else {
                in = request.getInputStream();
            }
            if (in == null) {
                return null;
            }
            byte[] buf = new byte[chunkSize];
            int read = 0;
            int i = 0;
            while ((i = in.read(buf, read, buf.length - read)) != -1) {
                read += i;
                if (read == buf.length) {//full
                    buf = Arrays.copyOf(buf, buf.length + chunkSize);
                }
            }
            return new String(buf, 0, read, "utf-8");
        } catch (IOException e) {
            logger.error("read payload error", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }

    public String getDecodedPayload(HttpServletRequest request, String secretKey) {
        return Encryptor.decodeAES(secretKey, getPayload(request));
    }

    public JSONObject getJsonArgs(HttpServletRequest request) {
        return JSON.parseObject(getPayload(request));
    }

    public JSONObject getDecodedJsonArgs(HttpServletRequest request, String secretKey) {
        return JSON.parseObject(Encryptor.decodeAES(secretKey, getPayload(request)));
    }

}
