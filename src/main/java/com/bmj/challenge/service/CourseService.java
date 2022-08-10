package com.bmj.challenge.service;


import com.bmj.challenge.mapper.CourseDTO;

import java.util.List;

public interface CourseService {

    List<CourseDTO> searchBy(String searchTerm);

    CoursesDurationDTO searchByAndGetCoursesDuration(String searchTerm);

}
