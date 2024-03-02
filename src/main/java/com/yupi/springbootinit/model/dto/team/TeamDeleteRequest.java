package com.yupi.springbootinit.model.dto.team;

import lombok.Data;

import java.io.Serializable;

@Data
public class TeamDeleteRequest implements Serializable {
    private Long id;

    private static final long serialVersionUID = 1L;
}
