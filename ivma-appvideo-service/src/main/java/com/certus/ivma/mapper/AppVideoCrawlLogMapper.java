package com.certus.ivma.mapper;

import com.certus.ivma.entity.AppVideoCrawlLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by 123 on 2019/2/21.
 */
@Mapper
public interface AppVideoCrawlLogMapper {

    public int insertCrawlLog(AppVideoCrawlLog log);

    public int updateCrawlLog(AppVideoCrawlLog log);

//    public List<AppVideoCrawlLog> selectCrawlTaskLogList(@Param("condition")AppVideoCrawlLog condition, @Param("orderBy") OrderBy orderBy, RowBounds rowBounds);

    public int countCrawlTaskLogList(@Param("condition")AppVideoCrawlLog condition);
}
