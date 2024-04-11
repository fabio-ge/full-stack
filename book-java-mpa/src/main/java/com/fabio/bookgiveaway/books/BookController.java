package com.fabio.bookgiveaway.books;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;




@RequiredArgsConstructor
@Controller
@RequestMapping("/books")
class BookController {
    
    private final BookService bookService;

    @GetMapping
    public String getIndex( @RequestParam(name = "q", required = false) String q,
                            Model model){
        ReadDTO readDTO;                        
        
        if(q == null || q.isBlank()){
            readDTO = bookService.getHomePageData();
        }else{
            readDTO = bookService.getHomePageDataFiltered(q);
        }
        

        model.addAttribute("newBook", new BookModel());
        model.addAttribute("filter",q);
        model.addAttribute("totale", readDTO.totale());
        model.addAttribute("libri", readDTO.books());
        return "books";
    }
    
    @PostMapping()
    public String addNewBook(@ModelAttribute BookModel newBookToSave) {
        if(!newBookToSave.getTitolo().isBlank()){
            bookService.saveNewBook(newBookToSave);
        }

        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);

        return "redirect:/books";
    }
    
    
}
