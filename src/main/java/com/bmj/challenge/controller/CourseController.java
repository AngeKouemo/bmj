package com.bmj.challenge.controller;

import com.bmj.challenge.mapper.CourseDTO;
import com.bmj.challenge.service.CourseService;
import com.bmj.challenge.service.CoursesDurationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService service;

    @GetMapping()
    public ResponseEntity<List<CourseDTO>> searchCourse(@RequestParam(required = false) String searchTerm) {
        return new ResponseEntity<>(service.searchBy(searchTerm), HttpStatus.OK);
    }

    @GetMapping("/duration")
    public ResponseEntity<CoursesDurationDTO> searchCoursesAndGetDuration(@RequestParam(required = false) String searchTerm) {
        return new ResponseEntity<>(service.searchByAndGetCoursesDuration(searchTerm), HttpStatus.OK);
    }
}
