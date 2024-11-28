package org.practice.simplehomeorderservice.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {
    private String taskName;
    private String description;
    private List<SubTaskDto> subTasks;
}
