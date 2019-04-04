package com.certus.ivma.service;

import com.certus.ivma.entity.AppVideoCrawlTaskType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 123 on 2019/2/21.
 */
@Service
public interface AppVideoCrawlTaskTypeService {

    List<AppVideoCrawlTaskType> getCrawlType();
}
