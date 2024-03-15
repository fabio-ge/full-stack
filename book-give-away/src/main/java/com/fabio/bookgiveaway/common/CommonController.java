package com.fabio.bookgiveaway.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.fabio.bookgiveaway.books.BookModel;
import com.fabio.bookgiveaway.books.BookService;
import com.fabio.bookgiveaway.books.ReadDTO;

import lombok.RequiredArgsConstructor;



@Controller
@RequestMapping("/")
@RequiredArgsConstructor
class CommonController {
    
    private final BookService bookService;
    @GetMapping
    public String home(Model model) {
        ReadDTO readDTO = bookService.getHomePageData();
        

        model.addAttribute("newBook", new BookModel());
        model.addAttribute("totale", readDTO.totale());
        model.addAttribute("libri", readDTO.books());
        return "books";
    }
        
}
