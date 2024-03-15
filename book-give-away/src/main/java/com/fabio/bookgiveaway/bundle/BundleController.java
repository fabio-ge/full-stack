package com.fabio.bookgiveaway.bundle;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fabio.bookgiveaway.books.BookModel;
import com.fabio.bookgiveaway.books.BookService;

import lombok.RequiredArgsConstructor;

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

    @PostMapping
    public String creaNewBundle(@ModelAttribute BundleRequestDTO bundleRequestDTO){

        bundleService.saveNewBundle(bundleRequestDTO);
        return "redirect:/books";
    }
    
    @GetMapping("/all")
    public String allBundles(Model model){
        model.addAttribute("bundles", bundleService.getAllBundles());

        return "bundles";
    }

    @GetMapping("/sgancia/{bundleId}")
    public String sgancia(@PathVariable("bundleId") Long bundleId) {
        bundleService.sgancia(bundleId);

        return "redirect:/bundle/all";
    }

    @GetMapping("/delete/{bundleId}")
    public String deleteBundleAndBooks(@PathVariable Long bundleId) {
        bundleService.deleteBundleWithBooks(bundleId);
        
        return "redirect:/bundle/all";
    }
    
    
}
