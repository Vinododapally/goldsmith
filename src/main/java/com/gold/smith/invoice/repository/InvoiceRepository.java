package com.gold.smith.invoice.repository;

import com.gold.smith.invoice.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
    @Query(value ="select * from shringeri_jewellers.order_invoice where ((showroom LIKE %?1%) AND (delivery_date BETWEEN  ?2 AND ?3)) OR (delivery_date BETWEEN ?2 AND ?3) OR ((showroom LIKE %?1%) OR (delivery_date =?2) OR (delivery_date =?3))",nativeQuery = true)
    List<Invoice> getInvoicesBasedOnTheParam(String showroom, Date fromDate, Date toDate);
    @Query(value ="select * from shringeri_jewellers.order_invoice where showroom LIKE %?1% AND delivery_date =?2",nativeQuery = true)
    List<Invoice> getInvoicesBasedOnTheParams(String showroom, Date fromDate);

}
