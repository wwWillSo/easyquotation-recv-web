package com.szw.easyquotation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.szw.easyquotation.entity.RealTimeMarketdata;


@Repository
public interface RealTimeMarketdataRepository extends JpaRepository<RealTimeMarketdata, String> {
}
