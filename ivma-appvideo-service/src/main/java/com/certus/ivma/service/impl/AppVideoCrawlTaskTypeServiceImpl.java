package com.certus.ivma.service.impl;

import com.certus.ivma.entity.AppVideoCrawlTaskType;
import com.certus.ivma.mapper.*;
import com.certus.ivma.service.AppVideoCrawlTaskTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 123 on 2019/2/21.
 */
@Service
public class AppVideoCrawlTaskTypeServiceImpl implements AppVideoCrawlTaskTypeService {

    private static final Logger LOG = LoggerFactory.getLogger(AppVideoCrawlTaskTypeServiceImpl.class);
    @Autowired
    private AppVideoCrawlTaskTypeMapper appVideoCrawlTaskTypeMapper;


    @Override
    public List<AppVideoCrawlTaskType> getCrawlType() {
        return appVideoCrawlTaskTypeMapper.getCrawlType();
    }
}
