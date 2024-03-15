package com.fabio.bookgiveaway.bundle;

import org.springframework.stereotype.Service;

import com.fabio.bookgiveaway.books.BookModel;
import com.fabio.bookgiveaway.books.BookService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BundleService {

    private final BundleRepo bundleRepo;
    private final BundleBookRepo bundleBookRepo;
    private final BookService bookService;

    void saveNewBundle(BundleRequestDTO bundleRequestDTO){
        BundleModel bundle = new BundleModel();
        bundle.setNome(bundleRequestDTO.nome());
        BundleModel newBundle = bundleRepo.save(bundle);

        for(Long bookId : bundleRequestDTO.selezionato()){
            BundleBookModel bundleBookToSave = new BundleBookModel();
            bundleBookToSave.setBookId(bookId);
            bundleBookToSave.setBundleId(newBundle.getId());
            bundleBookRepo.save(bundleBookToSave);
        }
    }

    List<BundleResponseDTO> getAllBundles(){
        return bundleRepo
                .findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional
    void sgancia(Long bundleId){
        bundleBookRepo.deleteByBundleId(bundleId);
        bundleRepo.deleteById(bundleId);
    }

    @Transactional
    void deleteBundleWithBooks(Long bundleId){
        List<Long> bookIds = bundleBookRepo.findByBundleId(bundleId)
                                           .stream()
                                           .map(bb ->bb.getBookId())
                                           .toList();

        bookIds.forEach(id -> bookService.deleteBook(id));
        bundleRepo.deleteById(bundleId);    
    }

    //UTIL METHODS

    private BundleResponseDTO toDTO(BundleModel bundle){
        var response = new BundleResponseDTO();
        response.setId(bundle.getId());
        response.setNome(bundle.getNome());
        
        List<BookModel> books = bundleBookRepo
                            .findByBundleId(bundle.getId())
                            .stream()
                            .map(obj -> {
                                var bookId = obj.getBookId();

                                return bookService.findBookById(bookId);
                            })
                            .toList();

        response.setBooks(books);
        return response;
    }
}
