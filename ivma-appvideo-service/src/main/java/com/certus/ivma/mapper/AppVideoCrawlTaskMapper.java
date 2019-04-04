package com.certus.ivma.mapper;

import com.certus.ivma.entity.AppVideoCrawlTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AppVideoCrawlTaskMapper {

	public int insertCrawlTask(AppVideoCrawlTask task);

    public List<AppVideoCrawlTask> selectFailFastCrawlTaskList(@Param("batchNo")String batchNo, @Param("limitTimes") Integer limitTimes);

    public int  reCrawlTask(@Param("taskId")Long taskId, @Param("priority")Integer priority);

    int countCrawlTaskSchedule(@Param("batchNo")String batchNo, @Param("taskType")String taskType);

    List<AppVideoCrawlTask> selectCrawlTaskSchedule(@Param("batchNo")String batchNo, @Param("taskType")String taskType);

    public int updateCrawlTask(AppVideoCrawlTask task);

    public int updataCrawlTaskScheduleHostByTaskId(@Param("ip") String ip, @Param("taskId") Long taskId);
/*
	public AppVideoCrawlTask selectCrawlTaskByTaskId(Long taskId);

	public int resetCrawlTask(@Param("taskId") Long taskId, @Param("priority") Integer priority);
	
	public int deleteCrawlTask(String batchNo);
	
	public AppVideoCrawlTask selectRandomCrawlTask4Schedule(@Param("batchNo") String batchNo, @Param("taskType") String taskType);
	
	public int countCrawlTask4Schedule(@Param("batchNo") String batchNo, @Param("taskType") String taskType);
	
	public int updateCrawlTask(AppVideoCrawlTask task);
	
	public List<AppVideoCrawlTask> selectCrawlTaskList(@Param("condition") AppVideoCrawlTask condition, @Param("orderBy") OrderBy orderBy, RowBounds rowBounds);
	
	public int countCrawlTaskList(@Param("condition") AppVideoCrawlTask condition);
	
	public int countCrawlTaskStatus(@Param("batchNo") String batchNo, @Param("statuses") List<Integer> statuses);
	

	
	public List<AppVideoCrawlTask> selectZombieCrawlTaskList(@Param("batchNo") String batchNo, @Param("limitTimes") Integer limitTimes);
	*/
}
