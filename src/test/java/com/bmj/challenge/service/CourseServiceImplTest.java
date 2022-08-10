package com.bmj.challenge.service;

import com.bmj.challenge.mapper.CourseDTO;
import com.bmj.challenge.mapper.CourseMapper;
import com.bmj.challenge.repository.Course;
import com.bmj.challenge.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceImplTest {

    @Mock
    private CourseRepository repository;
    @Mock
    private CourseMapper mapper;
    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    public void searchBy_ReturnsEmptyList_WhenSearchTermNotPresent() {

        when(repository.findByTitleContainingIgnoreCase(anyString())).thenReturn(Collections.emptyList());
        when(mapper.coursesToCourseAllDTOs(anyList())).thenReturn(Collections.emptyList());

        List<CourseDTO> actual = courseService.searchBy("Title");

        assertThat(actual).isNotNull();
        assertThat(actual.size()).isEqualTo(0);
    }

    @Test
    public void searchBy_ReturnsListOfCourseDTO_WhenSearchTermPresent() {

        List<Course> courses = Arrays.asList(
                new Course(1L, "Title 1", 2.0),
                new Course(2L, "Title 1", 4.0)
        );

        List<CourseDTO> courseDTOs = Arrays.asList(
                new CourseDTO(1L, "Title 1", 2.0),
                new CourseDTO(2L, "Title 1", 4.0)
        );

        when(repository.findByTitleContainingIgnoreCase(anyString())).thenReturn(courses);
        when(mapper.coursesToCourseAllDTOs(anyList())).thenReturn(courseDTOs);

        List<CourseDTO> actual = courseService.searchBy("Title");

        assertThat(actual).isNotNull();
        assertThat(actual.size()).isEqualTo(courseDTOs.size());
        assertThat(actual.get(0).getTitle()).isEqualTo("Title 1");
        verify(mapper, times(1)).coursesToCourseAllDTOs(anyList());
    }

    @Test
    public void searchByAndGetCoursesDuration_ReturnsCoursesDurationDTOWithEmptyList_WhenSearchTermNotPresent() {

        when(repository.findByTitleContainingIgnoreCase(anyString())).thenReturn(Collections.emptyList());
        when(mapper.coursesToCourseAllDTOs(anyList())).thenReturn(Collections.emptyList());

        CoursesDurationDTO actual = courseService.searchByAndGetCoursesDuration("Title");

        assertThat(actual).isNotNull();
        assertThat(actual.getCourses().size()).isEqualTo(0);
        assertThat(actual.getDuration()).isEqualTo(0.0);
    }

    @Test
    public void searchByAndGetCoursesDuration_ReturnsCoursesDurationDTOWithCourseDTOs_WhenSearchTermPresent() {

        List<Course> courses = Arrays.asList(
                new Course(1L, "Title 1", 2.0),
                new Course(2L, "Title 1", 4.0)
        );

        List<CourseDTO> courseDTOs = Arrays.asList(
                new CourseDTO(1L, "Title 1", 2.0),
                new CourseDTO(2L, "Title 1", 4.0)
        );

        when(repository.findByTitleContainingIgnoreCase(anyString())).thenReturn(courses);
        when(mapper.coursesToCourseAllDTOs(anyList())).thenReturn(courseDTOs);

        CoursesDurationDTO actual = courseService.searchByAndGetCoursesDuration("Title");

        assertThat(actual).isNotNull();
        assertThat(actual.getCourses().size()).isEqualTo(courseDTOs.size());
        assertThat(actual.getDuration()).isEqualTo(6.0);
    }
}