package com.adapter.rest.controller;

import com.adapter.database.GenericDao;
import com.adapter.rest.Request;
import com.adapter.rest.Response;

import java.util.List;

public class SearchController extends Controller {
    GenericDao genericDao;

    public SearchController(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    public Response search(Request request) {
        List<Object> searchResult = genericDao.read(request.getParams());
        return new Response(200, request.getContentType(), searchResult);
    }
}
