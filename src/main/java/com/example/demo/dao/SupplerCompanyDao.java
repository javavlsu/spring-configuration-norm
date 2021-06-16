package com.example.demo.dao;

import com.example.demo.model.SupplerCompany;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
@Repository
@Primary
public interface SupplerCompanyDao {
    public List<SupplerCompany> getAllSupplerCompanies();
    public SupplerCompany getSupplerCompanyById(int id);
    public void addingSupplerCompany(SupplerCompany sk);
    public void removingSupplerCompany(int id);
    public void updatingSupplerCompany(SupplerCompany skold, SupplerCompany sknew);
}
