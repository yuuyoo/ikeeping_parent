package com.zz.ikeeping.entity;

import lombok.Data;
import java.util.Date;

@Data
public class StepCount {
    private Integer id;
    private Integer uid;
    private Integer stepcount;
    private Date daydate;
}
