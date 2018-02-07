package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class TrelloCardDto {
private String name;
private String description;
private String pos;
private String listId;

}
