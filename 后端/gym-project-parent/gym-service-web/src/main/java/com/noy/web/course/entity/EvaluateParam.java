package com.noy.web.course.entity;

import lombok.Data;

@Data
public class EvaluateParam {
    private Long courseId;
    private Long memberId;
    private Integer rating;
    private String comment;
}