package org.example.hexlet.dto;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class MainPage {
    private Boolean visited;

    public Boolean isVisited() {
        return visited;
    }

}
