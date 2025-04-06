package org.example.infrastructure.adapter;

import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.adapter.ICSDNPort;
import org.example.domain.model.ArticleFunctionRequest;
import org.example.domain.model.ArticleFunctionResponse;
import org.example.infrastructure.gateway.ICSDNService;
import org.example.infrastructure.gateway.dto.ArticleRequestDTO;
import org.example.infrastructure.gateway.dto.ArticleResponseDTO;
import org.example.type.properties.CSDNApiProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Slf4j
@Service
public class CSDNPort implements ICSDNPort {

    @Resource
    private ICSDNService csdnService;

    @Resource
    private CSDNApiProperties csdnApiProperties;

    @Override
    public ArticleFunctionResponse writeArticle(ArticleFunctionRequest request) throws IOException {

        ArticleRequestDTO articleRequestDTO = new ArticleRequestDTO();
        articleRequestDTO.setTitle(request.getTitle());
        articleRequestDTO.setContent(request.getContent());
        articleRequestDTO.setTags(request.getTags());
        articleRequestDTO.setDescription(request.getDescription());
        articleRequestDTO.setCategories(csdnApiProperties.getCategories());

        String cookie=csdnApiProperties.getCookie();
        Call<ArticleResponseDTO> call = csdnService.saveArticle(cookie,articleRequestDTO);
        Response<ArticleResponseDTO> response = call.execute();

        log.info("请求CSDN发帖 \nreq:{} \nres:{}", JSON.toJSONString(articleRequestDTO), JSON.toJSONString(response));

        if (response.isSuccessful()) {
            ArticleResponseDTO articleResponseDTO = response.body();
            if (null == articleResponseDTO) return null;

            ArticleFunctionResponse articleFunctionResponse = new ArticleFunctionResponse();
            articleFunctionResponse.setCode(articleResponseDTO.getCode());
            articleFunctionResponse.setMsg(articleResponseDTO.getMsg());

            return articleFunctionResponse;
        }

        return null;
    }

}
