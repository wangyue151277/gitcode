package com.itdr.service;

import com.itdr.common.ResponseCode;
import com.itdr.dao.ProductDao;
import com.itdr.pojo.Product;

import java.util.List;

public class ProductService {
    private ProductDao pd = new ProductDao();

    public ResponseCode getAll() {

        ResponseCode rc = null;

        List<Product> li = pd.getAll();
        rc = ResponseCode.successRS(li);
        return rc;

    }
}
