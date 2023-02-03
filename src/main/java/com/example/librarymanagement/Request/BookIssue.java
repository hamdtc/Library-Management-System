package com.example.librarymanagement.Request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BookIssue {

    @NonNull
    private int cardId;

    @NonNull
    private int bookId;

    private int fine;

}
