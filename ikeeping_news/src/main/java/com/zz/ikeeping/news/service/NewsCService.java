package com.zz.ikeeping.news.service;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.NewsC;

public interface NewsCService {

    R update(NewsC c);

    R insert(NewsC c);
}
