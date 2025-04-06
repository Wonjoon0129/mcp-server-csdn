package org.example.domain.service;


import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.adapter.ICSDNPort;
import org.example.domain.model.ArticleFunctionRequest;
import org.example.domain.model.ArticleFunctionResponse;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class CSDNArticleService {

    @Resource
    private ICSDNPort port;

    @Tool(description = "发布文章到CSDN")
    public ArticleFunctionResponse saveArticle(ArticleFunctionRequest request) throws IOException {
        log.info("CSDN发帖，标题:{} 内容:{} 标签:{}", request.getTitle(), request.getMarkdowncontent(), request.getTags());
        return port.writeArticle(request);
    }

}
