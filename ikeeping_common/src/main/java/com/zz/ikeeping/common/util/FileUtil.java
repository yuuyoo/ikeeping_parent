package com.zz.ikeeping.common.util;

import sun.java2d.pipe.SpanShapeRenderer;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FileUtil {
    public static File createDir(String realFile,String desFile){
        File dir=new File(realFile,desFile);
        if(!dir.exists()){
            dir.mkdirs();
        }
        return dir;
    }
    //每月新建文件夹
    public static File createMonthDir(String realFile,String desFile) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
        String dirName=sdf.format(new Date());
        File dir=createDir(realFile, desFile);
        //是否存在当前月份的文件夹
//        String[] dirs=dir.list();
//        boolean isHave=false;
//        for(String f:dirs){
//            if(f.equals(dirName)){
//                isHave=true;
//            }
//        }
        File desDir=new File(dir,dirName);
        if(!desDir.exists()){
            desDir.mkdirs();
        }
        return desDir;
    }
    //每日新建文件夹
    public static File createDayDir(String realFile,String desFile) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String dirName=sdf.format(new Date());
        File dir=createDir(realFile, desFile);
        File desDir=new File(dir,dirName);
        if(!desDir.exists()){
            desDir.mkdirs();
        }
        return desDir;
    }
    //自动清除3个月以上的日志内容
    public static boolean clearDir(String realFile,String desFile){
        return true;
    }
    //截取并重命名
    public static String renameFile(String fileName){
        if(fileName.length()>50){
            fileName=fileName.substring(fileName.length()-50);
        }
        return System.currentTimeMillis()+"_"+UUID.randomUUID().toString().replace("-","")+"_"+fileName;
    }



}
