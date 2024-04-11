package com.fabio.bookgiveaway.books;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "book")
public class BookModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "titolo")
    private String titolo;

    @Column(name = "autore")
    private String autore;

    @Column(name = "edizione")
    private String edizione;

    @Column(name = "condizioni")
    private String condizioni;

    public String stampa(String filter){
        List<String> campiDaIncludere = List.of("a","t","e","c");
        
        if(filter != null){
            campiDaIncludere = List.of(filter.split(""));
        }
        
        String response = "";
        
        response += campiDaIncludere.contains("a") ? autore : " ";
        response += " ";
        response += campiDaIncludere.contains("t") ? titolo : " " + " "; 
        response += "  ";
        response += campiDaIncludere.contains("e") ? edizione  : " " + " ";
        response += " "; 
        response += campiDaIncludere.contains("c") ? condizioni : " " + " ";

        return response;
    }

}
