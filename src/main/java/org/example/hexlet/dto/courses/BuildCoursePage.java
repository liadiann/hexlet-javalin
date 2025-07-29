package org.example.hexlet.dto.courses;

import io.javalin.validation.ValidationError;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BuildCoursePage {
    private Map<String, List<ValidationError<Object>>> errors;
}
