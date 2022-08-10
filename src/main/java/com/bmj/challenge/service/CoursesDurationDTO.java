package com.bmj.challenge.service;

import com.bmj.challenge.mapper.CourseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CoursesDurationDTO {

    private List<CourseDTO> courses;

    private Double duration;

}
