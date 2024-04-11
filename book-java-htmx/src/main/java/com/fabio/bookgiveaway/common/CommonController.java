package com.fabio.bookgiveaway.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fabio.bookgiveaway.books.BookModel;
import com.fabio.bookgiveaway.books.BookService;
import com.fabio.bookgiveaway.books.ReadDTO;
import com.fabio.bookgiveaway.bundle.BundleService;

import lombok.RequiredArgsConstructor;



@Controller
@RequestMapping("/")
@RequiredArgsConstructor
class CommonController {
    
    private final BookService bookService;
    private final BundleService bundleService;

    @GetMapping
    public String home(Model model) {
        ReadDTO readDTO = bookService.getHomePageData();
        

        model.addAttribute("newBook", new BookModel());
        model.addAttribute("totale", readDTO.totale());
        model.addAttribute("libri", readDTO.books());
        return "books";
    }

    @GetMapping("stampa")
    public String stampa(@RequestParam(name = "filter", required = false) String filter,
                         Model model) {
        model.addAttribute("nomeBundle", null);
        model.addAttribute("elenco", bookService.elencoTotale(filter));
        return "stampa";
    }

    @GetMapping("stampa/{bundleId}")
    public String stampa(@RequestParam(name = "filter", required = false) String filter,
                        @PathVariable Long bundleId,
                        Model model) {


        model.addAttribute("bundleId",bundleId);                    
        model.addAttribute("nomeBundle", bundleService.getNomeBundle(bundleId));
        model.addAttribute("elenco", bundleService.elenco(bundleId, filter));
        return "stampa";
    }
        
}
