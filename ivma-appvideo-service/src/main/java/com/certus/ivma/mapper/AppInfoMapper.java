package com.certus.ivma.mapper;

import com.certus.ivma.entity.AppInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by 123 on 2019/2/21.
 */
@Mapper
public interface AppInfoMapper {
    public List<AppInfo> selectAllAppInfoList();

//    public AppInfo selectAppInfoById(Long id);

//    public List<AppInfo> selectCrawlAppList(@Param("condition")AppInfo condition, @Param("orderBy") OrderBy orderBy, RowBounds rowBounds);

//    public int countCrawlAppList(@Param("condition")AppInfo condition);
}
