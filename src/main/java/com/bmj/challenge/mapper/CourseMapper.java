package com.bmj.challenge.mapper;

import com.bmj.challenge.repository.Course;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDTO courseToCourseDTO(Course course);

    List<CourseDTO> coursesToCourseAllDTOs(List<Course> courses);

}
