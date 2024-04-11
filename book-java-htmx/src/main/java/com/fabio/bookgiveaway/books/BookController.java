package com.fabio.bookgiveaway.books;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;




@RequiredArgsConstructor
@Controller
@RequestMapping("/books")
class BookController {
    
    private final BookService bookService;

    @GetMapping
    public String getIndex(Model model){
        addAttributesToHomePage(model, null, bookService.getHomePageData());
        return "books";
    }

    @GetMapping("/search")
    public String getIndex(@RequestParam(name = "q") String q,
                           Model model){
            addAttributesToHomePage(model, q, bookService.getHomePageDataFiltered(q));
            return "books :: listaLibri";
    }
    
    
    @PostMapping()
    public String addNewBook(@ModelAttribute BookModel newBookToSave,
                            Model model) {
        BookModel book = null;
        if(!newBookToSave.getTitolo().isBlank()){
            book = bookService.saveNewBook(newBookToSave);
        }
        model.addAttribute("book", book);
        model.addAttribute("totale", bookService.getTotale());
        return "/parts/libro";
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") Long id,
                             Model model) {
        bookService.deleteBook(id);
        
        return "<div id='info-totale' hx-swap-oob='true'>Libri totali: <span>" + bookService.getTotale() + "</span></div>";
    }

    private void addAttributesToHomePage(Model model, String q, ReadDTO readDTO){
        model.addAttribute("newBook", new BookModel());
        model.addAttribute("filter",q);
        model.addAttribute("totale", readDTO.totale());
        model.addAttribute("libri", readDTO.books());  
    }
    
    
}
