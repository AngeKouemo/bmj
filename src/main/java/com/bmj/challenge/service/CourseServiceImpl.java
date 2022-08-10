package com.bmj.challenge.service;

import com.bmj.challenge.mapper.CourseDTO;
import com.bmj.challenge.mapper.CourseMapper;
import com.bmj.challenge.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private CourseRepository repository;

    private CourseMapper mapper;

    @Override
    public List<CourseDTO> searchBy(String searchTerm) {
        return mapper.coursesToCourseAllDTOs(repository.findByTitleContainingIgnoreCase(searchTerm));
    }

    @Override
    public CoursesDurationDTO searchByAndGetCoursesDuration(String searchTerm) {
        List<CourseDTO> courses = mapper.coursesToCourseAllDTOs(repository.findByTitleContainingIgnoreCase(searchTerm));
        Double sum = 0.0;

        if (!courses.isEmpty()) {
            sum = courses.stream()
                    .map(c -> c.getDuration())
                    .filter(x -> x != null)
                    .reduce(0.0, (a, b) -> Double.sum(a, b));
        }

        return new CoursesDurationDTO(courses, sum);
    }
}
