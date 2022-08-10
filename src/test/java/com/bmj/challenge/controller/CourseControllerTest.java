package com.bmj.challenge.controller;

import com.bmj.challenge.mapper.CourseDTO;
import com.bmj.challenge.service.CourseService;
import com.bmj.challenge.service.CoursesDurationDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourseController.class)
class CourseControllerTest {

    @MockBean
    private CourseService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void searchCourse_ReturnsListOfCourseDTOs() throws Exception {

        List<CourseDTO> expectedCourses = createListOfCourseDTOs();

        when(service.searchBy(anyString())).thenReturn(expectedCourses);

        mockMvc.perform(get("/courses")
                        .param("searchTerm", "Tit"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(expectedCourses.size()))
                .andDo(print());
    }

    @Test
    public void searchCoursesAndGetDuration_ReturnsCoursesDurationDTO() throws Exception {

        List<CourseDTO> expectedCourses = createListOfCourseDTOs();
        Double expectedDuration = 45.00;
        CoursesDurationDTO result = new CoursesDurationDTO(expectedCourses, expectedDuration);
        when(service.searchByAndGetCoursesDuration(anyString())).thenReturn(result);

        mockMvc.perform(get("/courses/duration")
                        .param("searchTerm", "Tit"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.duration").exists())
                .andExpect(jsonPath("$.duration").value(expectedDuration))
                .andExpect(jsonPath("$.courses").exists())
                .andExpect(jsonPath("$.courses.size()").value(expectedCourses.size()))
                .andDo(print());


    }

    private List<CourseDTO> createListOfCourseDTOs() {
        long id = 1L;
        CourseDTO courseDTO = new CourseDTO(id, "Title", 2.3);

        return Arrays.asList(courseDTO);
    }

}