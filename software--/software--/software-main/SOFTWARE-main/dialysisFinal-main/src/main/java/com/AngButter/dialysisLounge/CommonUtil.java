package com.AngButter.dialysisLounge;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Component;

@Component
//component사용하여 아래 클래스를 스프링 빈으로 등록
public class CommonUtil {
    public String markdown(String markdown) {
        //마크다운 텍스트를 html문서로 변환하여 리턴
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }
}
