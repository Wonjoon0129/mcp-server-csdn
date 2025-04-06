package org.example.domain.adapter;


import org.example.domain.model.ArticleFunctionRequest;
import org.example.domain.model.ArticleFunctionResponse;

import java.io.IOException;

public interface ICSDNPort {

    ArticleFunctionResponse writeArticle(ArticleFunctionRequest request) throws IOException;

}
