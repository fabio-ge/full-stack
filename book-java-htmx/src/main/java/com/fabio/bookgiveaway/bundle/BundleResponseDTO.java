package com.fabio.bookgiveaway.bundle;

import java.util.List;

import com.fabio.bookgiveaway.books.BookModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data 
public class BundleResponseDTO {
    private Long id;

    private String nome;

    private List<BookModel> books;
}
