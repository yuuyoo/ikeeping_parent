package com.zz.ikeeping.server.user.util;

import java.util.Random;


public class RandomUtil {
    public static int createNum(int start,int end){
        Random random=new Random();
        return random.nextInt(end-start)+start;
    }
}
