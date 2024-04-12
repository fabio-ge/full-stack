package com.fabio.bookgiveaway.bundle;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fabio.bookgiveaway.books.BookModel;
import com.fabio.bookgiveaway.books.BookService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;



@RequiredArgsConstructor
@RequestMapping("/bundle")
@Controller
public class BundleController {
    
    private final BookService bookService;
    private final BundleService bundleService;

    @GetMapping
    public String bundle(Model model){
        List<BookModel> books = bookService.getAllBooks();
        
        model.addAttribute("newBundle",new BundleRequestDTO("",List.of()));
        model.addAttribute("libri", books);
        return "bundle";
    }

    @ResponseBody
    @PostMapping
    public String creaNewBundle(@ModelAttribute BundleRequestDTO bundleRequestDTO){
        if(bundleRequestDTO.selezionato() == null){
            return "<span style='color: red'>Selezionare almeno un libro per creare un bundle</span>";
        }

        bundleService.saveNewBundle(bundleRequestDTO);
        return "<span style='color: green'>bundle salvato con successo</span><input hx-swap-oob='true' type='text' id='nome' name='nome' placeholder='Nome del bundle'>";
    }
    
    @GetMapping("/all")
    public String allBundles(Model model){
        model.addAttribute("bundles", bundleService.getAllBundles());

        return "bundles";
    }

    @ResponseBody
    @DeleteMapping("/{bundleId}")
    public String sgancia(@PathVariable("bundleId") Long bundleId) {
        bundleService.sgancia(bundleId);

        return "";
    }

    @ResponseBody
    @DeleteMapping("/complete/{bundleId}")
    public String deleteBundleAndBooks(@PathVariable Long bundleId) {
        bundleService.deleteBundleWithBooks(bundleId);
        
        return "";
    }
    
    
}
