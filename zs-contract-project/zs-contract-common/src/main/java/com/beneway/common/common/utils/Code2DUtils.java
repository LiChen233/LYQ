package com.beneway.common.common.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Code2DUtils {

    /**
     * 生成二维码
     * @param content 内容
     */
    public static void createCode(String content, int width, int height, HttpServletResponse response){
        QrConfig config = new QrConfig(width, height);

//        File file = new File("D:\\final-contract-zs\\zs-contract-project\\zs-contract-common\\src\\main\\resources\\static\\dog.jpg");
        //logo
//        config.setImg(file);
        // 边距
        config.setMargin(0);
        // 高纠错级别
        config.setErrorCorrection(ErrorCorrectionLevel.H);
        // 前景色
        config.setForeColor(new Color(24, 144, 255).getRGB());
        // 背景色
        config.setBackColor(new Color(242,242,242).getRGB());

        BufferedImage bufferedImage = QrCodeUtil.generate(//
                content, //二维码内容
                config
        );

        File outputfile = new File("saved.png");
        try {
            ImageIO.write(bufferedImage, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            //以JPEG格式向客户端发送
            ServletOutputStream os = response.getOutputStream();
            ImageIO.write(bufferedImage, "PNG",os);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 识别二维码
     * @param address 图片地址
     * @return
     */
    private static String decode(String address) {
        String decode = QrCodeUtil.decode(FileUtil.file(address));
        return decode;
    }

}
