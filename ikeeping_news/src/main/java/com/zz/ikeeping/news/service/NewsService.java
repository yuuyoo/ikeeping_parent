package com.zz.ikeeping.news.service;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.News;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface NewsService {
    R insert(News record);



    R queryPage(Map<String,String> map);

}
