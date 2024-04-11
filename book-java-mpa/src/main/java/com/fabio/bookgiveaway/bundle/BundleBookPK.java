package com.fabio.bookgiveaway.bundle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
class BundleBookPK {
    private Long bundleId;
    private Long bookId;    
}
