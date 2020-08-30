package com.onlineeducation.onlineeducation.dao;

import com.onlineeducation.onlineeducation.entity.Courseware;
import com.onlineeducation.onlineeducation.entity.Video;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CoursewareDao {

    /**
     * 显示所有课件信息
     * @param param
     * @return
     */
    List<Courseware> findCoursewareInTeacher(Map param);

    /**
     * 获取课件总数
     * @param param
     * @return
     */
    int getTotalCoursewareInTeacher(Map param);

    /**
     * 显示所有课件信息
     * @param param
     * @return
     */
    List<Courseware> findCoursewareInAdmin(Map param);

    /**
     * 获取课件总数
     * @param param
     * @return
     */
    int getTotalCoursewareInAdmin(Map param);

    /**
     * 上传课件
     *
     * @param courseware
     * @return
     */
    int addCourseware(Courseware courseware);

    /**
     * 根据课程名称查找课件
     * @param courseName
     * @return
     */
    List<Courseware> findCoursewareByCourseName(String courseName);

    /**
     * 更新课件(编辑)
     * @param courseware
     * @return
     */
    int updateCourseware(Courseware courseware);

    int checkCourseware(@Param("coursewareId") Long coursewareId, @Param("isChecked") String isChecked);

    /**
     * 通过id查找课件
     * @param id
     * @return
     */
    Courseware findCoursewareById(Integer id);

    /**
     * 批量删除
     * @param id
     * @return
     */
    int deleteCoursewareBatch(Object[] id);
}
