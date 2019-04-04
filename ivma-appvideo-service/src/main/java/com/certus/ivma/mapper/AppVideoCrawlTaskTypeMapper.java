package com.certus.ivma.mapper;

import com.certus.ivma.entity.AppVideoCrawlTaskType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AppVideoCrawlTaskTypeMapper {

    List<AppVideoCrawlTaskType> getCrawlType();
}
