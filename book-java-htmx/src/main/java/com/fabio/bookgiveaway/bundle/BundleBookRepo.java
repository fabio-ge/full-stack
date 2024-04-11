package com.fabio.bookgiveaway.bundle;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface BundleBookRepo extends JpaRepository<BundleBookModel,BundleBookPK>{
    List<BundleBookModel> findByBundleId(Long bundleId);
    
    boolean existsByBookId(Long bookId);

    int deleteByBookId(Long bookId);

    int deleteByBundleId(Long bundleId);
}
