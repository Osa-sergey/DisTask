package ru.serov.distask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.serov.distask.dao.repository.IArticleRepo;
import ru.serov.distask.service.IArticleService;

@Service
public class ArticleService implements IArticleService {

    private final IArticleRepo articleRepo;

    @Autowired
    public ArticleService(IArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }


}
