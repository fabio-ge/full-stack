package com.fabio.bookgiveaway.books;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fabio.bookgiveaway.bundle.BundleBookRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepo bookRepo;
    private final BundleBookRepo bundleBookRepo;

    public List<BookModel> getAllBooks(){
        return bookRepo.findAll();
    };

    public ReadDTO getHomePageData(){
        List<BookModel> books = bookRepo.findAll();
        Integer totale = (int) books.stream().count();
        return new ReadDTO(totale,books);        
    }

    ReadDTO getHomePageDataFiltered(String filter){
        List<BookModel> books = bookRepo.findAll()
                                        .stream()
                                        .filter(book -> book.getTitolo().toLowerCase().contains(filter.toLowerCase()))
                                        .toList();

        Integer totale = (int) books.stream().count();
        return new ReadDTO(totale,books);
    }

    void saveNewBook(BookModel book){
        bookRepo.save(book);
    }

    @Transactional
    public void deleteBook(Long id){
        if(bundleBookRepo.existsByBookId(id)){
            bundleBookRepo.deleteByBookId(id);
        }

        bookRepo.deleteById(id);
    }

    public BookModel findBookById(Long id){
        return bookRepo.findById(id).orElseThrow(() -> new NoSuchElementException());
    }

    public List<String> elencoTotale(){
            return bookRepo
                        .findAll()
                        .stream()
                        .map(BookModel::toString)
                        .toList();
    }

}
