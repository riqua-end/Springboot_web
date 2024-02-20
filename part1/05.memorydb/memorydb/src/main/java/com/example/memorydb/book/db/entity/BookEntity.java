package com.example.memorydb.book.db.entity;

import com.example.memorydb.entity.Entity;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookEntity extends Entity {

    private String name;
    private String category;
    private BigDecimal amount;
}
