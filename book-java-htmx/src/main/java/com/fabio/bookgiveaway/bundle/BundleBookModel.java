package com.fabio.bookgiveaway.bundle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "bundle_book")
@IdClass(BundleBookPK.class)
class BundleBookModel {
    @Id
    @Column(name = "bundle_id")
    private Long bundleId;

    @Id
    @Column(name = "book_id")
    private Long bookId;
}
